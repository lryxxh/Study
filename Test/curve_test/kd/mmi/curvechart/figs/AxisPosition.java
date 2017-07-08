package kd.mmi.curvechart.figs;

public enum AxisPosition {
	
	TOP(1),
	BOTTOM(2),
	LEFT(3),
	RIGHT(4);
	
	private AxisPosition(int type){
		this.type = type;
	}
	
	public int type = 1;
	
	public static final int TYPE_TOP = 1;
	
	public static final int TYPE_BOTTOM = 2;
	
	public static final int TYPE_LEFT = 3;
	
	public static final int TYPE_RIGHT = 4;

}
