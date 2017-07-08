package swing.task;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.Scrollable;

import org.jdesktop.swingx.plaf.TaskPaneUI;

public class JTaskPane extends JComponent
  implements Scrollable
{
  public static final String UI_CLASS_ID = "TaskPaneUI";

  public JTaskPane()
  {
    updateUI();
  }

  public void setUI(TaskPaneUI ui)
  {
    super.setUI(ui);
  }

  public String getUIClassID()
  {
    return "TaskPaneUI";
  }

  public void add(JTaskPaneGroup group)
  {
    super.add(group);
  }

  public void remove(JTaskPaneGroup group)
  {
    super.remove(group);
  }

  public Dimension getPreferredScrollableViewportSize()
  {
    return getPreferredSize();
  }

  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
  {
    return 10;
  }

  public boolean getScrollableTracksViewportHeight()
  {
    if (getParent() instanceof JViewport) {
      return (((JViewport)getParent()).getHeight() > getPreferredSize().height);
    }
    return false;
  }

  public boolean getScrollableTracksViewportWidth()
  {
    return true;
  }

  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
  {
    return 10;
  }

}