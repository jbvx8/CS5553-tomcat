package jdbcrealm;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import org.apache.catalina.Realm;

public class JDBCRealmArch extends AbstractMyxSimpleBrick
{
    public static final IMyxName msg_Realm = MyxUtils.createName("org.apache.catalina.Realm");

    public Realm OUT_Realm;

	private IJDBCRealmImp _imp;

    public JDBCRealmArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IJDBCRealmImp getImplementation(){
        try{
			return new JDBCRealmImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_Realm = (Realm) MyxUtils.getFirstRequiredServiceObject(this,msg_Realm);
        if (OUT_Realm == null){
 			System.err.println("Error: Interface org.apache.catalina.Realm returned null");
			return;       
        }
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		return null;
	}
}