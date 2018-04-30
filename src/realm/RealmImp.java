package realm;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.CredentialHandler;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Realm;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.realm.DigestCredentialHandlerBase;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.MemoryRealm;
import org.apache.catalina.realm.MessageDigestCredentialHandler;
import org.apache.catalina.realm.RealmBase;
import org.apache.catalina.realm.SecretKeyCredentialHandler;
import org.apache.catalina.realm.X509SubjectDnRetriever;
import org.apache.catalina.realm.X509UsernameRetriever;
import org.apache.catalina.util.LifecycleMBeanBase;
import org.apache.catalina.util.SessionConfig;
import org.apache.catalina.util.ToStringUtil;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.IntrospectionUtils;
import org.apache.tomcat.util.buf.B2CConverter;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.res.StringManager;
import org.apache.tomcat.util.security.ConcurrentMessageDigest;
import org.apache.tomcat.util.security.MD5Encoder;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSName;

public class RealmImp implements IRealmImp
{
	private static RealmArch _arch;
	private RealmBase defaultRealm;
	

    public RealmImp (){
    }

	public void setArch(RealmArch arch){
		_arch = arch;
	}
	public RealmArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
	    RealmBase.setArch(_arch);
	    MemoryRealm.setArch(_arch);
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

    @Override
    public CredentialHandler getCredentialHandler() {
        return defaultRealm.getCredentialHandler();
    }

    @Override
    public void setCredentialHandler(CredentialHandler credentialHandler) {
        defaultRealm.setCredentialHandler(credentialHandler);      
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        defaultRealm.addPropertyChangeListener(listener);
    }

    @Override
    public Principal authenticate(String username) {
       return defaultRealm.authenticate(username);
    }

    @Override
    public Principal authenticate(String username, String credentials) {
       return defaultRealm.authenticate(username, credentials);
    }

    @Override
    public Principal authenticate(String username, String digest, String nonce, String nc, String cnonce, String qop,
            String realm, String md5a2) {
        return defaultRealm.authenticate(username, digest, nonce, nc, cnonce, qop, realm, md5a2);
    }

    @Override
    public Principal authenticate(GSSContext gssContext, boolean storeCreds) {
        return defaultRealm.authenticate(gssContext, storeCreds);
    }

    @Override
    public Principal authenticate(X509Certificate[] certs) {
        return defaultRealm.authenticate(certs);
    }

    @Override
    public void backgroundProcess() {
        defaultRealm.backgroundProcess();
    }

    public SecurityConstraint[] findSecurityConstraints(Request request, javax.naming.Context context) {
        return defaultRealm.findSecurityConstraints(request, context);
    }

    public boolean hasResourcePermission(Request request, Response response, SecurityConstraint[] constraint,
            javax.naming.Context context) throws IOException {
        return defaultRealm.hasResourcePermission(request, response, constraint, context);
    }

    public boolean hasRole(java.sql.Wrapper wrapper, Principal principal, String role) {
        return defaultRealm.hasRole(wrapper, principal, role);
    }

    @Override
    public boolean hasUserDataPermission(Request request, Response response, SecurityConstraint[] constraint)
            throws IOException {
        return defaultRealm.hasUserDataPermission(request, response, constraint);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        defaultRealm.removePropertyChangeListener(listener);
        
    }

    @Override
    public String[] getRoles(Principal principal) {
        return defaultRealm.getRoles(principal);
    }

    @Override
    public boolean isAvailable() {
        return defaultRealm.isAvailable();
    }

    @Override
    public int getTransportGuaranteeRedirectStatus() {
        return defaultRealm.getTransportGuaranteeRedirectStatus();
    }

    @Override
    public void setTransportGuaranteeRedirectStatus(int transportGuaranteeRedirectStatus) {
        defaultRealm.setTransportGuaranteeRedirectStatus(transportGuaranteeRedirectStatus);
    }

    @Override
    public Container getContainer() {
        return defaultRealm.getContainer();
    }

    @Override
    public void setContainer(Container container) {
        defaultRealm.setContainer(container);
    }

    @Override
    public String getAllRolesMode() {
        return defaultRealm.getAllRolesMode();
    }

    @Override
    public void setAllRolesMode(String allRolesMode) {
        defaultRealm.setAllRolesMode(allRolesMode);
    }

    @Override
    public boolean getValidate() {
       return defaultRealm.getValidate();
    }

    @Override
    public void setValidate(boolean validate) {
        defaultRealm.setValidate(validate);
    }

    @Override
    public String getX509UsernameRetrieverClassName() {
        return defaultRealm.getX509UsernameRetrieverClassName();
    }

    @Override
    public void setX509UsernameRetrieverClassName(String className) {
        defaultRealm.setX509UsernameRetrieverClassName(className);
    }

    @Override
    public boolean isStripRealmForGss() {
        return defaultRealm.isStripRealmForGss();
    }

    @Override
    public void setStripRealmForGss(boolean stripRealmForGss) {
        defaultRealm.setStripRealmForGss(stripRealmForGss);
    }

    @Override
    public String getObjectNameKeyProperties() {
        return defaultRealm.getObjectNameKeyProperties();
    }

    @Override
    public String getDomainInternal() {
        return defaultRealm.getDomainInternal();
    }

    @Override
    public String getRealmPath() {
        return defaultRealm.getRealmPath();
    }

    @Override
    public void setRealmPath(String theRealmPath) {
        defaultRealm.setRealmPath(theRealmPath);
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

    @Override
    public boolean hasRole(Wrapper wrapper, Principal principal, String role) {
        // TODO Auto-generated method stub
        return false;
    }
}

	