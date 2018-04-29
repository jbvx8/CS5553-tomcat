package logutil;

import org.apache.tomcat.util.log.SystemLogHandler;

public class LogUtilityImp implements ILogUtilityImp
{
	private LogUtilityArch _arch;

    public LogUtilityImp (){
    }

	public void setArch(LogUtilityArch arch){
		_arch = arch;
	}
	public LogUtilityArch getArch(){
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
  
    
    public void startCapture ()   {
		SystemLogHandler.startCapture();	
    }
    
    public String stopCapture ()   {
		return SystemLogHandler.stopCapture();	
    }
}