# 【Java】原子类

## 前言

保证线程安全是 Java 并发编程必须要解决的重要问题。Java 从`原子性`、`可见性`、`有序性`这三大特性入手，确保多线程的数据一致性。

- 确保线程安全最常见的做法是利用锁机制（`Lock`、`sychronized`）来对共享数据做互斥同步，这样在同一个时刻，只有一个线程可以执行某个方法或者某个代码块，那么操作必然是原子性的，线程安全的。互斥同步最主要的问题是线程阻塞和唤醒所带来的性能问题；
- `volatile` 是轻量级的锁（自然比普通锁性能要好），它保证了共享变量在多线程中的可见性，但无法保证原子性。所以，它只能在一些特定场景下使用；
- 为了兼顾原子性以及锁带来的性能问题，Java 引入了 CAS （主要体现在 `Unsafe` 类）来实现非阻塞同步（也叫乐观锁），并基于 **CAS** ，提供了一套**原子工具类**。

## 原子类

原子变量类 **比锁的粒度更细，更轻量级**，并且对于在多处理器系统上实现高性能的并发代码来说是非常关键的。原子变量**将发生竞争的范围缩小到单个变量上**。

原子变量类可以分为 `4` 类

- 基本类型
  - `AtomicBoolean`：布尔类型原子类；
  - `AtomicInteger`：整型原子类；
  - `AtomicLong`：长整型原子类。
- 引用类型
  - `AtomicReference`：引用类型原子类；
  - `AtomicMarkableReference`：带有标记位的引用类型原子类；
  - `AtomicStampedReference`：带有版本号的引用类型原子类。
- 数组类型
  - `AtomicIntegerArray`：整形数组原子类；
  - `AtomicLongArray`：长整型数组原子类；
  - `AtomicReferenceArray`：引用类型数组原子类。
- 属性更新器类型
  - `AtomicIntegerFieldUpdater`：整型字段的原子更新器；
  - `AtomicLongFieldUpdater`：长整型字段的原子更新器；
  - `AtomicReferenceFieldUpdater`：原子更新引用类型里的字段。

### 基本类型

- `AtomicBoolean`：布尔类型原子类；
- `AtomicInteger`：整型原子类；
- `AtomicLong`：长整型原子类。

**线程不安全**

```java
package cn.com.codingce.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    
    static int value2 = 0;

    public static void main(String[] args) {
        System.out.println("==============ordinaryMethod==============");
        ExecutorService executor3 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor3.execute(()->{
                System.out.println(Thread.currentThread().getName() + ", value2: " + value2 + " ");
                value2++;
            });
        }
        executor3.shutdown();
    }

}
```

output

```java
==============Method==============
pool-1-thread-3, value2: 0 
pool-1-thread-2, value2: 0 
pool-1-thread-3, value2: 2 
pool-1-thread-4, value2: 0 
pool-1-thread-1, value2: 0 
pool-1-thread-3, value2: 3 
pool-1-thread-2, value2: 2 
pool-1-thread-5, value2: 1 
pool-1-thread-4, value2: 5 
pool-1-thread-1, value2: 5 
```

**synchronized案例**

```java
package cn.com.codingce.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    static int value1 = 0;

    public static void main(String[] args) {
        System.out.println("==============synchronizedMethod==============");
        ExecutorService executor2 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor2.execute(AtomicIntegerExample::synchronizedMethod);
        }
        executor2.shutdown();
    }

    public static synchronized void synchronizedMethod() {
        System.out.println(Thread.currentThread().getName() + ", value1: " + value1 + " ");
        value1++;
    }

}
```

output

```java
==============synchronizedMethod==============
pool-1-thread-1, value1: 0 
pool-1-thread-3, value1: 1 
pool-1-thread-2, value1: 2 
pool-1-thread-1, value1: 3 
pool-1-thread-4, value1: 4 
pool-1-thread-3, value1: 5 
pool-1-thread-4, value1: 6 
pool-1-thread-1, value1: 7 
pool-1-thread-5, value1: 8 
pool-1-thread-2, value1: 9
```

**AtomicInteger 案例**

```java
package cn.com.codingce.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    static AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("==============AtomicInteger==============");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor.execute(AtomicIntegerExample::atomicIntegerMethod);
        }
        executor.shutdown();
    }

    public static synchronized void atomicIntegerMethod() {
        System.out.println(Thread.currentThread().getName() + ", value: " + value.get() + " ");
        value.incrementAndGet();
    }

}

```

