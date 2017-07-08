package curve.kd.mmi.curve.jfreechart.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import agency.MidLayerAccessException;
import agency.exception.DataFaultException;
import agency.exception.LocatorException;
import agency.exception.ProxyFaultException;
import agency.exception.ServiceConnectionException;
import agency.message.base.DataUnit;
import agency.message.base.DomainManager;
import agency.message.base.MenuTool;
import agency.message.midhs.MidhsData;
import agency.message.midhs.MidhsService;
import agency.message.perm.PermStaticFinals;
import context.Context;
import login.LoginCtrl;
import login.MMIUser;
import support.CustomFileFilter;
import support.DataClient;
import support.custominfo.PrivInfoSet;
import uiframework.uiDialogs.uiPlainDialog.UIPlainDialogPanel;

public class CurveListPanel extends UIPlainDialogPanel {
	
    private String type = null;
	private String curveID = null;
	private String curveDateStr = null;
	private SimpleDateFormat dateFormat = null;

	private JPanel panel2 = null;
	private JPanel curveInfoPanel = null;
	private JTable totalTable = new JTable();

	private JLabel curveInfoLabel = null;
	// chenguang 20080317 add
	// Ϊ��������ʾ��ӱ�Ҫ�Ŀؼ�
	private JSplitPane splitPane1 = null;
	private JSplitPane splitPane2 = new JSplitPane();
	private JSplitPane splitPane3 = new JSplitPane();
	private JSplitPane splitPane4 = new JSplitPane();
	private JSplitPane splitPane5 = new JSplitPane();
	private JScrollPane scrollPane1 = new JScrollPane();
	private JScrollPane scrollPane2 = new JScrollPane();
	private JScrollPane scrollPane3 = new JScrollPane();
	private JScrollPane scrollPane4 = new JScrollPane();
	private JScrollPane scrollPane5 = new JScrollPane();
	private JScrollPane scrollPane6 = new JScrollPane();
	private DefaultTableModel tableModel1 = new DefaultTableModel();
	private JTable table1 = new JTable();
	private DefaultTableModel tableModel2 = new DefaultTableModel();
	private JTable table2 = new JTable();
	private DefaultTableModel tableModel3 = new DefaultTableModel();
	private JTable table3 = new JTable();
	private DefaultTableModel tableModel4 = new DefaultTableModel();
	private JTable table4 = new JTable();
	private DefaultTableModel tableModel5 = new DefaultTableModel();
	private JTable table5 = new JTable();
	private DefaultTableModel tableModel6 = new DefaultTableModel();
	private JTable table6 = new JTable();
	private Vector row1 = new Vector();// ��һ������޸ļ�¼
	private Vector row2 = new Vector();// �ڶ�������޸ļ�¼
	private Vector row3 = new Vector();// ����������޸ļ�¼
	private Vector row4 = new Vector();// ���ĸ�����޸ļ�¼
	private Vector row5 = new Vector();// ���������޸ļ�¼
	private Vector row6 = new Vector();// ����������޸ļ�¼
	private Vector head = null;// ��ͷ // @jve:decl-index=0:
	private Vector data = null;// ���е����� // @jve:decl-index=0:

	int iResidue = 0;

	/**
	 * 
	 * @param viewer TODO
	 * @param type
	 * @param id
	 * @param dateStr
	 * @param dateFormat
	 *            TODO
	 */
	public CurveListPanel(String type, String id, String dateStr, String dateFormat) {
		super();
		this.setCurveID(id);
		this.setCurveDateStr(dateStr);
		this.type = type;
		this.dateFormat = new SimpleDateFormat(dateFormat);
		initialize();
	}

