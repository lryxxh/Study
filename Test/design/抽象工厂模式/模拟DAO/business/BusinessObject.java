package 抽象工厂模式.模拟DAO.business;

import 抽象工厂模式.模拟DAO.dao.MyDaoFactory;
import 抽象工厂模式.模拟DAO.dao.dao.DAOFactory;
import 抽象工厂模式.模拟DAO.dao.impl.OrderDetailDAO;
import 抽象工厂模式.模拟DAO.dao.impl.OrderMainDAO;

public class BusinessObject {
	public static void main(String[] args) {
		// 创建DAO的抽象工厂
		DAOFactory df = MyDaoFactory.createDAOFactory(2);

		// 通过抽象工厂来获取需要的DAO接口
		OrderMainDAO mainDAO = df.createOrderMainDAO();
		OrderDetailDAO detailDAO = df.createOrderDetailDAO();
		// 调用DAO来完成数据存储的功能
		mainDAO.saveOrderMain();
		detailDAO.saveOrderDetail();
	}
}