output

```java
==============AtomicInteger==============
pool-1-thread-2, value: 0 
pool-1-thread-1, value: 1 
pool-1-thread-3, value: 2 
pool-1-thread-5, value: 3 
pool-1-thread-1, value: 4 
pool-1-thread-2, value: 5 
pool-1-thread-4, value: 6 
pool-1-thread-1, value: 7 
pool-1-thread-5, value: 8 
pool-1-thread-3, value: 9
```

`AtomicInteger`实现可以看到如下定义

```java
private static final Unsafe unsafe = Unsafe.getUnsafe();
private static final long valueOffset;

static {
    try {
        valueOffset = unsafe.objectFieldOffset
            (AtomicInteger.class.getDeclaredField("value"));
    } catch (Exception ex) { throw new Error(ex); }
}

private volatile int value;
```

- `value` - value 属性使用 `volatile` 修饰，使得对 value 的修改在并发环境下对所有线程可见；
- `valueOffset` - value 属性的偏移量，通过这个偏移量可以快速定位到 value 字段，这个是实现 AtomicInteger 的关键；
- `unsafe` - Unsafe 类型的属性，它为 AtomicInteger 提供了 CAS 操作。

### 引用类型

Java 数据类型分为 **基本数据类型** 和 **引用数据类型** 两大类。

- `AtomicReference`：引用类型原子类；
- `AtomicMarkableReference`：带有标记位的引用类型原子类；
- `AtomicStampedReference`：带有版本号的引用类型原子类。

> `AtomicStampedReference` 类在引用类型原子类中，彻底地解决了 ABA 问题，其它的 CAS 能力与另外两个类相近，所以最具代表性。

基于 `AtomicReference` 实现一个简单的自旋锁

```java
package cn.com.codingce.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {
    public static int ticket = 10;

    public static void main(String[] args) {
        threadSafe();
    }

    private static void threadSafe() {
        SpinLock lock = new SpinLock();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyThread(lock));
        }
        executorService.shutdown();
    }

    /**
     * 基于 {@link AtomicReference} 实现的简单自旋锁
     */
    static class SpinLock {
        private AtomicReference<Thread> atomicReference = new AtomicReference<>();

        public void lock() {
            Thread current = Thread.currentThread();
            while (!atomicReference.compareAndSet(null, current)) ;
        }

        public void unlock() {
            Thread current = Thread.currentThread();
            while (!atomicReference.compareAndSet(current, null)) ;
        }
    }

    static class MyThread implements Runnable {

        private SpinLock lock;

        public MyThread(SpinLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (ticket > 0) {
                lock.lock();
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " 卖出了第 " + ticket + " 张票");
                    ticket--;
                }
                lock.unlock();
            }
        }
    }
}
```

output

```java
pool-1-thread-1 卖出了第 10 张票
pool-1-thread-3 卖出了第 9 张票
pool-1-thread-2 卖出了第 8 张票
pool-1-thread-1 卖出了第 7 张票
pool-1-thread-2 卖出了第 6 张票
pool-1-thread-1 卖出了第 5 张票
pool-1-thread-3 卖出了第 4 张票
pool-1-thread-1 卖出了第 3 张票
pool-1-thread-2 卖出了第 2 张票
pool-1-thread-1 卖出了第 1 张票
```

原子类的实现基于 CAS 机制，而 CAS 存在 **ABA** 问题，为了解决 ABA 问题，才有了 `AtomicMarkableReference` 和 `AtomicStampedReference`。

`AtomicMarkableReference` 使用一个布尔值作为标记，修改时在 true / false 之间切换。这种策略不能根本上解决 ABA 问题，但是可以降低 ABA 发生的几率。常用于缓存或者状态描述这样的场景。

```java
package cn.com.codingce.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceExample {

    private final static String INIT_STR = "后端码匠";

    public static void main(String[] args) throws InterruptedException {

        final AtomicMarkableReference<String> amr = new AtomicMarkableReference<>(INIT_STR, false);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                System.out.println("当前线程: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(Math.abs((int) (Math.random() * 100)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String name = Thread.currentThread().getName();
                if (amr.compareAndSet(INIT_STR, name, amr.isMarked(), !amr.isMarked())) {
                    System.out.println(Thread.currentThread().getName() + " 修改了对象！");
                    System.out.println("新的对象为：" + amr.getReference());
                }
            });
        }

        executorService.shutdown();
        System.out.println(executorService.awaitTermination(3, TimeUnit.SECONDS));
        ;
    }
}
```

