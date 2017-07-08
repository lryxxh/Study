package xml_encoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XMLTest implements Serializable{
	private List list2 = new ArrayList();
	
	public XMLTest() {
	}
	
	public void put(Object obj) {
		list2.add(obj);
	}
	
	public List getList2() {
		return list2;
	}
	
	public void setList2(List list) {
		this.list2 = list;
	}
}