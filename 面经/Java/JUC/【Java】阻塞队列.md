# 【Java】阻塞队列

## 什么是阻塞队列？

阻塞队列（BlockingQueue）是一个支持两个附加操作的队列。这2个附加的操作支持阻塞的插入和移除方法。

- 支持阻塞的插入方法：当队列满时，队列会阻塞插入元素的线程，直到队列不满；
- 支持阻塞的移除方法：当队列为空时，获取元素的线程会等待队列变为非空。

阻塞队列常用于生产者和消费者的场景，生产者是向队列里添加元素的线程，消费者是从队列里取元素的线程。阻塞队列就是生产者用来存放元素、消费者用来获取元素的容器。

在阻塞队列不可用时，这两个附加操作提供了4种处理方式，如下所示：

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

## JDK提供的阻塞队列

![](https://raw.githubusercontent.com/xzMhehe/StaticFile_CDN/main/static/img20230109104841.png)

**ArrayBlockingQueue** **数组结构**组成的有界阻塞队列（需要指定队列的大小）：此队列按照先进先出（FIFO）的原则对元素进行排序，但是默认情况下不保证线程公平的访问队列，即如果队列满了，那么被阻塞在外面的线程对队列访问的顺序是不能保证线程公平（即先阻塞，先插入）的；

**LinkedBlockingQueue**一个由链表结构组成的有界阻塞队列：此队列的默认和最大长度为`Integer.MAX_VALUE`。此队列按照先进先出的原则对元素进行排序；

**PriorityBlockingQueue**支持优先级的无界阻塞队列：默认情况下元素采取自然顺序升序排列。也可以自定义类实现`compareTo()`方法来指定元素排序规则，或者初始化PriorityBlockingQueue时，指定构造参数`Comparator`来进行排序。需要注意的是不能保证同优先级元素的顺序；

**DelayQueue**支持延时获取元素的无界阻塞队列：即可以指定多久才能从队列中获取当前元素，队列使用PriorityBlockingQueue来实现。队列中的元素必须实现Delayed接口，在创建元素时可以指定多久才能从队列中获取当前元素。只有在延迟期满时才能从队列中提取元素；

DelayQueue运用在以下应用场景：

- 缓存系统的设计：可以用DelayQueue保存缓存元素的有效期，使用一个线程循环查询DelayQueue，一旦能从DelayQueue中获取元素时，表示缓存有效期到了；
- 任务超时处理：比如下单后15分钟内未付款，自动关闭订单。

**SynchronousQueue**是一个不存储元素的阻塞队列：每一个put操作必须等待一个take操作，否则不能继续添加元素；

**LinkedTransferQueue**由链表结构组成的无界阻塞TransferQueue队列：相对于其他阻塞队列，LinkedTransferQueue多了tryTransfer和transfer方法；

- `transfer`方法

  如果当前有消费者正在等待接收元素（消费者使用take()方法或带时间限制的poll()方法时），transfer方法可以把生产者传入的元素立刻transfer（传输）给消费者。如果没有消费者在等待接收元素，transfer方法会将元素存放在队列的tail节点，并等到该元素被消费者了才返回。

- `tryTransfer`方法

  tryTransfer方法时用来试探生产者传入的元素是否能直接传给消费者。如果没有消费者等待接收元素，则返回fasle。和transfer方法的区别是tryTransfer方法无论消费者是否接收，方法立即返回，而transfer方法是必须等到消费者消费了才返回。

**LinkedBlockingDeque**链表结构的双向阻塞队列：优势在于多线程入队时，减少一半的竞争。

## 线程池中使用阻塞队列

```java
 public ThreadPoolExecutor(int corePoolSize,
                           int maximumPoolSize,
                           long keepAliveTime,
                           TimeUnit unit,
                           BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), defaultHandler);
}
```
