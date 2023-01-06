# 【Kafka】Java实现数据的生产和消费

## Kafka介绍

Kafka 是由 `LinkedIn` 公司开发的，它是一个分布式的，支持多分区、多副本，基于 Zookeeper 的分布式消息流平台，它同时也是一款开源的**基于发布订阅模式的消息引擎系统**。

## Kafka术语

- Broker：消息中间件处理节点，一个Kafka节点就是一个Broker，一个或者多个Broker可以组成一个Kafka集群；
- Topic：每条发布到Kafka集群的消息都有一个类别，这个类别被称为Topic。（物理上不同Topic的消息分开存储，逻辑上一个Topic的消息虽然保存于一个或多个broker上但用户只需指定消息的Topic即可生产或消费数据而不必关心数据存于何处）；
- Partition：Partition是物理上的概念，每个Topic包含一个或多个Partition；
- Producer：负责发布消息到Kafka Broker；
- Consumer：消息消费者，向Kafka Broker读取消息的客户端；
- Consumer Group：每个Consumer属于一个特定的Consumer Group（可为每个Consumer指定Groupname，若不指定Groupname则属于默认的Group）；
- Consumer Offset：消费者在消费消息的过程中，记录消费者在分区中消费进度的字段，就是消息位移，它是一个偏移量，随着消费者不断消费分区中的消息而递增；
- Replica：Kafka 中消息的备份又叫做 `副本`（Replica），副本的数量是可以配置的，Kafka 定义了两类副本，领导者副本（Leader Replica） 和 追随者副本（Follower Replica），前者对外提供服务，后者只是被动跟随；
- Rebalance：当 Kafka 的某个主题的消费者组中，有一个消费者不可用后，其他消费者会自动重新分配订阅的主题分区，这个过程叫做 Rebalance，是 Kafka 实现消费者端高可用的重要手段。

## Kafka特性

- `高吞吐、低延迟`：kakfa 最大的特点就是收发消息非常快，kafka 每秒可以处理几十万条消息，它的最低延迟只有几毫秒；
- `高伸缩性`： 每个主题(topic) 包含多个分区(partition)，主题中的分区可以分布在不同的主机(broker)中；
- `持久性、可靠性`： Kafka 能够允许数据的持久化存储，消息被持久化到磁盘，并支持数据备份防止数据丢失，Kafka 底层的数据存储是基于 Zookeeper 存储的，Zookeeper 的数据能够持久存储；
- `容错性`： 允许集群中的节点失败，某个节点宕机，Kafka 集群能够正常工作；
- `高并发`： 支持数千个客户端同时读写。

## Kafka应用场景

- **活动跟踪**：Kafka 可以用来`跟踪用户行为`，比如你经常回去App购物，你打开App的那一刻，你的登陆信息，登陆次数都会作为消息传输到 Kafka ，当你浏览购物的时候，你的浏览信息，你的搜索指数，你的购物爱好都会作为一个个消息传递给 Kafka ，这样就可以生成报告，可以做`智能推荐`，`购买喜好`等；
- **传递消息**：Kafka 另外一个基本用途是`传递消息`，应用程序向用户发送通知就是通过传递消息来实现的，这些应用组件可以生成消息，而不需要关心消息的格式，也不需要关心消息是如何发送的；
- **度量指标**：Kafka也经常`用来记录运营监控数据`。包括收集各种分布式应用的数据，生产各种操作的集中反馈，比如报警和报告；
- **日志记录**：Kafka 的基本概念来源于提交日志，比如可以把数据库的更新发送到 Kafka 上，用来记录数据库的更新时间，通过Kafka以统一接口服务的方式开放给各种consumer，例如hadoop、Hbase、Solr等；
- **流式处理**：流式处理是有一个能够提供多种应用程序的领域；
- **限流削峰**：Kafka 多用于互联网领域某一时刻请求特别多的情况下，可以把请求写入Kafka 中，避免直接请求后端程序导致服务崩溃。

以上介绍参考Kafka官方文档。

## Kafka核心API

Kafka有`4`个核心API

- 应用程序使用Producer API发布消息到`1`个或`多`个Topics中；
- 应用程序使用ConsumerAPI来订阅`1`个或`多`个Topics，并处理产生的消息；
- 应用程序使用Streams API充当一个流处理器，从1个或多个Topics消费输入流，并产生一个输出流到1个或多个Topics，有效地将输入流转换到输出流；
- Connector API允许构建或运行可重复使用的生产者或消费者，将Topic链接到现有的应用程序或数据系统。

<img src="https://raw.githubusercontent.com/xzMhehe/StaticFile_CDN/main/static/img20230105195908.png" style="zoom: 50%;" />

## Kafka为何如此之快

