package swing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class PictureComponent extends JComponent implements FocusListener,
		MouseListener, MouseMotionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6539851599205092408L;
	
	Image image;
	HashSet<PictureComponent> pcs = new HashSet<PictureComponent>();

	public PictureComponent(Image image) {
		this.image = image;
		setPreferredSize(new Dimension(125, 125));
		setFocusable(true);
		setTransferHandler(new PictureTransferHandler());
		addFocusListener(this);
		addMouseListener(this);
	}

	public HashSet<PictureComponent> getPcs() {
		return this.pcs;
	}

	public void setPcs(HashSet<PictureComponent> pcs) {
		this.pcs = pcs;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
		repaint();
	}

	public void paintComponent(Graphics graphics) {
		Graphics g = graphics.create();
		g.setColor(Color.white);
		g.fillRect(0, 0, 125, 125);
		if (image != null) {
			g.drawImage(image, 0, 0, 125, 125, Color.BLACK, this);
		}
		if (isFocusOwner()) {
			g.setColor(Color.red);
		} else {
			g.setColor(Color.black);
		}
		g.drawRect(0, 0, 125, 125);
		g.dispose();
	}

	public void focusGained(FocusEvent e) {
		repaint();
	}

	public void focusLost(FocusEvent e) {
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		requestFocusInWindow();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		TransferHandler handler = getTransferHandler();
		handler.exportAsDrag(this, e, TransferHandler.MOVE);
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}