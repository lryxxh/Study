package 备忘录模式.结合命令模式;

public class AddCommand extends AbstractCommand {

	private int opeNum;

	public AddCommand(int opeNum) {
		this.opeNum = opeNum;
	}

	public void execute() {
		this.operation.add(opeNum);
	}
}
