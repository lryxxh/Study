package swing;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TransfornTest extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1184775945501576302L;

	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setTitle("AffineTransform");

		frame.setSize(1000, 600);

		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				System.exit(0);

			}

		});

		Container contentPane = frame.getContentPane();

		contentPane.add(new TransfornTest());

		frame.show();

	
		String string = System.getProperty("123");
		System.out.println(string);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.orange);

		g.drawLine(0, getHeight() / 4, getWidth(), getHeight() / 4);

		g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

		g.drawLine(0, getHeight() * 3 / 4, getWidth(), getHeight() * 3 / 4);

		paintWithGraphicsMethod(g);

		paintWithTransform(g);

		paintWithSetTransform(g);

		paintWithStaticTransformInstance(g);

	}

	// 通过Graphics自身提供的变换方法进行变换

	private void paintWithGraphicsMethod(Graphics g) {

              //Graphics中的变换都是组合变换方式，

              Graphics2D g2d = (Graphics2D) g;

              AffineTransform oldTrans = g2d.getTransform();

              g2d.setColor(Color.yellow);

              g2d.fillRect(20, 20, 60, 60);

              //1. 基于原点进行平移变换

              g2d.translate(160, 0);

              g2d.fillRect(20, 20, 60, 60);

              //2. 基于1)平移变换后继续平移变换

              g2d.translate(160, 0);

              //3. 在2)平移的基础上进行旋转变换

              g2d.rotate(Math.PI / 4);

              g2d.fillRect(20, 20, 60, 60);

              //4. 这里需要特别注意，由于3)中组合了旋转变换，因此这里再直接进行

              //平移变换x时，因为坐标系的角度发生了变化，所以不能得到期望的平移

              //效果，在平移前需要将3)中的旋转再反向旋转回来。

              g2d.rotate(-Math.PI / 4);

              g2d.translate(160, 0);

              //5. 在4)的基础上做放大变换

              g2d.scale(1.5, 1.5);

              g2d.fillRect(20, 20, 60, 60);

              //6. 做切变变化，可以通过调整shear()方法的两个参数，进一步掌握切变变换

              g2d.translate(160, 0);

              g2d.shear(0.5, 0);

              g2d.fillRect(20, 20, 60, 60);

              //7. 这里需要还原，否则会影响后面的变换

              g2d.setTransform(oldTrans);

          }

	// 该方法和paintWithGraphicsMethod机制是一致的，只是应用的方法改为

	// AffineTransform提供的变换方法

	private void paintWithTransform(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		AffineTransform oldTrans = g2d.getTransform();

		// 1. 先将坐标系的原点移到第二个泳道中，以便后面的代码可以和其他

		// 函数中的代码保持一致

		g2d.translate(0, getHeight() / 4);

		g2d.setColor(Color.red);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx1 = new AffineTransform();

		tx1.translate(160, 0);

		// 2. 这里用到的transform方法在Graphics对象内部也是组合方式的，

		// 换句话说，后面的transform方法将会基于本次变化的结果。

		g2d.transform(tx1);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx2 = new AffineTransform();

		tx2.translate(160, 0);

		tx2.rotate(Math.PI / 4);

		// 3. 本次transform将基于1)中transform的结果并进行组合

		g2d.transform(tx2);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx3 = new AffineTransform();

		// 4. 这里的rotate的作用和上面的paintWithGraphicsMethod()方法的4)是一致的。

		tx3.rotate(-Math.PI / 4);

		tx3.translate(160, 0);

		tx3.scale(1.5, 1.5);

		g2d.transform(tx3);

		g2d.fillRect(20, 20, 60, 60);

		// 5. 切变变换

		AffineTransform tx4 = new AffineTransform();

		tx4.shear(0.5, 0);

		tx4.translate(160, 0);

		g2d.transform(tx4);

		g2d.fillRect(20, 20, 60, 60);

		g2d.setTransform(oldTrans);

	}

	// 通过setTransform方法基于绝对坐标进行变换

	private void paintWithSetTransform(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		AffineTransform oldTrans = g2d.getTransform();

		// 1. 先将坐标系的原点移到第三个泳道中，以便后面的代码可以和其他

		// 函数中的代码保持一致

		g2d.translate(0, getHeight() / 2);

		g2d.setColor(Color.blue);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx1 = new AffineTransform();

		// 2. 由于setTransform方法是直接替换为参数中的变换对象，之前驻留在Graphics中的

		// 变换对象都不在起作用了，因此这里在平移时，需要基于最原始的(窗体左上角)原点坐标

		// 进行平移变换，可以理解为绝对平移变换。

		// 可以看到translate中的y参数是相对于最初原点的平移距离。

		tx1.translate(160, getHeight() / 2);

		// 3. 将g2d中的变换对象直接替换为参数对象。

		g2d.setTransform(tx1);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx2 = new AffineTransform();

		// 4. 道理和上面的代码一样，可以看到这个时候x参数也设定为绝对距离了。

		tx2.translate(320, getHeight() / 2);

		tx2.rotate(Math.PI / 4);

		g2d.setTransform(tx2);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx3 = new AffineTransform();

		// 5. 需要注意的是tx3.rotate(-Math.PI / 4) 方法不需要再被调用了，因为

		// setTransform并不和之前的变换组合。

		tx3.translate(480, getHeight() / 2);

		tx3.scale(1.5, 1.5);

		g2d.setTransform(tx3);

		g2d.fillRect(20, 20, 60, 60);

		// 6. 切变变换,为了保持和其他泳道显示的结果一致，这里需要重新设定scale变换

		AffineTransform tx4 = new AffineTransform();

		tx4.translate(720, getHeight() / 2);

		tx4.shear(0.5, 0);

		tx4.scale(1.5, 1.5);

		g2d.setTransform(tx4);

		g2d.fillRect(20, 20, 60, 60);

		g2d.setTransform(oldTrans);

	}

	// 通过AffineTransform的静态工厂方法获取AffineTransform的具体子类

	// 再通过已经设定好变换的AffineTransform和形状的原始坐标生成目标

	// 形状，由于目标形状中已经是变换后的形状，因此Graphics2D可以直接渲染即可

	private void paintWithStaticTransformInstance(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		AffineTransform oldTrans = g2d.getTransform();

		// 1. 先将坐标系的原点移到第四个泳道中，以便后面的代码可以和其他

		// 函数中的代码保持一致

		g2d.translate(0, getHeight() * 3 / 4);

		g2d.setColor(Color.green);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx1 = AffineTransform.getTranslateInstance(160, 0);

		Rectangle rect = new Rectangle(20, 20, 60, 60);

		Shape newShape = tx1.createTransformedShape(rect);

		g2d.fill(newShape);

		// 2. 由于是直接通过Graphics2D渲染目标形状，因此这些变换并没有在

		// Graphics2D中进行过组合，因此每次变换都是独立的变换，需要指定

		// 距离原始坐标的绝对距离

		AffineTransform tx2 = AffineTransform.getTranslateInstance(320, 0);

		tx2.rotate(Math.PI / 4);

		newShape = tx2.createTransformedShape(rect);

		g2d.fill(newShape);

		AffineTransform tx3 = AffineTransform.getTranslateInstance(480, 0);

		tx3.scale(1.5, 1.5);

		newShape = tx3.createTransformedShape(rect);

		g2d.fill(newShape);

		AffineTransform tx4 = AffineTransform.getTranslateInstance(720, 0);

		tx4.scale(1.5, 1.5);

		tx4.shear(0.5, 0);

		newShape = tx4.createTransformedShape(rect);

		g2d.fill(newShape);

		g2d.setTransform(oldTrans);

	}

}