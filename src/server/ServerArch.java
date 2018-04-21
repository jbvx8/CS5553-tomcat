package server;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import httpfileupload.IHTTPFileUpload;

public class ServerArch extends AbstractMyxSimpleBrick
{
    public static final IMyxName msg_IHTTPFileUpload = MyxUtils.createName("httpfileupload.IHTTPFileUpload");

    public IHTTPFileUpload OUT_IHTTPFileUpload;

	private IServerImp _imp;

    public ServerArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IServerImp getImplementation(){
        try{
			return new ServerImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IHTTPFileUpload = (IHTTPFileUpload) MyxUtils.getFirstRequiredServiceObject(this,msg_IHTTPFileUpload);
        if (OUT_IHTTPFileUpload == null){
 			System.err.println("Error: Interface httpfileupload.IHTTPFileUpload returned null");
			return;       
        }
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		return null;
	}
}