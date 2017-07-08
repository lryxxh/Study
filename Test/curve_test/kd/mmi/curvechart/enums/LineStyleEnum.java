package kd.mmi.curvechart.enums;

import kd.mmi.curvechart.figs.FinalStaticFactory;

/**
 * ÏßÐÍÃ¶¾Ù.
 * @author LRY
 *
 */
public enum LineStyleEnum {
	
	LINE_SOLID_TYPE(0, FinalStaticFactory.LINE_SOLID_TYPE, FinalStaticFactory.LINE_SOLID_TYPE_DASH),
	LINE_DASH_TYPE(1, FinalStaticFactory.LINE_DASH_TYPE, FinalStaticFactory.LINE_DASH_TYPE_DASH),
	LINE_DOT_TYPE(2, FinalStaticFactory.LINE_DOT_TYPE, FinalStaticFactory.LINE_DOT_TYPE_DASH),
	LINE_DASH_DOT_TYPE(3, FinalStaticFactory.LINE_DASH_DOT_TYPE, FinalStaticFactory.LINE_DASH_DOT_TYPE_DASH),
	LINE_DASH_DOT_DOT_TYPE(4, FinalStaticFactory.LINE_DASH_DOT_DOT_TYPE, FinalStaticFactory.LINE_DASH_DOT_DOT_TYPE_DASH);
	LineStyleEnum(int no, String name, float[] dash) {
		this.no = no;
		this.dash = dash;
		this.name = name;
	}

	private float[] dash = FinalStaticFactory.LINE_SOLID_TYPE_DASH;
	private String name  = FinalStaticFactory.LINE_SOLID_TYPE;
	private int no = 0;
	
	public static float[] getDashByName(String name) {
		float[] dash = FinalStaticFactory.LINE_SOLID_TYPE_DASH;
		if (FinalStaticFactory.LINE_DASH_TYPE.equals(name)) {
			dash = LINE_DASH_TYPE.dash;
		} else if (FinalStaticFactory.LINE_DOT_TYPE.equals(name)) {
			dash = LINE_DOT_TYPE.dash;
		} else if (FinalStaticFactory.LINE_DASH_DOT_TYPE.equals(name)) {
			dash = LINE_DASH_DOT_TYPE.dash;
		} else if (FinalStaticFactory.LINE_DASH_DOT_DOT_TYPE.equals(name)) {
			dash = LINE_DASH_DOT_DOT_TYPE.dash;
		}
		return dash;
	}
	
	public static String getNameByDash(float[] dash) {
		String name = FinalStaticFactory.LINE_SOLID_TYPE;
		if (FinalStaticFactory.LINE_DASH_TYPE_DASH.equals(dash)) {
			name = LINE_DASH_TYPE.name;
		} else if (FinalStaticFactory.LINE_DOT_TYPE_DASH.equals(dash)) {
			name = LINE_DOT_TYPE.name;
		} else if (FinalStaticFactory.LINE_DASH_DOT_TYPE_DASH.equals(dash)) {
			name = LINE_DASH_DOT_TYPE.name;
		} else if (FinalStaticFactory.LINE_DASH_DOT_DOT_TYPE_DASH.equals(dash)) {
			name = LINE_DASH_DOT_DOT_TYPE.name;
		}
		return name;
	}
	
	public static LineStyleEnum getEnumByNo(int no) {
		LineStyleEnum style =  LINE_SOLID_TYPE;
		switch (no) {
		case 1:
			style = LINE_DASH_TYPE;
			break;
		case 2:
			style = LINE_DOT_TYPE;
			break;
		case 3:
			style = LINE_DASH_DOT_TYPE;
			break;
		case 4:
			style = LINE_DASH_DOT_DOT_TYPE;
			break;
		default:
			style = LINE_SOLID_TYPE;
			break;
		}
		return style;
	}
	
	@Override
	public String toString() {
		return this.name + this.no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float[] getDash() {
		return dash;
	}
	
	public void setDash(float[] dash) {
		this.dash = dash;
	}
	
}
