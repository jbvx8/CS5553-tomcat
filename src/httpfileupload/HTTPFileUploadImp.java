package httpfileupload;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemHeaders;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.ParameterParser;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class HTTPFileUploadImp implements IHTTPFileUploadImp
{
	private HTTPFileUploadArch _arch;
	
	private DiskFileItemFactory diskFileItemFactory;
	
	private FileItem fileitem;
	
	private ParameterParser paramParser;
	
	private ServletFileUpload servletFileUpload;

    public HTTPFileUploadImp (){
    }

	public void setArch(HTTPFileUploadArch arch){
		_arch = arch;
	}
	public HTTPFileUploadArch getArch(){
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

	/*
  	  Implementation primitives required by the architecture
	*/
  
    //To be imported: File,InputStream,List,Map,FileItem,FileItemFactory,FileItemHeaders,RequestContext
    public void delete ()   {
        fileitem.delete();			
    }
    public String getContentType ()   {
		return fileitem.getContentType();
    }
    public String getFieldName ()   {
		return fileitem.getFieldName();
    }
    public InputStream getInputStream () {
        InputStream is = null;
		try {
            is = fileitem.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return is;
    }
    public long getSize () {
		return fileitem.getSize();
    }
    public void write (File file) {
		try {
            fileitem.write(file);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String getHeader (String name)   {
		return fileitem.getHeaders().getHeader(name);
    }
    public FileItemHeaders getHeaders ()   {
		return fileitem.getHeaders();
    }
    public Map<String,String> parse (final char[] charArray,char separator)   {
		return paramParser.parse(charArray, separator);
    }
    public Map<String,String> parse (final char[] charArray,int offset,int length,char separator)   {
		return paramParser.parse(charArray, offset, length, separator);
    }
    public Map<String,String> parse (final String str,char separator)   {
		return paramParser.parse(str, separator);
    }
    public Map<String,String> parse (final String str,char[] separators)   {
		return paramParser.parse(str, separators);
    }
    public void setLowerCaseNames (boolean b)   {
		paramParser.setLowerCaseNames(b);
    }
    public void setRepository (File repository)   {
		diskFileItemFactory.setRepository(repository);
    }
    public void setSizeThreshold (int sizeThreshold)   {
		diskFileItemFactory.setSizeThreshold(sizeThreshold);
    }
    public List<FileItem> parseRequest (RequestContext ctx)   {
        List<FileItem> fileItemList = null;
		try {
		    fileItemList = servletFileUpload.parseRequest(ctx);
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return fileItemList;
    }
    public void setFileItemFactory (FileItemFactory factory)   {
		servletFileUpload.setFileItemFactory(factory);
    }
    public void setFileSizeMax (long fileSizeMax)   {
		servletFileUpload.setFileSizeMax(fileSizeMax);
    }
    public void setSizeMax (long sizeMax)   {
		servletFileUpload.setSizeMax(sizeMax);
    }
}