package binarycodec;


import binarycodec.BinaryCodecArch;

public interface IBinaryCodecImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (BinaryCodecArch arch);
	public BinaryCodecArch getArch();
	
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
  
    
    public byte[] decodeBase64 (final byte[] base64Data)  ;        
    public byte[] decodeBase64 (final byte[] base64Data,final int off,final int len)  ;        
    public byte[] decodeBase64 (final String base64String)  ;        
    public byte[] encodeBase64 (final byte[] binaryData)  ;        
    public byte[] encodeBase64 (final byte[] binaryData,final boolean isChunked)  ;        
    public byte[] encodeBase64 (final byte[] binaryData,final boolean isChunked,final boolean urlSafe)  ;        
    public byte[] encodeBase64 (final byte[] binaryData,final boolean isChunked,final boolean urlSafe,final int maxResultSize)  ;        
    public String encodeBase64String (final byte[] binaryData)  ;        
}