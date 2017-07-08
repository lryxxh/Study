package curve.kd.mmi.curve.jfreechart.panel;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jfree.chart.CustomChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.Series;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.custom.CustomTimeSeries;

import agency.message.base.DomainManager;
import agency.message.base.KeyID;
import agency.message.midhs.MidhsCurveStaticFinals;
import curve.kd.mmi.curvechart.graphics.glanguage.GCurveConvertFromStr;
import curve.kd.mmi.curvechart.ui.dialog.CurveCalendarDialog;
import curve.kd.mmi.curvechart.util.CustomUtility;
import pictureeditor.SwingDialog;
import uiframework.widgets.keyidsearcher.view.SearchMainPanle;

/**
 * <p>
 * Title: CurveSourceBasePanel
 * </p>
 * <p>
 * Description: ������Ϣ��������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: kedong
 * </p>
 * 
 * @author mengxin
 * @version 1.0
 * @sign curve_update_116
 * @since 2008/01/08
 */

public class CurveSourcePanel extends JPanel {
//	private FigCurve figCurve = null;
//	private RecordSet recordSet = null; // @jve:decl-index=0:
	private boolean isCalCurve = false;

	private JLabel nameLabel = null;
	private JTextField idField = null;
	private JTextField descrField = null;
	private JTextField curveDateField = null;
	private JButton selectIdButton = null;
	private JButton selectDateButton = null;
	private JCheckBox visibleCheckBox = null;
	private JCheckBox deleteCheckBox = null;//ɾ����ѡ��
//	private Viewer viewer = null;
	private boolean isDelete = false; //�Ƿ���Ҫɾ��
	
//	CurveViewPanel viewPanel = null;
//	ChartSeriesAttribute seriesAttribute = null;
	CustomChartPanel chartPanel = null;
	private Series series = null;
//	CurveRefresherBean bean = null;
	/**
	 * This method initializes
	 * 
	 * @param figCurve
	 * 
	 * @param viewer
	 * 
	 * @modify curve_bug_11 mengxin 2008/08/25
	 */
	public CurveSourcePanel(Series series, CustomChartPanel chartPanel) {
		super();
		this.chartPanel = chartPanel;
		this.series = series;
//		if (figCurve instanceof FigCalCurve) {
//			setCalCurve(true);
//		}
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	protected void initialize(){
		String type = "1";
		if (series instanceof CustomTimeSeries) {
			type = ((CustomTimeSeries) series).getRefresherBean().getCurveType();
		}
		if (GCurveConvertFromStr.DYNAMIC_DATASOURCE_TYPE_XY.equals(type) || MidhsCurveStaticFinals.RTDB_SINGLE.equals(type)) {
			initializeXYType();
		} else if (type.equals(MidhsCurveStaticFinals.RTNET_RE_DAY_MR)
				|| type.equals(MidhsCurveStaticFinals.RTNET_RE_MONTH_MR)
				|| type.equals(MidhsCurveStaticFinals.RTNET_RE_YEAR_MR)
				|| type.equals(MidhsCurveStaticFinals.RAPS_SYS_RISK_DAY)) {
			initializeRTNETType();
		} else {
			initializeMidhsType();
		}
		
	};
	
	/**
	 * Midhs����Դ.
	 */
	private void initializeMidhsType() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// ����������Ҽ��
		gridBagConstraints.insets = new Insets(2, 3, 2, 3);
		this.setLayout(gridBagLayout);
		int gridX = 0;
		int gridY = 0;
		// �����������Label
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		this.add(getNameLabel(), gridBagConstraints);
		gridX += 1;

		// �������ID field
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(getIdField(), gridBagConstraints);
		gridX += 1;

		// ������߲������field
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(getDescrField(), gridBagConstraints);
		gridX += 1;

		// ��Ӹ��Ĳ��Button
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		add(getSelectIdButton(), gridBagConstraints);
		gridX += 1;

		// �����������Field
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(getCurveDateField(), gridBagConstraints);
		gridX += 1;

		// ��Ӹ������ڰ�ť
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		add(getSelectDateButton(), gridBagConstraints);
		gridX += 1;

		// ���"�ɼ�"��ѡ��
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		add(getVisibleCheckBox(), gridBagConstraints);
		gridX += 1;
		
		// ���"ɾ��"��ѡ��
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		add(getDeleteCheckBox(), gridBagConstraints);
		gridX = 0;
		gridY += 1;
	}
	
	/**
	 * xy����,����ʵʱ����.
	 */
	private void initializeXYType () {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// ����������Ҽ��
		gridBagConstraints.insets = new Insets(2, 3, 2, 3);
		this.setLayout(gridBagLayout);
		int gridX = 0;
		int gridY = 0;
		// �����������Label
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		this.add(getNameLabel(), gridBagConstraints);
		gridX += 1;

		// �����������Field
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(getCurveDateField(), gridBagConstraints);
		gridX += 1;

		// ��Ӹ������ڰ�ť
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		add(getSelectDateButton(), gridBagConstraints);
		gridX += 1;

		// ���"�ɼ�"��ѡ��
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		add(getVisibleCheckBox(), gridBagConstraints);

	}
	
