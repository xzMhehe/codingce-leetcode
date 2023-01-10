package cn.com.codingce.设计模式.代理模式;

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("RealImage Displaying " + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("RealImage Loading " + fileName);
    }
}