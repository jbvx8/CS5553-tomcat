package server;


import binarycodec.IBinaryCodec;

import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import fileutil.IFileUtil;

import httpfileupload.IHTTPFileUpload;

import java.io.UnsupportedEncodingException;

import java.nio.charset.Charset;

import logutil.ILogUtility;

import org.apache.catalina.Context;
import org.apache.catalina.Realm;

import security.ISecurity;

import tostring.IToStringUtil;

public class ServerArch extends AbstractMyxSimpleBrick implements IServer
{
    public static final IMyxName msg_IHTTPFileUpload = MyxUtils.createName("httpfileupload.IHTTPFileUpload");
    public static final IMyxName msg_IBinaryCodec = MyxUtils.createName("binarycodec.IBinaryCodec");
    public static final IMyxName msg_IServer = MyxUtils.createName("server.IServer");
    public static final IMyxName msg_Realm = MyxUtils.createName("org.apache.catalina.Realm");
    public static final IMyxName msg_ISecurity = MyxUtils.createName("security.ISecurity");
    public static final IMyxName msg_IToStringUtil = MyxUtils.createName("tostring.IToStringUtil");
    public static final IMyxName msg_IFileUtil = MyxUtils.createName("fileutil.IFileUtil");
    public static final IMyxName msg_ILogUtility = MyxUtils.createName("logutil.ILogUtility");

    public IHTTPFileUpload OUT_IHTTPFileUpload;
    public IBinaryCodec OUT_IBinaryCodec;
    public Realm OUT_Realm;
    public ISecurity OUT_ISecurity;
    public IToStringUtil OUT_IToStringUtil;
    public IFileUtil OUT_IFileUtil;
    public ILogUtility OUT_ILogUtility;

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
        OUT_Realm = (Realm) MyxUtils.getFirstRequiredServiceObject(this,msg_Realm);
        if (OUT_Realm == null){
 			System.err.println("Error: Interface org.apache.catalina.Realm returned null");
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
        OUT_IFileUtil = (IFileUtil) MyxUtils.getFirstRequiredServiceObject(this,msg_IFileUtil);
        if (OUT_IFileUtil == null){
 			System.err.println("Error: Interface fileutil.IFileUtil returned null");
			return;       
        }
        OUT_ILogUtility = (ILogUtility) MyxUtils.getFirstRequiredServiceObject(this,msg_ILogUtility);
        if (OUT_ILogUtility == null){
 			System.err.println("Error: Interface logutil.ILogUtility returned null");
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