	/**
	 * ״̬����.
	 */
	private void initializeRTNETType () {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// ����������Ҽ��
		gridBagConstraints.insets = new Insets(2, 3, 2, 3);
		this.setLayout(gridBagLayout);
		int gridX = 0;
		int gridY = 0;
		// �����������Label
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		this.add(getNameLabel(), gridBagConstraints);
		gridX += 1;

		// �����������Field
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(getCurveDateField(), gridBagConstraints);
		gridX += 1;

		// ��Ӹ������ڰ�ť
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		add(getSelectDateButton(), gridBagConstraints);
		gridX += 1;

		// ���"�ɼ�"��ѡ��
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 1.0;
		add(getVisibleCheckBox(), gridBagConstraints);
	}

	/**
	 * �õ���������Label
	 * 
	 * @return nameLabel
	 */
	protected JLabel getNameLabel() {
		if (nameLabel == null) {
			nameLabel = new JLabel();
			nameLabel.setText(series.getKey().toString());
			// nameLabel.setForeground(figCurve.getColor());
		}
		return nameLabel;
	}

	/**
	 * �õ���ʾ�������Ƶ�field
	 * 
	 * @return idField
	 */
	public JTextField getIdField() {
		if (idField == null) {
			idField = new JTextField(15);
			//ʱ����
			if (series instanceof CustomTimeSeries) {
				long keyID = ((CustomTimeSeries) series).getRefresherBean().getKeyid();
				idField.setText(String.valueOf(keyID));
			} else {
				//������
			}
			
			idField.setEditable(false);
		}
		return idField;
	}

	/**
	 * �õ���ʾ����������field
	 * 
	 * @return descrField
	 */
	public JTextField getDescrField() {
		if (descrField == null) {
			//ʱ����
			Long keyID = 0L;
			if (series instanceof CustomTimeSeries) {
				keyID = ((CustomTimeSeries) series).getRefresherBean().getKeyid();
			} else {
				//������
			}
			String descr = CustomUtility.getIDDescr(DomainManager.LOCAL_DOMAIN, keyID.toString());
			descr = "".equals(descr) ? series.getKey().toString() : descr;
			descrField = new JTextField(20);
			descrField.setText(descr);
			descrField.setEditable(false);
			descrField.setToolTipText(descr);
		}
		return descrField;
	}

	/**
	 * �õ���ʾ�������ڵ�field
	 * 
	 * @return curveDateField
	 */
	public JTextField getCurveDateField() {
		if (curveDateField == null) {
			curveDateField = new JTextField(15);
			curveDateField.setEditable(false);
			if (!isCalCurve()) {
				// ��ʾ��������
				long startTime = 0L;
				//ʱ����
				if (series instanceof CustomTimeSeries) {
					startTime = ((CustomTimeSeries) series).getRefresherBean().getStartTime();
				} else {
					//������
				}
				curveDateField.setText(CustomUtility.convertDateToDateFormatStr(
						"yyyy-MM-dd", new Date(startTime)));
				// curveDateField.setText(getRecordSet().getLoadCurveDate(true));
			}
		}
		return curveDateField;
	}

