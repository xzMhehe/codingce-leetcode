package cn.com.codingce.ioandnio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class MyNio {
    public static void main(String[] args) throws IOException {
        new MyNio().readNIO();
//        new MyNio().writeNIO();
        new MyNio().writeAndRead();
    }

    public void readNIO() throws IOException {
        //1.获取通道
        FileInputStream fin = new FileInputStream("/Users/williamma/mxz-code/github/phone_data.txt");
        FileChannel fc = fin.getChannel();

        //2.创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println("限制是：" + buffer.limit() + "容量是：" + buffer.capacity()
                + "位置是：" + buffer.position());

        int length = -1;
        //3.将数据从通道读到缓冲区中
        while ((length = fc.read(buffer)) != -1) {

            /*
             * 注意，读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储
             */
            buffer.clear();
            byte[] bytes = buffer.array();
            System.out.write(bytes, 0, length);
            System.out.println();

            System.out.println("限制是：" + buffer.limit() + "容量是：" + buffer.capacity()
                    + "位置是：" + buffer.position());

        }
        fin.close();
    }

    public void write() throws IOException {
        //1.获取通道
        FileInputStream fin = new FileInputStream("/Users/williamma/mxz-code/github/phone_data.txt");
        FileChannel fc = fin.getChannel();

        //2.创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println("限制是：" + buffer.limit() + "容量是：" + buffer.capacity()
                + "位置是：" + buffer.position());

        int length = -1;
        //3.将数据从通道读到缓冲区中
        while ((length = fc.write(buffer)) != -1) {

            /*
             * 注意，读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储
             */
            buffer.clear();
            byte[] bytes = buffer.array();
            System.out.write(bytes, 0, length);
            System.out.println();

            System.out.println("限制是：" + buffer.limit() + "容量是：" + buffer.capacity()
                    + "位置是：" + buffer.position());

        }
        fin.close();
    }

    public void writeNIO() {
        String filename = "out.txt";
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(new File(filename));

            //1.获取通道
            FileChannel channel = fos.getChannel();

            //2.创建缓冲区
            ByteBuffer src = Charset.forName("utf8").encode("你好你好你好你好你好\n嘻嘻嘻嘻喜");

            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
            System.out.println("初始化容量和limit：" + src.capacity() + ","
                    + src.limit());

            int length = 0;
            while ((length = channel.write(src)) != 0) {
                /*
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
                 */
                System.out.println("写入长度:" + length);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void writeAndRead() throws IOException {
        FileOutputStream fileInputStream = new FileOutputStream(new File("out.txt"));

        // 获取通道
        FileChannel fileChannel = fileInputStream.getChannel();

        // 创建缓存区
        ByteBuffer byteBuffer = Charset.forName("utf8").encode("你好NIO\n我是后端码匠\n你好");

        System.out.println("初始化容量和limit：" + byteBuffer.capacity() + ","
                + byteBuffer.limit());

        int length = 0;

        while ((length = fileChannel.write(byteBuffer)) != 0) {
            /*
             * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
             */
            System.out.println("写入长度:" + length);
        }

        byteBuffer = ByteBuffer.allocate(1024);

        FileChannel inputfileChannel = new FileInputStream("out.txt").getChannel();

        length = -1;
        while ((length = inputfileChannel.read(byteBuffer)) != -1) {
            byteBuffer.clear();
            byte[] bytes = byteBuffer.array();
            System.out.write(bytes, 0, length);
            System.out.println();

            System.out.println("限制是：" +  byteBuffer.limit());


        }


    }

}
