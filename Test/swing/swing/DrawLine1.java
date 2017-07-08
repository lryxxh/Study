package swing;

/**
 * 
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawLine1 extends Frame {
	/**
	 * @param args
	 */
	Image oimg = null;
	Graphics og = null;

	public void init() {
		setSize(300, 300);
		setVisible(true);
		Dimension dimension = getSize();
		oimg = createImage(dimension.width, dimension.height);
		og = oimg.getGraphics();
		addMouseListener(new MouseAdapter() {
			int orgx, orgy;

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mousePressed(e);
				orgx = e.getX();
				orgy = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseReleased(e);
				Graphics g = getGraphics();
				g.setColor(Color.RED);
				g.setFont(new Font("¡• È ", Font.ITALIC | Font.BOLD, 30));
				g.drawString(new String(orgx + "," + orgy), orgx, orgy);
				g.drawString(new String(e.getX() + "," + e.getY()), e.getX(), e.getY());
				g.drawLine(orgx, orgy, e.getX(), e.getY());
				og.setColor(Color.RED);
				og.setFont(new Font("¡• È ", Font.ITALIC | Font.BOLD, 30));
				og.drawString(new String(orgx + "," + orgy), orgx, orgy);
				og.drawString(new String(e.getX() + "," + e.getY()), e.getX(), e.getY());
				og.drawLine(orgx, orgy, e.getX(), e.getY());
			}

		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				// super.windowClosing(e);
				System.exit(0);
			}

		});

	}

	public void paint(Graphics g) {
		if (oimg != null) {
			g.drawImage(oimg, 0, 0, this);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DrawLine1().init();
	}
}