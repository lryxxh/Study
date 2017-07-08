package swing;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class PictureTransferHandler extends TransferHandler {
	public Transferable createTransferable(JComponent c) {
		PictureComponent pc = (PictureComponent) c;
		return new TransferablePicture(pc.getImage());
	}

	public boolean canImport(JComponent c, DataFlavor[] flavors) {
		for (DataFlavor flavor : flavors) {
			if (flavor.equals(DataFlavor.imageFlavor)) {
				return true;
			}
		}
		return false;
	}

	public boolean importData(JComponent c, Transferable t) {
		System.out.println(">>>>>>>>>>>>importData");
		if (canImport(c, t.getTransferDataFlavors())) {
			PictureComponent pc = (PictureComponent) c;
			try {
				Image image = (Image) t.getTransferData(DataFlavor.imageFlavor);
				pc.setImage(image);
				System.out.println("它能接受");
				return true;
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("它不能接受");
		return false;
	}

	public void exportDone(JComponent c, Transferable data, int action) {
		PictureComponent picture = (PictureComponent) c;
		System.out.println(">>>>>>>>>>>>exportDone");
		if (action == MOVE) {
			picture.setImage(null);
		}
	}

	public int getSourceActions(JComponent c) {
		return MOVE;
	}

	public Icon getVisualRepresentation(Transferable t) {
		Image image = null;
		try {
			System.out.println("getVisualRepresentation");
			image = (Image) t.getTransferData(DataFlavor.imageFlavor);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ImageIcon(image);
	}
}