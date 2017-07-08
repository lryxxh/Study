package swing;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VerticalTextTest extends JPanel{
  String s = "Vertical text";
  int v;

  public void paint(Graphics g) {
    v = g.getFontMetrics(getFont()).getHeight() + 1;
    int j = 0;
    int k = s.length();
    while (j < k + 1) {
      if (j == k)
        g.drawString(s.substring(j), 10, 10 + (j * v));
      else
        g.drawString(s.substring(j, j + 1), 10, 10 + (j * v));
      j++;
    }
  }
  public static void main(String[] a){
    JFrame f = new JFrame();
    f.add(new VerticalTextTest());
    f.setSize(300,300);
    f.setVisible(true);
  }
}
