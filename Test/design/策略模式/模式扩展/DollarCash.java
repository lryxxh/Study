package ����ģʽ.ģʽ��չ;

/**
 * ��Ԫ�ֽ�֧��
 */
public class DollarCash implements PaymentStrategy {

	public void pay(PaymentContext ctx) {
		System.out.println("���ڸ�" + ctx.getUserName() + "��Ԫ�ֽ�֧��"
				+ ctx.getMoney() + "Ԫ");
	}
}
