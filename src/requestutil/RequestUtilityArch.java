package requestutil;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtilityArch extends AbstractMyxSimpleBrick implements IRequestUtility
{
    public static final IMyxName msg_IRequestUtility = MyxUtils.createName("requestutil.IRequestUtility");


	private IRequestUtilityImp _imp;

    public RequestUtilityArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IRequestUtilityImp getImplementation(){
        try{
			return new RequestUtilityImp();    
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
		if (arg0.equals(msg_IRequestUtility)){
			return this;
		}        
		return null;
	}
  
    //To be imported: HttpServletRequest
    public StringBuffer getRequestURL (HttpServletRequest request)   {
		return _imp.getRequestURL(request);
    }    
}