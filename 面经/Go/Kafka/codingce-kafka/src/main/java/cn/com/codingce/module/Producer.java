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
