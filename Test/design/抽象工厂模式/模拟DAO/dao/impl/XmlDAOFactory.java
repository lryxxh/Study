package 抽象工厂模式.模拟DAO.dao.impl;

import 抽象工厂模式.模拟DAO.dao.dao.DAOFactory;

public class XmlDAOFactory implements DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new XmlDetailDAOImpl();
	}

	public OrderMainDAO createOrderMainDAO() {
		return new XmlMainDAOImpl();
	}
}
