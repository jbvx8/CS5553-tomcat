package fileutil;


import fileutil.FileUtilityArch;

import java.io.IOException;
import java.io.InputStream;

import java.util.Set;

public interface IFileUtilityImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (FileUtilityArch arch);
	public FileUtilityArch getArch();
	
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
  
    //To be imported: IOException,InputStream,Set
    public InputStream getInputStream (String location) throws IOException;        
    public boolean matchName (Set<String> patternSet,String fileName)  ;        
}