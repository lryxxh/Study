package ����ģʽ.ģʽ��չ;

/**
 * ������ֽ�֧��
 */
public class RMBCash implements PaymentStrategy {

	public void pay(PaymentContext ctx) {
		System.out.println("���ڸ�" + ctx.getUserName() + "������ֽ�֧��"
				+ ctx.getMoney() + "Ԫ");
	}

}
