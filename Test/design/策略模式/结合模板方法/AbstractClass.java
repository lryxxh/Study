package ����ģʽ.���ģ�巽��;

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
