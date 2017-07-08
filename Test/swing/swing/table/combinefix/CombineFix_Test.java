/**
 * JTableSorter_Test.java
 * Created by liurenyong at 2013-8-14
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing.table.combinefix;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * 
 * @author liurenyong 2013-8-14
 */
public class CombineFix_Test extends JFrame {

    /**  */
    private static final long serialVersionUID = 3945571109105196987L;

    private String[] fixColumns = null;

    private String[][] fixDatas = null;

    private String[] moveColumns = null;

    private String[][] moveDatas = null;


    /**
     * 
     * @author liurenyong 2013-8-14
     */
    public CombineFix_Test() {
        init();
    }

    /**
     * 
     * @author liurenyong 2013-8-14
     */
    private void init() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initData();
        this.initComponent();
        pack();
        this.setVisible(true);
    }

    /**
     * 
     * @author liurenyong 2013-8-14
     */
    private void initComponent() {
        CombineFixTable table = new CombineFixTable(fixColumns, fixDatas, moveColumns, moveDatas);
        this.getContentPane().add(table);
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        
    }

    /**
     * 
     * @author liurenyong 2013-8-13
     */
    private void initData() {
        int fixColumn = 5;
        int moveColumn = 10;
        int fixRow = 20;
        int moveRow = 20;
        fixColumns = new String[fixColumn];
        moveColumns = new String[moveColumn];
        fixDatas = new String[fixRow][fixColumn];
        moveDatas = new String[moveRow][moveColumn];

        setTableColumn(fixColumns, "Fix Column ", fixColumn);
        setTableColumn(moveColumns, "Move Column ", moveColumn);

        setTableData(fixDatas, "Fix", fixRow, fixColumn);
        setTableData(moveDatas, "Move", moveRow, moveColumn);

    }


    private void setTableColumn(String[] columns, String columnName, int column) {
        for (int i = 0; i < column; i++) {
            columns[i] = columnName + (i + 1);
        }
    }

    private void setTableData(String[][] datas, String columnName, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                datas[i][j] = columnName + "_data_" + Math.random() * 100;
            }
        }
    }

    
    public static void main(String[] args) {
        new CombineFix_Test();
    }

}
