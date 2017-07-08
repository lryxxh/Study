package kd.mmi.curvechart.tools;

import java.awt.Font;

public class FontTool {

	public static Font createFont(String fontFamily, int fontWeight, int fontSize) {
		Font font = new Font(fontFamily, fontWeight, fontSize);
		return font;
	}

}
