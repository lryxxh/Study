package design.com.lry.jfree.chart.design.propertybean;

import java.awt.Color;
import java.awt.Font;

/**
 * ◊÷ÃÂ Ù–‘.
 * @author LRY
 *
 */
public class FontPropertyBean implements PropertyBean{
	
	private Font font = new Font("Dialog", Font.PLAIN, 12);
	
	private Color fontColor = Color.BLACK;
	
	private String fontName = "Dialog";
	
	private int fontStyle = Font.PLAIN;
	
	private float fontSize = 12;
	
	public FontPropertyBean() {
		this(new Font("Dialog", Font.PLAIN, 12));
	}

	public FontPropertyBean(Font font) {
		this(font, Color.BLACK);
	}

	public FontPropertyBean(Font font, Color fontColor) {
		this.font = font;
		this.fontColor = fontColor;
	}
	
	public FontPropertyBean(String fontName, int fontStyle, float fontSize, Color fontColor) {
		this.font = new Font(fontName, fontStyle, (int)fontSize);
		this.fontColor = fontColor;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
		this.fontName = font.getFamily();
		this.fontStyle = font.getStyle();
		this.fontSize = font.getSize();
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
		font = new Font(fontName, font.getStyle(), font.getSize());
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
		font = font.deriveFont(fontStyle);
	}

	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
		font = font.deriveFont(fontSize);
	}
	

}
