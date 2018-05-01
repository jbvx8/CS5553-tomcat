package diagnostics;

import java.util.Enumeration;
import java.util.Locale;

import org.apache.tomcat.util.res.StringManager;

public interface IDiagnostics {
    public String getThreadDump();
    public String getThreadDump(Enumeration<Locale> requestedLocales);
    public String getThreadDump(StringManager requestedSm);
    public String getVMInfo();
    public String getVMInfo(Enumeration<Locale> requestedLocales);
    public String getVMInfo(StringManager requestedSm);
}
