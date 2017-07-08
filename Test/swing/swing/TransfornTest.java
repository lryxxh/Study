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

	// ͨ��Graphics�����ṩ�ı任�������б任

	private void paintWithGraphicsMethod(Graphics g) {

              //Graphics�еı任������ϱ任��ʽ��

              Graphics2D g2d = (Graphics2D) g;

              AffineTransform oldTrans = g2d.getTransform();

              g2d.setColor(Color.yellow);

              g2d.fillRect(20, 20, 60, 60);

              //1. ����ԭ�����ƽ�Ʊ任

              g2d.translate(160, 0);

              g2d.fillRect(20, 20, 60, 60);

              //2. ����1)ƽ�Ʊ任�����ƽ�Ʊ任

              g2d.translate(160, 0);

              //3. ��2)ƽ�ƵĻ����Ͻ�����ת�任

              g2d.rotate(Math.PI / 4);

              g2d.fillRect(20, 20, 60, 60);

              //4. ������Ҫ�ر�ע�⣬����3)���������ת�任�����������ֱ�ӽ���

              //ƽ�Ʊ任xʱ����Ϊ����ϵ�ĽǶȷ����˱仯�����Բ��ܵõ�������ƽ��

              //Ч������ƽ��ǰ��Ҫ��3)�е���ת�ٷ�����ת������

              g2d.rotate(-Math.PI / 4);

              g2d.translate(160, 0);

              //5. ��4)�Ļ��������Ŵ�任

              g2d.scale(1.5, 1.5);

              g2d.fillRect(20, 20, 60, 60);

              //6. ���б�仯������ͨ������shear()������������������һ�������б�任

              g2d.translate(160, 0);

              g2d.shear(0.5, 0);

              g2d.fillRect(20, 20, 60, 60);

              //7. ������Ҫ��ԭ�������Ӱ�����ı任

              g2d.setTransform(oldTrans);

          }

	// �÷�����paintWithGraphicsMethod������һ�µģ�ֻ��Ӧ�õķ�����Ϊ

	// AffineTransform�ṩ�ı任����

	private void paintWithTransform(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		AffineTransform oldTrans = g2d.getTransform();

		// 1. �Ƚ�����ϵ��ԭ���Ƶ��ڶ���Ӿ���У��Ա����Ĵ�����Ժ�����

		// �����еĴ��뱣��һ��

		g2d.translate(0, getHeight() / 4);

		g2d.setColor(Color.red);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx1 = new AffineTransform();

		tx1.translate(160, 0);

		// 2. �����õ���transform������Graphics�����ڲ�Ҳ����Ϸ�ʽ�ģ�

		// ���仰˵�������transform����������ڱ��α仯�Ľ����

		g2d.transform(tx1);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx2 = new AffineTransform();

		tx2.translate(160, 0);

		tx2.rotate(Math.PI / 4);

		// 3. ����transform������1)��transform�Ľ�����������

		g2d.transform(tx2);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx3 = new AffineTransform();

		// 4. �����rotate�����ú������paintWithGraphicsMethod()������4)��һ�µġ�

		tx3.rotate(-Math.PI / 4);

		tx3.translate(160, 0);

		tx3.scale(1.5, 1.5);

		g2d.transform(tx3);

		g2d.fillRect(20, 20, 60, 60);

		// 5. �б�任

		AffineTransform tx4 = new AffineTransform();

		tx4.shear(0.5, 0);

		tx4.translate(160, 0);

		g2d.transform(tx4);

		g2d.fillRect(20, 20, 60, 60);

		g2d.setTransform(oldTrans);

	}

	// ͨ��setTransform�������ھ���������б任

	private void paintWithSetTransform(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		AffineTransform oldTrans = g2d.getTransform();

		// 1. �Ƚ�����ϵ��ԭ���Ƶ�������Ӿ���У��Ա����Ĵ�����Ժ�����

		// �����еĴ��뱣��һ��

		g2d.translate(0, getHeight() / 2);

		g2d.setColor(Color.blue);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx1 = new AffineTransform();

		// 2. ����setTransform������ֱ���滻Ϊ�����еı任����֮ǰפ����Graphics�е�

		// �任���󶼲����������ˣ����������ƽ��ʱ����Ҫ������ԭʼ��(�������Ͻ�)ԭ������

		// ����ƽ�Ʊ任���������Ϊ����ƽ�Ʊ任��

		// ���Կ���translate�е�y��������������ԭ���ƽ�ƾ��롣

		tx1.translate(160, getHeight() / 2);

		// 3. ��g2d�еı任����ֱ���滻Ϊ��������

		g2d.setTransform(tx1);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx2 = new AffineTransform();

		// 4. ���������Ĵ���һ�������Կ������ʱ��x����Ҳ�趨Ϊ���Ծ����ˡ�

		tx2.translate(320, getHeight() / 2);

		tx2.rotate(Math.PI / 4);

		g2d.setTransform(tx2);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx3 = new AffineTransform();

		// 5. ��Ҫע�����tx3.rotate(-Math.PI / 4) ��������Ҫ�ٱ������ˣ���Ϊ

		// setTransform������֮ǰ�ı任��ϡ�

		tx3.translate(480, getHeight() / 2);

		tx3.scale(1.5, 1.5);

		g2d.setTransform(tx3);

		g2d.fillRect(20, 20, 60, 60);

		// 6. �б�任,Ϊ�˱��ֺ�����Ӿ����ʾ�Ľ��һ�£�������Ҫ�����趨scale�任

		AffineTransform tx4 = new AffineTransform();

		tx4.translate(720, getHeight() / 2);

		tx4.shear(0.5, 0);

		tx4.scale(1.5, 1.5);

		g2d.setTransform(tx4);

		g2d.fillRect(20, 20, 60, 60);

		g2d.setTransform(oldTrans);

	}

	// ͨ��AffineTransform�ľ�̬����������ȡAffineTransform�ľ�������

	// ��ͨ���Ѿ��趨�ñ任��AffineTransform����״��ԭʼ��������Ŀ��

	// ��״������Ŀ����״���Ѿ��Ǳ任�����״�����Graphics2D����ֱ����Ⱦ����

	private void paintWithStaticTransformInstance(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		AffineTransform oldTrans = g2d.getTransform();

		// 1. �Ƚ�����ϵ��ԭ���Ƶ����ĸ�Ӿ���У��Ա����Ĵ�����Ժ�����

		// �����еĴ��뱣��һ��

		g2d.translate(0, getHeight() * 3 / 4);

		g2d.setColor(Color.green);

		g2d.fillRect(20, 20, 60, 60);

		AffineTransform tx1 = AffineTransform.getTranslateInstance(160, 0);

		Rectangle rect = new Rectangle(20, 20, 60, 60);

		Shape newShape = tx1.createTransformedShape(rect);

		g2d.fill(newShape);

		// 2. ������ֱ��ͨ��Graphics2D��ȾĿ����״�������Щ�任��û����

		// Graphics2D�н��й���ϣ����ÿ�α任���Ƕ����ı任����Ҫָ��

		// ����ԭʼ����ľ��Ծ���

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