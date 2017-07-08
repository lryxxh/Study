package design.com.lry.jfree.chart.design.factory;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("rawtypes")
public class FinalStaticFactory {
	
	/*start-----------------标签-------------------------------*/
	public static final String COLOR_START_LABEL = "起始颜色";
	public static final String COLOR_END_LABEL = "结束颜色";
	public static final String LEFT_TO_RIGHT_LABEL = "从左到右";
	public static final String RIGHT_TO_LEFT_LABEL = "从右到左";
	public static final String TOP_TO_BOTTOM_LABEL = "从上到下";
	public static final String BOTTOM_TO_UP_LABEL = "从下到上";
	public static final String COLOR_RADIATE_LABEL = "辐射";
	public static final String COLOR_FOCUS_LABEL = "集中";
	public static final String LEFT_TO_RIGHT_ACTOIN_COMMAND = "0";
	public static final String RIGHT_TO_LEFT_ACTOIN_COMMAND = "1";
	public static final String TOP_TO_BOTTOM_ACTOIN_COMMAND = "2";
	public static final String BOTTOM_TO_UP_ACTOIN_COMMAND = "3";
	public static final String COLOR_RADIATE_ACTOIN_COMMAND = "4";
	public static final String COLOR_FOCUS_ACTOIN_COMMAND = "5";
	public static final String COLOR_LABEL = "颜色";
	public static final String FILL_TYPE_LABEL = "填充方式";
	public static final String IMAGE_LABEL = "图片";
	public static final String SELECT_LABEL = "选择";
	public static final String LINE_TYPE_LABEL = "线型";
	public static final String LINE_COLOR_LABEL = "线色";
	public static final String LINE_WIDTH_LABEL = "线宽";
	public static final String PREVIEW_LABEL = "预览";
	public static final String MAIN_GRID_LINE_H_LABEL = "水平主网格";
	public static final String MAIN_GRID_LINE_V_LABEL = "垂直主网格";
	public static final String SUB_GRID_LINE_H_LABEL = "水平副网格";
	public static final String SUB_GRID_LINE_V_LABEL = "垂直副网格";
	public static final String OK_BUTTON_LABEL = "确定";
	public static final String CANCEL_BUTTON_LABEL = "取消";
	public static final String FONT_BOLD_STYLE_LABEL = "粗体";
	public static final String FONT_ITALIC_STYLE_LABEL = "斜体";
	public static final String NAME_LABEL = "名称";
	public static final String SIZE_LABEL = "大小";
	public static final String FONT_TEST = "AaBbCc中文字体";
	public static final String FIX_GRADUATE_LABEL = "固定刻度";
	public static final String AUTO_GRADUATE_LABEL = "自适应刻度";
	public static final String LIMIT_GRADUATE_LABEL = "限值内自适应";
	public static final String INCREASE_GRADUATE_LABEL = "增量自适应";
	public static final String MIN_LABEL = "下限";
	public static final String MAX_LABEL = "上限";
	public static final String MAIN_SPACE_LABEL = "主刻度数";
	public static final String SUB_SPACE_LABEL = "副刻度数";
	public static final String UP_BALANCE_LABEL = "上浮比例";
	public static final String DOWN_BALANCE_LABEL = "下浮比例";
	public static final String DECIMAL_LABEL = "小数位数";
	public static final String INCREASE_BALANCE_LABEL = "增量比例";
	public static final String COMMON_LABEL = "通用";
	public static final String BACKGROUND_LABEL = "背景";
	public static final String GRID_LINE_LABEL = "网格";
	public static final String CURVE_LABEL = "曲线";
	public static final String REFRESH_CYCLE_LABEL = "刷新周期";
	public static final String SECOND_LABEL = "秒";
	public static final String BACKGROUND_WIDTH_LABEL = "背景宽度";
	public static final String BACKGROUND_HEIGHT_LABEL = "背景高度";
	public static final String LEFT_LOCATION_LABEL = "左侧";
	public static final String RIGHT_LOCATIOIN_LABEL = "右侧";
	/*-------------------------------------------------------end*/
	
