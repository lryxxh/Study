package jmx;

import java.util.Vector;

public class HelloWorld implements HelloWorldMBean {

	private String name;

	public String getName() {
		return name;
	}

	public void printHello() {
		System.out.println("Hello, " + name);
	}

	public void printHello(String theName) {
		System.out.println("Hello, " + theName);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void say(Vector<String> data) {
		System.out.println("saydata:"+data);
	}

}