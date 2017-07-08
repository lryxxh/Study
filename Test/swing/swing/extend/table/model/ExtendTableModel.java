package swing.extend.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import swing.extend.table.model.column.ColumnIdentifier;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ExtendTableModel extends DefaultTableModel {
	
	//
	private static final long serialVersionUID = -2611889438404260651L;
	
	private List<ColumnIdentifier> identifiers = null;

	public ExtendTableModel() {
	}
	
	public ExtendTableModel(List<List> data, List<ColumnIdentifier> identifiers ) {
		super(convertToVector(data), convertListToVector(identifiers));
	}
	
	/**
	 * 刷新表格数据，如果列数据没有变化，则只更新表格数据，否则更新表格的整个结构.
	 * @param data
	 * @param identifiers
	 */
	public void refreshDataVector(List<List> data, List<ColumnIdentifier> identifiers) {
		super.dataVector = convertToVector(data);
		if(!this.identifiers.equals(identifiers)) {
			super.columnIdentifiers = convertListToVector(identifiers);
			fireTableStructureChanged();
		} else {
			fireTableDataChanged();
		}
	}
	
	public void refreshDataVector(List<List> data) {
		refreshDataVector(data, null);
	}
	
	private static Vector convertListToVector(List data) {
		Vector vector = new Vector();
		vector.addAll(data);
		return vector;
	}
	
	private static Vector convertToVector(List<List> data) {
		Vector<Vector> tempVector = new Vector<Vector>();
		for(List rowData : data) {
			tempVector.add(convertListToVector(rowData));
		}
		return tempVector;
	}
}
