package httpfileupload;


import httpfileupload.HTTPFileUploadArch;

import java.io.File;
import java.io.InputStream;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemHeaders;
import org.apache.tomcat.util.http.fileupload.RequestContext;

public interface IHTTPFileUploadImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (HTTPFileUploadArch arch);
	public HTTPFileUploadArch getArch();
	
	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init();	
	public void begin();
	public void end();
	public void destroy();

	/*
  	  Implementation primitives required by the architecture
	*/
  
    //To be imported: File,InputStream,List,Map,FileItem,FileItemFactory,FileItemHeaders,RequestContext
    public void delete ()  ;        
    public String getContentType ()  ;        
    public String getFieldName ()  ;        
    public InputStream getInputStream ()  ;        
    public long getSize ()  ;        
    public void write (File file)  ;        
    public String getHeader (String name)  ;        
    public FileItemHeaders getHeaders ()  ;        
    public Map<String,String> parse (final char[] charArray,char separator)  ;        
    public Map<String,String> parse (final char[] charArray,int offset,int length,char separator)  ;        
    public Map<String,String> parse (final String str,char separator)  ;        
    public Map<String,String> parse (final String str,char[] separators)  ;        
    public void setLowerCaseNames (boolean b)  ;        
    public void setRepository (File repository)  ;        
    public void setSizeThreshold (int sizeThreshold)  ;        
    public List<FileItem> parseRequest (RequestContext ctx)  ;        
    public void setFileItemFactory (FileItemFactory factory)  ;        
    public void setFileSizeMax (long fileSizeMax)  ;        
    public void setSizeMax (long sizeMax)  ;        
}