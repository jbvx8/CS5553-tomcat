package security;


import security.SecurityArch;

public interface ISecurityImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SecurityArch arch);
	public SecurityArch getArch();
	
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
  
    
    public byte[] digestMD5 (byte[]... input)  ;        
    public String encode (byte[] binaryData)  ;        
}