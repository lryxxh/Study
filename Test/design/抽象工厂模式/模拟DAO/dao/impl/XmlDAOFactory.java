package ���󹤳�ģʽ.ģ��DAO.dao.impl;

import ���󹤳�ģʽ.ģ��DAO.dao.dao.DAOFactory;

public class XmlDAOFactory implements DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new XmlDetailDAOImpl();
	}

	public OrderMainDAO createOrderMainDAO() {
		return new XmlMainDAOImpl();
	}
}
