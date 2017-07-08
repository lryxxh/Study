package 外观模式.example5;

public class FacadeFactory {
	private FacadeFactory() {

	}

	public static FacadeApi createFacadeApi() {
		return new Facade();
	}
}
