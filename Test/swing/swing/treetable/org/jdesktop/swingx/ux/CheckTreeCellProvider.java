/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.treetable.org.jdesktop.swingx.ux;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.renderer.CellContext;
import org.jdesktop.swingx.renderer.ComponentProvider;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

/**
 * 
 * @author vearn
 */
public class CheckTreeCellProvider extends ComponentProvider<JPanel> {

    private CheckTreeSelectionModel selectionModel;
    private TristateCheckBox _checkBox = null;
    private JLabel _label = null;

    public CheckTreeCellProvider(CheckTreeSelectionModel selectionModel) {
        this.selectionModel = selectionModel;
        _checkBox = new TristateCheckBox(); 
        _checkBox.setOpaque(false); 
        _label = new JLabel();
    }

    @Override
    protected void format(CellContext arg0) {
        JTree tree = (JTree) arg0.getComponent();
        DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode) arg0.getValue();
        Object obj = node.getUserObject();
        _label.setText(obj.toString());
        _label.setIcon(arg0.getIcon());

        TreePath path = tree.getPathForRow(arg0.getRow());
        if (path != null) {
            if (selectionModel.isPathSelected(path, true)) {
                _checkBox.setState(Boolean.TRUE);
            } else if (selectionModel.isPartiallySelected(path)) {
                _checkBox.setState(null); // 濞���伴�婊�����锟芥���拷���锟介���I
            } else {
                _checkBox.setState(Boolean.FALSE);
            }
        }

        // 娴ｈ法��orderLayout������灞肩贩濞�������istateCheckBox���Label
        rendererComponent.setLayout(new BorderLayout());
        rendererComponent.add(_checkBox);
        rendererComponent.add(_label, BorderLayout.LINE_END);
    }

    @Override
    protected void configureState(CellContext arg0) {
    }

    /**
     * ���������娑��Panel��������istateCheckBox���Label
     */
    @Override
    protected JPanel createRendererComponent() {
        JPanel panel = new JPanel();
        return panel;
    }
}