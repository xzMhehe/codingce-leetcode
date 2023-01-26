package cn.com.codingce.设计模式.代理模式;

public class ProxyImage implements Image {

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            System.out.println("Loading");
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
