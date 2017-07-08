package ���󹤳�ģʽ.ʵ��DAO;

public class RdbDAOFactory extends DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new RdbDetailDAOImpl();
	}

	public OrderMainDAO createOrderMainDAO() {
		return new RdbMainDAOImpl();
	}
}
