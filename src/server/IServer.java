package server;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.catalina.Context;

public interface IServer {
    public byte[] digestMD5(byte[]... input);
    
    public String encode(byte[] binaryData);
    
    public Charset getCharset(String enc) throws UnsupportedEncodingException;
    
    public String getSessionUriParamName(Context context);
    
    public boolean setProperty(Object o, String name, String value);
    
    public String toHexString(byte[] bytes);
}
