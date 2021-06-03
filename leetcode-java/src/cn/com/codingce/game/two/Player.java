package cn.com.codingce.game.two;

public class Player {

    Player(String job, double hp, double au, double ad, double armor) {
        this.job = job;
        this.hp = hp;
        this.au = au;
        this.ad = ad;
        this.armor = armor;
    }

    String job;//职位
    double hp, au, ad, armor;//生命，攻击上限，攻击下限，护甲

    public void attack(Player other) {
        double hurt = Math.random() * (au - ad) + ad;
        if (hurt <= other.armor) hurt = 0;
        else {
            hurt -= other.armor;
        }
        other.hp -= hurt;
        System.out.println(job + "对" + other.job + "造成了" + hurt + "点伤害");
        System.out.println(job + "当前血量为:" + hp);
        System.out.println(other.job + "当前血量为:" + other.hp);
    }

    public boolean live(Player other) {
        if (hp <= 0) {
            System.out.println(job + "已死亡      " + other.job + "获得战斗胜利");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Player p1 = new Player("剑圣", 500, 20, 10, 500);
        Player p2 = new Player("圣骑士", 1000, 1000, 5, 10);
        while (true) {
            p1.attack(p2);
            if (!p2.live(p1)) {
                break;
            }
            p2.attack(p1);
            if (!p1.live(p2)) {
                break;
            }
        }
    }
}
