package jdbcrealm;

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
import org.apache.catalina.realm.JDBCRealm;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.ietf.jgss.GSSContext;

public class JDBCRealmImp implements IJDBCRealmImp
{
    private JDBCRealmArch _arch;
    
    private JDBCRealm jdbcRealm;

    public JDBCRealmImp (){
    }

    public void setArch(JDBCRealmArch arch){
        _arch = arch;
    }
    public JDBCRealmArch getArch(){
        return _arch;
    }

    /*
      Myx Lifecycle Methods: these methods are called automatically by the framework
      as the bricks are created, attached, detached, and destroyed respectively.
    */  
    public void init(){
        JDBCRealm.setArch(_arch);
    }
    public void begin(){
        //TODO Auto-generated method stub
    }
    public void end(){
        //TODO Auto-generated method stub
    }
    public void destroy(){
        //TODO Auto-generated method stub
    }

    
    public CredentialHandler getCredentialHandler() {
        return jdbcRealm.getCredentialHandler();
    }

    
    public void setCredentialHandler(CredentialHandler credentialHandler) {
        jdbcRealm.setCredentialHandler(credentialHandler);    
    }

    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        jdbcRealm.addPropertyChangeListener(listener);
    }

    
    public Principal authenticate(String username) {
        return jdbcRealm.authenticate(username);
    }

    
    public Principal authenticate(String username, String credentials) {
        return jdbcRealm.authenticate(username, credentials);
    }

    
    public Principal authenticate(String username, String digest, String nonce, String nc, String cnonce, String qop,
            String realm, String md5a2) {
        return jdbcRealm.authenticate(username, digest, nonce, nc, cnonce, qop, realm, md5a2);
    }

    
    public Principal authenticate(GSSContext gssContext, boolean storeCreds) {
        return jdbcRealm.authenticate(gssContext, storeCreds);
    }

    
    public Principal authenticate(X509Certificate[] certs) {
        return jdbcRealm.authenticate(certs);
    }

    
    public void backgroundProcess() {
        jdbcRealm.backgroundProcess();
    }

    
    public SecurityConstraint[] findSecurityConstraints(Request request, Context context) {
        return jdbcRealm.findSecurityConstraints(request, context);
    }

    
    public boolean hasResourcePermission(Request request, Response response, SecurityConstraint[] constraint,
            Context context) throws IOException {
       return jdbcRealm.hasResourcePermission(request, response, constraint, context);
    }

    
    public boolean hasRole(Wrapper wrapper, Principal principal, String role) {
        return jdbcRealm.hasRole(wrapper, principal, role);
    }

    
    public boolean hasUserDataPermission(Request request, Response response, SecurityConstraint[] constraint)
            throws IOException {
        return jdbcRealm.hasUserDataPermission(request, response, constraint);
    }

    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        jdbcRealm.removePropertyChangeListener(listener);
    }

    
    public String[] getRoles(Principal principal) {
        return jdbcRealm.getRoles(principal);
    }

    
    public boolean isAvailable() {
        return jdbcRealm.isAvailable();
    }

    
    public int getTransportGuaranteeRedirectStatus() {
        return jdbcRealm.getTransportGuaranteeRedirectStatus();
    }

    
    public void setTransportGuaranteeRedirectStatus(int transportGuaranteeRedirectStatus) {
        jdbcRealm.setTransportGuaranteeRedirectStatus(transportGuaranteeRedirectStatus);
    }

    
    public Container getContainer() {
        return jdbcRealm.getContainer();
    }

    
    public void setContainer(Container container) {
        jdbcRealm.setContainer(container);
    }

    
    public String getAllRolesMode() {
        return jdbcRealm.getAllRolesMode();
    }

    
    public void setAllRolesMode(String allRolesMode) {
        jdbcRealm.setAllRolesMode(allRolesMode);
    }

    
    public boolean getValidate() {
        return jdbcRealm.getValidate();
    }

    
    public void setValidate(boolean validate) {
        jdbcRealm.setValidate(validate);
    }

    
    public String getX509UsernameRetrieverClassName() {
        return jdbcRealm.getX509UsernameRetrieverClassName();
    }

    
    public void setX509UsernameRetrieverClassName(String className) {
        jdbcRealm.setX509UsernameRetrieverClassName(className);
    }

    
    public boolean isStripRealmForGss() {
        return jdbcRealm.isStripRealmForGss();
    }

    
    public void setStripRealmForGss(boolean stripRealmForGss) {
        jdbcRealm.setStripRealmForGss(stripRealmForGss);
    }

    
    public String getObjectNameKeyProperties() {
        return jdbcRealm.getObjectNameKeyProperties();
    }

    
    public String getDomainInternal() {
        return jdbcRealm.getDomainInternal();
    }

    
    public String getRealmPath() {
        return jdbcRealm.getRealmPath();
    }

    
    public void setRealmPath(String theRealmPath) {
        jdbcRealm.setRealmPath(theRealmPath);
    }

    /*
      Implementation primitives required by the architecture
    */
}