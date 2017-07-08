/**
 * 
 */
package swing.triple_buffer;

import java.awt.geom.Point2D;

public class GeometryUtil {  
    // ����֮��ľ���  
    public static double distanceOfPoints(Point2D p1, Point2D p2) {  
        double disX = p2.getX() - p1.getX();  
        double disY = p2.getY() - p1.getY();  
        double dis = Math.sqrt(disX * disX + disY * disY);  
  
        return dis;  
    }  
  
    // ������е�  
    public static Point2D middlePoint(Point2D p1, Point2D p2) {  
        double x = (p1.getX() + p2.getX()) / 2;  
        double y = (p1.getY() + p2.getY()) / 2;  
  
        return new Point2D.Double(x, y);  
    }  
  
    // ����������ֱ���ϣ��Դ�startPoint��endPointΪ������startPoint�ľ���disToStartPoint�ĵ�  
    public static Point2D extentPoint(Point2D startPoint, Point2D endPoint, double disToStartPoint) {  
        double disX = endPoint.getX() - startPoint.getX();  
        double disY = endPoint.getY() - startPoint.getY();  
        double dis = Math.sqrt(disX * disX + disY * disY);  
        double sin = (endPoint.getY() - startPoint.getY()) / dis;  
        double cos = (endPoint.getX() - startPoint.getX()) / dis;  
        double deltaX = disToStartPoint * cos;  
        double deltaY = disToStartPoint * sin;  
  
        return new Point2D.Double(startPoint.getX() + deltaX, startPoint.getY() + deltaY);  
    }  
  
    // ��ԭ�����ת�������������ת���������ƶ���ԭ�㣬��ת��Ȼ�����ƻ�ȥ  
    // cos�� -sin�� 0  
    // sin�� +con�� 0  
    // 0000 +0000 1  
    // x = r*cos��, y = r*sin��  
    // x' = r*cos(��+��) = r*cos��*cos�� - r*sin��*sin�� = x*cos�� - y*sin��  
    // y' = r*sin(��+��) = r*sin��*cos�� + r*cos��*sin�� = x*sin�� + y*cos��  
    // (x, y)��Բ����תdegree��  
    public static Point2D rotate(double x, double y, double degree) {  
        return rotate(x, y, 0, 0, degree);  
    }  
  
    // (x, y)��(ox, oy)��תdegree��  
    public static Point2D rotate(double x, double y, double ox, double oy, double degree) {  
        x -= ox;  
        y -= oy;  
  
        double cos = Math.cos(Math.toRadians(degree));  
        double sin = Math.sin(Math.toRadians(degree));  
  
        double temp = x * cos - y * sin;  
        y = x * sin + y * cos;  
        x = temp;  
  
        return new Point2D.Double(x + ox, y + oy);  
    }  
  
    public static void main(String[] args) {  
        Point2D p = rotate(50, 10, 10);  
        System.out.println(p);  
        p = rotate(100, 60, 50, 50, 10);  
        System.out.println(p);  
    }  
}  