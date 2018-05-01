package diagnostics;


import java.util.Enumeration;
import java.util.Locale;

import org.apache.tomcat.util.Diagnostics;
import org.apache.tomcat.util.res.StringManager;

public class DiagnosticsImp implements IDiagnosticsImp
{
	private DiagnosticsArch _arch;

    public DiagnosticsImp (){
    }

	public void setArch(DiagnosticsArch arch){
		_arch = arch;
	}
	public DiagnosticsArch getArch(){
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
  
    //To be imported: Enumeration,Locale,StringManager
    public String getThreadDump ()   {
		return Diagnostics.getThreadDump();
    }
    public String getThreadDump (Enumeration<Locale> requestedLocales)   {
		return Diagnostics.getThreadDump(requestedLocales);
    }
    public String getThreadDump (StringManager requestedSm)   {
		return Diagnostics.getThreadDump(requestedSm);
    }
    public String getVMInfo ()   {
		return Diagnostics.getVMInfo();
    }
    public String getVMInfo (Enumeration<Locale> requestedLocales)   {
		return Diagnostics.getVMInfo(requestedLocales);
    }
    public String getVMInfo (StringManager requestedSm)   {
		return Diagnostics.getVMInfo(requestedSm);
    }
}