	/**
	 * �õ���������Id��ť
	 * 
	 * @return selectIdButton
	 */
	protected JButton getSelectIdButton() {
		if (selectIdButton == null) {
			selectIdButton = new JButton();
			selectIdButton.setIcon(support.CustomUtilities.arrowIcon);
			selectIdButton.setPreferredSize(new Dimension(40, 20));
			selectIdButton.setEnabled(!isCalCurve());
			selectIdButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectIdButton_ActionPerformed(e);
				}
			});
		}
		return selectIdButton;
	}

	/**
	 * ѡ������Ӧ����
	 * 
	 * @param e
	 */
	private void selectIdButton_ActionPerformed(ActionEvent e) {
		SearchMainPanle searchIdPanel = new SearchMainPanle();
		SwingDialog dialog = new SwingDialog((Frame) SwingUtilities
				.getAncestorOfClass(Frame.class, this), "������", true);
		dialog.getContentPane().add(searchIdPanel);
		dialog.pack();
		dialog.setAutoResize(false);
		dialog.show();

		KeyID keyid = searchIdPanel.getKeyID();
		String selectedId = keyid.getKeyID();
		if (selectedId != null && selectedId.length() != 0) {
			this.getIdField().setText(selectedId);
			String descr = CustomUtility.getIDDescr(DomainManager.LOCAL_DOMAIN, selectedId);
			this.getDescrField().setText(descr);
			this.getDescrField().setToolTipText(descr);
		}
	}

	/**
	 * �õ������������ڰ�ť
	 * 
	 * @return selectDateButton
	 */
	protected JButton getSelectDateButton() {
		if (selectDateButton == null) {
			selectDateButton = new JButton();
			selectDateButton.setIcon(support.CustomUtilities.arrowIcon);
			selectDateButton.setPreferredSize(new Dimension(40, 20));
			selectDateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectDateButton_actionPerformed(e);
				}
			});
		}
		return selectDateButton;
	}

	/**
	 * �õ�"�ɼ�"��ѡ��
	 * 
	 * @return visibleCheckBox
	 */
	protected JCheckBox getVisibleCheckBox() {
		if (visibleCheckBox == null) {
			visibleCheckBox = new JCheckBox("�ɼ�");
			visibleCheckBox.setSelected(isLengendVisiable());
			visibleCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					visibleCheckBox_actionPerformed(e);
				}
			});
		}
		return visibleCheckBox;
	}
	
	/**
	 * �õ�"ɾ��"��ѡ��
	 * 
	 * @return visibleCheckBox
	 */
	protected JCheckBox getDeleteCheckBox() {
		if (deleteCheckBox == null) {
			deleteCheckBox = new JCheckBox("ɾ��");
			deleteCheckBox.setSelected(false);
			deleteCheckBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setDelete(deleteCheckBox.isSelected());
				}
			});
		}
		return deleteCheckBox;
	}


	/**
	 * �������ڰ�ť��Ӧ����
	 * 
	 * @param e
	 */
	public void selectDateButton_actionPerformed(ActionEvent e) {
		// curve_bug_11 mengxin 2008/08/25
		//ʱ����
		long startTime = 0;
		if (series instanceof CustomTimeSeries) {
			startTime = ((CustomTimeSeries) series).getRefresherBean().getStartTime();
		} else {
			//������
		}
		Date date = CurveCalendarDialog.showDateDialog(this, "ѡ������",
				new Date(startTime), false, chartPanel, series);
		if (date == null) {
			return;
		}
		this.getCurveDateField().setText(
				CustomUtility.convertDateToDateFormatStr("yyyy-MM-dd", date));
	}

	/**
	 * "�ɼ�"��ѡ����Ӧ����
	 * 
	 * @param e
	 */
	public void visibleCheckBox_actionPerformed(ActionEvent e) {
		if (isLengendVisiable()) {
			setLengendVisiable(false);
		} else {
			setLengendVisiable(true);
		}
	}
	
	protected boolean isLengendVisiable() { 
		XYPlot plot = chartPanel.getChart().getXYPlot();
		boolean isShow = true;
		for (int i=0; i < plot.getDatasetCount(); i++) {
			XYDataset dataset = plot.getDataset(i);
			int index = dataset.indexOf(series.getKey());
			if (index != -1) {
				isShow = plot.getRenderer(i).isSeriesVisible(index);
				break;
			}else {
				continue;
			}
		}
		return isShow;
	}
	
	protected void setLengendVisiable(boolean isShow) { 
		XYPlot plot = chartPanel.getChart().getXYPlot();
		for (int i=0; i < plot.getDatasetCount(); i++) {
			XYDataset dataset = plot.getDataset(i);
			int index = dataset.indexOf(series.getKey());
			if (index != -1) {
				plot.getRenderer(i).setSeriesVisible(index, isShow);
				break;
			}else {
				continue;
			}
		}
	}

//	/**
//	 * ��������Դ����е�����Fig
//	 * 
//	 * @param figCurve
//	 */
//	protected void setSeriesAttribute(ChartSeriesAttribute seriesAttribute) {
//		this.seriesAttribute = seriesAttribute;
//	}
//
//	/**
//	 * �õ�����Դ����е�����Fig
//	 * 
//	 * @return figCurve
//	 */
//	protected ChartSeriesAttribute getSeriesAttribute() {
//		return seriesAttribute;
//	}

	/**
	 * ������������Դ
	 * 
	 * @param set
	 */
//	private void setRecordSet(RecordSet set) {
//		this.recordSet = set;
//	}

	/**
	 * ������������Դ
	 * 
	 * @return recordSet
	 */
//	private RecordSet getRecordSet() {
//		return recordSet;
//	}

	/**
	 * �����Ƿ�Ϊ�������߱�־
	 * 
	 * @param isCal
	 */
	protected void setCalCurve(boolean isCal) {
		this.isCalCurve = isCal;
	}

	/**
	 * �õ��Ƿ�Ϊ���߱�־
	 * 
	 * @return isCalCurve
	 */
	protected boolean isCalCurve() {
		return isCalCurve;
	}
	
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	public void setDeleteEnable(boolean isEnable){
		this.getDeleteCheckBox().setEnabled(isEnable);
	}
	
	public Series getSeries () {
		return series;
	}
	
} // @jve:decl-index=0:visual-constraint="10,10"
