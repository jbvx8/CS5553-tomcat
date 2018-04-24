package realm;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import java.beans.PropertyChangeListener;

import java.io.IOException;

import java.security.Principal;

import java.security.cert.X509Certificate;

import java.sql.Wrapper;

import javax.naming.Context;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.CredentialHandler;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

import org.ietf.jgss.GSSContext;

import security.ISecurity;

import server.IServer;

public class RealmArch extends AbstractMyxSimpleBrick implements IRealm
{
    public static final IMyxName msg_IServer = MyxUtils.createName("server.IServer");
    public static final IMyxName msg_IRealm = MyxUtils.createName("realm.IRealm");
    public static final IMyxName msg_ISecurity = MyxUtils.createName("security.ISecurity");

    public IServer OUT_IServer;
    public ISecurity OUT_ISecurity;

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
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_IRealm)){
			return this;
		}        
		return null;
	}
  
    //To be imported: PropertyChangeListener,IOException,Principal,X509Certificate,Wrapper,Context,Contained,CredentialHandler,Request,Response,SecurityConstraint,GSSContext
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
    public SecurityConstraint [] findSecurityConstraints (Request request,Context context)   {
		return _imp.findSecurityConstraints(request,context);
    }    
    public boolean hasResourcePermission (Request request,Response response,SecurityConstraint [] constraint,Context context) throws IOException {
		return _imp.hasResourcePermission(request,response,constraint,context);
    }    
    public boolean hasRole (Wrapper wrapper,Principal principal,String role)   {
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

    @Override
    public Container getContainer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setContainer(Container container) {
        // TODO Auto-generated method stub
        
    }    
}