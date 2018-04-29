package fileutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public interface IFileUtil {
    public InputStream getInputStream(String location) throws IOException;
    
    public boolean matchName(Set<String> patternSet, String fileName);
}
