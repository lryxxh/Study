package tgraph3d.hreed;


import java.awt.Color;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 *
 * @author hadeslee
 */
public class CollisionDemoSoftware {

    public CollisionDemoSoftware() {
        build();
    }

    private void build() {
        //���ɷ�֧���
        BranchGroup bg = createSceneGraph();
        //�õ��Ƽ�������
        GraphicsConfiguration config =
        SimpleUniverse.getPreferredConfiguration();
        //�ô����ù���һ��������ʾ3D�Ļ���
        Canvas3D c=new Canvas3D(config);
        //�ô˻�������һ�����׵���������
        SimpleUniverse u = new SimpleUniverse(c);
        //���ù۲��ߵ�λ��
        u.getViewingPlatform().setNominalViewingTransform();
        //�����ǹ���õ�һ����֧�Ž���
        u.addBranchGraph(bg);
        //����һ��Frame����
        JFrame f=new JFrame("3D Hello");
        f.add(c);
        f.setSize(300,300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //�����֦��ϵķ���
    private BranchGroup createSceneGraph() {
        //����һ����֧����
        BranchGroup objRoot = new BranchGroup();
        //����һ���任��϶���
        TransformGroup objTrans = new TransformGroup();
        //������Ϊ��д��Ҳ����������ʱ���Խ��и���
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        //����һ���任����
        Transform3D trans = new Transform3D();
        Vector3f vec = new Vector3f(0.5f, 0, 0);
        //��������λ�ã�Ϊ0.5,0,0
        trans.setTranslation(vec);
        //���˱任����Ӧ�õ��任�����
        objTrans.setTransform(trans);
        //���ַ�ʽ
        Appearance app=new Appearance();
        //����
        Material mater=new Material();
        //�����Լ�����ɫ����ɫ
        mater.setEmissiveColor(new Color3f(Color.BLUE));
        app.setMaterial(mater);
        //����һ��Բ������ָ������ʾ��ʽ
        Cylinder box = new Cylinder(0.2f, 0.50f,app);
        //��Բ�����뵽�任����У�ʹ�����Ա��任
        objTrans.addChild(box);
        //�������任������뵽��֧�����
        objRoot.addChild(objTrans);
        //���õĿռ䷶Χ 
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        //�����ת
        MouseRotate behavior = new MouseRotate();
        behavior.setTransformGroup(objTrans);
        objTrans.addChild(behavior);
        behavior.setSchedulingBounds(bounds);
        //����ƶ�
        MouseTranslate tr = new MouseTranslate();
        tr.setTransformGroup(objTrans);
        objTrans.addChild(tr);
        tr.setSchedulingBounds(bounds);
        //����������
        MouseWheelZoom tr1 = new MouseWheelZoom();
        tr1.setTransformGroup(objTrans);
        objTrans.addChild(tr1);
        tr1.setSchedulingBounds(bounds);
        //Ӫ��ֱ��ƹ�Ϊ��ɫ
        Color3f light1Color = new Color3f(Color.RED);
        Vector3f light1Direction = new Vector3f(0f, 0f, -10f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);
        //Ԥ����һ�£����Ч�ʣ�Ҳ���Բ�Ҫ
        objRoot.compile();
        return objRoot;
    }

    public static void main(String[] args) {
        new CollisionDemoSoftware();
    }
}