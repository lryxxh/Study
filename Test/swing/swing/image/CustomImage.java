package swing.image;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.Serializable;

public class CustomImage extends Image implements Serializable{

	@Override
	public int getWidth(ImageObserver observer) {
		return 0;
	}

	@Override
	public int getHeight(ImageObserver observer) {
		return 0;
	}

	@Override
	public ImageProducer getSource() {
		return null;
	}

	@Override
	public Graphics getGraphics() {
		return null;
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		return null;
	}

}