output

```java
当前线程: pool-1-thread-1
当前线程: pool-1-thread-3
当前线程: pool-1-thread-2
pool-1-thread-3 修改了对象！
新的对象为：pool-1-thread-3
true
```

**`AtomicStampedReference` 使用一个整型值做为版本号，每次更新前先比较版本号，如果一致，才进行修改**。通过这种策略，可以根本上解决 ABA 问题。

```java
package cn.com.codingce.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceExample {

    private final static String INIT_REF = "后端码匠";
    private final static AtomicStampedReference<String> asr = new AtomicStampedReference<>(INIT_REF, 0);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("初始对象为：" + asr.getReference());
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                System.out.println("当前线程: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(Math.abs((int) (Math.random() * 100)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final int stamp = asr.getStamp();
                if (asr.compareAndSet(INIT_REF, Thread.currentThread().getName(), stamp, stamp + 1)) {
                    System.out.println(Thread.currentThread().getName() + " 修改了对象！");
                    System.out.println("新的对象为：" + asr.getReference());
                }
            });
        }

        executorService.shutdown();
        System.out.println(executorService.awaitTermination(3, TimeUnit.SECONDS));
    }

}
```

output

```bash
初始对象为：后端码匠
当前线程: pool-1-thread-1
当前线程: pool-1-thread-2
当前线程: pool-1-thread-3
pool-1-thread-2 修改了对象！
新的对象为：pool-1-thread-2
true
```

### 数组类型

- `AtomicIntegerArray`：整形数组原子类；
- `AtomicLongArray`：长整型数组原子类；
- `AtomicReferenceArray`：引用类型数组原子类。

**AtomicIntegerArray示例**

```java
package cn.com.codingce.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayExample {

    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    public static void main(final String[] arguments) throws InterruptedException {
        System.out.println("Init Values: ");
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.set(i, i);
            System.out.print(atomicIntegerArray.get(i) + " ");
        }
        System.out.println();
        System.out.println("========================================");
        Thread t1 = new Thread(new Increment());
        Thread t2 = new Thread(new Compare());
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Values: ");
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.print(atomicIntegerArray.get(i) + " ");
        }
        System.out.println();
    }

    static class Increment implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                int value = atomicIntegerArray.incrementAndGet(i);
                System.out.println(Thread.currentThread().getName() + ", index = " + i + ", value = " + value);
            }
        }
    }

    static class Compare implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                boolean swapped = atomicIntegerArray.compareAndSet(i, 1, 100);
                if (swapped) {
                    System.out.println(Thread.currentThread().getName() + " swapped, index = " + i + ", value = 3");
                }
            }
        }
    }

}
```

output

```java
Init Values: 
0 1 2 3 4 5 6 7 8 9 
========================================
Thread-0, index = 0, value = 1
Thread-0, index = 1, value = 2
Thread-1 swapped, index = 0, value = 3
Thread-0, index = 2, value = 3
Thread-0, index = 3, value = 4
Thread-0, index = 4, value = 5
Thread-0, index = 5, value = 6
Thread-0, index = 6, value = 7
Thread-0, index = 7, value = 8
Thread-0, index = 8, value = 9
Thread-0, index = 9, value = 10
Final Values: 
100 2 3 4 5 6 7 8 9 10 
```

### 属性更新器类型

更新器类支持基于反射机制的更新字段值的原子操作。

- `AtomicIntegerFieldUpdater`：整型字段的原子更新器。
- `AtomicLongFieldUpdater`：长整型字段的原子更新器。
- `AtomicReferenceFieldUpdater`：原子更新引用类型里的字段。

这些类的使用有一定限制：

- 因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法 `newUpdater()` 创建一个更新器，并且需要设置想要更新的类和属性；
- 字段必须是 `volatile` 类型的；
- 不能作用于静态变量（`static`）；
- 不能作用于常量（`final`）。

```java
package cn.com.codingce.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaterExample {

    static User user = new User("欢迎关注");

    static AtomicReferenceFieldUpdater<User, String> updater = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyThread());
        }
        executorService.shutdown();
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            if (updater.compareAndSet(user, "欢迎关注", "后端码匠")) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 已修改 name = " + user.getName());
            } else {
                System.out.println(Thread.currentThread().getName() + " 已被其他线程修改");
            }
        }

    }

    static class User {
        volatile String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

    }
}
```

