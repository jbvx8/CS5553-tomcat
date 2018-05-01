package diagnostics;


import diagnostics.DiagnosticsArch;

import java.util.Enumeration;
import java.util.Locale;

import org.apache.tomcat.util.res.StringManager;

public interface IDiagnosticsImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (DiagnosticsArch arch);
	public DiagnosticsArch getArch();
	
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
  
    //To be imported: Enumeration,Locale,StringManager
    public String getThreadDump ()  ;        
    public String getThreadDump (Enumeration<Locale> requestedLocales)  ;        
    public String getThreadDump (StringManager requestedSm)  ;        
    public String getVMInfo ()  ;        
    public String getVMInfo (Enumeration<Locale> requestedLocales)  ;        
    public String getVMInfo (StringManager requestedSm)  ;        
}