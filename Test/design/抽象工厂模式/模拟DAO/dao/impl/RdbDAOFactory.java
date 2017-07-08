package ���󹤳�ģʽ.ģ��DAO.dao.impl;

import ���󹤳�ģʽ.ģ��DAO.dao.dao.DAOFactory;

public class RdbDAOFactory implements DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new RdbDetailDAOImpl();
	}

	public OrderMainDAO createOrderMainDAO() {
		return new RdbMainDAOImpl();
	}
}
