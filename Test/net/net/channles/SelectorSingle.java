package net.channles;

import java.io.IOException;
import java.nio.channels.Selector;

public class SelectorSingle {
	static Selector selector = null;
	
	public static Selector getSelector() {
		if(selector == null) {
			try {
				selector =  Selector.open();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return selector;
	}

}
