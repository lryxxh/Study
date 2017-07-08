package design.享元模式;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
	
	private Map<String, Flyweight> flyMap = new HashMap<String, Flyweight>();
	
	/**
	 * 获取对应的享元对象
	 * @param key 获取享元对象的key
	 * @return	key对应的享元对象
	 */
	public Flyweight getFlyweight(String key) {
		//这个方法中基本的实现步骤如下:
		//1:先从缓存中查找，是否存在key对应的Flyweight
		Flyweight flyweight = flyMap.get(key);
		
		//2.如果存在，就返回相对应的Flyweight对象
		if(null == flyweight) {
			//3.如果不存在，创建一个新的Flyweight对象
			flyweight = new ConcreteFlyweight(key);
			//3.1把这个心的Flyweight对象添加到缓存中
			flyMap.put(key, flyweight);
		}
		return flyweight;
	}

}
