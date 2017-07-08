package 抽象工厂模式.实现DAO;

public class XmlDAOFactory extends DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new XmlDetailDAOImpl();
	}

	public OrderMainDAO createOrderMainDAO() {
		return new XmlMainDAOImpl();
	}
}
