package swing.extend.table.ui;

import java.awt.Component;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import swing.extend.table.model.column.ColumnIdentifier;

/**
 *  表格渲染管理.
 * @author HMI-Lenovo
 *
 */
public abstract class AbstractRendererManager {
	
	//表格
	private JTable table = null;
	
	//是否闪烁
	private boolean glint = false;
	
	//闪烁是否暂停
	private boolean pause = false;
	
	//同步锁
	private Object lock = new Object();
	
	//列模型数据
	private List<ColumnIdentifier> columnIdentifiers = new ArrayList<ColumnIdentifier>();
	
	//存储闪烁的表格单元格
	private List<ColumnIdentifier> glintCells = new ArrayList<ColumnIdentifier>();
	
	//渲染器处理
	private List<RendererProcessor> rendererProcessors = new ArrayList<RendererProcessor>();
	
	public AbstractRendererManager(JTable table, List<ColumnIdentifier> columnIdentifiers) {
		this.table = table;
		this.columnIdentifiers = columnIdentifiers;
		initRendererProcessor();
	}
	
	/**
	 * 初始化单元格渲染器.
	 */
	public abstract void initRendererProcessor();
	
	/**
	 * 注册渲染处理器.
	 */
	public void registerRendererProcessor(RendererProcessor processor) {
		rendererProcessors.add(processor);
	}
	
	/**
	 * 控件处理.
	 * @param component		对应的渲染器组件
	 * @param table			表格
	 * @param value			表格数据
	 * @param row			行号
	 * @param column		列号
	 */
	public void processRenderer(Component component, JTable table, Object value, int row, int column) {
		for(RendererProcessor processor : rendererProcessors) {
			if(processor.isFilterProcessor(table, value, row, column)) {
				processor.process(component, columnIdentifiers.get(column), table, value, row, column);
			}
		}
	}
	
	private synchronized void setPause(boolean pause) {
		this.pause = pause;
	}
	
	private synchronized boolean isPause() {
		return pause;
	}
	
	private synchronized void setGlint(boolean glint) {
		this.glint = glint;
	}
	
	private synchronized boolean isGlint() {
		return glint;
	}
	
	/**
	 * 开始闪烁.
	 */
	public void startGlint() {
		//启动闪烁线程
		glint();
	}
	
	/**
	 * 停止闪烁
	 */
	public void stopGlint() {
		setGlint(false);
	}
	
	/**
	 * 暂停闪烁.
	 */
	public void pauseGlint() {
		synchronized (lock) {
			setPause(true);
		}
	}
	
	public void restoreGline() {
		setPause(false);
		synchronized (lock) {
			lock.notify();
		}
	}
	
	/**
	 * 闪烁.
	 */
	public void glint() {
		new Thread() {
			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			public void run() {
				while(isGlint()) {
					if (isPause()) {
						synchronized (lock) {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					
					while(!isPause() && isGlint()) {
						Rectangle rectangle = table.getVisibleRect();
						double minx = 0;
						double maxx = 0;
						double miny = rectangle.getMinY();
						double maxy = rectangle.getMaxY();
						Rectangle cellRectangel = null;
						for(ColumnIdentifier identifier : glintCells) {
							cellRectangel = table.getCellRect(0, columnIdentifiers.indexOf(identifier), false);
							minx = cellRectangel.getMinX();
							maxx = cellRectangel.getMaxX();
							cellRectangel = new Rectangle((int)minx, (int)miny, (int)(maxx - minx), (int)(maxy - miny));
							table.repaint(cellRectangel);
						}
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			};
		}.start();
		
	}

}