	/**
	 * This method inializes this
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setSize(1200, 675);
		//this.setLocation(0, 0);
		this.add(this.getCurveInfoPanel(), BorderLayout.NORTH);
		this.add(getSplitPane1(), BorderLayout.CENTER);

		// chenguang 20080317 add
		// �����޸ĵ���
		tableModel1.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getLastRow() != -1) {
					boolean flag = true;
					String right = String.valueOf(e.getLastRow());
					for (int i = 0; i < row1.size(); i++) {
						if (row1.get(i).toString().equals(right)) {
							flag = false;
							break;
						}
					}
					if (flag) {
						row1.addElement(String.valueOf(e.getLastRow()));
					}
				}
			}
		});

		tableModel2.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getLastRow() != -1) {
					boolean flag = true;
					String right = String.valueOf(e.getLastRow());
					for (int i = 0; i < row2.size(); i++) {
						if (row2.get(i).toString().equals(right)) {
							flag = false;
							break;
						}
					}
					if (flag) {
						row2.addElement(String.valueOf(e.getLastRow()));
					}
				}
			}
		});

		tableModel3.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getLastRow() != -1) {
					boolean flag = true;
					String right = String.valueOf(e.getLastRow());
					for (int i = 0; i < row3.size(); i++) {
						if (row3.get(i).toString().equals(right)) {
							flag = false;
							break;
						}
					}
					if (flag) {
						row3.addElement(String.valueOf(e.getLastRow()));
					}
				}
			}
		});

		tableModel4.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getLastRow() != -1) {
					boolean flag = true;
					String right = String.valueOf(e.getLastRow());
					for (int i = 0; i < row4.size(); i++) {
						if (row4.get(i).toString().equals(right)) {
							flag = false;
							break;
						}
					}
					if (flag) {
						row4.addElement(String.valueOf(e.getLastRow()));
					}
				}
			}
		});

		tableModel5.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getLastRow() != -1) {
					boolean flag = true;
					String right = String.valueOf(e.getLastRow());
					for (int i = 0; i < row5.size(); i++) {
						if (row5.get(i).toString().equals(right)) {
							flag = false;
							break;
						}
					}
					if (flag) {
						row5.addElement(String.valueOf(e.getLastRow()));
					}
				}
			}
		});

		tableModel6.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getLastRow() != -1) {
					boolean flag = true;
					String right = String.valueOf(e.getLastRow());
					for (int i = 0; i < row6.size(); i++) {
						if (row6.get(i).toString().equals(right)) {
							flag = false;
							break;
						}
					}
					if (flag) {
						row6.addElement(String.valueOf(e.getLastRow()));
					}
				}
			}
		});
		// chenguang end
	}

	private JPanel getPanel2() {
		if (panel2 == null) {
			panel2 = new JPanel();
			panel2.setLayout(new BorderLayout());
			panel2.setPreferredSize(new Dimension(400, 300));
		}
		return panel2;
	}

	private JPanel getCurveInfoPanel() {
		if (curveInfoPanel == null) {
			curveInfoPanel = new JPanel();
			curveInfoPanel.setLayout(new BorderLayout());
			curveInfoPanel.setMaximumSize(new Dimension(900, 30));
			curveInfoPanel.setPreferredSize(new Dimension(900, 30));
			curveInfoPanel.setMinimumSize(new Dimension(900, 30));
			curveInfoPanel.add(this.getCurveInfoLabel(), BorderLayout.CENTER);
		}
		return curveInfoPanel;
	}

	private JSplitPane getSplitPane1() {
		if (splitPane1 == null) {
			splitPane1 = new JSplitPane();
			splitPane1.add(scrollPane1, JSplitPane.LEFT);
			splitPane1.add(splitPane2, JSplitPane.RIGHT);
			splitPane1.setDividerSize(2);
			splitPane1.setDividerLocation(this.getWidth() / 6);
			splitPane1.setContinuousLayout(true);
		}
		return splitPane1;
	}

	private JLabel getCurveInfoLabel() {
		if (curveInfoLabel == null) {
			//modify by liurenyong 20110808 begin
			curveInfoLabel = new JLabel("  ���ID  " + getIDDescr(DomainManager.getLocalDomain(),this.getCurveID())
					+ "  ����    " + this.getCurveDateStr());
			//modify by liurenyong 20110808 end
		}
		return curveInfoLabel;
	}
	
	/**
	 * ȡ����
	 * @param id
	 * 
	 * @sign curve_bj_1
	 * @author mengxin
	 * @since 2006/03/22
	 */
	public static String getIDDescr(String domain, String id) {
		String sql = "select dev_name||field_name from hisdb.sys_info_scada_analog@orahis where data_id = '"
				+ id + "'";
		String descr = "";
		try {
			Vector<Vector<DataUnit>> result = DataClient.RDBCommand(domain,
					Context.REALTIME, sql, null);
			if (result != null && result.size() != 0) {
				descr = result.get(0).get(0).toString();
			} else {
				System.err.println(domain + "ID" + id + "������û��ȡ����!!");
			}
		} catch (MidLayerAccessException ex) {
			ex.printStackTrace();
		}
		return descr;
	}

	private void setCurveID(String curveID) {
		this.curveID = curveID;
	}

	private String getCurveID() {
		return curveID;
	}

	private void setCurveDateStr(String curveDate) {
		this.curveDateStr = curveDate;
	}

