package server;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Bootstrap;
import org.apache.catalina.util.SessionConfig;
import org.apache.tomcat.util.IntrospectionUtils;
import org.apache.tomcat.util.buf.B2CConverter;
import org.apache.tomcat.util.buf.HexUtils;
import org.apache.tomcat.util.security.ConcurrentMessageDigest;
import org.apache.tomcat.util.security.MD5Encoder;

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
	    //TODO Auto-generated method stub
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