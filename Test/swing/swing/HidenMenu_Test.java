/**
 * HidenMenu_Test.java
 * Created by liurenyong at 2013-8-23
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

/**
 * 
 * @author liurenyong 2013-8-23
 */
public class HidenMenu_Test extends JFrame implements ActionListener {

    Vector<JSplitPane> splitPanes = new Vector<JSplitPane>();

    /**
     * 
     * @author liurenyong 2013-8-23
     */
    public HidenMenu_Test() {
        setSize(500, 300);
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * 
     * @author liurenyong 2013-8-23
     */
    private void init() {
        JSplitPane firstSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        firstSplitPane.setName("FirstSplit");
        firstSplitPane.setContinuousLayout(true);
        firstSplitPane.setDividerSize(5);
        JButton firstButton = new JButton("First");
        firstSplitPane.setTopComponent(firstButton);
        firstButton.addActionListener(this);

        JSplitPane secondSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        secondSplitPane.setName("secondSplitPane");
        secondSplitPane.setContinuousLayout(true);
        secondSplitPane.setDividerSize(5);
        JPanel secondTop = new JPanel();
        secondTop.setName("secondTop");
        secondTop.setBackground(Color.BLUE);
        secondSplitPane.setTopComponent(secondTop);

        JSplitPane thirdSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        thirdSplitPane.setName("thirdSplitPane");
        thirdSplitPane.setContinuousLayout(true);
        thirdSplitPane.setDividerSize(5);
        JButton secondButton = new JButton("second");
        thirdSplitPane.setTopComponent(secondButton);
        secondButton.addActionListener(this);

        JSplitPane fourSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        fourSplitPane.setName("fourSplitPane");
        fourSplitPane.setContinuousLayout(true);
        fourSplitPane.setDividerSize(5);
        JPanel fourBottom = new JPanel();
        fourBottom.setName("fourBottom");
        fourBottom.setBackground(Color.GREEN);
        fourSplitPane.setTopComponent(fourBottom);

        JSplitPane fiveSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        fiveSplitPane.setName("fiveSplitPane");
        fiveSplitPane.setContinuousLayout(true);
        fiveSplitPane.setDividerSize(5);
        JButton fiveButton = new JButton("third");
        fiveButton.addActionListener(this);
        fiveSplitPane.setTopComponent(fiveButton);

        JSplitPane sixSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sixSplitPane.setName("sixSplitPane");
        sixSplitPane.setContinuousLayout(true);
        sixSplitPane.setDividerSize(5);
        JPanel sixBottom = new JPanel();
        sixBottom.setName("sixBottom");
        sixBottom.setBackground(Color.RED);
        sixSplitPane.setTopComponent(sixBottom);

        JSplitPane sevenSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sevenSplitPane.setName("sixSplitPane");
        sevenSplitPane.setContinuousLayout(true);
        sevenSplitPane.setDividerSize(5);
        JPanel panel = new JPanel();
        JButton sevenButton = new JButton("four");
        panel.add(sevenButton);
        JPanel sevenPanel = new JPanel();
        sevenPanel.setName("sevenPane");
        sevenPanel.setBackground(Color.yellow);
        sevenSplitPane.setTopComponent(panel);
        sevenSplitPane.setBottomComponent(sevenPanel);

        sixSplitPane.setBottomComponent(sevenSplitPane);
        fiveSplitPane.setBottomComponent(sixSplitPane);
        fourSplitPane.setBottomComponent(fiveSplitPane);
        thirdSplitPane.setBottomComponent(fourSplitPane);
        secondSplitPane.setBottomComponent(thirdSplitPane);
        firstSplitPane.setBottomComponent(secondSplitPane);

        splitPanes.add(firstSplitPane);
        splitPanes.add(secondSplitPane);
        splitPanes.add(thirdSplitPane);
        splitPanes.add(fourSplitPane);
        splitPanes.add(fiveSplitPane);
        splitPanes.add(sixSplitPane);
        splitPanes.add(sevenSplitPane);

        JScrollPane scrollPane = new JScrollPane();
        JTree tree = new JTree();
        scrollPane.setViewportView(tree);
        getContentPane().add(scrollPane, BorderLayout.WEST);
        getContentPane().add(firstSplitPane, BorderLayout.CENTER);
        hidenOtherSplitPane();
    }

    public static void main(String[] args) {
        new HidenMenu_Test();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        JSplitPane parentContainer = (JSplitPane) button.getParent();
        int index = splitPanes.indexOf(parentContainer);
        for (int i = 0; i < splitPanes.size(); i++) {
            JSplitPane tempSplit = splitPanes.get(i);
            
            // µÚÒ»¸ö
            if (index == splitPanes.size() - 1) {
                tempSplit.setDividerLocation(100);
//                hidenOtherSplitPane(index, splitPanes);
            } else {
                
            }
        }
        // for(int i = splitPanes.size() - 1; i >= 0; i--) {
        // JSplitPane tempSplit = splitPanes.get(i);
        // JComponent component = (JComponent) tempSplit.getTopComponent();
        // if((index + 1) == i) {
        // component.setVisible(true);
        // tempSplit.setDividerLocation(100);
        // } else {
        // if(component instanceof JPanel) {
        // tempSplit.setDividerLocation(100);
        // tempSplit.getTopComponent().setVisible(false);
        // } else {
        // if(i == splitPanes.size() - 1) {
        // tempSplit.getBottomComponent().setVisible(false);
        // } else {
        // if(index == splitPanes.size() - 1) {
        // tempSplit.getBottomComponent().setVisible(true);
        // }
        // }
        // }
        //
        // }
        // }
    }

    /**
     * 
     * @author liurenyong 2013-8-26
     */
    private void hidenOtherSplitPane() {
        splitPanes.get(1).setDividerLocation(50);
        for (int i = splitPanes.size() - 2 ; i > 1; i=i-2) {
            JSplitPane splitPane = splitPanes.get(i);
            splitPane.getTopComponent().setVisible(false);
        }
        splitPanes.get(splitPanes.size() - 1).getBottomComponent().setVisible(false);
    }

}
