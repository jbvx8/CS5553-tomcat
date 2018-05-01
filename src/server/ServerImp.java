package server;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.catalina.Context;
import org.apache.catalina.authenticator.AuthenticatorBase;
import org.apache.catalina.authenticator.FormAuthenticator;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.apache.catalina.authenticator.BasicAuthenticator.BasicCredentials;
import org.apache.catalina.authenticator.DigestAuthenticator.DigestInfo;
import org.apache.catalina.connector.Request;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.catalina.core.ApplicationHttpRequest;
import org.apache.catalina.core.ApplicationPart;
import org.apache.catalina.core.ContainerBase;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardPipeline;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.core.StandardWrapperValve;
import org.apache.catalina.filters.RemoteIpFilter;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.realm.MessageDigestCredentialHandler;
import org.apache.catalina.servlets.WebdavServlet;
import org.apache.catalina.session.ManagerBase;
import org.apache.catalina.session.StoreBase;
import org.apache.catalina.startup.Bootstrap;
import org.apache.catalina.users.MemoryUserDatabase;
import org.apache.catalina.util.SessionConfig;
import org.apache.catalina.valves.ValveBase;
import org.apache.coyote.http2.Http2UpgradeHandler;
import org.apache.tomcat.buildutil.SignCode;
import org.apache.tomcat.util.IntrospectionUtils;
import org.apache.tomcat.util.buf.B2CConverter;
import org.apache.tomcat.util.buf.HexUtils;
import org.apache.tomcat.util.net.SSLUtilBase;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.apache.tomcat.util.net.jsse.PEMFile;
import org.apache.tomcat.util.security.ConcurrentMessageDigest;
import org.apache.tomcat.util.security.MD5Encoder;
import org.apache.tomcat.websocket.DigestAuthenticator;
import org.apache.tomcat.websocket.WsWebSocketContainer;
import org.apache.tomcat.websocket.server.UpgradeUtil;

public class ServerImp implements IServerImp
{
	private ServerArch _arch;

    public ServerImp (){
    }

	public void setArch(ServerArch arch){
		_arch = arch;
	}
	public ServerArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
	    BasicCredentials.setArch(_arch);
        Request.setArch(_arch);
        ApplicationPart.setArch(_arch);
        SpnegoAuthenticator.setArch(_arch);
        MessageDigestCredentialHandler.setArch(_arch);
        Http2UpgradeHandler.setArch(_arch);
        SignCode.setArch(_arch);
        PEMFile.setArch(_arch);
        WsWebSocketContainer.setArch(_arch);
        UpgradeUtil.setArch(_arch);
        DigestInfo.setArch(_arch);
        WebdavServlet.setArch(_arch);
        DigestAuthenticator.setArch(_arch);
        StandardPipeline.setArch(_arch);
        SimpleTcpCluster.setArch(_arch);
        WebappLoader.setArch(_arch);
        ManagerBase.setArch(_arch);
        StoreBase.setArch(_arch);
        ValveBase.setArch(_arch);
        MemoryUserDatabase.setArch(_arch);
        SSLUtilBase.setArch(_arch);
        JSSEUtil.setArch(_arch);
        ApplicationFilterConfig.setArch(_arch);
        StandardWrapper.setArch(_arch);
        StandardWrapperValve.setArch(_arch);
        AuthenticatorBase.setArch(_arch);
        FormAuthenticator.setArch(_arch);
        Request.setArch(_arch);
        StandardContext.setArch(_arch);
        ContainerBase.setArch(_arch);
        ApplicationHttpRequest.setArch(_arch);
        RemoteIpFilter.XForwardedRequest.setArch(_arch);
	}
	public void begin(){
		System.setProperty("catalina.home", "C:\\Users\\Jackie\\Documents\\CS5553\\workspace\\tomcat\\output\\build");
		Bootstrap.main(new String[] {"start"});
	}
	public void end(){
		//TODO Auto-generated method stub
	}
	public void destroy(){
		//TODO Auto-generated method stub
	}

    @Override
    public String toHexString(byte[] bytes) {
        return HexUtils.toHexString(bytes);
    }

    @Override
    public byte[] digestMD5(byte[]... input) {
        return ConcurrentMessageDigest.digestMD5(input);
    }

    @Override
    public String encode(byte[] binaryData) {
        return MD5Encoder.encode(binaryData);
    }

    @Override
    public Charset getCharset(String enc) throws UnsupportedEncodingException {
        return B2CConverter.getCharset(enc);
    }

    @Override
    public String getSessionUriParamName(Context context) {
        return SessionConfig.getSessionUriParamName(context);
    }

    @Override
    public boolean setProperty(Object o, String name, String value) {
        return IntrospectionUtils.setProperty(o, name, value);
    }

	/*
  	  Implementation primitives required by the architecture
	*/
}