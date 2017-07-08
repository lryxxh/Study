package 工厂方法模式.example1;

public class Client {
	public static void main(String[] args) {
		ExportOperate operate = new ExportOperate();
		operate.export(1, "要导出的测试数据");
	}
}
