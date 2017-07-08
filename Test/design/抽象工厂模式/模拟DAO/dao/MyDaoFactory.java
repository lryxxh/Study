package ���󹤳�ģʽ.ģ��DAO.dao;

import ���󹤳�ģʽ.ģ��DAO.dao.dao.DAOFactory;
import ���󹤳�ģʽ.ģ��DAO.dao.impl.RdbDAOFactory;
import ���󹤳�ģʽ.ģ��DAO.dao.impl.XmlDAOFactory;

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
