package swing.multipletableheader;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ColumnGroup {
    protected TableCellRenderer renderer;

    protected Vector v;

    protected String text;

    protected int margin = 0;

    public ColumnGroup(String text) {
        this(null, text);
    }

    public ColumnGroup(TableCellRenderer renderer, String text) {
        if (renderer == null) {
            this.renderer = new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JTableHeader header = table.getTableHeader();
                    if (header != null) {
                        setForeground(header.getForeground());
                        setBackground(header.getBackground());
                        setFont(header.getFont());
                    }
                    setHorizontalAlignment(JLabel.CENTER);
                    this.setText((value == null) ? "" : value.toString());
                    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                    return this;
                }
            };
        } else {
            this.renderer = renderer;
        }
        this.text = text;
        v = new Vector();
    }

    public void add(Object obj) {
        if (obj == null) {
            return;
        }
        v.addElement(obj);
    }

    public Vector getColumnGroups(TableColumn c, Vector g) {
        g.addElement(this);
        if (v.contains(c)) {
            return g;
        }
        Enumeration enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            Object obj = enumeration.nextElement();
            if (obj instanceof ColumnGroup) {
                Vector groups = (Vector) ((ColumnGroup) obj).getColumnGroups(c, (Vector) g.clone());
                if (groups != null) {
                    return groups;
                }
            }
        }
        return null;
    }

    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }

    public Object getHeaderValue() {
        return text;
    }

    public int getSize() {
        return v == null ? 0 : v.size();
    }

    public Dimension getSize(JTable table) {
        Component comp = renderer.getTableCellRendererComponent(table, getHeaderValue(), false, false, -1, -1);
        int height = comp.getPreferredSize().height;
        int width = 0;
        Enumeration enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            Object obj = enumeration.nextElement();
            if (obj instanceof TableColumn) {
                TableColumn aColumn = (TableColumn) obj;
                width += aColumn.getWidth();
                width += margin;
            } else {
                width += ((ColumnGroup) obj).getSize(table).width;
            }
        }
        return new Dimension(width, height);
    }

    public java.lang.String getText() {
        return text;
    }

    public boolean removeColumn(ColumnGroup ptg, TableColumn tc) {
        boolean retFlag = false;
        if (tc != null) {
            for (int i = 0; i < ptg.v.size(); i++) {
                Object tmpObj = ptg.v.get(i);
                if (tmpObj instanceof ColumnGroup) {
                    retFlag = removeColumn((ColumnGroup) tmpObj, tc);
                    if (retFlag) {
                        break;
                    }
                } else if (tmpObj instanceof TableColumn) {
                    if (tmpObj == tc) {
                        ptg.v.remove(i);
                        retFlag = true;
                        break;
                    }
                }
            }
        }
        return retFlag;
    }

    public boolean removeColumnGrp(ColumnGroup ptg, ColumnGroup tg) {
        boolean retFlag = false;
        if (tg != null) {
            for (int i = 0; i < ptg.v.size(); i++) {
                Object tmpObj = ptg.v.get(i);
                if (tmpObj instanceof ColumnGroup) {
                    if (tmpObj == tg) {
                        ptg.v.remove(i);
                        retFlag = true;
                        break;
                    } else {
                        retFlag = removeColumnGrp((ColumnGroup) tmpObj, tg);
                        if (retFlag) {
                            break;
                        }

                    }
                } else if (tmpObj instanceof TableColumn) {
                    break;
                }
            }
        }
        return retFlag;
    }

    public void setColumnMargin(int margin) {
        this.margin = margin;
        Enumeration enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            Object obj = enumeration.nextElement();
            if (obj instanceof ColumnGroup) {
                ((ColumnGroup) obj).setColumnMargin(margin);
            }
        }
    }

    public void setHeaderRenderer(TableCellRenderer renderer) {
        if (renderer != null) {
            this.renderer = renderer;
        }
    }

    public void setText(java.lang.String newText) {
        text = newText;
    }
}