package tostring;


import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Manager;

import tostring.ToStringUtilArch;

public interface IToStringUtilityImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (ToStringUtilArch arch);
	public ToStringUtilArch getArch();
	
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
  
    //To be imported: Contained,Container,Manager
    public String toString (Contained contained)  ;        
    public String toString (Object obj,Container container)  ;        
    public String toString (Object obj,Manager manager)  ;        
}