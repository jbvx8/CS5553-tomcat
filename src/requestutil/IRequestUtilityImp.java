package requestutil;


import javax.servlet.http.HttpServletRequest;

import requestutil.RequestUtilityArch;

public interface IRequestUtilityImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (RequestUtilityArch arch);
	public RequestUtilityArch getArch();
	
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
  
    //To be imported: HttpServletRequest
    public StringBuffer getRequestURL (HttpServletRequest request)  ;        
}