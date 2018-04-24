package server;


import binarycodec.IBinaryCodec;

import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import httpfileupload.IHTTPFileUpload;

public class ServerArch extends AbstractMyxSimpleBrick implements IServer
{
    public static final IMyxName msg_IHTTPFileUpload = MyxUtils.createName("httpfileupload.IHTTPFileUpload");
    public static final IMyxName msg_IBinaryCodec = MyxUtils.createName("binarycodec.IBinaryCodec");
    public static final IMyxName msg_IServer = MyxUtils.createName("server.IServer");

    public IHTTPFileUpload OUT_IHTTPFileUpload;
    public IBinaryCodec OUT_IBinaryCodec;

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
        OUT_IBinaryCodec = (IBinaryCodec) MyxUtils.getFirstRequiredServiceObject(this,msg_IBinaryCodec);
        if (OUT_IBinaryCodec == null){
 			System.err.println("Error: Interface binarycodec.IBinaryCodec returned null");
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
		if (arg0.equals(msg_IServer)){
			return this;
		}        
		return null;
	}
  
    
    public String toHexString (byte[] bytes)   {
		return _imp.toHexString(bytes);
    }    
}