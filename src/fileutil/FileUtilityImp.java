package fileutil;


import java.io.IOException;
import java.io.InputStream;

import java.util.Set;

import org.apache.tomcat.util.file.ConfigFileLoader;
import org.apache.tomcat.util.file.Matcher;

public class FileUtilityImp implements IFileUtilityImp
{
	private FileUtilityArch _arch;

    public FileUtilityImp (){
    }

	public void setArch(FileUtilityArch arch){
		_arch = arch;
	}
	public FileUtilityArch getArch(){
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
  
    //To be imported: IOException,InputStream,Set
    public InputStream getInputStream (String location) throws IOException {
		return ConfigFileLoader.getInputStream(location);
    }
    public boolean matchName (Set<String> patternSet,String fileName)   {
		return Matcher.matchName(patternSet, fileName);
    }
}