output

```java
pool-1-thread-2 已被其他线程修改
pool-1-thread-3 已被其他线程修改
pool-1-thread-2 已被其他线程修改
pool-1-thread-3 已被其他线程修改
pool-1-thread-1 已修改 name = 后端码匠
```

### 方案对比

**Atomic|synchronized**

**1、背后原理的不同**

**原子类**：它保证线程安全的原理是利用了 CAS 操作。从这一点上看，虽然原子类和 synchronized 都能保证线程安全，但是其实现原理是大有不同的；

**synchronized**：背后的 **monitor** 锁，也就是 synchronized 原理，同步方法和同步代码块的背后原理会有少许差异，但总体思想是一致的：在执行同步代码之前，需要首先获取到 **monitor** 锁，执行完毕后，再释放锁。
**2、使用范围的不同**

**原子类**：它的使用范围是比较局限的。因为一个原子类仅仅是一个对象，不够灵活；

**synchronized** 它的使用范围要广泛得多。比如说 synchronized 既可以修饰一个方法，又可以修饰一段代码，相当于可以根据我们的需要，非常灵活地去控制它的应用范围；

所以仅有少量的场景，例如计数器等场景，可以使用原子类。而在其他更多的场景下，如果原子类不适用，那么就可以考虑用 synchronized 来解决这个问题。

**3、粒度的区别**

**原子类**：原子变量的粒度是比较小的，它可以把竞争范围缩小到变量级别；

**synchronized**：通常情况下，synchronized 锁的粒度都要大于原子变量的粒度；

如果只把一行代码用 synchronized 给保护起来的话，有一点杀鸡焉用牛刀的感觉。

**4、性能的区别，同时也是悲观锁和乐观锁的区别**

因为 **synchronized 是一种典型的悲观锁，而原子类恰恰相反，它利用的是乐观锁**。所以，在比较 synchronized 和 AtomicInteger 的时候，其实也就相当于比较了悲观锁和乐观锁的区别；

从性能上来考虑的话，**悲观锁的操作相对来讲是比较重量级的**。因为 synchronized 在竞争激烈的情况下，会让拿不到锁的线程阻塞，而原子类是永远不会让线程阻塞的。不过，**虽然 synchronized 会让线程阻塞，但是这并不代表它的性能就比原子类差**；

>悲观锁的**开销是固定的**，也是一劳永逸的。随着时间的增加，这种开销并不会线性增长，而乐观锁虽然在短期内的开销不大，但是随着时间的增加，它的**开销也是逐步上涨的**

所以从性能的角度考虑，它们没有一个孰优孰劣的关系，而是要区分具体的使用场景。**在竞争非常激烈的情况下，推荐使用 synchronized；而在竞争不激烈的情况下，使用原子类会得到更好的效果**。

**注意**

**synchronized** 的性能随着 JDK 的升级，也得到了不断的优化。**synchronized 会从无锁升级到偏向锁，再升级到轻量级锁，最后才会升级到让线程阻塞的重量级锁**。因此synchronized 在竞争不激烈的情况下，性能也是不错的，不需要“**谈锁色变**”。

## Unsafe类

实际上Atomic包里的类基本都是使用Unsafe实现的包装类。也就是上面的原子类实现过程中都会用到Unsafe类。Java中的Unsafe类提供了类似C++手动管理内存的能力。Unsafe类，全限定名是**sun.misc.Unsafe**，从名字可以看出来这个类对普通程序员来说是“危险”的，一般应用开发者不会用到这个类。Unsafe类是"`final`"的，不允许继承。且构造函数是private的，无法在外部对其进行实例化。

 **Unsafe的典型应用**

- 堆外内存操作。DirectByteBuffer是Java用于实现堆外内存的一个重要类，通常用在通信过程中做缓冲池，如在Netty、MINA等NIO框架中应用广泛；DirectByteBuffer对于堆外内存的创建、 使用、销毁等逻辑均由Unsafe提供的堆外内存API来实现；
- ReentrantLock、Atomic等API通过CAS修改state等等，底层用的也是Unsafe；
- 线程调度：如LockSupport.park()和LockSupport.unpark()实现线程的阻塞和唤醒。而 LockSupport的park、unpark方法实际是调用Unsafe的park、unpark方式来实现；
- 内存屏障,通过Unsafe的loadFence方法加入一个内存屏障，目的是避免指令重排。