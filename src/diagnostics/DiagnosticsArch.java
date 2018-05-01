package diagnostics;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import java.util.Enumeration;
import java.util.Locale;

import org.apache.tomcat.util.res.StringManager;

public class DiagnosticsArch extends AbstractMyxSimpleBrick implements IDiagnostics
{
    public static final IMyxName msg_IDiagnostics = MyxUtils.createName("diagnostics.IDiagnostics");


	private IDiagnosticsImp _imp;

    public DiagnosticsArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IDiagnosticsImp getImplementation(){
        try{
			return new DiagnosticsImp();    
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
		if (arg0.equals(msg_IDiagnostics)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Enumeration,Locale,StringManager
    public String getThreadDump ()   {
		return _imp.getThreadDump();
    }    
    public String getThreadDump (Enumeration<Locale> requestedLocales)   {
		return _imp.getThreadDump(requestedLocales);
    }    
    public String getThreadDump (StringManager requestedSm)   {
		return _imp.getThreadDump(requestedSm);
    }    
    public String getVMInfo ()   {
		return _imp.getVMInfo();
    }    
    public String getVMInfo (Enumeration<Locale> requestedLocales)   {
		return _imp.getVMInfo(requestedLocales);
    }    
    public String getVMInfo (StringManager requestedSm)   {
		return _imp.getVMInfo(requestedSm);
    }    
}