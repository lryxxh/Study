package 抽象工厂模式.实现DAO;

public class RdbDAOFactory extends DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new RdbDetailDAOImpl();
	}

	public OrderMainDAO createOrderMainDAO() {
		return new RdbMainDAOImpl();
	}
}
