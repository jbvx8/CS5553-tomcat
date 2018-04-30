/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.catalina.realm;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.CredentialHandler;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Realm;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.realm.RealmBase.AllRolesMode;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.ExceptionUtils;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.res.StringManager;
import org.ietf.jgss.GSSContext;

import jdbcrealm.JDBCRealmArch;
import realm.RealmArch;


/**
*
* Implementation of <b>Realm</b> that works with any JDBC supported database.
* See the JDBCRealm.howto for more details on how to set up the database and
* for configuration options.
*
* <p>For a <b>Realm</b> implementation that supports connection pooling and
* doesn't require synchronisation of <code>authenticate()</code>,
* <code>getPassword()</code>, <code>roles()</code> and
* <code>getPrincipal()</code> or the ugly connection logic use the
* <code>DataSourceRealm</code>.</p>
*
* @author Craig R. McClanahan
* @author Carson McDonald
* @author Ignacio Ortega
*/
public class JDBCRealm
    implements Realm {


    // ----------------------------------------------------- Instance Variables
    
    private static final Log log = LogFactory.getLog(RealmBase.class);

    private static final List<Class<? extends DigestCredentialHandlerBase>> credentialHandlerClasses =
            new ArrayList<>();

    static {
        // Order is important since it determines the search order for a
        // matching handler if only an algorithm is specified when calling
        // main()
        credentialHandlerClasses.add(MessageDigestCredentialHandler.class);
        credentialHandlerClasses.add(SecretKeyCredentialHandler.class);
    }

    // ----------------------------------------------------- Instance Variables
    
    private static JDBCRealmArch _arch;
    
    private RealmBase defaultRealm;

    /**
     * The Container with which this Realm is associated.
     */
    protected Container container = null;


    /**
     * Container log
     */
    protected Log containerLog = null;


    private CredentialHandler credentialHandler;


    /**
     * The string manager for this package.
     */
    protected static final StringManager sm = StringManager.getManager(RealmBase.class);


    /**
     * The property change support for this component.
     */
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);


    /**
     * Should we validate client certificate chains when they are presented?
     */
    protected boolean validate = true;

    /**
     * The name of the class to use for retrieving user names from X509
     * certificates.
     */
    protected String x509UsernameRetrieverClassName;

    /**
     * The object that will extract user names from X509 client certificates.
     */
    protected X509UsernameRetriever x509UsernameRetriever;

    /**
     * The all role mode.
     */
    protected AllRolesMode allRolesMode = AllRolesMode.STRICT_MODE;


    /**
     * When processing users authenticated via the GSS-API, should any
     * &quot;@...&quot; be stripped from the end of the user name?
     */
    protected boolean stripRealmForGss = true;


    private int transportGuaranteeRedirectStatus = HttpServletResponse.SC_FOUND;


    // ------------------------------------------------------------- Properties
    
    public static void setArch(JDBCRealmArch arch){
        _arch = arch;
    }



    /**
     * The connection username to use when trying to connect to the database.
     */
    protected String connectionName = null;


    /**
     * The connection URL to use when trying to connect to the database.
     */
    protected String connectionPassword = null;


    /**
     * The connection URL to use when trying to connect to the database.
     */
    protected String connectionURL = null;


    /**
     * The connection to the database.
     */
    protected Connection dbConnection = null;


    /**
     * Instance of the JDBC Driver class we use as a connection factory.
     */
    protected Driver driver = null;


    /**
     * The JDBC driver to use.
     */
    protected String driverName = null;


    /**
     * The PreparedStatement to use for authenticating users.
     */
    protected PreparedStatement preparedCredentials = null;


    /**
     * The PreparedStatement to use for identifying the roles for
     * a specified user.
     */
    protected PreparedStatement preparedRoles = null;


    /**
     * The column in the user role table that names a role
     */
    protected String roleNameCol = null;


    /**
     * The column in the user table that holds the user's credentials
     */
    protected String userCredCol = null;


    /**
     * The column in the user table that holds the user's name
     */
    protected String userNameCol = null;


    /**
     * The table that holds the relation between user's and roles
     */
    protected String userRoleTable = null;


    /**
     * The table that holds user data.
     */
    protected String userTable = null;


    // ------------------------------------------------------------- Properties

    /**
     * @return the username to use to connect to the database.
     */
    public String getConnectionName() {
        return connectionName;
    }

    /**
     * Set the username to use to connect to the database.
     *
     * @param connectionName Username
     */
    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    /**
     * @return the password to use to connect to the database.
     */
    public String getConnectionPassword() {
        return connectionPassword;
    }

    /**
     * Set the password to use to connect to the database.
     *
     * @param connectionPassword User password
     */
    public void setConnectionPassword(String connectionPassword) {
        this.connectionPassword = connectionPassword;
    }

    /**
     * @return the URL to use to connect to the database.
     */
    public String getConnectionURL() {
        return connectionURL;
    }

    /**
     * Set the URL to use to connect to the database.
     *
     * @param connectionURL The new connection URL
     */
    public void setConnectionURL( String connectionURL ) {
      this.connectionURL = connectionURL;
    }

    /**
     * @return the JDBC driver that will be used.
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * Set the JDBC driver that will be used.
     *
     * @param driverName The driver name
     */
    public void setDriverName( String driverName ) {
      this.driverName = driverName;
    }

    /**
     * @return the column in the user role table that names a role.
     */
    public String getRoleNameCol() {
        return roleNameCol;
    }

    /**
     * Set the column in the user role table that names a role.
     *
     * @param roleNameCol The column name
     */
    public void setRoleNameCol( String roleNameCol ) {
        this.roleNameCol = roleNameCol;
    }

    /**
     * @return the column in the user table that holds the user's credentials.
     */
    public String getUserCredCol() {
        return userCredCol;
    }

    /**
     * Set the column in the user table that holds the user's credentials.
     *
     * @param userCredCol The column name
     */
    public void setUserCredCol( String userCredCol ) {
       this.userCredCol = userCredCol;
    }

    /**
     * @return the column in the user table that holds the user's name.
     */
    public String getUserNameCol() {
        return userNameCol;
    }

    /**
     * Set the column in the user table that holds the user's name.
     *
     * @param userNameCol The column name
     */
    public void setUserNameCol( String userNameCol ) {
       this.userNameCol = userNameCol;
    }

    /**
     * @return the table that holds the relation between user's and roles.
     */
    public String getUserRoleTable() {
        return userRoleTable;
    }

    /**
     * Set the table that holds the relation between user's and roles.
     *
     * @param userRoleTable The table name
     */
    public void setUserRoleTable( String userRoleTable ) {
        this.userRoleTable = userRoleTable;
    }

    /**
     * @return the table that holds user data..
     */
    public String getUserTable() {
        return userTable;
    }

    /**
     * Set the table that holds user data.
     *
     * @param userTable The table name
     */
    public void setUserTable( String userTable ) {
      this.userTable = userTable;
    }


    // --------------------------------------------------------- Public Methods

    


    // -------------------------------------------------------- Package Methods


    // ------------------------------------------------------ Protected Methods


    /**
     * Attempt to authenticate the user with the provided credentials.
     *
     * @param dbConnection The database connection to be used
     * @param username Username of the Principal to look up
     * @param credentials Password or other credentials to use in authenticating
     *                    this username
     *
     * @return Return the Principal associated with the specified username and
     *         credentials, if there is one; otherwise return <code>null</code>.
     */
    public synchronized Principal authenticate(Connection dbConnection,
                                               String username,
                                               String credentials) {
        // No user or no credentials
        // Can't possibly authenticate, don't bother the database then
        if (username == null || credentials == null) {
            if (containerLog.isTraceEnabled())
                containerLog.trace(sm.getString("jdbcRealm.authenticateFailure",
                                                username));
            return null;
        }

        // Look up the user's credentials
        String dbCredentials = getPassword(username);

        if (dbCredentials == null) {
            // User was not found in the database.
            // Waste a bit of time as not to reveal that the user does not exist.
            getCredentialHandler().mutate(credentials);

            if (containerLog.isTraceEnabled())
                containerLog.trace(sm.getString("jdbcRealm.authenticateFailure",
                                                username));
            return null;
        }

        // Validate the user's credentials
        boolean validated = getCredentialHandler().matches(credentials, dbCredentials);

        if (validated) {
            if (containerLog.isTraceEnabled())
                containerLog.trace(sm.getString("jdbcRealm.authenticateSuccess",
                                                username));
        } else {
            if (containerLog.isTraceEnabled())
                containerLog.trace(sm.getString("jdbcRealm.authenticateFailure",
                                                username));
            return null;
        }

        ArrayList<String> roles = getRoles(username);

        // Create and return a suitable Principal for this user
        return new GenericPrincipal(username, credentials, roles);
    }


    /**
     * Close the specified database connection.
     *
     * @param dbConnection The connection to be closed
     */
    protected void close(Connection dbConnection) {

        // Do nothing if the database connection is already closed
        if (dbConnection == null)
            return;

        // Close our prepared statements (if any)
        try {
            preparedCredentials.close();
        } catch (Throwable f) {
            ExceptionUtils.handleThrowable(f);
        }
        this.preparedCredentials = null;


        try {
            preparedRoles.close();
        } catch (Throwable f) {
            ExceptionUtils.handleThrowable(f);
        }
        this.preparedRoles = null;


        // Close this database connection, and log any errors
        try {
            dbConnection.close();
        } catch (SQLException e) {
            containerLog.warn(sm.getString("jdbcRealm.close"), e); // Just log it here
        } finally {
           this.dbConnection = null;
        }

    }


    /**
     * Return a PreparedStatement configured to perform the SELECT required
     * to retrieve user credentials for the specified username.
     *
     * @param dbConnection The database connection to be used
     * @param username Username for which credentials should be retrieved
     * @return the prepared statement
     * @exception SQLException if a database error occurs
     */
    protected PreparedStatement credentials(Connection dbConnection,
                                            String username)
        throws SQLException {

        if (preparedCredentials == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            sb.append(userCredCol);
            sb.append(" FROM ");
            sb.append(userTable);
            sb.append(" WHERE ");
            sb.append(userNameCol);
            sb.append(" = ?");

            if(containerLog.isDebugEnabled()) {
                containerLog.debug("credentials query: " + sb.toString());
            }

            preparedCredentials =
                dbConnection.prepareStatement(sb.toString());
        }

        if (username == null) {
            preparedCredentials.setNull(1,java.sql.Types.VARCHAR);
        } else {
            preparedCredentials.setString(1, username);
        }

        return preparedCredentials;
    }


    /**
     * Get the password for the specified user.
     * @param username The user name
     * @return the password associated with the given principal's user name.
     */
    protected synchronized String getPassword(String username) {

        // Look up the user's credentials
        String dbCredentials = null;

        // Number of tries is the number of attempts to connect to the database
        // during this login attempt (if we need to open the database)
        // This needs rewritten with better pooling support, the existing code
        // needs signature changes since the Prepared statements needs cached
        // with the connections.
        // The code below will try twice if there is a SQLException so the
        // connection may try to be opened again. On normal conditions (including
        // invalid login - the above is only used once.
        int numberOfTries = 2;
        while (numberOfTries > 0) {
            try {
                // Ensure that we have an open database connection
                open();

                PreparedStatement stmt = credentials(dbConnection, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        dbCredentials = rs.getString(1);
                    }

                    dbConnection.commit();

                    if (dbCredentials != null) {
                        dbCredentials = dbCredentials.trim();
                    }

                    return dbCredentials;
                }
            } catch (SQLException e) {
                // Log the problem for posterity
                containerLog.error(sm.getString("jdbcRealm.exception"), e);
            }

            // Close the connection so that it gets reopened next time
            if (dbConnection != null) {
                close(dbConnection);
            }

            numberOfTries--;
        }

        return null;
    }

    /**
     * Get the principal associated with the specified user.
     * @param username The user name
     * @return the Principal associated with the given user name.
     */
    protected synchronized Principal getPrincipal(String username) {

        return new GenericPrincipal(username,
                                     getPassword(username),
                                     getRoles(username));

    }


    /**
     * Return the roles associated with the given user name.
     * @param username The user name
     * @return an array list of the role names
     */
    protected ArrayList<String> getRoles(String username) {

        if (allRolesMode != AllRolesMode.STRICT_MODE && !isRoleStoreDefined()) {
            // Using an authentication only configuration and no role store has
            // been defined so don't spend cycles looking
            return null;
        }

        // Number of tries is the number of attempts to connect to the database
        // during this login attempt (if we need to open the database)
        // This needs rewritten wuth better pooling support, the existing code
        // needs signature changes since the Prepared statements needs cached
        // with the connections.
        // The code below will try twice if there is a SQLException so the
        // connection may try to be opened again. On normal conditions (including
        // invalid login - the above is only used once.
        int numberOfTries = 2;
        while (numberOfTries>0) {
            try {
                // Ensure that we have an open database connection
                open();

                PreparedStatement stmt = roles(dbConnection, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    // Accumulate the user's roles
                    ArrayList<String> roleList = new ArrayList<>();

                    while (rs.next()) {
                        String role = rs.getString(1);
                        if (null!=role) {
                            roleList.add(role.trim());
                        }
                    }

                    return roleList;
                } finally {
                    dbConnection.commit();
                }
            } catch (SQLException e) {
                // Log the problem for posterity
                containerLog.error(sm.getString("jdbcRealm.exception"), e);

                // Close the connection so that it gets reopened next time
                if (dbConnection != null)
                    close(dbConnection);
            }

            numberOfTries--;
        }

        return null;
    }


    /**
     * Open (if necessary) and return a database connection for use by
     * this Realm.
     * @return the opened connection
     * @exception SQLException if a database error occurs
     */
    protected Connection open() throws SQLException {

        // Do nothing if there is a database connection already open
        if (dbConnection != null)
            return dbConnection;

        // Instantiate our database driver if necessary
        if (driver == null) {
            try {
                Class<?> clazz = Class.forName(driverName);
                driver = (Driver) clazz.getConstructor().newInstance();
            } catch (Throwable e) {
                ExceptionUtils.handleThrowable(e);
                throw new SQLException(e.getMessage(), e);
            }
        }

        // Open a new connection
        Properties props = new Properties();
        if (connectionName != null)
            props.put("user", connectionName);
        if (connectionPassword != null)
            props.put("password", connectionPassword);
        dbConnection = driver.connect(connectionURL, props);
        if (dbConnection == null) {
            throw new SQLException(sm.getString(
                    "jdbcRealm.open.invalidurl",driverName, connectionURL));
        }
        dbConnection.setAutoCommit(false);
        return dbConnection;

    }


    /**
     * Return a PreparedStatement configured to perform the SELECT required
     * to retrieve user roles for the specified username.
     *
     * @param dbConnection The database connection to be used
     * @param username Username for which roles should be retrieved
     * @return the prepared statement
     * @exception SQLException if a database error occurs
     */
    protected synchronized PreparedStatement roles(Connection dbConnection,
            String username)
        throws SQLException {

        if (preparedRoles == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            sb.append(roleNameCol);
            sb.append(" FROM ");
            sb.append(userRoleTable);
            sb.append(" WHERE ");
            sb.append(userNameCol);
            sb.append(" = ?");
            preparedRoles =
                dbConnection.prepareStatement(sb.toString());
        }

        preparedRoles.setString(1, username);
        return preparedRoles;

    }


    private boolean isRoleStoreDefined() {
        return userRoleTable != null || roleNameCol != null;
    }


    // ------------------------------------------------------ Lifecycle Methods

    /**
     * Prepare for the beginning of active use of the public methods of this
     * component and implement the requirements of
     * {@link org.apache.catalina.util.LifecycleBase#startInternal()}.
     *
     * @exception LifecycleException if this component detects a fatal error
     *  that prevents this component from being used
     */
    protected void startInternal() throws LifecycleException {

        // Validate that we can open our connection - but let tomcat
        // startup in case the database is temporarily unavailable
        try {
            open();
        } catch (SQLException e) {
            containerLog.error(sm.getString("jdbcRealm.open"), e);
        }

        defaultRealm.startInternal();
    }


    /**
     * Gracefully terminate the active use of the public methods of this
     * component and implement the requirements of
     * {@link org.apache.catalina.util.LifecycleBase#stopInternal()}.
     *
     * @exception LifecycleException if this component detects a fatal error
     *  that needs to be reported
     */
     protected void stopInternal() throws LifecycleException {

        defaultRealm.stopInternal();

        // Close any open DB connection
        close(this.dbConnection);

    }

     @Override
     public CredentialHandler getCredentialHandler() {
         return _arch.OUT_Realm.getCredentialHandler();
     }

     @Override
     public void setCredentialHandler(CredentialHandler credentialHandler) {
         _arch.OUT_Realm.setCredentialHandler(credentialHandler);      
     }

     @Override
     public void addPropertyChangeListener(PropertyChangeListener listener) {
         _arch.OUT_Realm.addPropertyChangeListener(listener);
     }

     @Override
     public Principal authenticate(String username) {
        return _arch.OUT_Realm.authenticate(username);
     }

     @Override
     public Principal authenticate(String username, String credentials) {
        return _arch.OUT_Realm.authenticate(username, credentials);
     }

     @Override
     public Principal authenticate(String username, String digest, String nonce, String nc, String cnonce, String qop,
             String realm, String md5a2) {
         return _arch.OUT_Realm.authenticate(username, digest, nonce, nc, cnonce, qop, realm, md5a2);
     }

     @Override
     public Principal authenticate(GSSContext gssContext, boolean storeCreds) {
         return _arch.OUT_Realm.authenticate(gssContext, storeCreds);
     }

     @Override
     public Principal authenticate(X509Certificate[] certs) {
         return _arch.OUT_Realm.authenticate(certs);
     }

     @Override
     public void backgroundProcess() {
         _arch.OUT_Realm.backgroundProcess();
     }

     public SecurityConstraint[] findSecurityConstraints(Request request, javax.naming.Context context) {
         return _arch.OUT_Realm.findSecurityConstraints(request, (Context) context);
     }

     public boolean hasResourcePermission(Request request, Response response, SecurityConstraint[] constraint,
             javax.naming.Context context) throws IOException {
         return _arch.OUT_Realm.hasResourcePermission(request, response, constraint, (Context) context);
     }

     public boolean hasRole(java.sql.Wrapper wrapper, Principal principal, String role) {
         return _arch.OUT_Realm.hasRole((Wrapper) wrapper, principal, role);
     }

     @Override
     public boolean hasUserDataPermission(Request request, Response response, SecurityConstraint[] constraint)
             throws IOException {
         return _arch.OUT_Realm.hasUserDataPermission(request, response, constraint);
     }

     @Override
     public void removePropertyChangeListener(PropertyChangeListener listener) {
         _arch.OUT_Realm.removePropertyChangeListener(listener);
         
     }

     @Override
     public String[] getRoles(Principal principal) {
         return _arch.OUT_Realm.getRoles(principal);
     }

     @Override
     public boolean isAvailable() {
         return _arch.OUT_Realm.isAvailable();
     }

     @Override
     public int getTransportGuaranteeRedirectStatus() {
         return _arch.OUT_Realm.getTransportGuaranteeRedirectStatus();
     }

     @Override
     public void setTransportGuaranteeRedirectStatus(int transportGuaranteeRedirectStatus) {
         _arch.OUT_Realm.setTransportGuaranteeRedirectStatus(transportGuaranteeRedirectStatus);
     }

     @Override
     public Container getContainer() {
         return _arch.OUT_Realm.getContainer();
     }

     @Override
     public void setContainer(Container container) {
         _arch.OUT_Realm.setContainer(container);
     }

     @Override
     public String getAllRolesMode() {
         return _arch.OUT_Realm.getAllRolesMode();
     }

     @Override
     public void setAllRolesMode(String allRolesMode) {
         _arch.OUT_Realm.setAllRolesMode(allRolesMode);
     }

     @Override
     public boolean getValidate() {
        return _arch.OUT_Realm.getValidate();
     }

     @Override
     public void setValidate(boolean validate) {
         _arch.OUT_Realm.setValidate(validate);
     }

     @Override
     public String getX509UsernameRetrieverClassName() {
         return _arch.OUT_Realm.getX509UsernameRetrieverClassName();
     }

     @Override
     public void setX509UsernameRetrieverClassName(String className) {
         _arch.OUT_Realm.setX509UsernameRetrieverClassName(className);
     }

     @Override
     public boolean isStripRealmForGss() {
         return _arch.OUT_Realm.isStripRealmForGss();
     }

     @Override
     public void setStripRealmForGss(boolean stripRealmForGss) {
         _arch.OUT_Realm.setStripRealmForGss(stripRealmForGss);
     }

     @Override
     public String getObjectNameKeyProperties() {
         return _arch.OUT_Realm.getObjectNameKeyProperties();
     }

     @Override
     public String getDomainInternal() {
         return _arch.OUT_Realm.getDomainInternal();
     }

     @Override
     public String getRealmPath() {
         return _arch.OUT_Realm.getRealmPath();
     }

     @Override
     public void setRealmPath(String theRealmPath) {
         _arch.OUT_Realm.setRealmPath(theRealmPath);
     }

     public boolean hasRole(Wrapper wrapper, Principal principal, String role) {
         // TODO Auto-generated method stub
         return false;
     }

    @Override
    public SecurityConstraint[] findSecurityConstraints(Request request, Context context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasResourcePermission(Request request, Response response, SecurityConstraint[] constraint,
            Context context) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }
 }