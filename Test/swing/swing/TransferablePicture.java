package swing;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class TransferablePicture implements Transferable {
	DataFlavor[] flavors = { DataFlavor.imageFlavor };
	Image image;

	public TransferablePicture(Image image) {
		this.image = image;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public Object getTransferData(DataFlavor flavor) {
		if (flavor.equals(DataFlavor.imageFlavor)) {
			return image;
		}
		return null;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(DataFlavor.imageFlavor);
	}
}