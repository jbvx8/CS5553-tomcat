package logutil;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

public class LogUtilityArch extends AbstractMyxSimpleBrick implements ILogUtility
{
    public static final IMyxName msg_ILogUtility = MyxUtils.createName("logutil.ILogUtility");


	private ILogUtilityImp _imp;

    public LogUtilityArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ILogUtilityImp getImplementation(){
        try{
			return new LogUtilityImp();    
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
		if (arg0.equals(msg_ILogUtility)){
			return this;
		}        
		return null;
	}
  
    
    public void startCapture ()   {
		_imp.startCapture();
    }    
    public String stopCapture ()   {
		return _imp.stopCapture();
    }    
}