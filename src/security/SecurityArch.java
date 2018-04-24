package security;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

public class SecurityArch extends AbstractMyxSimpleBrick implements ISecurity
{
    public static final IMyxName msg_ISecurity = MyxUtils.createName("security.ISecurity");


	private ISecurityImp _imp;

    public SecurityArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISecurityImp getImplementation(){
        try{
			return new SecurityImp();    
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
		if (arg0.equals(msg_ISecurity)){
			return this;
		}        
		return null;
	}
  
    
    public byte[] digestMD5 (byte[]... input)   {
		return _imp.digestMD5(input);
    }    
    public String encode (byte[] binaryData)   {
		return _imp.encode(binaryData);
    }    
}