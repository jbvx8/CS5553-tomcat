package httpfileupload;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import java.io.File;
import java.io.InputStream;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemHeaders;
import org.apache.tomcat.util.http.fileupload.RequestContext;

public class HTTPFileUploadArch extends AbstractMyxSimpleBrick implements IHTTPFileUpload
{
    public static final IMyxName msg_IHTTPFileUpload = MyxUtils.createName("httpfileupload.IHTTPFileUpload");


	private IHTTPFileUploadImp _imp;

    public HTTPFileUploadArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IHTTPFileUploadImp getImplementation(){
        try{
			return new HTTPFileUploadImp();    
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
		if (arg0.equals(msg_IHTTPFileUpload)){
			return this;
		}        
		return null;
	}
  
    //To be imported: File,InputStream,List,Map,FileItem,FileItemFactory,FileItemHeaders,RequestContext
    public void delete ()   {
		_imp.delete();
    }    
    public String getContentType ()   {
		return _imp.getContentType();
    }    
    public String getFieldName ()   {
		return _imp.getFieldName();
    }    
    public InputStream getInputStream ()   {
		return _imp.getInputStream();
    }    
    public long getSize ()   {
		return _imp.getSize();
    }    
    public void write (File file)   {
		_imp.write(file);
    }    
    public String getHeader (String name)   {
		return _imp.getHeader(name);
    }    
    public FileItemHeaders getHeaders ()   {
		return _imp.getHeaders();
    }    
    public Map<String,String> parse (final char[] charArray,char separator)   {
		return _imp.parse(charArray,separator);
    }    
    public Map<String,String> parse (final char[] charArray,int offset,int length,char separator)   {
		return _imp.parse(charArray,offset,length,separator);
    }    
    public Map<String,String> parse (final String str,char separator)   {
		return _imp.parse(str,separator);
    }    
    public Map<String,String> parse (final String str,char[] separators)   {
		return _imp.parse(str,separators);
    }    
    public void setLowerCaseNames (boolean b)   {
		_imp.setLowerCaseNames(b);
    }    
    public void setRepository (File repository)   {
		_imp.setRepository(repository);
    }    
    public void setSizeThreshold (int sizeThreshold)   {
		_imp.setSizeThreshold(sizeThreshold);
    }    
    public List<FileItem> parseRequest (RequestContext ctx)   {
		return _imp.parseRequest(ctx);
    }    
    public void setFileItemFactory (FileItemFactory factory)   {
		_imp.setFileItemFactory(factory);
    }    
    public void setFileSizeMax (long fileSizeMax)   {
		_imp.setFileSizeMax(fileSizeMax);
    }    
    public void setSizeMax (long sizeMax)   {
		_imp.setSizeMax(sizeMax);
    }    
}