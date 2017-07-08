package tool;

public class PrintMemory {

	public static void printMemory() {
		float totalMemory = Float.parseFloat(""
				+ (Runtime.getRuntime().totalMemory())) / 1024 / 1024;
		float maxMemeory = Float.parseFloat(""
				+ (Runtime.getRuntime().maxMemory())) / 1024 / 1024;
		float freeMemory = Float.parseFloat(""
				+ (Runtime.getRuntime().freeMemory())) / 1024 / 1024;
		float useMemory = (totalMemory - freeMemory);
		System.out.println("  " + " maxMemory:" + maxMemeory
				+ "m  , totalMemory:" + totalMemory + "m  , useMemory:"
				+ useMemory + "m  , freeMemory:" + freeMemory +"m");

	}
}
