package jdbcrealm;


import jdbcrealm.JDBCRealmArch;

public interface IJDBCRealmImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (JDBCRealmArch arch);
	public JDBCRealmArch getArch();
	
	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init();	
	public void begin();
	public void end();
	public void destroy();

	/*
  	  Implementation primitives required by the architecture
	*/
}