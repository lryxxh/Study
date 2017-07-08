package swing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;

/**
 * TestApplet.java
 * Created by HHD at 2013-5-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author HHD 2013-5-24
 */
public class AffineTransform_Test extends JApplet {
    
    /**  */
    private static final long serialVersionUID = 5043065920751625426L;
    
    /* (non-Javadoc)
     * @see java.applet.Applet#init()
     */
    @Override
    public void init() {
        // TODO Auto-generated method stub
        this.setBackground(Color.BLACK);
        this.getRootPane().setBackground(Color.red);
        setSize(1800,1600);
        super.init();
    }
    public static void main(String[] args) {
        System.out.println(0x040600000000018dl);
    }
    /* (non-Javadoc)
     * @see java.awt.Container#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
       g2.setClip(-1000, -1000, 2000, 2000);
//        g2.fill(rectangle);
//        g2.drawLine(0, 0, 50, 100);
//        g2.setColor(Color.RED);
//        g2.drawLine(0, 0, 100, 100);
//        g2.setColor(Color.BLACK);
//        g2.drawLine(0, 0, 100, 50);
//        g2.drawLine(0, 0, (int)(100 * Math.cos(Math.toRadians(75))), (int)(100 * Math.sin(Math.toRadians(75))));
//        g2.drawOval(-(int)(50*tt), -(int)(50*tt), 2*(int)(50*tt), 2*(int)(50*tt));
//        AffineTransform at =  g2.getTransform();
//        at.setToRotation(2,2);
//        at.setToRotation(Math.toRadians(45));
//        at.setToScale(0.5, 0.5);
//        at.translate(50,50);
//        at.setToTranslation(50,50);
//        at.setToRotation(1, 1, 50, 50);
//        at.shear(1, 2);
        
        @SuppressWarnings("unused")
        Rectangle2D rectangle2d = new Rectangle2D.Double(200,200,100,100);
        for(int i = 0; i < 6; i ++) {
            g2.drawLine(0, 100 * i, 800, 100 * i);
        }
        Point2D start = new Point2D.Float(200,200);
        Point2D end = new Point2D.Float(300, 300);
        float[] dist = {0.0f, 0.2f, 1.0f};
        Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
        @SuppressWarnings("unused")
        LinearGradientPaint p =   new LinearGradientPaint(start, end, dist, colors);
        GeneralPath path = new GeneralPath();
        path.moveTo(180, 200);
        path.lineTo(150, 250);
        path.lineTo(200, 250);
        path.lineTo(220, 190);
        path.closePath();
        g2.draw(path);
//        AffineTransform affineTransform = g2.getTransform();
//        System.out.println(affineTransform.getTranslateX() + " translate " + affineTransform.getTranslateY());
//        System.out.println(affineTransform.getShearX() + " shear " + affineTransform.getShearY());
//        System.out.println(affineTransform.getScaleX() + " scale " + affineTransform.getScaleY());
//        affineTransform.translate(0, 500);
////        affineTransform.shear(0, 0);
//        affineTransform.scale(1, -1);
//        
//        System.out.println(affineTransform.getTranslateX() + " translate " + affineTransform.getTranslateY());
//        System.out.println(affineTransform.getShearX() + " shear " + affineTransform.getShearY());
//        System.out.println(affineTransform.getScaleX() + " scale " + affineTransform.getScaleY());
//  
////        AffineTransform transform = new AffineTransform(1, 0, 0, -1, 0,
////                0);
//        g2.setTransform(affineTransform);
        g2.setColor(Color.blue);
//        AffineTransform affineTransform = new AffineTransform(-1,0,0,-1,350,500);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(-1, -1);
        affineTransform.translate(350,500);
        
        Point2D srcpoint = new Point2D.Double(180,250);
        Point2D ptpoint = new Point2D.Double();
        affineTransform.transform(srcpoint, ptpoint);
        System.out.println(ptpoint);
        g2.setTransform(affineTransform);
        g2.draw(path);
//        for(int i = -10; i < 10;i++) {
//            g2.setColor(new Color(i));
//            g2.drawLine(-1000, i*100, 1000, 100*i);
//        }
//        g2.setColor(Color.RED);
        g2.fill(new Rectangle2D.Double(-1000,-1000,2000,2000));
        g2.setColor(Color.RED);
        g2.drawString("HelloWorld !", 800, 800);
//        g2.setPaint(p);
////        g2.getTransform().setToScale(-2.0, -1.0);
//        g2.scale(1.1, 1.1);
//        g2.setColor(Color.red);
//        g2.fill(rectangle2d);
//        Rectangle2D rectangle2d2 = new Rectangle2D.Double(300,300,100,100);
//        AffineTransform affineTransform = (AffineTransform) g2.getTransform().clone();
//        g2.getTransform().scale(-1.0, 1.0);
//        start = new Point2D.Float(300,300);
//        end = new Point2D.Float(400, 400);
//        p =   new LinearGradientPaint(start, end, dist, colors);
//        g2.setPaint(p);
//        g2.fill(rectangle2d2);
//        g2.setTransform(affineTransform);
        
//        g2.setColor(Color.GREEN);
////        System.out.println("-----------"+Math.toDegrees(Math.atan2(2, 1)));
//        System.out.println(Math.toDegrees(Math.atan(Math.sqrt(3)/2)));
//        g2.setTransform(at);
//        g2.fill(rectangle);
//        g2.setColor(Color.YELLOW);
//        g2.drawLine(0, 0, 200, 200);
    }

}
