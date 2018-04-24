package binarycodec;

import org.apache.tomcat.util.codec.binary.Base64;

public class BinaryCodecImp implements IBinaryCodecImp
{
	private BinaryCodecArch _arch;

    public BinaryCodecImp (){
    }

	public void setArch(BinaryCodecArch arch){
		_arch = arch;
	}
	public BinaryCodecArch getArch(){
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
  
    
    public byte[] decodeBase64 (final byte[] base64Data)   {
		return Base64.decodeBase64(base64Data);
    }
    public byte[] decodeBase64 (final byte[] base64Data,final int off,final int len)   {
		return Base64.decodeBase64(base64Data, off, len);
    }
    public byte[] decodeBase64 (final String base64String)   {
		return Base64.decodeBase64(base64String);
    }
    public byte[] encodeBase64 (final byte[] binaryData)   {
		return Base64.encodeBase64(binaryData);
    }
    public byte[] encodeBase64 (final byte[] binaryData,final boolean isChunked)   {
		return Base64.encodeBase64(binaryData, isChunked);
    }
    public byte[] encodeBase64 (final byte[] binaryData,final boolean isChunked,final boolean urlSafe)   {
		return Base64.encodeBase64(binaryData, isChunked, urlSafe);
    }
    public byte[] encodeBase64 (final byte[] binaryData,final boolean isChunked,final boolean urlSafe,final int maxResultSize)   {
		return Base64.encodeBase64(binaryData, isChunked, urlSafe, maxResultSize);
    }
    public String encodeBase64String (final byte[] binaryData)   {
		return Base64.encodeBase64String(binaryData);
    }
}