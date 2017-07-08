/**
 * 
 */
package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

/**
 * @author HMI-Lenovo
 *
 */
public class FigText extends JComponent{
	
    private static final String uiClassID = "FigTextUI";
    
    static {
    	UIManager.put(uiClassID, "swing.FigTextUI");
    }
    
    public FigText() {
    	updateUI();
    	init();
    }
    
    private void init() {
    	setToolTipText("自定义组件");
    	addMouseListener(new MouseAdapter() {
    		/* (non-Javadoc)
    		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
    		 */
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			Point point = getPopupLocation(e);
                JPopupMenu popupMenu = new JPopupMenu("ss");
                popupMenu.add(new JMenuItem(getBackground().toString()));
                popupMenu.add(new JMenuItem("sss2"));
                popupMenu.add(new JMenuItem("sss3"));
                popupMenu.add(new JMenuItem("sss4"));
                popupMenu.show(FigText.this, FigText.this.getWidth(), 0);
//                popupMenu.setBorder(new MetalBorders.PopupMenuBorder());
//                popupMenu.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
		});
	}
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#getPopupLocation(java.awt.event.MouseEvent)
     */
    @Override
    public Point getPopupLocation(MouseEvent event) {
    	Rectangle bounds = event.getComponent().getBounds();
    	Point point = new Point(bounds.x + bounds.width, bounds.y);
    	System.out.println(point);
    	return point;
    }

    @Override
	public String getUIClassID() {
        return uiClassID;
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#setUI(javax.swing.plaf.ComponentUI)
     */
    @Override
    protected void setUI(ComponentUI newUI) {
    	super.setUI(newUI);
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#updateUI()
     */
    @Override
    public void updateUI() {
    	setUI(UIManager.getUI(this));
    }

    public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(new JButton("sd"));
		FigText text = new FigText();
		text.setBackground(Color.GRAY);
		text.setBounds(10,10,100,100);
		text.setOpaque(true);
		text.setPreferredSize(new Dimension(200,100));
		FigText text2 = new FigText();
		text2.setBackground(Color.BLACK);
		text2.setBounds(50,50,100,100);
		text2.setPreferredSize(new Dimension(200,100));
		text2.setOpaque(true);
		panel.add(text2);
		panel.add(text);
		JList list1=new JList(new String[]{"1","2","3"});
		list1.setBounds(200, 10, 100, 30);
		JList list2 =new JList(new String[]{"1","2","3"});
		list2.setBounds(200, 50, 100, 30);
		panel.add(list1);
		panel.add(list2);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

}
