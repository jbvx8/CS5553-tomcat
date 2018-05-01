package uriutil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public interface IURIUtility {
    public boolean hasScheme(CharSequence uri);
    public URL buildJarUrl(File jarFile) throws MalformedURLException;
    public URL buildJarUrl(File jarFile, String entryPath) throws MalformedURLException;
    public URL buildJarUrl(String fileUrlString) throws MalformedURLException;
    public URL buildJarUrl(String fileUrlString, String entryPath) throws MalformedURLException;
    public URL buildJarSafeUrl(File file) throws MalformedURLException;
    public URL warToJar(URL warUrl) throws MalformedURLException;
    public String getWarSeparator();
}
