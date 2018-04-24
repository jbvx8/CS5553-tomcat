package server;


import binarycodec.IBinaryCodec;

import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import httpfileupload.IHTTPFileUpload;

import java.io.UnsupportedEncodingException;

import java.nio.charset.Charset;

import org.apache.catalina.Context;

import realm.IRealm;

import security.ISecurity;

import tostring.IToStringUtil;

public class ServerArch extends AbstractMyxSimpleBrick implements IServer
{
    public static final IMyxName msg_IHTTPFileUpload = MyxUtils.createName("httpfileupload.IHTTPFileUpload");
    public static final IMyxName msg_IBinaryCodec = MyxUtils.createName("binarycodec.IBinaryCodec");
    public static final IMyxName msg_IServer = MyxUtils.createName("server.IServer");
    public static final IMyxName msg_IRealm = MyxUtils.createName("realm.IRealm");
    public static final IMyxName msg_ISecurity = MyxUtils.createName("security.ISecurity");
    public static final IMyxName msg_IToStringUtil = MyxUtils.createName("tostring.IToStringUtil");

    public IHTTPFileUpload OUT_IHTTPFileUpload;
    public IBinaryCodec OUT_IBinaryCodec;
    public IRealm OUT_IRealm;
    public ISecurity OUT_ISecurity;
    public IToStringUtil OUT_IToStringUtil;

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
        OUT_IRealm = (IRealm) MyxUtils.getFirstRequiredServiceObject(this,msg_IRealm);
        if (OUT_IRealm == null){
 			System.err.println("Error: Interface realm.IRealm returned null");
			return;       
        }
        OUT_ISecurity = (ISecurity) MyxUtils.getFirstRequiredServiceObject(this,msg_ISecurity);
        if (OUT_ISecurity == null){
 			System.err.println("Error: Interface security.ISecurity returned null");
			return;       
        }
        OUT_IToStringUtil = (IToStringUtil) MyxUtils.getFirstRequiredServiceObject(this,msg_IToStringUtil);
        if (OUT_IToStringUtil == null){
 			System.err.println("Error: Interface tostring.IToStringUtil returned null");
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
  
    //To be imported: UnsupportedEncodingException,Charset,Context
    public byte[] digestMD5 (byte[]... input)   {
		return _imp.digestMD5(input);
    }    
    public String encode (byte[] binaryData)   {
		return _imp.encode(binaryData);
    }    
    public Charset getCharset (String enc) throws UnsupportedEncodingException {
		return _imp.getCharset(enc);
    }    
    public String getSessionUriParamName (Context context)   {
		return _imp.getSessionUriParamName(context);
    }    
    public boolean setProperty (Object o,String name,String value)   {
		return _imp.setProperty(o,name,value);
    }    
    public String toHexString (byte[] bytes)   {
		return _imp.toHexString(bytes);
    }    
}