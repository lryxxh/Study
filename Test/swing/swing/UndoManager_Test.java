package swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;

public class UndoManager_Test extends JApplet {
	private UndoableList list = new UndoableList();
	private JScrollPane scrollPane = new JScrollPane(list);

	private JButton addButton = new JButton("Add Item"),
					endButton = new JButton("End"),
					undoButton = new JButton("Undo");

	private UndoAction undoAction = new UndoAction();
	private UndoManager compoundEdit = new UndoManager();
	private int cnt=0;

	public void init() {
		Container contentPane = getContentPane();

		contentPane.setLayout(new FlowLayout());
		contentPane.add(addButton);
		contentPane.add(endButton);
		contentPane.add(undoButton);
		contentPane.add(scrollPane);

		scrollPane.setPreferredSize(new Dimension(150,150));
		list.addUndoableEditListener(undoAction);

		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compoundEdit.end();
				updateButtonsEnabledState();
			}
		});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.undoableAdd("item #" + cnt++);
				updateButtonsEnabledState();
			}
		});
		undoButton.addActionListener(undoAction);

		endButton.setEnabled(false);
		undoButton.setEnabled(false);
	}
	private void updateButtonsEnabledState() {
		boolean inProgress = compoundEdit.isInProgress();

		endButton.setEnabled(inProgress);
		addButton.setEnabled(inProgress);

		if(undoButton.getText().equals("Undo"))
			undoButton.setEnabled(compoundEdit.canUndo());
		else
			undoButton.setEnabled(compoundEdit.canRedo());
	}
	class UndoAction extends AbstractAction 
					 implements UndoableEditListener {

		public UndoAction() {
			putValue(Action.NAME, "Undo");
		}
		public void actionPerformed(ActionEvent e) {
			String name = undoButton.getText();
			boolean isUndo = name.equals("Undo");

			if(isUndo) 	compoundEdit.undo();	
			else		compoundEdit.redo();	

			undoButton.setText(isUndo ? "Redo" : "Undo");
		}
		public void undoableEditHappened(UndoableEditEvent e) {
			UndoableEdit edit = e.getEdit();
			compoundEdit.addEdit(edit);
			endButton.setEnabled(true);
		}
	}
}
class UndoableList extends JList {
	UndoableEditSupport support = new UndoableEditSupport();
	DefaultListModel model;
	AddItemEdit edit = new AddItemEdit();
	public UndoableList() {
		setModel(model = new DefaultListModel());
	}
	public void addUndoableEditListener(UndoableEditListener l) {
		support.addUndoableEditListener(l);
	}
	public void removeUndoableEditListener(
										UndoableEditListener l) {
		support.removeUndoableEditListener(l);
	}
	public void undoableAdd(Object s) {
		model.addElement(s);
//		support.postEdit(new AddItemEdit());
		support.postEdit(edit);
	}
	class AddItemEdit extends AbstractUndoableEdit {
		Object lastItemAdded;

		public void undo() throws CannotUndoException {
			super.undo();
			lastItemAdded = model.getElementAt(model.getSize()-1);
			model.removeElement(lastItemAdded);
		}
		public void redo() throws CannotRedoException {
			super.redo();
			model.addElement(lastItemAdded);
		}
	}
}
