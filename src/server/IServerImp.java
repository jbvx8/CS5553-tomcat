package server;


import java.io.UnsupportedEncodingException;

import java.nio.charset.Charset;

import org.apache.catalina.Context;

import server.ServerArch;

public interface IServerImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (ServerArch arch);
	public ServerArch getArch();
	
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
  
    //To be imported: UnsupportedEncodingException,Charset,Context
    public byte[] digestMD5 (byte[]... input)  ;        
    public String encode (byte[] binaryData)  ;        
    public Charset getCharset (String enc) throws UnsupportedEncodingException;        
    public String getSessionUriParamName (Context context)  ;        
    public boolean setProperty (Object o,String name,String value)  ;        
    public String toHexString (byte[] bytes)  ;        
}