package binarycodec;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import server.IServer;

public class BinaryCodecArch extends AbstractMyxSimpleBrick implements IBinaryCodec
{
    public static final IMyxName msg_IBinaryCodec = MyxUtils.createName("binarycodec.IBinaryCodec");
    public static final IMyxName msg_IServer = MyxUtils.createName("server.IServer");

    public IServer OUT_IServer;

	private IBinaryCodecImp _imp;

    public BinaryCodecArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IBinaryCodecImp getImplementation(){
        try{
			return new BinaryCodecImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IServer = (IServer) MyxUtils.getFirstRequiredServiceObject(this,msg_IServer);
        if (OUT_IServer == null){
 			System.err.println("Error: Interface server.IServer returned null");
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
		if (arg0.equals(msg_IBinaryCodec)){
			return this;
		}        
		return null;
	}
  
    
    public byte[] decodeBase64 (final byte[] base64Data)   {
		return _imp.decodeBase64(base64Data);
    }    
    public byte[] decodeBase64 (final byte[] base64Data,final int off,final int len)   {
		return _imp.decodeBase64(base64Data,off,len);
    }    
    public byte[] decodeBase64 (final String base64String)   {
		return _imp.decodeBase64(base64String);
    }    
    public byte[] encodeBase64 (final byte[] binaryData)   {
		return _imp.encodeBase64(binaryData);
    }    
    public byte[] encodeBase64 (final byte[] binaryData,final boolean isChunked)   {
		return _imp.encodeBase64(binaryData,isChunked);
    }    
    public byte[] encodeBase64 (final byte[] binaryData,final boolean isChunked,final boolean urlSafe)   {
		return _imp.encodeBase64(binaryData,isChunked,urlSafe);
    }    
    public byte[] encodeBase64 (final byte[] binaryData,final boolean isChunked,final boolean urlSafe,final int maxResultSize)   {
		return _imp.encodeBase64(binaryData,isChunked,urlSafe,maxResultSize);
    }    
    public String encodeBase64String (final byte[] binaryData)   {
		return _imp.encodeBase64String(binaryData);
    }    
}