package design.com.lry.jfree.chart.design.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

@SuppressWarnings("rawtypes")
public class MapTypeComboxModel extends AbstractListModel implements MutableComboBoxModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3725508481598435090L;
	
	
	private List<HashMap> list = new ArrayList<HashMap>();
	
	Object selectItem = null;

	public MapTypeComboxModel(List<HashMap> list) {
		if (null == list) {
			list = new ArrayList<HashMap>();
		}
		this.list = list;
		setSelectedIndex(0);
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public Object getElementAt(int index) {
		if(index != -1 && null != list) {
			return list.get(index);
		}
		return null;
	}

	@Override
	public Object getSelectedItem() {
		return selectItem;
	}
	
	private void setSelectedIndex(int index) {
		setSelectedItem(list.get(index));
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selectItem = anItem;
		fireContentsChanged(this, -1, -1);
	}

	@Override
	public void addElement(Object obj) {
		this.list.add((HashMap) obj);
	}

	@Override
	public void removeElement(Object obj) {
		this.list.remove((HashMap) obj);
	}

	@Override
	public void insertElementAt(Object obj, int index) {
		this.list.add(index, (HashMap) obj);
	}

	@Override
	public void removeElementAt(int index) {
		this.list.remove(index);
	}
	
	
	
}