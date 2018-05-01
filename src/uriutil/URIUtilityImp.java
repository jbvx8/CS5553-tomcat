package uriutil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tomcat.util.buf.UriUtil;

public class URIUtilityImp implements IURIUtilityImp
{
	private URIUtilityArch _arch;

    public URIUtilityImp (){
    }

	public void setArch(URIUtilityArch arch){
		_arch = arch;
	}
	public URIUtilityArch getArch(){
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

    @Override
    public boolean hasScheme(CharSequence uri) {
        return UriUtil.hasScheme(uri);
    }

    @Override
    public URL buildJarUrl(File jarFile) throws MalformedURLException {
        return UriUtil.buildJarUrl(jarFile);
    }

    @Override
    public URL buildJarUrl(File jarFile, String entryPath) throws MalformedURLException {
        return UriUtil.buildJarUrl(jarFile, entryPath);
    }

    @Override
    public URL buildJarUrl(String fileUrlString) throws MalformedURLException {
        return UriUtil.buildJarUrl(fileUrlString);
    }

    @Override
    public URL buildJarUrl(String fileUrlString, String entryPath) throws MalformedURLException {
        return UriUtil.buildJarUrl(fileUrlString, entryPath);
    }

    @Override
    public URL buildJarSafeUrl(File file) throws MalformedURLException {
        return UriUtil.buildJarSafeUrl(file);
    }

    @Override
    public URL warToJar(URL warUrl) throws MalformedURLException {
        return UriUtil.warToJar(warUrl);
    }

    @Override
    public String getWarSeparator() {
        return UriUtil.getWarSeparator();
    }

	/*
  	  Implementation primitives required by the architecture
	*/
}