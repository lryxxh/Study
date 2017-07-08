package 备忘录模式.结合命令模式;

public class SubstractCommand extends AbstractCommand {

	private int opeNum;

	public SubstractCommand(int opeNum) {
		this.opeNum = opeNum;
	}

	public void execute() {
		this.operation.substract(opeNum);
	}

}
