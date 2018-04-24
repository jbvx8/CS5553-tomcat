package security;

import org.apache.tomcat.util.security.ConcurrentMessageDigest;
import org.apache.tomcat.util.security.MD5Encoder;

public class SecurityImp implements ISecurityImp
{
	private SecurityArch _arch;

    public SecurityImp (){
    }

	public void setArch(SecurityArch arch){
		_arch = arch;
	}
	public SecurityArch getArch(){
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
		//TODO Auto-generated method stub
	}
	public void end(){
		//TODO Auto-generated method stub
	}
	public void destroy(){
		//TODO Auto-generated method stub
	}

	/*
  	  Implementation primitives required by the architecture
	*/
  
    
    public byte[] digestMD5 (byte[]... input)   {
		return ConcurrentMessageDigest.digestMD5(input);
    }
    public String encode (byte[] binaryData)   {
		return MD5Encoder.encode(binaryData);
    }
}