package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class LabelDnd  {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JLabel label;
    private JTextField textField;
    private JColorChooser colorChooser;
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenu  menu = new JMenu( "Menu" );
    private JMenuItem menuItem = new JMenuItem( "Handle Foregound" );
    private TransferHandler t1 = new TransferHandler( "text" ) ;
    private TransferHandler t2 = new TransferHandler( "foreground" );
    public LabelDnd() {
        mainFrame = new JFrame();
        mainPanel = new JPanel( new BorderLayout() );
        label = new JLabel( "label" );
        label.setTransferHandler( t1 );
        
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( label.getTransferHandler().equals( t1 ) ) {
                    LabelDnd.this.menuItem.setText( "Handle Text" );
                    label.setTransferHandler( t2 );
                } else {
                    LabelDnd.this.menuItem.setText( "Handle Foreground" );
                    label.setTransferHandler( t1 );
                }
            }
        });
        menu.add( menuItem );
        menu.setTransferHandler( t1 );
        menuBar.add( menu );
        mainFrame.setJMenuBar( menuBar );
        label.addMouseListener( new MouseAdapter() {
            public void mousePressed( MouseEvent e ) {
                JComponent c = (JComponent)e.getSource();
                TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag( c, e, TransferHandler.COPY );
            }
        });
        textField = new JTextField( 20 );
        textField.setDragEnabled( true );
        colorChooser = new JColorChooser();
        colorChooser.setDragEnabled( true );
        mainPanel.add( label, BorderLayout.PAGE_START );
        mainPanel.add( textField, BorderLayout.PAGE_END );
        mainPanel.add( colorChooser, BorderLayout.WEST );
        mainFrame.getContentPane().add( mainPanel );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.pack();
        mainFrame.setLocationRelativeTo( null );
        mainFrame.setVisible( true );
    }
    public static void main( String[] args ) {
        new LabelDnd();
    }
}