package ���󹤳�ģʽ.ģ��DAO.dao.dao;

import ���󹤳�ģʽ.ģ��DAO.dao.impl.OrderDetailDAO;
import ���󹤳�ģʽ.ģ��DAO.dao.impl.OrderMainDAO;

/**
 * ���󹤳����������������Ӽ�¼��Ӧ��DAO����
 */
public interface DAOFactory {
	/**
	 * ������������¼��Ӧ��DAO����
	 * 
	 * @return ��������¼��Ӧ��DAO����
	 */
	public OrderMainDAO createOrderMainDAO();

	/**
	 * ���������Ӽ�¼��Ӧ��DAO����
	 * 
	 * @return �����Ӽ�¼��Ӧ��DAO����
	 */
	public OrderDetailDAO createOrderDetailDAO();
}
