package 策略模式.模式扩展;

/**
 * 美元现金支付
 */
public class DollarCash implements PaymentStrategy {

	public void pay(PaymentContext ctx) {
		System.out.println("现在给" + ctx.getUserName() + "美元现金支付"
				+ ctx.getMoney() + "元");
	}
}
