package swing.extend.table.demo.ui;

import java.util.List;

import javax.swing.JTable;

import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.ui.AbstractRendererManager;
import swing.extend.table.ui.GlintRendererProcessor;

public class RendererManager extends AbstractRendererManager{

	public RendererManager(JTable table, List<ColumnIdentifier> columnIdentifiers) {
		super(table, columnIdentifiers);
	}

	@Override
	public void initRendererProcessor() {
		GlintRendererProcessor glintRendererProcessor = new GlintRendererProcessor();
		BackgroundRendererProcessor bgRendererProcessor = new BackgroundRendererProcessor();
		
		registerRendererProcessor(glintRendererProcessor);
		registerRendererProcessor(bgRendererProcessor);
	}

}
