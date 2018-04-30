package realm;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import fileutil.IFileUtil;

import java.beans.PropertyChangeListener;

import java.io.IOException;

import java.security.Principal;

import java.security.cert.X509Certificate;

import java.sql.Wrapper;

import javax.naming.Context;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.CredentialHandler;
import org.apache.catalina.Realm;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

import org.ietf.jgss.GSSContext;

import security.ISecurity;

import server.IServer;

import tostring.IToStringUtil;

public class RealmArch extends AbstractMyxSimpleBrick implements Realm
{
    public static final IMyxName msg_IServer = MyxUtils.createName("server.IServer");
    public static final IMyxName msg_Realm = MyxUtils.createName("org.apache.catalina.Realm");
    public static final IMyxName msg_ISecurity = MyxUtils.createName("security.ISecurity");
    public static final IMyxName msg_IToStringUtil = MyxUtils.createName("tostring.IToStringUtil");
    public static final IMyxName msg_IFileUtil = MyxUtils.createName("fileutil.IFileUtil");

    public IServer OUT_IServer;
    public ISecurity OUT_ISecurity;
    public IToStringUtil OUT_IToStringUtil;
    public IFileUtil OUT_IFileUtil;

	private IRealmImp _imp;

    public RealmArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IRealmImp getImplementation(){
        try{
			return new RealmImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IServer = (IServer) MyxUtils.getFirstRequiredServiceObject(this,msg_IServer);
        if (OUT_IServer == null){
 			System.err.println("Error: Interface server.IServer returned null");
			return;       
        }
        OUT_ISecurity = (ISecurity) MyxUtils.getFirstRequiredServiceObject(this,msg_ISecurity);
        if (OUT_ISecurity == null){
 			System.err.println("Error: Interface security.ISecurity returned null");
			return;       
        }
        OUT_IToStringUtil = (IToStringUtil) MyxUtils.getFirstRequiredServiceObject(this,msg_IToStringUtil);
        if (OUT_IToStringUtil == null){
 			System.err.println("Error: Interface tostring.IToStringUtil returned null");
			return;       
        }
        OUT_IFileUtil = (IFileUtil) MyxUtils.getFirstRequiredServiceObject(this,msg_IFileUtil);
        if (OUT_IFileUtil == null){
 			System.err.println("Error: Interface fileutil.IFileUtil returned null");
			return;       
        }
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_Realm)){
			return this;
		}        
		return null;
	}
  
    //To be imported: PropertyChangeListener,IOException,Principal,X509Certificate,Wrapper,Context,Contained,Container,CredentialHandler,Request,Response,SecurityConstraint,GSSContext
    public CredentialHandler getCredentialHandler ()   {
		return _imp.getCredentialHandler();
    }    
    public void setCredentialHandler (CredentialHandler credentialHandler)   {
		_imp.setCredentialHandler(credentialHandler);
    }    
    public void addPropertyChangeListener (PropertyChangeListener listener)   {
		_imp.addPropertyChangeListener(listener);
    }    
    public Principal authenticate (String username)   {
		return _imp.authenticate(username);
    }    
    public Principal authenticate (String username,String credentials)   {
		return _imp.authenticate(username,credentials);
    }    
    public Principal authenticate (String username,String digest,String nonce,String nc,String cnonce,String qop,String realm,String md5a2)   {
		return _imp.authenticate(username,digest,nonce,nc,cnonce,qop,realm,md5a2);
    }    
    public Principal authenticate (GSSContext gssContext,boolean storeCreds)   {
		return _imp.authenticate(gssContext,storeCreds);
    }    
    public Principal authenticate (X509Certificate certs[])   {
		return _imp.authenticate(certs);
    }    
    public void backgroundProcess ()   {
		_imp.backgroundProcess();
    }    
    public SecurityConstraint [] findSecurityConstraints (Request request,org.apache.catalina.Context context)   {
		return _imp.findSecurityConstraints(request,context);
    }    
    public boolean hasResourcePermission (Request request,Response response,SecurityConstraint [] constraint,org.apache.catalina.Context context) throws IOException {
		return _imp.hasResourcePermission(request,response,constraint,context);
    }    
    public boolean hasRole (org.apache.catalina.Wrapper wrapper,Principal principal,String role)   {
		return _imp.hasRole(wrapper,principal,role);
    }    
    public boolean hasUserDataPermission (Request request,Response response,SecurityConstraint []constraint) throws IOException {
		return _imp.hasUserDataPermission(request,response,constraint);
    }    
    public void removePropertyChangeListener (PropertyChangeListener listener)   {
		_imp.removePropertyChangeListener(listener);
    }    
    public String[] getRoles (Principal principal)   {
		return _imp.getRoles(principal);
    }    
    public boolean isAvailable ()   {
		return _imp.isAvailable();
    }    
    public int getTransportGuaranteeRedirectStatus ()   {
		return _imp.getTransportGuaranteeRedirectStatus();
    }    
    public void setTransportGuaranteeRedirectStatus (int transportGuaranteeRedirectStatus)   {
		_imp.setTransportGuaranteeRedirectStatus(transportGuaranteeRedirectStatus);
    }    
    public Container getContainer ()   {
		return _imp.getContainer();
    }    
    public void setContainer (Container container)   {
		_imp.setContainer(container);
    }    
    public String getAllRolesMode ()   {
		return _imp.getAllRolesMode();
    }    
    public void setAllRolesMode (String allRolesMode)   {
		_imp.setAllRolesMode(allRolesMode);
    }    
    public boolean getValidate ()   {
		return _imp.getValidate();
    }    
    public void setValidate (boolean validate)   {
		_imp.setValidate(validate);
    }    
    public String getX509UsernameRetrieverClassName ()   {
		return _imp.getX509UsernameRetrieverClassName();
    }    
    public void setX509UsernameRetrieverClassName (String className)   {
		_imp.setX509UsernameRetrieverClassName(className);
    }    
    public boolean isStripRealmForGss ()   {
		return _imp.isStripRealmForGss();
    }    
    public void setStripRealmForGss (boolean stripRealmForGss)   {
		_imp.setStripRealmForGss(stripRealmForGss);
    }    
    public String toString ()   {
		return _imp.toString();
    }    
    public String getObjectNameKeyProperties ()   {
		return _imp.getObjectNameKeyProperties();
    }    
    public String getDomainInternal ()   {
		return _imp.getDomainInternal();
    }    
    public String getRealmPath ()   {
		return _imp.getRealmPath();
    }    
    public void setRealmPath (String theRealmPath)   {
		_imp.setRealmPath(theRealmPath);
    }    
}