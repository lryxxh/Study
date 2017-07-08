/**
 * T_T.java
 * Created by liurenyong at 2013-7-17
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package reflect_beans;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 
 * @author liurenyong 2013-7-17
 */
@SuppressWarnings("unused")
public class BeanInfo_Descriptor {
    
    private int a = 90;
    private int b = 100;
    private String str = "";
    private String address = "2232";
    
    private class B {
        private int c = 10;
        
        private int d = 100;
        
        private void test() {
            
        }
        
        private void tt() {
            
        }
    }
    
    private static class DD{
        private int dd = 0;
        private static int ee = 100;
        
        private void t1() {
            
        }
        
        private static void t2(){}
    }
    
    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public int getB() {
        return b;
    }
    public void setB(int b) {
        this.b = b;
    }
    public String getStr() {
        return str;
    }
    public void setStr(String str) {
        this.str = str;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public static void main(String[] args) {
        Introspector in;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(BeanInfo_Descriptor.class);
            BeanInfo[] beanInfos = beanInfo.getAdditionalBeanInfo();
            BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
            int eventIndex = beanInfo.getDefaultEventIndex();
            int propertyIndex = beanInfo.getDefaultPropertyIndex();
            EventSetDescriptor[] eventSetDescriptors = beanInfo.getEventSetDescriptors();
            MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for(MethodDescriptor methodDescriptor : methodDescriptors) {
                String displayName = methodDescriptor.getDisplayName();
                Method method = methodDescriptor.getMethod();
                String name = methodDescriptor.getName();
                String shortDescription = methodDescriptor.getShortDescription();
                System.out.println(displayName);
                System.out.println(method);
                System.out.println(name);
                System.out.println(shortDescription);
                ParameterDescriptor[] parameterDescriptors = methodDescriptor.getParameterDescriptors();
                if(null != parameterDescriptors) {
                    for (ParameterDescriptor parameterDescriptor:parameterDescriptors) {
                        String displayName2 = parameterDescriptor.getDisplayName();
                        String d = parameterDescriptor.getName();
                        String shortDescString = parameterDescriptor.getShortDescription();
                        System.err.println("        "+displayName2);
                        System.err.println("        "+d);
                        System.err.println("        "+shortDescString);
                    }
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
