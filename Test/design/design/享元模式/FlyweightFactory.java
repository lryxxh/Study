package design.��Ԫģʽ;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
	
	private Map<String, Flyweight> flyMap = new HashMap<String, Flyweight>();
	
	/**
	 * ��ȡ��Ӧ����Ԫ����
	 * @param key ��ȡ��Ԫ�����key
	 * @return	key��Ӧ����Ԫ����
	 */
	public Flyweight getFlyweight(String key) {
		//��������л�����ʵ�ֲ�������:
		//1:�ȴӻ����в��ң��Ƿ����key��Ӧ��Flyweight
		Flyweight flyweight = flyMap.get(key);
		
		//2.������ڣ��ͷ������Ӧ��Flyweight����
		if(null == flyweight) {
			//3.��������ڣ�����һ���µ�Flyweight����
			flyweight = new ConcreteFlyweight(key);
			//3.1������ĵ�Flyweight������ӵ�������
			flyMap.put(key, flyweight);
		}
		return flyweight;
	}

}
