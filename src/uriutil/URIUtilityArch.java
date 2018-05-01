package uriutil;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;

public class URIUtilityArch extends AbstractMyxSimpleBrick implements IURIUtility
{
    public static final IMyxName msg_IURIUtility = MyxUtils.createName("uriutil.IURIUtility");


	private IURIUtilityImp _imp;

    public URIUtilityArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IURIUtilityImp getImplementation(){
        try{
			return new URIUtilityImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_IURIUtility)){
			return this;
		}        
		return null;
	}
  
    //To be imported: File,MalformedURLException,URL
    public boolean hasScheme (CharSequence uri)   {
		return _imp.hasScheme(uri);
    }    
    public URL buildJarUrl (File jarFile) throws MalformedURLException {
		return _imp.buildJarUrl(jarFile);
    }    
    public URL buildJarUrl (File jarFile,String entryPath) throws MalformedURLException {
		return _imp.buildJarUrl(jarFile,entryPath);
    }    
    public URL buildJarUrl (String fileUrlString) throws MalformedURLException {
		return _imp.buildJarUrl(fileUrlString);
    }    
    public URL buildJarUrl (String fileUrlString,String entryPath) throws MalformedURLException {
		return _imp.buildJarUrl(fileUrlString,entryPath);
    }    
    public URL buildJarSafeUrl (File file) throws MalformedURLException {
		return _imp.buildJarSafeUrl(file);
    }    
    public URL warToJar (URL warUrl) throws MalformedURLException {
		return _imp.warToJar(warUrl);
    }    
    public String getWarSeparator ()   {
		return _imp.getWarSeparator();
    }    
}