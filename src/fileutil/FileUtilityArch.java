package fileutil;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import java.io.IOException;
import java.io.InputStream;

import java.util.Set;

public class FileUtilityArch extends AbstractMyxSimpleBrick implements IFileUtil
{
    public static final IMyxName msg_IFileUtil = MyxUtils.createName("fileutil.IFileUtil");


	private IFileUtilityImp _imp;

    public FileUtilityArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IFileUtilityImp getImplementation(){
        try{
			return new FileUtilityImp();    
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
		if (arg0.equals(msg_IFileUtil)){
			return this;
		}        
		return null;
	}
  
    //To be imported: IOException,InputStream,Set
    public InputStream getInputStream (String location) throws IOException {
		return _imp.getInputStream(location);
    }    
    public boolean matchName (Set<String> patternSet,String fileName)   {
		return _imp.matchName(patternSet,fileName);
    }    
}