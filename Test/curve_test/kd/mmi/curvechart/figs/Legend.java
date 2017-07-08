package kd.mmi.curvechart.figs;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


import sun.swing.SwingUtilities2;

/**
 * 图例.
 * @author LRY
 *
 */
public class Legend extends JComponent{
	
	/**是否显示图例 */
	private boolean isShow = true;
	
	/** 图例横坐标 */
	private int x = 0;
	
	/** 图例纵坐标*/
	private int y = 0;
	
	/** 曲线集合*/
	private List<Series> seriesList = new ArrayList<Series>();
	
	/** 图标宽度*/
	private int ICON_WIDTH = 8;
	
	
	/**
	 * 构造方法.
	 * @param seriesList
	 */
	public Legend() {
		initComponent();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = 20;
		int height = 10;
		FontMetrics fontMetrics = g.getFontMetrics();
		height += fontMetrics.getHeight();
		for(Series series : seriesList) {
			width += fontMetrics.stringWidth(series.getCurveName());
			width += ICON_WIDTH;
			width += 30;
		}
		setBounds(this.x, this.y, width, height);
		revalidate();
		super.paintComponent(g);
	}
	
	public void addFigCurve(Series series) {
		seriesList.add(series);
		JLabel label = new LegendLabel(series);
		add(label);
	}
	
	private void initComponent() {
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		layout.setHgap(15);
		setLayout(layout);
		setBounds(20,20, 20, 20);
		
	}

	/**
	 * get isShow value
	 * @return the isShow
	 */
	public boolean isShow() {
		return isShow;
	}

	/**
	 * set isShow value
	 * @param isShow 
	 */
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * get x value
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * set x value
	 * @param x 
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * get y value
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * set y value
	 * @param y 
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	private class LegendLabel extends JLabel {
		Series series = null;
		CurveIcon icon = null;
		public LegendLabel(Series tempSeries) {
			this.series = tempSeries;
			icon = new CurveIcon(series.getColor());
			setIcon(icon);
			setForeground(series.getColor());
			setText(series.getCurveName());
			setFont(getFont());
			setIconTextGap(5);
			setHorizontalTextPosition(SwingConstants.RIGHT);
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					icon.setFill(!icon.isFill());
					repaint();
					series.setShow(icon.isFill());
				}
			});
		}
		
	}
	
	private class CurveIcon implements Icon {
		
		private Color color = Color.red;
		
		private boolean isFill = true;
		
		public CurveIcon(Color color) {
			this.color = color; 
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			g.setColor(color);
			if(isFill) {
				g.fillRect(x, y, getIconWidth(), getIconHeight());
			} else {
				g.drawRect(x, y, getIconWidth(), getIconHeight());
			}
		}

		@Override
		public int getIconWidth() {
			return ICON_WIDTH;
		}

		@Override
		public int getIconHeight() {
			return ICON_WIDTH;
		}
		
		public void setFill(boolean isFill) {
			this.isFill = isFill;
		}
		
		public boolean isFill() {
			return isFill;
		}
		
	}
	

}
