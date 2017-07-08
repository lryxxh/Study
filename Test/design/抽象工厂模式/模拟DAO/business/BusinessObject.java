package ���󹤳�ģʽ.ģ��DAO.business;

import ���󹤳�ģʽ.ģ��DAO.dao.MyDaoFactory;
import ���󹤳�ģʽ.ģ��DAO.dao.dao.DAOFactory;
import ���󹤳�ģʽ.ģ��DAO.dao.impl.OrderDetailDAO;
import ���󹤳�ģʽ.ģ��DAO.dao.impl.OrderMainDAO;

public class BusinessObject {
	public static void main(String[] args) {
		// ����DAO�ĳ��󹤳�
		DAOFactory df = MyDaoFactory.createDAOFactory(2);

		// ͨ�����󹤳�����ȡ��Ҫ��DAO�ӿ�
		OrderMainDAO mainDAO = df.createOrderMainDAO();
		OrderDetailDAO detailDAO = df.createOrderDetailDAO();
		// ����DAO��������ݴ洢�Ĺ���
		mainDAO.saveOrderMain();
		detailDAO.saveOrderDetail();
	}
}
