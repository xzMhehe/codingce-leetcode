# 【Java】生产者消费者模型

## 0x1 前言

生产者和消费者问题是线程模型中的经典问题：生产者和消费者在同一时间段内共用同一个存储空间，生产者往存储空间中添加产品，消费者从存储空间中取走产品，当存储空间为空时，消费者阻塞，当存储空间满时，生产者阻塞。

<img src="https://raw.githubusercontent.com/xzMhehe/StaticFile_CDN/main/static/img20230108202630.png" style="zoom:67%;" />

## 0x2 实现

**以下用4种方式来实现生产者消费者模型**
### 0x21 wait()和notify()方法

这也是最简单最基础的实现，缓冲区满和为空时都调用wait()方法等待，当生产者生产了一个产品或者消费者消费了一个产品之后会唤醒所有线程。

```java
package cn.com.codingce.juc.生产者消费者模型;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产者和消费者, wait()和notify()方式实现
 * <p>
 * 后端码匠
 *
 * @author mxz
 */
public class WaitNotifyExample {

    private static Integer count = 0;
    private static final Integer maxCount = 10;
    private final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        WaitNotifyExample example = new WaitNotifyExample();
        service.execute(example.new Producer());
        service.execute(example.new Producer());
        service.execute(example.new Consumer());
        service.shutdown();
        System.out.println(service.awaitTermination(10L, TimeUnit.SECONDS));
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    // 缓冲区满调用wait()方法等待
                    while (Objects.equals(count, maxCount)) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("*************** " + Thread.currentThread().getName() + ", 生产者生产, 目前总共有: " + count + " ***************");
                    LOCK.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    // 缓冲区为空调用wait()方法等待
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("--------------- " + Thread.currentThread().getName() + ", 消费者消费, 目前总共有: " + count + " ---------------");
                    LOCK.notifyAll();
                }
            }
        }
    }
    
}
```

output

```bash
*************** pool-1-thread-2, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 2 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 3 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 2 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 3 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 4 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 3 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 4 ***************
false
*************** pool-1-thread-1, 生产者生产, 目前总共有: 5 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 6 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 5 ---------------
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 4 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 5 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 6 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 7 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 6 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 7 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 8 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 7 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 8 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 9 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 10 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 9 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 10 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 9 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 10 ***************
```

### 0x22 可重入锁ReentrantLock

`java.util.concurrent.lock` 中的 `Lock` 框架是锁定的一个抽象，通过对`lock`的`lock()`方法和`unlock()`方法实现了对锁的显示控制，而`synchronize()`则是对锁的隐性控制。
可重入锁，也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响，简单来说，该锁维护这一个与获取锁相关的计数器，如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，函数调用结束计数器就减1，然后锁需要被释放两次才能获得真正释放。已经获取锁的线程进入其他需要相同锁的同步代码块不会被阻塞。

```java
package cn.com.codingce.juc.生产者消费者模型;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者, {@link ReentrantLock} 方式实现
 * <p>
 * 后端码匠
 *
 * @author mxz
 */
public class ReentrantLockExample {

    private static Integer count = 0;
    private static final Integer maxCount = 10;
    // 创建一个锁对象
    private final Lock lock = new ReentrantLock();
    // 使用 Condition 对象来实现 wait 和 notify 的功能
    // 创建两个条件变量, 一个为缓冲区非满, 一个为缓冲区非空
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        ReentrantLockExample example = new ReentrantLockExample();
        service.execute(example.new Producer());
        service.execute(example.new Consumer());
        service.shutdown();
        System.out.println(service.awaitTermination(15L, TimeUnit.SECONDS));
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 获取锁
                lock.lock();
                try {
                    while (Objects.equals(count, maxCount)) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("*************** " + Thread.currentThread().getName() + ", 生产者生产, 目前总共有: " + count + " ***************");
                    // 唤醒消费者
                    notEmpty.signal();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == 0) {
                        try {
                            notEmpty.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("--------------- " + Thread.currentThread().getName() + ", 消费者消费, 目前总共有: " + count + " ---------------");
                    notFull.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
```

output

```bash
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
false
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-2, 消费者消费, 目前总共有: 0 ---------------
```

### 0x23 阻塞队列BlockingQueue

`BlockingQueue` 即`阻塞队列`，从阻塞这个词可以看出，`在某些情况下对阻塞队列的访问可能会造成阻塞`。被阻塞的情况主要有如下两种：

