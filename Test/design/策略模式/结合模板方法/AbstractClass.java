package 策略模式.结合模板方法;

public abstract class AbstractClass implements Strategy {

	public void algorithmInterface() {
		stepOneOpe();
		stepTwoOpe();
		stepThreeOpe();
	}

	private void stepThreeOpe() {
	}

	protected abstract void stepOneOpe();

	protected abstract void stepTwoOpe();
}
