package cn.com.codingce.我的.天理.反射;

public class Hero {
    public String name;
    public float hp;
    public int damage;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hero() {

    }

    public Hero(String string) {
        name = string;
    }

    public Hero(String name, float hp) {
        this.name = name;
        this.hp = hp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Hero [name=" + name + "]";
    }

    public boolean isDead() {
        // TODO Auto-generated method stub
        return false;
    }

    public void attackHero(Hero h2) {
        // TODO Auto-generated method stub

    }
}
