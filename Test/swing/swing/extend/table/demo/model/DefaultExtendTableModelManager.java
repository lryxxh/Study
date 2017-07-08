package swing.extend.table.demo.model;

import java.util.List;

import javax.swing.JTable;

import swing.extend.table.model.AbstractExtendTableModelManager;
import swing.extend.table.model.ExtendTableModel;
import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.model.parmeter.Parameter;
import swing.extend.table.model.process.DataProcessor;

/**
 * @author HMI-Lenovo
 * 默认的表格模型管理类.
 *
 */
@SuppressWarnings({"rawtypes"})
public class DefaultExtendTableModelManager extends AbstractExtendTableModelManager{

	public DefaultExtendTableModelManager(JTable table,
			List<ColumnIdentifier> identifiers, Parameter parameter, boolean isSort) {
		super(table, identifiers, parameter, isSort);
	}

	@Override
	protected void buildExtendTableModel(List<List> data,
			List<ColumnIdentifier> identifiers) {
		tableModel = new ExtendTableModel(data, identifiers);
	}

	@Override
	protected void initRequestDataManager() {
		requestDataManager = new RealtimeRequestDataManager(parameter);
	}

	@Override
	protected void initDataProcessor() {
		DataProcessor dataProcessor = new DateProcessor(super.identifiers, parameter);
		dataProcessorManager.registerProcess(dataProcessor);
	}

}
