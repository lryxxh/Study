package lang;
public class StringSpaceTabSplitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String linesssString= "192.1.120.6 ncg-scd2 switch-1 switch-2 switch-3 switch-4";

		String[] line = null;
		if (linesssString.contains(" ")) {
			line = linesssString.split(" ");
		} else if (linesssString.contains("\t")) {
			line = linesssString.split("\t");
		}

		if (line.length > 1) {
			String hostName = line[line.length - 1].trim();
			String hostAddr = line[0].trim();
			System.out.println(hostName + " " +  hostAddr);
		}

	}

}
