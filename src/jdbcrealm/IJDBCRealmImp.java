package jdbcrealm;


import java.beans.PropertyChangeListener;

import java.io.IOException;

import java.security.Principal;

import java.security.cert.X509Certificate;

import java.sql.Wrapper;

import javax.naming.Context;

import jdbcrealm.JDBCRealmArch;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.CredentialHandler;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

import org.ietf.jgss.GSSContext;

public interface IJDBCRealmImp 
{

    /*
      Getter and Setter of architecture reference
    */
    public void setArch (JDBCRealmArch arch);
    public JDBCRealmArch getArch();
    
    /*
      Myx Lifecycle Methods: these methods are called automatically by the framework
      as the bricks are created, attached, detached, and destroyed respectively.
    */  
    public void init(); 
    public void begin();
    public void end();
    public void destroy();

    /*
      Implementation primitives required by the architecture
    */
  
    //To be imported: PropertyChangeListener,IOException,Principal,X509Certificate,Wrapper,Context,Contained,Container,CredentialHandler,Request,Response,SecurityConstraint,GSSContext
    public CredentialHandler getCredentialHandler ()  ;        
    public void setCredentialHandler (CredentialHandler credentialHandler)  ;        
    public void addPropertyChangeListener (PropertyChangeListener listener)  ;        
    public Principal authenticate (String username)  ;        
    public Principal authenticate (String username,String credentials)  ;        
    public Principal authenticate (String username,String digest,String nonce,String nc,String cnonce,String qop,String realm,String md5a2)  ;        
    public Principal authenticate (GSSContext gssContext,boolean storeCreds)  ;        
    public Principal authenticate (X509Certificate certs[])  ;        
    public void backgroundProcess ()  ;        
    public SecurityConstraint [] findSecurityConstraints (Request request,org.apache.catalina.Context context)  ;        
    public boolean hasResourcePermission (Request request,Response response,SecurityConstraint [] constraint,org.apache.catalina.Context context) throws IOException;        
    public boolean hasRole (org.apache.catalina.Wrapper wrapper,Principal principal,String role)  ;        
    public boolean hasUserDataPermission (Request request,Response response,SecurityConstraint []constraint) throws IOException;        
    public void removePropertyChangeListener (PropertyChangeListener listener)  ;        
    public String[] getRoles (Principal principal)  ;        
    public boolean isAvailable ()  ;        
    public int getTransportGuaranteeRedirectStatus ()  ;        
    public void setTransportGuaranteeRedirectStatus (int transportGuaranteeRedirectStatus)  ;        
    public Container getContainer ()  ;        
    public void setContainer (Container container)  ;        
    public String getAllRolesMode ()  ;        
    public void setAllRolesMode (String allRolesMode)  ;        
    public boolean getValidate ()  ;        
    public void setValidate (boolean validate)  ;        
    public String getX509UsernameRetrieverClassName ()  ;        
    public void setX509UsernameRetrieverClassName (String className)  ;        
    public boolean isStripRealmForGss ()  ;        
    public void setStripRealmForGss (boolean stripRealmForGss)  ;        
    public String toString ()  ;        
    public String getObjectNameKeyProperties ()  ;        
    public String getDomainInternal ()  ;        
    public String getRealmPath ()  ;        
    public void setRealmPath (String theRealmPath)  ;        
}