	private String getCurveDateStr() {
		return curveDateStr;
	}

	public String getConfirmButtonText() {
		return "��������";
	}

	/*
	 * ���ܣ����ݵ��� chenguang 20080317 add
	 */
	public void confirmActionProformed(ActionEvent e) {
		if (data != null && data.size() != 0) {
			JFileChooser fileChooser = new JFileChooser();
			CustomFileFilter filterTXT = new CustomFileFilter();
			filterTXT.addExtension("txt");
			filterTXT.setDescription("�ı��ļ�(�Ʊ���ָ�)");

			CustomFileFilter filterCSV = new CustomFileFilter();
			filterCSV.addExtension("csv");
			filterCSV.setDescription("CSV(���ŷָ�)");

			CustomFileFilter filterXLS = new CustomFileFilter();
			filterXLS.addExtension("xls");
			filterXLS.setDescription("Microsoft Office Excel ������");

			fileChooser.addChoosableFileFilter(filterTXT);
			fileChooser.addChoosableFileFilter(filterCSV);
			fileChooser.addChoosableFileFilter(filterXLS);
			if (JFileChooser.APPROVE_OPTION != fileChooser.showSaveDialog(this)) {
				return;
			}

			String filePath = fileChooser.getSelectedFile().getPath();
			// �Զ�����չ��
			FileFilter filter = fileChooser.getFileFilter();
			String extension = CustomFileFilter.getExtension(fileChooser
					.getSelectedFile());
			if (extension == null || extension.length() == 0) {
				if (filter == filterTXT) {
					// ѡ���ı��ļ�
					filePath += ".txt";
				} else if (filter == filterCSV) {
					// ѡ��CSV�ļ�
					filePath += ".csv";
				} else if (filter == filterXLS) {
					// ѡ��excel�ļ�
					filePath += ".xls";
				} else {
					// Ĭ��Ϊ�ı��ļ�
					filePath += ".txt";
				}

			}

			File outFile = new File(filePath);
			if (outFile.isFile()) {
				if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(
						this, "�ļ�" + outFile.getName() + "�Ѵ��ڣ��Ƿ񸲸ǣ�", "ȷ���滻",
						JOptionPane.YES_NO_OPTION)) {
					return;
				}
			}

			if (filePath.endsWith(".xls")) {
				// Excel�ļ�
				exportExcelFile(filePath);
			} else {
				// �ı�����CSV�ļ�
				exportTextFile(filePath);
			}
		}

	}

	public String getApplyButtonText() {
		return "��������";
	}

	/**
	 * ��������
	 * 
	 * @param e
	 */
	public void applyActionProformed(ActionEvent e) {
		MMIUser user = LoginCtrl.getInstance().getLoginMMIUser();
		int funcID = PrivInfoSet.getPrivInfo(PermStaticFinals.SAMPLE_MODIFY).getFuncID();
	
		Vector<DataUnit> result = null;
		try {
			result = DataClient.hasGivenFunc(user.getUserName(), null, null, "", DataClient.getLocalHostName(), null,
					false, funcID);
		} catch (DataFaultException e1) {
			e1.printStackTrace();
		} catch (ServiceConnectionException e1) {
			e1.printStackTrace();
		} catch (LocatorException e1) {
			e1.printStackTrace();
		} catch (ProxyFaultException e1) {
			e1.printStackTrace();
		}

		if (result != null && result.size() > 0) 
		{
			
			int returnCode = (int) result.get(0).getLongData();
			if (returnCode == PermStaticFinals.P_PERMIT) 
			{
				
				////������û����Ȩ�޿���֮ǰ�Ĵ���

				// if (!canModify()) {
				// JOptionPane.showMessageDialog(this, "��û���޸���ʷ���ݵ�Ȩ�ޣ�", "��ʾ",
				// JOptionPane.INFORMATION_MESSAGE);
				// return;
				// }
				// ���ã��޸ĵ�Ԫ�����ݺ󣬲���Ҫ�س����ߵ��������Ԫ����ܵõ���ǰ����
				totalTable.editCellAt(-1, -1);
				table1.editCellAt(-1, -1);
				table2.editCellAt(-1, -1);
				table3.editCellAt(-1, -1);
				table4.editCellAt(-1, -1);
				table5.editCellAt(-1, -1);
				table6.editCellAt(-1, -1);

				Vector<MidhsData> updateData = new Vector<MidhsData>();
				if (row1.size() != 0) {
					for (int i = 0; i < row1.size(); i++) {
						int logical = Integer.parseInt(row1.get(i).toString());
						long modifyTime = -1;
						try {
							modifyTime = this.dateFormat.parse(
									this.getCurveDateStr() + " "
											+ table1.getValueAt(logical, 0).toString())
									.getTime();
							
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						float modifyValue = new Float(table1.getValueAt(logical, 1).toString());
						
						String diaplayQuality = table1.getValueAt(logical, 2).toString();
						String[] displayArray = diaplayQuality.split("/");
						int quality = 0;
						for (String str : displayArray) {
							int actual = MenuTool.getActualValue(DomainManager.LOCAL_DOMAIN, "ң��״̬", str);
							quality = quality + (int)Math.pow(2, actual-1);
						}
						if (MenuTool.maskTrue(quality, 22) == false) {
							quality = quality + 2097152;
						}
						
						MidhsData curveData = new MidhsData();
						curveData.setValidFlag(0);
						curveData.setQuality(quality);
						curveData.setXValue(modifyTime);
						curveData.setYValue(modifyValue);
						updateData.add(curveData);
					}
					row1.removeAllElements();
				}

				// chenguang 20080417 add
				// ��������
				if (row2.size() != 0) {
					for (int i = 0; i < row2.size(); i++) {
						int logical = Integer.parseInt(row2.get(i).toString());
						long modifyTime = -1;
						try {
							modifyTime = this.dateFormat.parse(
									this.getCurveDateStr() + " "
											+ table2.getValueAt(logical, 0).toString())
									.getTime();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						float modifyValue = new Float(table2.getValueAt(logical, 1)
								.toString());
						
						String diaplayQuality = table2.getValueAt(logical, 2).toString();
						String[] displayArray = diaplayQuality.split("/");
						int quality = 0;
						for (String str : displayArray) {
							int actual = MenuTool.getActualValue(DomainManager.LOCAL_DOMAIN, "ң��״̬", str);
							quality = quality + (int)Math.pow(2, actual-1);
						}
						if (MenuTool.maskTrue(quality, 22) == false) {
							quality = quality + 2097152;
						}

						MidhsData curveData = new MidhsData();
						curveData.setValidFlag(0);
						curveData.setQuality(quality);
						curveData.setXValue(modifyTime);
						curveData.setYValue(modifyValue);
						updateData.add(curveData);
					}
					row2.removeAllElements();
				}

				if (row3.size() != 0) {
					for (int i = 0; i < row3.size(); i++) {
						int logical = Integer.parseInt(row3.get(i).toString());
						long modifyTime = -1;
						try {
							modifyTime = this.dateFormat.parse(
									this.getCurveDateStr() + " "
											+ table3.getValueAt(logical, 0).toString())
									.getTime();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						float modifyValue = new Float(table3.getValueAt(logical, 1)
								.toString());
						
						String diaplayQuality = table3.getValueAt(logical, 2).toString();
						String[] displayArray = diaplayQuality.split("/");
						int quality = 0;
						for (String str : displayArray) {
							int actual = MenuTool.getActualValue(DomainManager.LOCAL_DOMAIN, "ң��״̬", str);
							quality = quality + (int)Math.pow(2, actual-1);
						}
						if (MenuTool.maskTrue(quality, 22) == false) {
							quality = quality + 2097152;
						}

						MidhsData curveData = new MidhsData();
						curveData.setValidFlag(0);
						curveData.setQuality(quality);
						curveData.setXValue(modifyTime);
						curveData.setYValue(modifyValue);
						updateData.add(curveData);
					}
					row3.removeAllElements();
				}

				if (row4.size() != 0) {
					for (int i = 0; i < row4.size(); i++) {
						int logical = Integer.parseInt(row4.get(i).toString());
						long modifyTime = -1;
						try {
							modifyTime = this.dateFormat.parse(
									this.getCurveDateStr() + " "
											+ table4.getValueAt(logical, 0).toString())
									.getTime();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						float modifyValue = new Float(table4.getValueAt(logical, 1)
								.toString());

						String diaplayQuality = table4.getValueAt(logical, 2).toString();
						String[] displayArray = diaplayQuality.split("/");
						int quality = 0;
						for (String str : displayArray) {
							int actual = MenuTool.getActualValue(DomainManager.LOCAL_DOMAIN, "ң��״̬", str);
							quality = quality + (int)Math.pow(2, actual-1);
						}
						if (MenuTool.maskTrue(quality, 22) == false) {
							quality = quality + 2097152;
						}
						
						MidhsData curveData = new MidhsData();
						curveData.setValidFlag(0);
						curveData.setQuality(quality);
						curveData.setXValue(modifyTime);
						curveData.setYValue(modifyValue);
						updateData.add(curveData);
					}
					row4.removeAllElements();
				}

				if (row5.size() != 0) {
					for (int i = 0; i < row5.size(); i++) {
						int logical = Integer.parseInt(row5.get(i).toString());
						long modifyTime = -1;
						try {
							modifyTime = this.dateFormat.parse(
									this.getCurveDateStr() + " "
											+ table5.getValueAt(logical, 0).toString())
									.getTime();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						float modifyValue = new Float(table5.getValueAt(logical, 1)
								.toString());
						
						String diaplayQuality = table5.getValueAt(logical, 2).toString();
						String[] displayArray = diaplayQuality.split("/");
						int quality = 0;
						for (String str : displayArray) {
							int actual = MenuTool.getActualValue(DomainManager.LOCAL_DOMAIN, "ң��״̬", str);
							quality = quality + (int)Math.pow(2, actual-1);
						}
						if (MenuTool.maskTrue(quality, 22) == false) {
							quality = quality + 2097152;
						}

						MidhsData curveData = new MidhsData();
						curveData.setValidFlag(0);
						curveData.setQuality(quality);
						curveData.setXValue(modifyTime);
						curveData.setYValue(modifyValue);
						updateData.add(curveData);
					}
					row5.removeAllElements();
				}

				if (row6.size() != 0) {
					for (int i = 0; i < row6.size(); i++) {
						int logical = Integer.parseInt(row6.get(i).toString());
						long modifyTime = -1;
						try {
							modifyTime = this.dateFormat.parse(
									this.getCurveDateStr() + " "
											+ table6.getValueAt(logical, 0).toString())
									.getTime();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						float modifyValue = new Float(table6.getValueAt(logical, 1)
								.toString());
						
						String diaplayQuality = table6.getValueAt(logical, 2).toString();
						String[] displayArray = diaplayQuality.split("/");
						int quality = 0;
						for (String str : displayArray) {
							int actual = MenuTool.getActualValue(DomainManager.LOCAL_DOMAIN, "ң��״̬", str);
							quality = quality + (int)Math.pow(2, actual-1);
						}
						if (MenuTool.maskTrue(quality, 22) == false) {
							quality = quality + 2097152;
						}

						MidhsData curveData = new MidhsData();
						curveData.setValidFlag(0);
						curveData.setQuality(quality);
						curveData.setXValue(modifyTime);
						curveData.setYValue(modifyValue);
						updateData.add(curveData);
					}
					row6.removeAllElements();
				}
				
				try {
					MidhsService.updateCurveData(DomainManager.LOCAL_DOMAIN, null, this.type, new Long(this.getCurveID()), updateData);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DataFaultException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ServiceConnectionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LocatorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ProxyFaultException e1) {
					e1.printStackTrace();
					return;
				}
				
//				viewer.startDataEngine();
//				viewer.damageAll();
//				viewer.repaintBuffer();
			
                   ////			������û����Ȩ�޿���֮ǰ�Ĵ���
			} else {
				String message = PermStaticFinals.getCodeString(returnCode);
				JOptionPane.showMessageDialog(null, message, "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	public String getCancelButtonText() {
		return "�˳�";
	}
	
	// add rentao 20091026 ���Ӽ���������ʷ���ݲ쿴
	public void displayData(Vector curveData) {
		data = curveData;
		String[] columnNames = { "ʱ��", "ֵ" ,"״̬"};
		head = new Vector(Arrays.asList(columnNames));
		buildTable(curveData);
		totalTable.setRowHeight(20);
		totalTable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		JScrollPane dntableScrollPane = new JScrollPane(totalTable);
		getPanel2().add(dntableScrollPane, BorderLayout.CENTER);
	}

	/**
	 * ���ܣ�������񣨷�������Ļ����ʾ��
	 * 
	 * @param displayData
	 *            ��ʾ������
	 * @param data
	 *            ��ѯ�������е�����
	 * @param head
	 *            ��ͷ chenguang 20080317 add
	 * 
	 * @modify ����displayData���� scadaoper_update_2 mengxin 2008/10/14
	 */
	private void buildTable(Vector displayData) {
		Vector vTableData1 = new Vector();
		Vector vTableData2 = new Vector();
		Vector vTableData3 = new Vector();
		Vector vTableData4 = new Vector();
		Vector vTableData5 = new Vector();
		Vector vTableData6 = new Vector();

		int iTableSize = 0;
		int iSumScreen = 0;

		if (displayData.size() > 288) {
			iTableSize = displayData.size() / 6;
			iSumScreen = 6;
		} else {
			iTableSize = 48;
			iResidue = displayData.size() % 48;
			if (iResidue != 0) {
				for (int i = 0; i < (48 - iResidue); i++) {
					Vector v = new Vector();
					v.setSize(2);
					displayData.add(v);
				}
			}
			iSumScreen = displayData.size() / 48;
		}

		if (iSumScreen > 0) {
			for (int i = 0; i < iTableSize; i++) {
				Vector vTemp = (Vector) displayData.get(i);
				vTableData1.add(vTemp);
			}
		}
		if (iSumScreen > 1) {
			for (int i = iTableSize; i < iTableSize * 2; i++) {
				Vector vTemp = (Vector) displayData.get(i);
				vTableData2.add(vTemp);
			}
		}
		if (iSumScreen > 2) {
			for (int i = iTableSize * 2; i < iTableSize * 3; i++) {
				Vector vTemp = (Vector) displayData.get(i);
				vTableData3.add(vTemp);
			}
		}
		if (iSumScreen > 3) {
			for (int i = iTableSize * 3; i < iTableSize * 4; i++) {
				Vector vTemp = (Vector) displayData.get(i);
				vTableData4.add(vTemp);
			}
		}
		if (iSumScreen > 4) {
			for (int i = iTableSize * 4; i < iTableSize * 5; i++) {
				Vector vTemp = (Vector) displayData.get(i);
				vTableData5.add(vTemp);
			}
		}
		if (iSumScreen > 5) {
			for (int i = iTableSize * 5; i < displayData.size(); i++) {
				Vector vTemp = (Vector) displayData.get(i);
				vTableData6.add(vTemp);
			}
		}
		tableModel1.setDataVector(vTableData1, head);
		table1.setModel(tableModel1);
		table1.setRowHeight(18);
		table1.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane1.getViewport().removeAll();
		scrollPane1.getViewport().add(table1);

		tableModel2.setDataVector(vTableData2, head);
		table2.setModel(tableModel2);
		table2.setRowHeight(18);
		table2.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane2.getViewport().removeAll();
		scrollPane2.getViewport().add(table2);

		tableModel3.setDataVector(vTableData3, head);
		table3.setModel(tableModel3);
		table3.setRowHeight(18);
		table3.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane3.getViewport().removeAll();
		scrollPane3.getViewport().add(table3);

		tableModel4.setDataVector(vTableData4, head);
		table4.setModel(tableModel4);
		table4.setRowHeight(18);
		table4.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane4.getViewport().removeAll();
		scrollPane4.getViewport().add(table4);

		tableModel5.setDataVector(vTableData5, head);
		table5.setModel(tableModel5);
		table5.setRowHeight(18);
		table5.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane5.getViewport().removeAll();
		scrollPane5.getViewport().add(table5);

		tableModel6.setDataVector(vTableData6, head);
		table6.setModel(tableModel6);
		table6.setRowHeight(18);
		table6.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane6.getViewport().removeAll();
		scrollPane6.getViewport().add(table6);

		splitPane2.add(scrollPane2, JSplitPane.LEFT);
		splitPane2.add(splitPane3, JSplitPane.RIGHT);

		splitPane3.add(scrollPane3, JSplitPane.LEFT);
		splitPane3.add(splitPane4, JSplitPane.RIGHT);

		splitPane4.add(scrollPane4, JSplitPane.LEFT);
		splitPane4.add(splitPane5, JSplitPane.RIGHT);

		splitPane5.add(scrollPane5, JSplitPane.LEFT);
		splitPane5.add(scrollPane6, JSplitPane.RIGHT);

		splitPane2.setDividerSize(2);
		splitPane3.setDividerSize(2);
		splitPane4.setDividerSize(2);
		splitPane5.setDividerSize(2);

		splitPane2.setDividerLocation(this.getWidth() / 6);
		splitPane3.setDividerLocation(this.getWidth() / 6);
		splitPane4.setDividerLocation(this.getWidth() / 6);
		splitPane5.setDividerLocation(this.getWidth() / 6);

		splitPane2.setContinuousLayout(true);
		splitPane3.setContinuousLayout(true);
		splitPane4.setContinuousLayout(true);
		splitPane5.setContinuousLayout(true);

		if (iSumScreen == 2) {
			scrollPane1
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			JScrollBar bar2 = scrollPane2.getVerticalScrollBar();
			bar2.addAdjustmentListener(new AdjustmentListener() {
				public void adjustmentValueChanged(AdjustmentEvent e) {
					scrollPane1.getVerticalScrollBar().setValue(
							scrollPane2.getVerticalScrollBar().getValue());
				}
			});
		} else if (iSumScreen == 3) {
			scrollPane1
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane2
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			JScrollBar bar3 = scrollPane3.getVerticalScrollBar();

			bar3.addAdjustmentListener(new AdjustmentListener() {
				public void adjustmentValueChanged(AdjustmentEvent e) {
					scrollPane1.getVerticalScrollBar().setValue(
							scrollPane3.getVerticalScrollBar().getValue());
					scrollPane2.getVerticalScrollBar().setValue(
							scrollPane3.getVerticalScrollBar().getValue());
				}
			});
		} else if (iSumScreen == 4) {
			scrollPane1
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane2
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane3
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			JScrollBar bar4 = scrollPane4.getVerticalScrollBar();

			bar4.addAdjustmentListener(new AdjustmentListener() {
				public void adjustmentValueChanged(AdjustmentEvent e) {
					scrollPane1.getVerticalScrollBar().setValue(
							scrollPane4.getVerticalScrollBar().getValue());
					scrollPane2.getVerticalScrollBar().setValue(
							scrollPane4.getVerticalScrollBar().getValue());
					scrollPane3.getVerticalScrollBar().setValue(
							scrollPane4.getVerticalScrollBar().getValue());
				}
			});
		} else if (iSumScreen == 5) {
			scrollPane1
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane2
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane3
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane4
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

			JScrollBar bar5 = scrollPane5.getVerticalScrollBar();

			bar5.addAdjustmentListener(new AdjustmentListener() {
				public void adjustmentValueChanged(AdjustmentEvent e) {
					scrollPane1.getVerticalScrollBar().setValue(
							scrollPane5.getVerticalScrollBar().getValue());
					scrollPane2.getVerticalScrollBar().setValue(
							scrollPane5.getVerticalScrollBar().getValue());
					scrollPane3.getVerticalScrollBar().setValue(
							scrollPane5.getVerticalScrollBar().getValue());
					scrollPane4.getVerticalScrollBar().setValue(
							scrollPane5.getVerticalScrollBar().getValue());

				}
			});
		} else if (iSumScreen == 6) {
			scrollPane1
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane2
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane3
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane4
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane5
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			JScrollBar bar6 = scrollPane6.getVerticalScrollBar();

			bar6.addAdjustmentListener(new AdjustmentListener() {
				public void adjustmentValueChanged(AdjustmentEvent e) {
					scrollPane1.getVerticalScrollBar().setValue(
							scrollPane6.getVerticalScrollBar().getValue());
					scrollPane2.getVerticalScrollBar().setValue(
							scrollPane6.getVerticalScrollBar().getValue());
					scrollPane3.getVerticalScrollBar().setValue(
							scrollPane6.getVerticalScrollBar().getValue());
					scrollPane4.getVerticalScrollBar().setValue(
							scrollPane6.getVerticalScrollBar().getValue());
					scrollPane5.getVerticalScrollBar().setValue(
							scrollPane6.getVerticalScrollBar().getValue());
				}
			});
		}
	}

	public static boolean canModify() {
		login.MMIUser user = login.LoginCtrl.getInstance().getLoginMMIUser();
		if (user == null) {
			return false;
		}
		boolean f = user.hasOperationPerm("�����޸�");
		return f;
	}

	public String getTitle() {
		return "��������";
	}

	/**
	 * ����������ݱ�Ϊexcel����ļ�
	 * 
	 * @param filePath
	 *            String ����ļ���
	 */
	private void exportExcelFile(String filePath) {
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			HSSFWorkbook workBook = new HSSFWorkbook(); // ����������
			HSSFSheet workSheet = workBook.createSheet(); // ����������
			HSSFRow row = null; // ��
			HSSFCell column = null; // ��
			HSSFCellStyle cellStyleTitle = workBook.createCellStyle(); // �����еĵ�Ԫ����
			HSSFCellStyle cellStyleSheetText = workBook.createCellStyle(); // �����ı��ĵ�Ԫ����
			HSSFCellStyle cellStyleSheetNumber = workBook.createCellStyle(); // �������ֵĵ�Ԫ����
			HSSFDataFormat dataFormat = workBook.createDataFormat();
			HSSFFont fontTitle = workBook.createFont(); // ��������

			fontTitle.setFontHeightInPoints((short) 12);
			// fontTitle.setColor( (short) 0xc);
			fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFFont fontCell = workBook.createFont(); // ��������
			fontCell.setFontHeightInPoints((short) 9);

			cellStyleTitle.setFont(fontTitle);
			cellStyleTitle.setDataFormat(dataFormat.getFormat("#,##0.0"));

			cellStyleSheetText.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyleSheetText.setDataFormat(HSSFDataFormat
					.getBuiltinFormat("text"));
			cellStyleSheetText.setFont(fontCell);

			cellStyleSheetNumber.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyleSheetNumber.setDataFormat(dataFormat.getFormat("#,##0.0"));
			cellStyleSheetNumber.setFont(fontCell);

			workBook.setSheetName(0, "worksheet1");

			// д�����ͷ
			row = workSheet.createRow((short) 0);
			for (short cellnum = (short) 0; cellnum < head.size(); cellnum++) {
				column = row.createCell(cellnum);
				column.setCellStyle(cellStyleTitle);
				column.setEncoding(HSSFCell.ENCODING_UTF_16);
				column.setCellValue(head.get(cellnum).toString());
				short colWidth = (short) (table1.getColumnModel().getColumn(
						cellnum).getPreferredWidth() * 2 * 22);

				workSheet.setColumnWidth(cellnum, colWidth);

			}
			// д������
			short rownum;
			// add rentao 20091027 ��������е�����
			Vector v = data;// tableModel1.getDataVector();
			for (rownum = (short) 0; rownum < v.size(); rownum++) {
				row = workSheet.createRow(rownum + 1);
				Vector vTemp = (Vector) v.get(rownum);
				for (short cellnum = (short) 0; cellnum < head.size(); cellnum++) {
					column = row.createCell(cellnum);
					column.setCellStyle(cellStyleSheetText);
					column.setEncoding(HSSFCell.ENCODING_UTF_16);
					String sTemp = (String) vTemp.get(cellnum);
					column.setCellValue(sTemp);
					short colWidth = (short) (table1.getColumnModel()
							.getColumn(cellnum).getPreferredWidth() * 2 * 22);
					workSheet.setColumnWidth(cellnum, colWidth);
				}
			}
			/*
			 * for (rownum = (short) 0; rownum < data.size(); rownum++) { row =
			 * workSheet.createRow(rownum + 1); Vector vTemp = (Vector)
			 * data.get(rownum); for (short cellnum = (short) 0; cellnum <
			 * head.size(); cellnum++) { column = row.createCell(cellnum);
			 * column.setCellStyle(cellStyleSheetText);
			 * column.setEncoding(HSSFCell.ENCODING_UTF_16); String sTemp =
			 * (String) vTemp.get(cellnum); column.setCellValue(sTemp); short
			 * colWidth = (short) (table1.getColumnModel()
			 * .getColumn(cellnum).getPreferredWidth() * 2 * 22);
			 * workSheet.setColumnWidth(cellnum, colWidth); } }
			 */
			// end rentao 20091027 ��������е�����
			rownum++;
			rownum++;

			workBook.write(out);
			out.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex2) {
			ex2.printStackTrace();
		}
	}

	/**
	 * �����ı��ļ���������Ϊ���ŷָ����ı��ļ�(.csv)���Ʊ���ָ����ı��ļ�(txt)����
	 * 
	 * @param filePath
	 *            String �ļ���
	 */
	private void exportTextFile(String filePath) {
		try {
			FileWriter writer = new FileWriter(filePath);
			char delimiter = '\t'; // Ĭ�ϵ��зָ���
			if (filePath.endsWith(".csv")) {
				delimiter = ',';
			}
			StringBuffer sbfH = new StringBuffer(); // д���ͷ

			for (int i = 0; i < head.size(); i++) {
				sbfH.append(head.get(i).toString()).append(delimiter);

			}
			writer.write(sbfH.append("\r\n").toString());
			/** ����������д���ı�* */
			StringBuffer sbf = new StringBuffer();
			for (int i = 0; i < data.size(); i++) {
				Vector vTemp = (Vector) data.get(i);
				for (int j = 0; j < head.size(); j++) {
					String sTemp = vTemp.get(j).toString();
					sbf.append(sTemp).append(delimiter);
				}
				sbf.append("\r\n");
			}
			writer.write(sbf.toString());
			writer.close();
		} catch (IOException ex3) {
			ex3.printStackTrace();
		}

	}
} // @jve:decl-index=0:visual-constraint="8,8"
