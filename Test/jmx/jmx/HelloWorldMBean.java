package jmx;

import java.util.Vector;


public interface HelloWorldMBean{

	public String getName();

	public void setName(String name);

	public void printHello();

	public void printHello(String theName);
	
	public void say(Vector<String> data);
}
