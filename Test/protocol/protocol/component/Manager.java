package protocol.component;

import java.beans.PropertyEditorManager;
import java.util.Vector;

public class Manager {

	public Manager() {
		PropertyEditorManager.registerEditor(Vector.class, VectorEditor.class);
		PropertyEditorManager.registerEditor(String.class, StringEditor.class);

	}

}
