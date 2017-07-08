package swing.extend.table.ui;

import java.awt.Component;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import swing.extend.table.model.column.ColumnIdentifier;

/**
 *  �����Ⱦ����.
 * @author HMI-Lenovo
 *
 */
public abstract class AbstractRendererManager {
	
	//���
	private JTable table = null;
	
	//�Ƿ���˸
	private boolean glint = false;
	
	//��˸�Ƿ���ͣ
	private boolean pause = false;
	
	//ͬ����
	private Object lock = new Object();
	
	//��ģ������
	private List<ColumnIdentifier> columnIdentifiers = new ArrayList<ColumnIdentifier>();
	
	//�洢��˸�ı��Ԫ��
	private List<ColumnIdentifier> glintCells = new ArrayList<ColumnIdentifier>();
	
	//��Ⱦ������
	private List<RendererProcessor> rendererProcessors = new ArrayList<RendererProcessor>();
	
	public AbstractRendererManager(JTable table, List<ColumnIdentifier> columnIdentifiers) {
		this.table = table;
		this.columnIdentifiers = columnIdentifiers;
		initRendererProcessor();
	}
	
	/**
	 * ��ʼ����Ԫ����Ⱦ��.
	 */
	public abstract void initRendererProcessor();
	
	/**
	 * ע����Ⱦ������.
	 */
	public void registerRendererProcessor(RendererProcessor processor) {
		rendererProcessors.add(processor);
	}
	
	/**
	 * �ؼ�����.
	 * @param component		��Ӧ����Ⱦ�����
	 * @param table			���
	 * @param value			�������
	 * @param row			�к�
	 * @param column		�к�
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
	 * ��ʼ��˸.
	 */
	public void startGlint() {
		//������˸�߳�
		glint();
	}
	
	/**
	 * ֹͣ��˸
	 */
	public void stopGlint() {
		setGlint(false);
	}
	
	/**
	 * ��ͣ��˸.
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
	 * ��˸.
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
