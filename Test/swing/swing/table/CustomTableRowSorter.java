package swing.table;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CustomTableRowSorter extends TableRowSorter {
    
    int offset = 0;
    JTable table = null;

    /**
     * 
     * @author liurenyong 2013-8-15
     */
    public CustomTableRowSorter(TableModel model) {
        super(model);
    }
    
//    @Override
    public void toggleSortOrder(int column) {
        super.toggleSortOrder(column + offset);
    }
//
//    /**
//     * 
//     * @param column
//     * @return
//     * @author liurenyong 2013-8-15
//     */
//    private int getTotalColumn(int column) {
//        if(column >= offset) {
//            column = offset + column;
//        }
//        return column;
//    }
    
    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
}
