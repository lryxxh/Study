package swing.task;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JCollapsiblePane extends JPanel
{
  public static final String ANIMATION_STATE_KEY = "animationState";
  public static final String TOGGLE_ACTION = "toggle";
  public static final String COLLAPSE_ICON = "collapseIcon";
  public static final String EXPAND_ICON = "expandIcon";
  private boolean collapsed = false;
  private Timer animateTimer;
  private int currentHeight = -1;
  private WrapperContainer wrapper;
  private boolean useAnimation = true;
  static Class class$com$l2fprod$common$swing$JCollapsiblePane$JCollapsiblePaneContainer;

  public JCollapsiblePane()
  {
    super.setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    setContentPane(panel);


  }

  public void setContentPane(Container contentPanel)
  {
    if (contentPanel == null) {
      throw new IllegalArgumentException("Content pane can't be null");
    }

    if (this.wrapper != null) {
      super.remove(this.wrapper);
    }
    this.wrapper = new WrapperContainer(contentPanel);
    super.addImpl(this.wrapper, "Center", -1);
  }

  public Container getContentPane()
  {
    return this.wrapper.c;
  }

  public void setLayout(LayoutManager mgr)
  {
    if (this.wrapper != null)
      getContentPane().setLayout(mgr);
  }

  protected void addImpl(Component comp, Object constraints, int index)
  {
    getContentPane().add(comp, constraints, index);
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

  public void setAnimated(boolean animated)
  {
    if (animated != this.useAnimation) {
      this.useAnimation = animated;
      firePropertyChange("animated", !(this.useAnimation), this.useAnimation);
    }
  }

  public boolean isAnimated()
  {
    return this.useAnimation;
  }

  public boolean isCollapsed()
  {
    return this.collapsed;
  }

  public void setCollapsed(boolean val)
  {
    if (this.collapsed != val) {
      this.collapsed = val;
      if (isAnimated()) {
        if (this.collapsed) {

          this.animateTimer.start();
        } else {


          this.animateTimer.start();
        }
      } else {
        this.wrapper.c.setVisible(!(this.collapsed));
        invalidate();
        doLayout();
      }
      repaint();
      firePropertyChange("collapsed", !(this.collapsed), this.collapsed);
    }
  }

  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  public Dimension getPreferredSize()
  {
    Dimension dim;
    if (!(isAnimated())) {
      if (getContentPane().isVisible())
        dim = getContentPane().getPreferredSize();
      else
        dim = super.getPreferredSize();
    }
    else {
      dim = new Dimension(getContentPane().getPreferredSize());
      if ((!(getContentPane().isVisible())) && (this.currentHeight != -1)) {
        dim.height = this.currentHeight;
      }
    }
    return dim;
  }


  static Class class$(String x0)
  {
    try
    {
      return Class.forName(x0); } catch (ClassNotFoundException x1) { throw new NoClassDefFoundError(x1.getMessage());
    }
  }

  private final class WrapperContainer extends JPanel
  {
    private BufferedImage img;
    private Container c;
    float alpha = 1.0F;

    public WrapperContainer(Container c) {
      super(new BorderLayout());
      this.c = c;
      add(c, "Center");

      if ((c instanceof JComponent) && (!(((JComponent)c).isOpaque())))
        ((JComponent)c).setOpaque(true);
    }

    public void showImage()
    {
      makeImage();
      this.c.setVisible(false);
    }


    void makeImage()
    {
      if ((getGraphicsConfiguration() != null) && (getWidth() > 0)) {
        Dimension dim = this.c.getPreferredSize();

        if (dim.height > 0) {
          this.img = getGraphicsConfiguration().createCompatibleImage(getWidth(), dim.height);

          this.c.setSize(getWidth(), dim.height);
          this.c.paint(this.img.getGraphics());
        } else {
          this.img = null;
        }
      }
    }

    public void paintComponent(Graphics g) {
      if ((!(JCollapsiblePane.this.useAnimation)) || (this.c.isVisible())) {
        super.paintComponent(g);
      }
      else
      {
        if (this.img == null) {
          makeImage();
        }

        if ((g == null) || (this.img == null))
          return;
        g.drawImage(this.img, 0, getHeight() - this.img.getHeight(), null);
      }
    }

    public void paint(Graphics g)
    {
      Graphics2D g2d = (Graphics2D)g;
      Composite oldComp = g2d.getComposite();
      Composite alphaComp = AlphaComposite.getInstance(3, this.alpha);

      g2d.setComposite(alphaComp);
      super.paint(g2d);
      g2d.setComposite(oldComp);
    }
  }

}