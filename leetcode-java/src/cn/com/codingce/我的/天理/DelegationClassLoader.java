package cn.com.codingce.我的.天理;

import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * 怎么自定义一个类加载器？
 *
 * 加载一个类时，一般是调用类加载器的loadClass()方法来加载一个类，loadClass()方法的工作流程如下：
 *
 * 1.先调用findLoadedClass(className)来获取这个类，判断类是否已加载。
 *
 * 2.如果未加载，如果父类加载器不为空，调用父类加载器的loadClass()来加载这个类，父类加载器为空，就调用父类加载器加载这个类。
 *
 * 3.父类加载器加载失败，那么调用该类加载器findClass(className)方法来加载这个类。
 *
 * 所以我们一般自定义类加载器都是继承ClassLoader，重写findClass()方法，来实现类加载，这样不会违背双亲委派类加载机制，也可以通过重写loadClass()方法进行类加载，但是这样会违背双亲委派类加载机制。
 *
 *
 *
 *
 *
 * @author notfound9
 */
public class DelegationClassLoader extends ClassLoader {
    private String classpath;

    public DelegationClassLoader(String classpath, ClassLoader parent) {
        super(parent);
        this.classpath = classpath;
    }

    @Override
    protected Class<?>
    findClass(String name) throws ClassNotFoundException {
        InputStream is = null;
        try {
            String classFilePath = this.classpath + name.replace(".", "/") + ".class";
            is = new FileInputStream(classFilePath);
            byte[] buf = new byte[is.available()];
            is.read(buf);
            return defineClass(name, buf, 0, buf.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new IOError(e);
                }
            }
        }
    }

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            MalformedURLException {
        sun.applet.Main main1 = new sun.applet.Main();

        DelegationClassLoader cl = new DelegationClassLoader("java-study/target/classes/",
                getSystemClassLoader());
        String name = "sun.applet.Main";
        Class<?> clz = cl.loadClass(name);
        Object main2 = clz.newInstance();

        System.out.println("main1 class: " + main1.getClass());
        System.out.println("main2 class: " + main2.getClass());
        System.out.println("main1 classloader: " + main1.getClass().getClassLoader());
        System.out.println("main2 classloader: " + main2.getClass().getClassLoader());
        ClassLoader itrCl = cl;
        while (itrCl != null) {
            System.out.println(itrCl);
            itrCl = itrCl.getParent();
        }
    }
}
