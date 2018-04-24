package tostring;


import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Manager;
import org.apache.catalina.util.ToStringUtil;

public class ToStringUtilImp implements IToStringUtilityImp
{
	private ToStringUtilArch _arch;

    public ToStringUtilImp (){
    }

	public void setArch(ToStringUtilArch arch){
		_arch = arch;
	}
	public ToStringUtilArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
	    //TODO Auto-generated method stub
	}
	public void begin(){
		//TODO Auto-generated method stub
	}
	public void end(){
		//TODO Auto-generated method stub
	}
	public void destroy(){
		//TODO Auto-generated method stub
	}

	/*
  	  Implementation primitives required by the architecture
	*/
  
    //To be imported: Contained,Container,Manager
    public String toString (Contained contained)   {
		return ToStringUtil.toString(contained);
    }
    public String toString (Object obj,Container container)   {
		return ToStringUtil.toString(obj, container);
    }
    public String toString (Object obj,Manager manager)   {
		return ToStringUtil.toString(obj, manager);
    }
}