package swing;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

/**
 * 使图片水平翻转、垂直翻转、旋转180度
 * 
 * @author Administrator
 */
public class TestPicture {

    public static void main(String[] args) throws IOException {
        BufferedImage sourceImage = ImageIO.read(TestPicture.class.getResource("image/working.gif"));
        BufferedImage dstImage = null;
        // AffineTransform transform = new AffineTransform(-1, 0, 0, 1,
        // sourceImage.getWidth(), 0);// 水平翻转
        AffineTransform transform = new AffineTransform(1, 0, 0, -1, 0,
                sourceImage.getHeight());// 垂直翻转
        // AffineTransform transform = new AffineTransform(-1, 0, 0, -1,
        // sourceImage.getWidth(), sourceImage.getHeight());// 旋转180度
        AffineTransformOp op = new AffineTransformOp(transform,
                AffineTransformOp.TYPE_BILINEAR);
        dstImage = op.filter(sourceImage, null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Source Transform", new JLabel(
                new ImageIcon(sourceImage)));
        tabbedPane.add("Affine Transform", new JLabel(new ImageIcon(dstImage)));

        JFrame jframe = new JFrame();
        jframe.setSize(800, 600);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.getContentPane().add(tabbedPane);
        jframe.setVisible(true);
    }

    /** 获得文件的绝对地址 */
    public static final URL getURL(String path) {
        return "".getClass().getResource(path);
    }
}

//
//class IsAffineTransform extends JComponent {
//
//    private static final long serialVersionUID = 1L;
//
//    public IsAffineTransform() {
//        setDoubleBuffered(true);
//    }
//
//    public void paintComponent(Graphics g) {
//        AffineTransform at;
//        int i;
//        Graphics2D g2 = (Graphics2D) g;
//        // 消除锯齿边缘
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        Dimension size = getSize();
//        g2.setColor(Color.white);
//        g2.fill(new Rectangle2D.Double(0, 0, size.width, size.height));
//
//        at = new AffineTransform();
//
//        Font f1 = new Font("Serif", Font.BOLD, 18);
//        g2.setFont(f1);
//
//        Color colorArray[] = new Color[10];
//        colorArray[0] = Color.blue;
//        colorArray[1] = Color.green;
//        colorArray[2] = Color.magenta;
//        colorArray[3] = Color.black;
//        colorArray[4] = Color.blue;
//        colorArray[5] = Color.green;
//        colorArray[6] = Color.magenta;
//        colorArray[7] = Color.black;
//        for (i = 0; i < 8; i++) {
//            at.rotate(Math.PI / 4, 180, 200);
//            g2.setTransform(at);
//            g2.setColor(colorArray[i % 8]);
//            g2.drawString("Hello,World!", 200, 200);
//        }
//
//    }
//
//    public static void main(String args[]) {
//        MyWindowListener l = new MyWindowListener();
//        IsAffineTransform c = new IsAffineTransform();
//
//        JFrame fr = new JFrame("旋转");
//        fr.addWindowListener(l);
//        fr.getContentPane().add(c, BorderLayout.CENTER);
//        fr.pack();
//        fr.setSize(400, 400);
//        fr.setLocation(400, 400);
//        fr.setVisible(true);
//
//    }
//
//}
//
//class MyWindowListener extends WindowAdapter {
//    public void windowClosing(WindowEvent e) {
//        System.exit(0);
//    }
//}