	/*start-----------------------线型--------------------------------*/
	public static final String LINE_SOLID_TYPE = "实线";
	public static final String LINE_DASH_TYPE = "虚线";
	public static final String LINE_DOT_TYPE = "点线";
	public static final String LINE_DASH_DOT_TYPE = "点划线-.-";
	public static final String LINE_DASH_DOT_DOT_TYPE = "点划线-..-";
	public static float[] LINE_SOLID_TYPE_DASH = null;
	public static float[] LINE_DASH_TYPE_DASH = new float[] { 4.0f, 4.0f };
	public static float[] LINE_DOT_TYPE_DASH = new float[] { 1.0f, 2.0f };
	public static float[] LINE_DASH_DOT_TYPE_DASH = new float[] { 4.0f, 2.0f, 1.0f, 2.0f };
	public static float[] LINE_DASH_DOT_DOT_TYPE_DASH = new float[] { 4.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f };
	public static String[] LINE_STYPE_ARRAY = new String[]{LINE_SOLID_TYPE, LINE_DASH_TYPE, LINE_DOT_TYPE, LINE_DASH_DOT_TYPE, LINE_DASH_DOT_DOT_TYPE} ;
	/*----------------------------------------------------------------end*/
	
	/*start-----------------填充方式-------------------------------*/
	public static final String FILL_SINGLE_BG_COLOR = "单颜色";
	public static final String FILL_STEP_BY_STEP_BG_COLOR = "渐进颜色";
	public static final String FILL_IMAGE_BG_COLOR = "图片";
	public static final String FILL_TRANSPAREND_BG_COLOR = "透明";
	public static final int FILL_SINGLE_BG_COLOR_NO = 0;
	public static final int FILL_STEP_BY_STEP_BG_COLOR_NO = 1;
	public static final int FILL_IMAGE_BG_COLOR_NO = 2;
	public static final int FILL_TRANSPAREND_BG_COLOR_NO = 3;
	/*-----------------------------------------------------------end*/
	
	/* start--------------------颜色-------------------------*/
	public static final String BLACK_COLOR = "Black";
	public static final String BLUE_COLOR = "Blue";
	public static final String CYAN_COLOR = "Cyan";
	public static final String DARK_GRAY_COLOR = "Dark_Gray";
	public static final String GRAY_COLOR = "Gray";
	public static final String GREEN_COLOR = "Green";
	public static final String LIGHT_GRAY_COLOR = "Light_Gray";
	public static final String MAGENTA_COLOR = "Magenta";
	public static final String ORANGE_COLOR = "Orange";
	public static final String PINK_COLOR = "Pink";
	public static final String RED_COLOR = "Red";
	public static final String WHITE_COLOR = "White";
	public static final String YELLOW_COLOR = "Yellow";
	public static final String USER_DEFINE_COLOR = "自定义";
	/* --------------------------------------------------- end*/
	
	private FinalStaticFactory() {};
	
	
	/**
	 * 得到颜色对应的map.
	 * @return
	 */
	public static HashMap<String, Color> getColorHashMap() {
		HashMap<String, Color> colorMap = new HashMap<String, Color>();
		colorMap.put(BLACK_COLOR, Color.BLACK);
		colorMap.put(BLUE_COLOR, Color.BLUE);
		colorMap.put(CYAN_COLOR, Color.CYAN);
		colorMap.put(DARK_GRAY_COLOR, Color.DARK_GRAY);
		colorMap.put(GRAY_COLOR, Color.GRAY);
		colorMap.put(GREEN_COLOR, Color.GREEN);
		colorMap.put(LIGHT_GRAY_COLOR, Color.LIGHT_GRAY);
		colorMap.put(MAGENTA_COLOR, Color.MAGENTA);
		colorMap.put(ORANGE_COLOR, Color.ORANGE);
		colorMap.put(PINK_COLOR, Color.PINK);
		colorMap.put(RED_COLOR, Color.RED);
		colorMap.put(WHITE_COLOR, Color.WHITE);
		colorMap.put(YELLOW_COLOR, Color.YELLOW);
		colorMap.put(USER_DEFINE_COLOR, Color.BLACK);
		return colorMap;
	}
	
