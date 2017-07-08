package xml_encoder;

import java.util.ArrayList;

public class SubTestBean extends TestBean{
	private String gAddress;
	private int gAge;
	private String gName;
	ArrayList<TestBean> list = new ArrayList<TestBean>();
	TestBean testBean = new TestBean();
	public void addTest(String name) {
		testBean.setName(name);
	}
	
	public TestBean getTestBean() {
		return testBean;
	}

	public void setTestBean(TestBean testBean) {
		this.testBean = testBean;
	}

	public void notest() {
		System.out.println("76543121");
	}
	public SubTestBean() {
	}

	public String getgAddress() {
		return gAddress;
	}

	public int getgAge() {
		return gAge;
	}

	// public String getTest() {
	// return test;
	// }
	// public void setTest(String test) {
	// this.test = test;
	// }
	public String getgName() {
		return gName;
	}

	public void setgAddress(String gAddress) {
		this.gAddress = gAddress;
	}

	public void setgAge(int gAge) {
		this.gAge = gAge;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}
	
	public void testMethod(String str) {
		this.gName += str;
	}
	
	
	public TestBean addTest(int i,TestBean test ) {
		add1(test);
		return test;
	}
	
	public void add1(TestBean test) {
		list.add(test);
	}

	public ArrayList<TestBean> getList() {
		return list;
	}

	public void setList(ArrayList<TestBean> list) {
		this.list = list;
	}

}
