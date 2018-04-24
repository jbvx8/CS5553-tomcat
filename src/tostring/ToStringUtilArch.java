package tostring;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Manager;

public class ToStringUtilArch extends AbstractMyxSimpleBrick implements IToStringUtil
{
    public static final IMyxName msg_IToStringUtil = MyxUtils.createName("tostring.IToStringUtil");


	private IToStringUtilityImp _imp;

    public ToStringUtilArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IToStringUtilityImp getImplementation(){
        try{
			return new ToStringUtilImp();    
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
		if (arg0.equals(msg_IToStringUtil)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Contained,Container,Manager
    public String toString (Contained contained)   {
		return _imp.toString(contained);
    }    
    public String toString (Object obj,Container container)   {
		return _imp.toString(obj,container);
    }    
    public String toString (Object obj,Manager manager)   {
		return _imp.toString(obj,manager);
    }    
}