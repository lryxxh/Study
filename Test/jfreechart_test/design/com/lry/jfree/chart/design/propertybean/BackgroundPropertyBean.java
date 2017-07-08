package design.com.lry.jfree.chart.design.propertybean;

import java.awt.Color;
import java.awt.Image;
import java.awt.Paint;

/**
 * ��������.
 * @author LRY
 *
 */
public class BackgroundPropertyBean  implements PropertyBean{

	//�������,Ĭ��͸��.
	private int fillType = 0;
	
	//����ɫ���ɫ.
	private Paint bgColor = Color.GRAY;
	
	//������ɫ1.
	private Paint step_by_step_color1 = Color.GREEN;
	
	//������ɫ2.
	private Paint step_by_step_color2 = Color.RED;
	
	//������ɫ����,Ĭ�ϴ�����.
	private int step_by_step_type = 0;
	
	//����ͼƬ·��
	private Image bgImage = null;
	
	private LinePropertyBean lineProperty = new LinePropertyBean();
	
	public BackgroundPropertyBean() {
	}
	
	public BackgroundPropertyBean(int fillType, Paint bgColor, Paint step_by_step_color1,
			Paint step_by_step_color2, int step_by_step_type, Image iconPath,
			LinePropertyBean lineProperty) {
		super();
		this.fillType = fillType;
		this.bgColor = bgColor;
		this.step_by_step_color1 = step_by_step_color1;
		this.step_by_step_color2 = step_by_step_color2;
		this.step_by_step_type = step_by_step_type;
		this.bgImage = iconPath;
		this.lineProperty = lineProperty;
	}

	public BackgroundPropertyBean(int fillType, Paint bgColor, Paint step_by_step_color1,
			Paint step_by_step_color2, int step_by_step_type, Image iconPath,
			float[] dash, Paint borderLineColor, int borderLineWidth) {
		super();
		this.fillType = fillType;
		this.bgColor = bgColor;
		this.step_by_step_color1 = step_by_step_color1;
		this.step_by_step_color2 = step_by_step_color2;
		this.step_by_step_type = step_by_step_type;
		this.bgImage = iconPath;
		lineProperty.setLineColor(borderLineColor);
		lineProperty.setStrokeDash(dash);
		lineProperty.setLineWidth(borderLineWidth);
	}

	public int getFillType() {
		return fillType;
	}

	public void setFillType(int fillType) {
		this.fillType = fillType;
	}

	public Paint getBgColor() {
		return bgColor;
	}

	public void setBgColor(Paint bgColor) {
		this.bgColor = bgColor;
	}

	public Paint getStep_by_step_color1() {
		return step_by_step_color1;
	}

	public void setStep_by_step_color1(Paint step_by_step_color1) {
		this.step_by_step_color1 = step_by_step_color1;
	}

	public Paint getStep_by_step_color2() {
		return step_by_step_color2;
	}

	public void setStep_by_step_color2(Paint step_by_step_color2) {
		this.step_by_step_color2 = step_by_step_color2;
	}

	public int getStep_by_step_type() {
		return step_by_step_type;
	}

	public void setStep_by_step_type(int step_by_step_type) {
		this.step_by_step_type = step_by_step_type;
	}

	public Image getBGImage() {
		return bgImage;
	}

	public void setBGImage(Image iconPath) {
		this.bgImage = iconPath;
	}
	
	public void setLineProperty(LinePropertyBean lineProperty) {
		this.lineProperty = lineProperty;
	}
	
	public LinePropertyBean getLineProperty() {
		return lineProperty;
	}
	
	@Override
	public BackgroundPropertyBean clone() throws CloneNotSupportedException {
		return (BackgroundPropertyBean) super.clone();
	}
	
}
