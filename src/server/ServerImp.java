package server;

import org.apache.catalina.startup.Bootstrap;
import org.apache.tomcat.util.buf.HexUtils;

public class ServerImp implements IServerImp
{
	private ServerArch _arch;

    public ServerImp (){
    }

	public void setArch(ServerArch arch){
		_arch = arch;
	}
	public ServerArch getArch(){
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
		System.setProperty("catalina.home", "C:\\Users\\Jackie\\Documents\\CS5553\\workspace\\tomcat\\output\\build");
		Bootstrap.main(new String[] {"start"});
	}
	public void end(){
		//TODO Auto-generated method stub
	}
	public void destroy(){
		//TODO Auto-generated method stub
	}

    @Override
    public String toHexString(byte[] bytes) {
        return HexUtils.toHexString(bytes);
    }

	/*
  	  Implementation primitives required by the architecture
	*/
}