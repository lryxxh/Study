package 抽象工厂模式.模拟DAO.dao.impl;

import 抽象工厂模式.模拟DAO.dao.dao.DAOFactory;

public class RdbDAOFactory implements DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new RdbDetailDAOImpl();
	}

	public OrderMainDAO createOrderMainDAO() {
		return new RdbMainDAOImpl();
	}
}
