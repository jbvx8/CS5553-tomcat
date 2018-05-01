package uriutil;


import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;

import uriutil.URIUtilityArch;

public interface IURIUtilityImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (URIUtilityArch arch);
	public URIUtilityArch getArch();
	
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
  
    //To be imported: File,MalformedURLException,URL
    public boolean hasScheme (CharSequence uri)  ;        
    public URL buildJarUrl (File jarFile) throws MalformedURLException;        
    public URL buildJarUrl (File jarFile,String entryPath) throws MalformedURLException;        
    public URL buildJarUrl (String fileUrlString) throws MalformedURLException;        
    public URL buildJarUrl (String fileUrlString,String entryPath) throws MalformedURLException;        
    public URL buildJarSafeUrl (File file) throws MalformedURLException;        
    public URL warToJar (URL warUrl) throws MalformedURLException;        
    public String getWarSeparator ()  ;        
}