package 迭代器模式.含有迭代策略;

import java.util.Iterator;

/**
 * 聚合对象的接口，定义创建相应迭代器对象的接口
 */
public abstract class Aggregate {
	/**
	 * 工厂方法，创建相应迭代器对象的接口
	 * 
	 * @return 相应迭代器对象的接口
	 */
	public abstract Iterator createIterator();
}
