package httpfileupload;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemHeaders;
import org.apache.tomcat.util.http.fileupload.RequestContext;

public interface IHTTPFileUpload {
    // org.apache.tomcat.util.http.fileupload.FileItem
    public void delete();
    
    public String getContentType();
    
    public String getFieldName();
    
    public InputStream getInputStream();
    
    public long getSize();

    public void write(File file);
    
    // org.apache.tomcat.util.http.fileupload.FileItemHeaders
    public String getHeader(String name);
    
    // org.apache.tomcat.util.http.fileupload.FileItemHeadersSupport
    public FileItemHeaders getHeaders();   
    
    // org.apache.tomcat.util.http.fileupload.ParameterParser
    public Map<String,String> parse(final char[] charArray, char separator);
    
    public Map<String,String> parse(
            final char[] charArray,
            int offset,
            int length,
            char separator);

    public Map<String,String> parse(final String str, char separator);

    public Map<String,String> parse(final String str, char[] separators);

    public void setLowerCaseNames(boolean b);
    
    // org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory
    public void setRepository(File repository);

    public void setSizeThreshold(int sizeThreshold);
    
    // org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload
    public List<FileItem> parseRequest(RequestContext ctx);

    public void setFileItemFactory(FileItemFactory factory);

    public void setFileSizeMax(long fileSizeMax);

    public void setSizeMax(long sizeMax);
    
   
    
}