- 当队列满了的时候进行入队列操作；

- 当队列空了的时候进行出队列操作。

因此，当一个线程对已经满了的阻塞队列进行入队操作时会阻塞，除非有另外一个线程进行了出队操作，当一个线程对一个空的阻塞队列进行出队操作时也会阻塞，除非有另外一个线程进行了入队操作。从上可知，`阻塞队列是线程安全的`。
下面是BlockingQueue接口的一些方法：

| 操作 | 抛异常     | 特定值   | 阻塞    | 超时                        |
| ---- | ---------- | -------- | ------- | --------------------------- |
| 插入 | add(o)     | offer(o) | put(o)  | offer(o, timeout, timeunit) |
| 移除 | remove(o)  | poll(o)  | take(o) | poll(timeout, timeunit)     |
| 检查 | element(o) | peek(o)  |         |                             |


这四类方法分别对应的是：

- ThrowsException：如果操作不能马上进行，则抛出异常；
- Special Value：如果操作不能马上进行，将会返回一个特殊的值，一般是true或者false；
- Blocks：如果操作不能马上进行，操作会被阻塞；
- TimesOut：如果操作不能马上进行，操作会被阻塞指定的时间，如果指定时间没执行，则返回一个特殊值，一般是true或者false。

下面来看由阻塞队列实现的生产者消费者模型，这里使用 take() 和 put() 方法，这里生产者和生产者，消费者和消费者之间不存在同步，所以会出现连续生成和连续消费的现象。

```java
package cn.com.codingce.juc.生产者消费者模型;

import java.util.concurrent.*;

/**
 * 使用 {@link BlockingQueue} 实现生产者消费者模型
 * <p>
 * 后端码匠
 *
 * @author mxz
 */
public class BlockingQueueExample {

    private static Integer count = 0;
    // 创建一个阻塞队列
    final BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueExample example = new BlockingQueueExample();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(example.new Producer());
        service.execute(example.new Producer());
        service.execute(example.new Consumer());
        service.shutdown();
        System.out.println(service.awaitTermination(15L, TimeUnit.SECONDS));
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    blockingQueue.put(1);
                    count++;
                    System.out.println("*************** " + Thread.currentThread().getName() + ", 生产者生产, 目前总共有: " + count + " ***************");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    blockingQueue.take();
                    count--;
                    System.out.println("--------------- " + Thread.currentThread().getName() + ", 消费者消费, 目前总共有: " + count + " ---------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
```

output

```bash
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 1 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 1 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 2 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 2 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 1 ---------------
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 1 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 3 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 2 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 5 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 5 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 4 ---------------
false
*************** pool-1-thread-1, 生产者生产, 目前总共有: 5 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 6 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 5 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 6 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 6 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 6 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 7 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 7 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 8 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 6 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 7 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 8 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 8 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 9 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 8 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 10 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 9 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 10 ***************
```

### 0x24 信号量Semaphore

**Semaphore**（信号量）是**用来控制同时访问特定资源的线程数量**，它通过协调各个线程，以保证合理的使用公共资源。Java中的Semaphore维护了一个许可集，一开始先设定这个许可集的数量，可以使用 acquire() 方法获得一个许可，当许可不足时会被阻塞，release() 添加一个许可。在下列代码中，还加入了另外一个 mutex 信号量，维护生产者消费者之间的同步关系，保证生产者和消费者之间的交替进行。

```java
package cn.com.codingce.juc.生产者消费者模型;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * {@link Semaphore} （信号量）
 * <p>
 * 后端码匠
 *
 * @author mxz
 */
public class SemaphoreExample {

    private static Integer count = 0;
    // 创建三个信号量
    final Semaphore notFull = new Semaphore(10);
    final Semaphore notEmpty = new Semaphore(0);
    final Semaphore mutex = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        SemaphoreExample example = new SemaphoreExample();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(example.new Producer());
        service.execute(example.new Producer());
        service.execute(example.new Consumer());
        service.shutdown();
        System.out.println(service.awaitTermination(15L, TimeUnit.SECONDS));
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    notFull.acquire();
                    mutex.acquire();
                    count++;
                    System.out.println("*************** " + Thread.currentThread().getName() + ", 生产者生产, 目前总共有: " + count + " ***************");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    notEmpty.release();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    notEmpty.acquire();
                    mutex.acquire();
                    count--;
                    System.out.println("--------------- " + Thread.currentThread().getName() + ", 消费者消费, 目前总共有: " + count + " ---------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    notFull.release();
                }
            }
        }
    }

}
```

