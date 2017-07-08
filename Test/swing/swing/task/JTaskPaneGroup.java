package swing.task;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class JTaskPaneGroup extends JPanel
{
  public static final String UI_CLASS_ID = "TaskPaneGroupUI";
  public static final String EXPANDED_CHANGED_KEY = "expanded";
  public static final String COLLAPSABLE_CHANGED_KEY = "collapsable";
  public static final String SCROLL_ON_EXPAND_CHANGED_KEY = "scrollOnExpand";
  public static final String TITLE_CHANGED_KEY = "title";
  public static final String ICON_CHANGED_KEY = "icon";
  public static final String SPECIAL_CHANGED_KEY = "special";
  public static final String ANIMATED_CHANGED_KEY = "animated";
  private String title;
  private Icon icon;
  private boolean special;
  private boolean expanded = true;
  private boolean scrollOnExpand;
  private boolean collapsable = true;
  private JCollapsiblePane collapsePane;

  public JTaskPaneGroup()
  {
    this.collapsePane = new JCollapsiblePane();
    super.setLayout(new BorderLayout(0, 0));
    super.addImpl(this.collapsePane, "Center", -1);

    updateUI();
    setFocusable(true);
    setOpaque(false);

    setAnimated(!(Boolean.FALSE.equals(UIManager.get("TaskPaneGroup.animate"))));

    this.collapsePane.addPropertyChangeListener("animationState", new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent evt) {
        JTaskPaneGroup.this.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
      }
    });
  }

  public Container getContentPane()
  {
    return this.collapsePane.getContentPane();
  }



  public String getUIClassID()
  {
    return "TaskPaneGroupUI";
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    String old = title;
    this.title = title;
    firePropertyChange("title", old, title);
  }

  /** @deprecated */
  public void setText(String text)
  {
    setTitle(text);
  }

  /** @deprecated */
  public String getText()
  {
    return getTitle();
  }

  public Icon getIcon()
  {
    return this.icon;
  }

  public void setIcon(Icon icon)
  {
    Icon old = icon;
    this.icon = icon;
    firePropertyChange("icon", old, icon);
  }

  public boolean isSpecial()
  {
    return this.special;
  }

  public void setSpecial(boolean special)
  {
    if (this.special != special) {
      this.special = special;
      firePropertyChange("special", !(special), special);
    }
  }

  public void setScrollOnExpand(boolean scrollOnExpand)
  {
    if (this.scrollOnExpand != scrollOnExpand) {
      this.scrollOnExpand = scrollOnExpand;
      firePropertyChange("scrollOnExpand", !(scrollOnExpand), scrollOnExpand);
    }
  }

  public boolean isScrollOnExpand()
  {
    return this.scrollOnExpand;
  }

  public void setExpanded(boolean expanded)
  {
    if (this.expanded != expanded) {
      this.expanded = expanded;
      this.collapsePane.setCollapsed(!(expanded));
      firePropertyChange("expanded", !(expanded), expanded);
    }
  }

  public boolean isExpanded()
  {
    return this.expanded;
  }

  public void setCollapsable(boolean collapsable)
  {
    if (this.collapsable != collapsable) {
      this.collapsable = collapsable;
      firePropertyChange("collapsable", !(collapsable), collapsable);
    }
  }

  public boolean isCollapsable()
  {
    return this.collapsable;
  }

  public void setAnimated(boolean animated)
  {
    if (isAnimated() != animated) {
      this.collapsePane.setAnimated(animated);
      firePropertyChange("animated", !(isAnimated()), isAnimated());
    }
  }

  public boolean isAnimated()
  {
    return this.collapsePane.isAnimated();
  }

  public Component add(Action action)
  {
   JButton btt = new JButton(action);
    return btt;
  }

  public Container getValidatingContainer() {
    return getParent();
  }

  protected void addImpl(Component comp, Object constraints, int index)
  {
    getContentPane().add(comp, constraints, index);
  }

  public void setLayout(LayoutManager mgr)
  {
    if (this.collapsePane != null)
      getContentPane().setLayout(mgr);
  }

  public void remove(Component comp)
  {
    getContentPane().remove(comp);
  }

  public void remove(int index)
  {
    getContentPane().remove(index);
  }

  public void removeAll()
  {
    getContentPane().removeAll();
  }

  public boolean isFocusable()
  {
    return ((super.isFocusable()) && (isCollapsable()));
  }

  protected String paramString()
  {
    return super.paramString() + ",title=" + getTitle() + ",icon=" + getIcon() + ",expanded=" + String.valueOf(isExpanded()) + ",special=" + String.valueOf(isSpecial()) + ",scrollOnExpand=" + String.valueOf(isScrollOnExpand()) + ",ui=" + getUI();
  }

}