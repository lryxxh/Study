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
	
	//存储过滤条件集合。
	public List<RowFilter> rowFilterList = new ArrayList<RowFilter>();
	
	/**
	 * 添加过滤器。
	 * @param rowFilter
	 */
	public void addRowFilter(RowFilter rowFilter) {
		if (null != rowFilter && !rowFilterList.contains(rowFilter)) {
			rowFilterList.add(rowFilter);
		}
	}
	
	/**
	 * 移除过滤器。
	 * @param rowFilter
	 */
	public void removeRowFilter(RowFilter rowFilter) {
		if(rowFilter != null && rowFilterList!= null && rowFilterList.contains(rowFilter)) {
			rowFilterList.remove(rowFilter);
		}
	}
	
	/**
	 * 清楚所有的过滤器。
	 */
	public void clear() {
		rowFilterList.clear();
	}

	@Override
	public boolean include(Entry entry) {
		boolean flag = true;
		//便利所有的过滤器，有一个过滤器不满足条件就不显示。
		for(RowFilter rowFilter : rowFilterList) {
			if (!rowFilter.include(entry)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

}