	public static ArrayList<Color> getColorList() { 
		ArrayList<Color> list = new ArrayList<Color>(); 
		list.add(Color.BLACK);
		list.add(Color.BLUE);
		list.add(Color.CYAN);
		list.add(Color.DARK_GRAY);
		list.add(Color.GRAY);
		list.add(Color.GREEN);
		list.add(Color.LIGHT_GRAY);
		list.add(Color.MAGENTA);
		list.add(Color.ORANGE);
		list.add(Color.PINK);
		list.add(Color.RED);
		list.add(Color.WHITE);
		list.add(Color.YELLOW);
		return list;
	}
	
	/**
	 * 得到颜色map对应的list.
	 * @return
	 */
	public static List<HashMap> getColorModelData() {
		List<HashMap> list = new ArrayList<HashMap>();
		HashMap<String, Color> blackMap = new HashMap<String, Color>();
		blackMap.put(BLACK_COLOR, Color.BLACK);
		list.add(blackMap);
		
		HashMap<String, Color> blueMap = new HashMap<String, Color>();
		blueMap.put(BLUE_COLOR, Color.BLUE);
		list.add(blueMap);
		
		HashMap<String, Color> cyanMap = new HashMap<String, Color>();
		cyanMap.put(CYAN_COLOR, Color.CYAN);
		list.add(cyanMap);
		
		HashMap<String, Color> darkGraMap = new HashMap<String, Color>();
		darkGraMap.put(DARK_GRAY_COLOR, Color.DARK_GRAY);
		list.add(darkGraMap);
		
		HashMap<String, Color> grayMap = new HashMap<String, Color>();
		grayMap.put(GRAY_COLOR, Color.GRAY);
		list.add(grayMap);
		
		HashMap<String, Color> greenMap = new HashMap<String, Color>();
		greenMap.put(GREEN_COLOR, Color.GREEN);
		list.add(greenMap);
		
		HashMap<String, Color> lightGrayMap = new HashMap<String, Color>();
		lightGrayMap.put(LIGHT_GRAY_COLOR, Color.LIGHT_GRAY);
		list.add(lightGrayMap);
		
		HashMap<String, Color> magentaMap = new HashMap<String, Color>();
		magentaMap.put(MAGENTA_COLOR, Color.MAGENTA);
		list.add(magentaMap);
		
		HashMap<String, Color> orgngeMap = new HashMap<String, Color>();
		orgngeMap.put(ORANGE_COLOR, Color.ORANGE);
		list.add(orgngeMap);
		
		HashMap<String, Color> pinkMap = new HashMap<String, Color>();
		pinkMap.put(PINK_COLOR, Color.PINK);
		list.add(pinkMap);
		
		HashMap<String, Color> redMap = new HashMap<String, Color>();
		redMap.put(RED_COLOR, Color.RED);
		list.add(redMap);
		
		HashMap<String, Color> whiteMap = new HashMap<String, Color>();
		whiteMap.put(WHITE_COLOR, Color.WHITE);
		list.add(whiteMap);
		
		HashMap<String, Color> yellowMap = new HashMap<String, Color>();
		yellowMap.put(YELLOW_COLOR, Color.YELLOW);
		list.add(yellowMap);
		
		HashMap<String, Color> userDefineMap = new HashMap<String, Color>();
		userDefineMap.put(USER_DEFINE_COLOR, Color.BLACK);
		list.add(userDefineMap);
		return list;
	}
	
	public static String[] getFontNameList() {
		String[] fontNames = null;
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = environment.getAllFonts();
		fontNames = new String[fonts.length];
		int i = 0;
		for (Font font : fonts) {
			fontNames[i] = font.getFamily();
			i++;
		}
		return fontNames;
	}
	

	/**
	 * 得到线型对应的map.
	 * @return
	 */
	public static String[] getLineTypeArray() {
		return LINE_STYPE_ARRAY.clone();
	}

}
