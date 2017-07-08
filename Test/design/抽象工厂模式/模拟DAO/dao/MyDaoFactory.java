package 抽象工厂模式.模拟DAO.dao;

import 抽象工厂模式.模拟DAO.dao.dao.DAOFactory;
import 抽象工厂模式.模拟DAO.dao.impl.RdbDAOFactory;
import 抽象工厂模式.模拟DAO.dao.impl.XmlDAOFactory;

public class MyDaoFactory {
	public static DAOFactory createDAOFactory(int type) {
		if (type == 1) {
			return new RdbDAOFactory();
		} else if (type == 2) {
			return new XmlDAOFactory();
		}
		return null;
	}
}
