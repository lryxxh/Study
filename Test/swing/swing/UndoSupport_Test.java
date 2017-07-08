package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;

public class UndoSupport_Test extends JApplet {
	private ColorPanel colorPanel = new ColorPanel();
	private UndoAction undoAction = new UndoAction();

	public void init() {
		colorPanel.setBorder(
			BorderFactory.createTitledBorder(
				"Change color and subsequently undo " +
				"from the Edit menu"));

		makeMenuBar();
		colorPanel.addUndoableEditListener(undoAction);
		getContentPane().add(colorPanel, BorderLayout.CENTER);
	}
	private void makeMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu editMenu = new JMenu("Edit");

		editMenu.add(new SetColorAction());
		editMenu.add(undoAction);

		menuBar.add(editMenu);
		setJMenuBar(menuBar);
	}
	class UndoAction extends AbstractAction 
					 implements UndoableEditListener {
		UndoableEdit lastEdit;

		public UndoAction() {
			putValue(Action.NAME, "Undo");
			setEnabled(false);
		}
		public void actionPerformed(ActionEvent e) {
			String name = (String)getValue(Action.NAME);
			boolean isUndo = name.equals(
							 lastEdit.getUndoPresentationName());
			if(isUndo) {
				lastEdit.undo();	
				putValue(Action.NAME,
						 lastEdit.getRedoPresentationName());
			}
			else {
				lastEdit.redo();	
				putValue(Action.NAME,
						 lastEdit.getUndoPresentationName());
			}
		}
		public void undoableEditHappened(UndoableEditEvent e) {
			lastEdit = e.getEdit();

			putValue(Action.NAME, 
					 lastEdit.getUndoPresentationName());

			if(lastEdit.canUndo())
				setEnabled(true);
		}
	}
	class SetColorAction extends AbstractAction {
		public SetColorAction() {
			super("Set color ...");
		}
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(
							UndoSupport_Test.this, // parent component
							"Pick A Color", // dialog title
							null); // initial color

			if(color != null) { 
				colorPanel.setBackground(color);
			}
		}
	}
}
class ColorPanel extends JPanel {
	UndoableEditSupport support;
	BackgroundColorEdit edit = new BackgroundColorEdit();
	Color oldColor;

	public void addUndoableEditListener(
										UndoableEditListener l) {
		support.addUndoableEditListener(l);
	}
	public void removeUndoableEditListener(
										UndoableEditListener l) {
		support.removeUndoableEditListener(l);
	}
	public void setBackground(Color color) {
		oldColor = getBackground();
		super.setBackground(color);

		if(support == null)
			support = new UndoableEditSupport();

		support.postEdit(edit);
	}
	class BackgroundColorEdit extends AbstractUndoableEdit {
		public void undo() throws CannotUndoException {
			super.undo();
			toggleColor();
		}
		public void redo() throws CannotRedoException {
			super.redo();
			toggleColor();
		}
		public String getUndoPresentationName() {
			return "Undo Background Color Change";
		}
		public String getRedoPresentationName() {
			return "Redo Background Color Change";
		}
		private void toggleColor() {
			Color color = getBackground();
			setBackground(oldColor);
			oldColor = color;
		}
	}
}
