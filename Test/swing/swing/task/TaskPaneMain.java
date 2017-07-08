package swing.task;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class TaskPaneMain extends JPanel {
    static ResourceBundle RESOURCE = ResourceBundle.getBundle("swing.task.message");

    public TaskPaneMain() throws Exception {
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();

        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        DemoPanel demo = new DemoPanel();
        tabs.addTab("Glossy", demo);
        add("Center", tabs);
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame frame = new JFrame("JTaskPane / JTaskPaneGroup");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add("Center", new TaskPaneMain());
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setLocation(100, 100);
        frame.setVisible(true);
    }

    static class DemoPanel extends JTaskPane {
        public DemoPanel() {
            JTaskPane taskPane = new JTaskPane();

            JTaskPaneGroup systemGroup = new JTaskPaneGroup();
            systemGroup.setTitle(TaskPaneMain.RESOURCE.getString("Main.tasks.systemGroup"));
            systemGroup.setToolTipText(TaskPaneMain.RESOURCE.getString("Main.tasks.systemGroup.tooltip"));

            systemGroup.setSpecial(true);
            systemGroup.setIcon(null);

            systemGroup.add(new AbstractAction() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                }
            });

            systemGroup.add(new AbstractAction() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                }
            });

            taskPane.add(systemGroup);

            JTaskPaneGroup officeGroup = new JTaskPaneGroup();
            officeGroup.setTitle(TaskPaneMain.RESOURCE.getString("Main.tasks.office"));
            officeGroup.add(new AbstractAction() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                }
            });

            officeGroup.setExpanded(false);
            officeGroup.setScrollOnExpand(true);

            taskPane.add(officeGroup);

            JTaskPaneGroup seeAlsoGroup = new JTaskPaneGroup();

            seeAlsoGroup.setCollapsable(false);
            seeAlsoGroup.setTitle(TaskPaneMain.RESOURCE.getString("Main.tasks.seealso"));
            seeAlsoGroup.add(new AbstractAction() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                }
            });

            seeAlsoGroup.add(new AbstractAction() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                }
            });

            taskPane.add(seeAlsoGroup);

            JTaskPaneGroup detailsGroup = new JTaskPaneGroup();
            detailsGroup.setTitle(TaskPaneMain.RESOURCE.getString("Main.tasks.details"));
            detailsGroup.setScrollOnExpand(true);

            JEditorPane detailsText = new JEditorPane("text/html", "<html>");
            detailsText.setText(TaskPaneMain.RESOURCE.getString("Main.tasks.details.message"));
            detailsGroup.add(detailsText);

            taskPane.add(detailsGroup);

            JScrollPane scroll = new JScrollPane(taskPane);
            scroll.setBorder(null);

            setLayout(new BorderLayout());
            add("Center", scroll);

            setBorder(null);
        }

    }
}