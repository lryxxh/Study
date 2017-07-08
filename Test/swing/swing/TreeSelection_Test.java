/**
 * TreeSelection_Test.java
 * Created by liurenyong at 2013-8-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


/**
 * 
 * @author liurenyong 2013-8-19
 */
public class TreeSelection_Test extends JFrame {

    /**
     * 
     * @author liurenyong 2013-8-19
     */
    public TreeSelection_Test() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    /**
     * 
     * @author liurenyong 2013-8-19
     */
    private void init() {
        DefaultMutableTreeNode rootNode = new TestNode("Root");
        DefaultMutableTreeNode level1 = new TestNode("1");
        DefaultMutableTreeNode level2 = new TestNode("2");

        DefaultMutableTreeNode level11 = new TestNode("11");
        DefaultMutableTreeNode level12 = new TestNode("12");
        level1.add(level11);
        level1.add(level12);

        rootNode.add(level1);
        rootNode.add(level2);
        final JTree tree = new JTree(rootNode);

        JButton button = new JButton(">>>>>>>>>>>>>>>>>");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTreeModel dataModel = (DefaultTreeModel) tree.getModel();
//                DefaultTreeModel modelClone = dataModel.
                TreePath path = tree.getSelectionPath();
                System.err.println(path);
                DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) dataModel.getRoot();
                DefaultMutableTreeNode level1 = new TestNode("1");
                DefaultMutableTreeNode level2 = new TestNode("2");

                DefaultMutableTreeNode level11 = new TestNode("11");
                DefaultMutableTreeNode level12 = new TestNode("123");
                level1.add(level11);
                level1.add(level12);

                rootNode.removeAllChildren();
                rootNode.add(level1);
                rootNode.add(level2);

                dataModel.reload();
                
              
//                TreePath treePath = new TreePath(selectionNodeToRoot);
//                List<TreePath> pathssList = new ArrayList<TreePath>();
//                while(treePath!= null) {
//                    pathssList.add(treePath);
//                    treePath = treePath.getParentPath();
//                }
//                TreePath[] selePaths = new TreePath[pathssList.size()];
//                pathssList.toArray(selePaths);
//                int rows[] = new int[selePaths.length];
//                for(int i = selePaths.length - 1; i >= 0; i--) {
//                    rows[i] = tree.getRowForPath(selePaths[i]);
//                    tree.expandRow(rows[i]);
//                }
                tree.setSelectionPath(path);

            }
        });
        getContentPane().add(button, BorderLayout.SOUTH);
        getContentPane().add(tree);
    }

    public static void main(String[] args) {
        new TreeSelection_Test();
    }
    
    
}
class TestNode extends DefaultMutableTreeNode {
    
    public TestNode(String text) {
        super(text);
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        StringBuffer treePathString = new StringBuffer();
        TreeNode node = this;
        while(node != null) {
            treePathString.append(node.toString());
            node = node.getParent();
        }
       return treePathString.toString().hashCode();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return getUserObject().equals(((DefaultMutableTreeNode)obj).getUserObject());
    }
    
}