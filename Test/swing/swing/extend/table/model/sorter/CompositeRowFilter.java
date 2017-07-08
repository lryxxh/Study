package swing.extend.table.model.sorter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.RowFilter;

/**
 * @author HMI-Lenovo
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CompositeRowFilter extends RowFilter{
	
	//�洢�����������ϡ�
	public List<RowFilter> rowFilterList = new ArrayList<RowFilter>();
	
	/**
	 * ��ӹ�������
	 * @param rowFilter
	 */
	public void addRowFilter(RowFilter rowFilter) {
		if (null != rowFilter && !rowFilterList.contains(rowFilter)) {
			rowFilterList.add(rowFilter);
		}
	}
	
	/**
	 * �Ƴ���������
	 * @param rowFilter
	 */
	public void removeRowFilter(RowFilter rowFilter) {
		if(rowFilter != null && rowFilterList!= null && rowFilterList.contains(rowFilter)) {
			rowFilterList.remove(rowFilter);
		}
	}
	
	/**
	 * ������еĹ�������
	 */
	public void clear() {
		rowFilterList.clear();
	}

	@Override
	public boolean include(Entry entry) {
		boolean flag = true;
		//�������еĹ���������һ�������������������Ͳ���ʾ��
		for(RowFilter rowFilter : rowFilterList) {
			if (!rowFilter.include(entry)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

}
