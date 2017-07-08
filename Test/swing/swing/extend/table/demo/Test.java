package swing.extend.table.demo;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import swing.extend.table.demo.model.DefaultExtendTableModelManager;
import swing.extend.table.demo.model.RealtimeParameter;
import swing.extend.table.demo.ui.DefaultExtendTableCellRenderer;
import swing.extend.table.demo.ui.RendererManager;
import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.model.parmeter.Parameter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		
		JScrollPane scrollPane = new JScrollPane();
		JTable table = new JTable();
		
		List<ColumnIdentifier> identifiers = new ArrayList<ColumnIdentifier>();
		ColumnIdentifier identifier = new ColumnIdentifier();
		identifier.setEn_Name("key");
		identifier.setCh_Name("LongID");
		identifier.setClazz(Long.class);
		identifiers.add(identifier);
		
		identifier = new ColumnIdentifier();
		identifier.setEn_Name("id");
		identifier.setCh_Name("进程编号");
		identifier.setClazz(Integer.class);
		identifiers.add(identifier);
		
		identifier = new ColumnIdentifier();
		identifier.setEn_Name("node_name");
		identifier.setCh_Name("节点名");
		identifier.setClazz(String.class);
		identifiers.add(identifier);
		
		identifier = new ColumnIdentifier();
		identifier.setEn_Name("proc_pid");
		identifier.setCh_Name("进程id");
		identifier.setClazz(Integer.class);
		identifiers.add(identifier);
		
		identifier = new ColumnIdentifier();
		identifier.setEn_Name("startup_time");
		identifier.setCh_Name("启动时间");
		identifier.setClazz(Date.class);
		identifiers.add(identifier);
		
		RealtimeParameter parameter = new RealtimeParameter();
		parameter.setDbid("local,null,realtime,public,sysmng,null");
		parameter.setTableName("sys_proc_status_info");
		
		DefaultExtendTableModelManager modelManager = new DefaultExtendTableModelManager(table, identifiers, parameter, false);
		RendererManager rendererManager = new RendererManager(table, identifiers);
		DefaultExtendTableCellRenderer renderer = new DefaultExtendTableCellRenderer();
		renderer.setRendererManager(rendererManager);
		table.setDefaultRenderer(Object.class, renderer);
		for(int i = 0; i< table.getColumnCount();i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
