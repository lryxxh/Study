package swing.image;

import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.io.Serializable;

public class CustomImageProducer implements ImageProducer, Serializable{

	@Override
	public void addConsumer(ImageConsumer ic) {
	}

	@Override
	public boolean isConsumer(ImageConsumer ic) {
		return false;
	}

	@Override
	public void removeConsumer(ImageConsumer ic) {
	}

	@Override
	public void startProduction(ImageConsumer ic) {
	}

	@Override
	public void requestTopDownLeftRightResend(ImageConsumer ic) {
	}

}
