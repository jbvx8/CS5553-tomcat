package org.apache.catalina.realm;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.X509Certificate;

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.CredentialHandler;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.ietf.jgss.GSSContext;

public interface IRealmDef {

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getTransportGuaranteeRedirectStatus()
     */
    int getTransportGuaranteeRedirectStatus();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#setTransportGuaranteeRedirectStatus(int)
     */
    void setTransportGuaranteeRedirectStatus(int transportGuaranteeRedirectStatus);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getCredentialHandler()
     */
    CredentialHandler getCredentialHandler();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#setCredentialHandler(org.apache.catalina.CredentialHandler)
     */
    void setCredentialHandler(CredentialHandler credentialHandler);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getContainer()
     */
    Container getContainer();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#setContainer(org.apache.catalina.Container)
     */
    void setContainer(Container container);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getAllRolesMode()
     */
    String getAllRolesMode();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#setAllRolesMode(java.lang.String)
     */
    void setAllRolesMode(String allRolesMode);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getValidate()
     */
    boolean getValidate();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#setValidate(boolean)
     */
    void setValidate(boolean validate);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getX509UsernameRetrieverClassName()
     */
    String getX509UsernameRetrieverClassName();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#setX509UsernameRetrieverClassName(java.lang.String)
     */
    void setX509UsernameRetrieverClassName(String className);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#isStripRealmForGss()
     */
    boolean isStripRealmForGss();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#setStripRealmForGss(boolean)
     */
    void setStripRealmForGss(boolean stripRealmForGss);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#addPropertyChangeListener(java.beans.PropertyChangeListener)
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#authenticate(java.lang.String)
     */
    Principal authenticate(String username);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#authenticate(java.lang.String, java.lang.String)
     */
    Principal authenticate(String username, String credentials);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#authenticate(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    Principal authenticate(String username, String clientDigest, String nonce, String nc, String cnonce, String qop,
            String realm, String md5a2);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#authenticate(java.security.cert.X509Certificate[])
     */
    Principal authenticate(X509Certificate certs[]);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#authenticate(org.ietf.jgss.GSSContext, boolean)
     */
    Principal authenticate(GSSContext gssContext, boolean storeCreds);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#backgroundProcess()
     */
    void backgroundProcess();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#findSecurityConstraints(org.apache.catalina.connector.Request, org.apache.catalina.Context)
     */
    SecurityConstraint[] findSecurityConstraints(Request request, Context context);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#hasResourcePermission(org.apache.catalina.connector.Request, org.apache.catalina.connector.Response, org.apache.tomcat.util.descriptor.web.SecurityConstraint[], org.apache.catalina.Context)
     */
    boolean hasResourcePermission(Request request, Response response, SecurityConstraint[] constraints, Context context)
            throws IOException;

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#hasRole(org.apache.catalina.Wrapper, java.security.Principal, java.lang.String)
     */
    boolean hasRole(Wrapper wrapper, Principal principal, String role);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#hasUserDataPermission(org.apache.catalina.connector.Request, org.apache.catalina.connector.Response, org.apache.tomcat.util.descriptor.web.SecurityConstraint[])
     */
    boolean hasUserDataPermission(Request request, Response response, SecurityConstraint[] constraints)
            throws IOException;

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#removePropertyChangeListener(java.beans.PropertyChangeListener)
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#toString()
     */
    String toString();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getObjectNameKeyProperties()
     */
    String getObjectNameKeyProperties();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getDomainInternal()
     */
    String getDomainInternal();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getRealmPath()
     */
    String getRealmPath();

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#setRealmPath(java.lang.String)
     */
    void setRealmPath(String theRealmPath);

    /* (non-Javadoc)
     * @see org.apache.catalina.realm.IRealm#getRoles(java.security.Principal)
     */
    String[] getRoles(Principal principal);

}