Kafka 实现了`零拷贝`原理来快速移动数据，避免了内核之间的切换。Kafka 可以将数据记录分批发送，从生产者到文件系统（Kafka 主题日志）到消费者，可以端到端的查看这些批次的数据。批处理能够进行更有效的数据压缩并减少 I/O 延迟，Kafka 采取顺序写入磁盘的方式，避免了随机磁盘寻址的浪费。

总结一下其实就是四个要点：

- 顺序读写；
- 零拷贝；
- 消息压缩；
- 分批发送。

## 案例

项目创建：

<img src="https://raw.githubusercontent.com/xzMhehe/StaticFile_CDN/main/static/img20230105200734.png" style="zoom: 67%;" />

dependencies：

<img src="https://raw.githubusercontent.com/xzMhehe/StaticFile_CDN/main/static/img20230105200805.png" style="zoom:67%;" />

构建工具为Maven，Maven的依赖如下：

```xml
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka_2.12</artifactId>
    <version>1.0.0</version>
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>1.0.0</version>
</dependency>

<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-streams</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Kafka Producer

```java
package cn.com.codingce.module;

import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer {

    // 定义主题
    public static String topic = "codingce_test";

    public static void main(String[] args) throws InterruptedException {
        Properties p = new Properties();
        // bootstrap.servers: kafka的地址, 多个地址用逗号分割
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.31.150:9092");
        // acks:消息的确认机制，默认值是0. acks=0: 如果设置为0，生产者不会等待kafka的响应; acks=1: 这个配置意味着kafka会把这条消息写到本地日志文件中，但是不会等待集群中其他机器的成功响应
        // acks=all: 这个配置意味着leader会等待所有的follower同步完成. 这个确保消息不会丢失, 除非kafka集群中所有机器挂掉. 这是最强的可用性保证. 
        p.put("acks", "all");
        // retries: 配置为大于0的值的话, 客户端会在消息发送失败时重新发送.
        p.put("retries", 0);
        // batch.size: 当多条消息需要发送到同一个分区时，生产者会尝试合并网络请求. 这会提高client和生产者的效率.
        p.put("batch.size", 16384);
        // key.serializer: 键序列化，默认org.apache.kafka.common.serialization.StringDeserializer. 
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // value.deserializer:值序列化，默认org.apache.kafka.common.serialization.StringDeserializer. 
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        try {
            do {
                String msg = "后端码匠, " + new Random().nextInt(100);
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
                kafkaProducer.send(record);
                System.out.println("======消息发送成功: " + msg + " ======");
                Thread.sleep(1000L);
            } while (true);
        } finally {
            kafkaProducer.close();
        }

    }
}
```

output

```bash
======消息发送成功: 后端码匠, 97 ======
======消息发送成功: 后端码匠, 35 ======
======消息发送成功: 后端码匠, 81 ======
======消息发送成功: 后端码匠, 46 ======
======消息发送成功: 后端码匠, 62 ======
======消息发送成功: 后端码匠, 53 ======
======消息发送成功: 后端码匠, 42 ======
======消息发送成功: 后端码匠, 56 ======
======消息发送成功: 后端码匠, 99 ======
======消息发送成功: 后端码匠, 46 ======
======消息发送成功: 后端码匠, 49 ======
======消息发送成功: 后端码匠, 35 ======
======消息发送成功: 后端码匠, 17 ======
======消息发送成功: 后端码匠, 78 ======
======消息发送成功: 后端码匠, 66 ======
======消息发送成功: 后端码匠, 4 ======
======消息发送成功: 后端码匠, 9 ======
======消息发送成功: 后端码匠, 69 ======
======消息发送成功: 后端码匠, 52 ======
======消息发送成功: 后端码匠, 2 ======
======消息发送成功: 后端码匠, 8 ======
======消息发送成功: 后端码匠, 86 ======
======消息发送成功: 后端码匠, 12 ======
======消息发送成功: 后端码匠, 67 ======
======消息发送成功: 后端码匠, 91 ======
======消息发送成功: 后端码匠, 8 ======
======消息发送成功: 后端码匠, 56 ======
======消息发送成功: 后端码匠, 89 ======
======消息发送成功: 后端码匠, 37 ======
======消息发送成功: 后端码匠, 39 ======
======消息发送成功: 后端码匠, 71 ======
```

### Kafka Consumer

```java
package cn.com.codingce.module;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class Consumer {
    private static final String GROUPID = "codingce_consumer_a";

    public static void main(String[] args) {

        Properties p = new Properties();
        // bootstrap.servers: kafka的地址, 多个地址用逗号分割
        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.31.150:9092");
        // 消费者所属的分组id, 组名 不同组名可以重复消费.例如你先使用了组名A消费了Kafka的1000条数据, 但是你还想再次进行消费这1000条数据, 
        // 并且不想重新去产生, 那么这里你只需要更改组名就可以重复消费了.
        p.put(ConsumerConfig.GROUP_ID_CONFIG, GROUPID);
        // 是否自动提交, 默认为true.
        p.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 从poll(拉)的回话处理时长
        p.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // 超时时间
        p.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        // 一次最大拉取的条数
        p.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1000);
        // 消费规则, 默认earliest
        p.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // key.serializer: 键序列化, 默认org.apache.kafka.common.serialization.StringDeserializer.
        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // value.deserializer:值序列化, 默认org.apache.kafka.common.serialization.StringDeserializer.
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);
        // 订阅消息
        kafkaConsumer.subscribe(Collections.singletonList(Producer.topic));
        do {
            // 订阅之后, 再从kafka中拉取数据
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("-----topic:%s, offset:%d, 消息:%s-----\n", record.topic(), record.offset(), record.value());
            }
        } while (true);
    }
}
```

output

```bash
-----topic:codingce_test, offset:289, 消息:后端码匠, 97-----
-----topic:codingce_test, offset:290, 消息:后端码匠, 35-----
-----topic:codingce_test, offset:291, 消息:后端码匠, 81-----
-----topic:codingce_test, offset:292, 消息:后端码匠, 46-----
-----topic:codingce_test, offset:293, 消息:后端码匠, 62-----
-----topic:codingce_test, offset:294, 消息:后端码匠, 53-----
-----topic:codingce_test, offset:295, 消息:后端码匠, 42-----
-----topic:codingce_test, offset:296, 消息:后端码匠, 56-----
-----topic:codingce_test, offset:297, 消息:后端码匠, 99-----
-----topic:codingce_test, offset:298, 消息:后端码匠, 46-----
-----topic:codingce_test, offset:299, 消息:后端码匠, 49-----
-----topic:codingce_test, offset:300, 消息:后端码匠, 35-----
-----topic:codingce_test, offset:301, 消息:后端码匠, 17-----
-----topic:codingce_test, offset:302, 消息:后端码匠, 78-----
-----topic:codingce_test, offset:303, 消息:后端码匠, 66-----
-----topic:codingce_test, offset:304, 消息:后端码匠, 4-----
-----topic:codingce_test, offset:305, 消息:后端码匠, 9-----
-----topic:codingce_test, offset:306, 消息:后端码匠, 69-----
-----topic:codingce_test, offset:307, 消息:后端码匠, 52-----
-----topic:codingce_test, offset:308, 消息:后端码匠, 2-----
-----topic:codingce_test, offset:309, 消息:后端码匠, 8-----
-----topic:codingce_test, offset:310, 消息:后端码匠, 86-----
-----topic:codingce_test, offset:311, 消息:后端码匠, 12-----
-----topic:codingce_test, offset:312, 消息:后端码匠, 67-----
-----topic:codingce_test, offset:313, 消息:后端码匠, 91-----
-----topic:codingce_test, offset:314, 消息:后端码匠, 8-----
-----topic:codingce_test, offset:315, 消息:后端码匠, 56-----
-----topic:codingce_test, offset:316, 消息:后端码匠, 89-----
-----topic:codingce_test, offset:317, 消息:后端码匠, 37-----
-----topic:codingce_test, offset:318, 消息:后端码匠, 39-----
-----topic:codingce_test, offset:319, 消息:后端码匠, 71-----
```



本次采用Docker 搭建的单机 Kafka、Zookeeper，Kafka介绍参考官方文档：http://kafka.apache.org/intro

项目地址：https://gitee.com/codingce/codingce-leetcode





## centos7安装Docker详细步骤

**查看当前的内核版本**





```bash
https://archive.apache.org/dist/zookeeper/zookeeper-3.6.3/apache-zookeeper-3.6.3-bin.tar.gz
```





```bash
curl -L https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
```



```bash
192.168.31.150
```





```bash
docker run -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=192.168.31.150:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.31.150:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -e TZ="Asia/Shanghai" wurstmeister/kafka
```



```bash
docker run -d --name kafka \
-p 9092:9092 \
-e KAFKA_BROKER_ID=0 \
-e KAFKA_ZOOKEEPER_CONNECT=192.168.31.150:2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.31.150:9092 \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 wurstmeister/kafka
```



```bash
# 将docker容器设为自启动和取消容器自启动

# 将正在运行的容器设为自启动
# docker update --restart=always 容器名或容器ID
docker update --restart=always <CONTAINER ID>
# 例如将tomcat设为自启动
docker update --restart=always tomcat

# 将自启动的容器取消自启动
# docker update --restart=no 容器名或容器ID
docker update --restart=no <CONTAINER ID>
# 例如取消tomcat的自启动
docker update --restart=no tomcat
```