output

```java
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 2 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 1 ---------------
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 0 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 1 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 2 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 1 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 2 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 3 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 4 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 5 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 4 ---------------
false
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 3 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 4 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 5 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 6 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 5 ---------------
*************** pool-1-thread-1, 生产者生产, 目前总共有: 6 ***************
*************** pool-1-thread-2, 生产者生产, 目前总共有: 7 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 8 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 7 ---------------
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 6 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 7 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 8 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 9 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 8 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 9 ***************
*************** pool-1-thread-1, 生产者生产, 目前总共有: 10 ***************
--------------- pool-1-thread-3, 消费者消费, 目前总共有: 9 ---------------
*************** pool-1-thread-2, 生产者生产, 目前总共有: 10 ***************
```

## 0x3 拓展

### 0x31 管道输入输出流PipedInputStream和PipedOutputStream

在`Java`的**io**包下，`PipedOutputStream` 和 `PipedInputStream` 分别是**管道输出流**和**管道输入流**。
它们的作用是**让多线程可以通过管道进行线程间的通讯**。在使用管道通信时，必须将PipedOutputStream 和 PipedInputStream **配套使用**。
**使用方法**：先创建一个管道输入流和管道输出流，然后将输入流和输出流进行连接，用生产者线程往管道输出流中写入数据，消费者在管道输入流中读取数据，这样就可以实现了不同线程间的相互通讯，但是这种方式在生产者和生产者、消费者和消费者之间不能保证同步，**也就是说在一个生产者和一个消费者的情况下是可以生产者和消费者之间交替运行的**，**多个生产者和多个消费者者之间则不行**。

```java
package cn.com.codingce.juc.生产者消费者模型;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * {@link PipedInputStream} {@link PipedOutputStream}
 * <p>
 * 后端码匠
 *
 * @author mxz
 */
public class PipedInputStreamExample {

    final PipedInputStream pis = new PipedInputStream();
    final PipedOutputStream pos = new PipedOutputStream();

    {
        try {
            pis.connect(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PipedInputStreamExample example = new PipedInputStreamExample();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(example.new Producer());
        service.execute(example.new Producer());
        service.execute(example.new Consumer());
        service.shutdown();
        System.out.println(service.awaitTermination(15L, TimeUnit.SECONDS));
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000L);
                    int num = (int) (Math.random() * 10);
                    System.out.println("*************** " + Thread.currentThread().getName() + ", 生产者生产了一个数字, 该数字为: " + num + " ***************");
                    pos.write(num);
                    pos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    pos.close();
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000L);
                    int num = pis.read();
                    System.out.println("--------------- " + Thread.currentThread().getName() + ", 消费者消费了一个数字, 该数字为: " + num + " ---------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    pos.close();
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
```

output

```bash
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 1 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 7 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 1 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 2 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 7 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 7 ---------------
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 2 ---------------
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 7 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 8 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 6 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 4 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 7 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 2 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 7 ---------------
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 3 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 0 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 1 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 8 ---------------
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 6 ---------------
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 1 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 7 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 9 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 1 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 4 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 7 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 2 ---------------
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 5 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 1 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 5 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 3 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 2 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 0 ---------------
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 5 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 8 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 0 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 1 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 1 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 8 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 1 ---------------
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 3 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 7 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 0 ***************
false
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 1 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 0 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 9 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 5 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 1 ---------------
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 7 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 7 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 7 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 4 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 7 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 5 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 5 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 2 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 1 ---------------
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 1 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 5 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 4 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 0 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 2 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 2 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 9 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 5 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 8 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 7 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 8 ---------------
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 2 ***************
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 2 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 5 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 0 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 3 ***************
*************** pool-1-thread-1, 生产者生产了一个数字, 该数字为: 7 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 1 ---------------
*************** pool-1-thread-2, 生产者生产了一个数字, 该数字为: 0 ***************
--------------- pool-1-thread-3, 消费者消费了一个数字, 该数字为: 8 ---------------
### 省略.......
```

项目地址：**https://gitee.com/codingce/codingce-leetcode**