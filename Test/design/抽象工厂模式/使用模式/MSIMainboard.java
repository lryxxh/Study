package 抽象工厂模式.使用模式;

/**
 * 微星的主板
 */
public class MSIMainboard implements MainboardApi {
	/**
	 * CPU插槽的孔数
	 */
	private int cpuHoles = 0;

	/**
	 * 构造方法，传入CPU插槽的孔数
	 * 
	 * @param cpuHoles
	 *            CPU插槽的孔数
	 */
	public MSIMainboard(int cpuHoles) {
		this.cpuHoles = cpuHoles;
	}

	public void installCPU() {
		System.out.println("now in MSIMainboard,cpuHoles=" + cpuHoles);
	}
}
