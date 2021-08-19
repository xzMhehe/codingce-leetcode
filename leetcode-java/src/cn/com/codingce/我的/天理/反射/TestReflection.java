package cn.com.codingce.我的.天理.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author mxz
 */
public class TestReflection {
    public static void main(String[] args) {


        try {
            Class hClass = Class.forName("cn.com.codingce.我的.天理.反射.Hero");
            Method setName = hClass.getMethod("setName", String.class);
            Method getName = hClass.getMethod("getName");
            Method setId = hClass.getMethod("setId", int.class);
            Method getId = hClass.getMethod("getId");


            // 通过 Constructor 对象的 newInstance() 方法
            // 根据 Class 对象实例获取 Constructor 对象   (通过反射创建类对象)
            Constructor heroConstructor = hClass.getConstructor();

            // 使用 Constructor 对象的 newInstance 方法获取反射类对象
            Object o = heroConstructor.newInstance();


            // 通过 Class 对象的 newInstance() 方法 (通过反射创建类对象)
            Hero hero = (Hero) hClass.newInstance();

            setName.invoke(o, "后端码匠");
            setName.invoke(hero, "欢迎关注");
            setId.invoke(hero, 16);


            System.out.println("反射实现: " + getName.invoke(o));
            System.out.println("反射实现: " + getName.invoke(hero));
            System.out.println("反射实现: " + getId.invoke(hero));
            System.out.println("传统: " + hero.getName());


            // 通过反射创建类对象
            Class clz = Hero.class;
            // 我们通过 Class 对象的 getFields() 方法可以获取 Class 类的属性，但无法获取私有属性
            Field[] fields = clz.getFields();
            for (Field field : fields) {
                System.out.println(field.getName());
            }

            // 而如果使用 Class 对象的 getDeclaredFields() 方法则可以获取包括私有属性在内的所有属性
            System.out.println("getDeclaredFields()所有属性");
            Field[] fields2 = clz.getDeclaredFields();
            for (Field field : fields2) {
                System.out.println(field.getName());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
