package cn.com.codingce.我的.天理.游戏.one;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Method {
    public int sum_priece_play1 = 500;
    public int sum_priece_play2 = 500;

    //明天的任务：将这三个map进行存储使用
    Map<Player, Attribute1> map001 = new LinkedHashMap<>();//存储玩家和武器
    Map<Player, User> map002 = new LinkedHashMap<>();//存储玩家和角色
    Map<Player, Attribute2> map003 = new LinkedHashMap<>();//存储玩家和防具


    User user1 = new User(1, "索隆", 2000, 200, 0, 50, 50);
    User user2 = new User(2, "甄姬", 1000, 0, 400, 50, 50);
    User user3 = new User(3, "丘比特", 1500, 300, 300, 100, 100);
    User user4 = new User(4, "狄仁杰", 1200, 600, 0, 100, 100);
    User user5 = new User(5, "无敌的寂寞", 10000, 10000, 10000, 10000, 10000);
    public Scanner scanner = new Scanner(System.in);
    Player player1 = new Player();
    Player player2 = new Player();

    //旁白
    public void Narrator() {
        System.out.println("I see the player you mean.\n" +
                "\n" +
                "我看到你所指的那位玩家了。 ******（Playername）?\n" +
                "\n" +
                "******（玩家名称）?");
        System.out.println("------------请输入任意键+回车继续------------");
        Scanner input = new Scanner(System.in);
        String str = input.next();
        System.out.println("Yes. Take care. It has reached a higher level now. It can read our thoughts.\n" +
                "\n" +
                "是的。小心。它已达到了更高的境界。它能够阅读我们的思想。");
        System.out.println("------------请输入任意键+回车继续------------");
        Scanner input1 = new Scanner(System.in);
        String str1 = input.next();
        System.out.println("That doesn't matter. It thinks we are part of the game.\n" +
                "\n" +
                "无伤大雅。它认为我们是游戏的一部分。");
        System.out.println("------------请输入任意键+回车继续------------");
        Scanner input2 = new Scanner(System.in);
        String str2 = input2.next();
        System.out.println("废话少说，来吧！哈哈哈哈");
        System.out.println("------------请输入任意键+回车继续------------");
        Scanner input3 = new Scanner(System.in);
        String str3 = input3.next();
    }

    //界面
    public void boundary1() {
//      System.out.println("------------------------------------------");
        System.out.println("-----------欢迎进入二人世纪------------");
        System.out.println("-------------1.开始游戏---------------");
        System.out.println("-------------2.金币余额---------------");
        System.out.println("-------------3.退出游戏---------------");
    }

    public void choose1() {
        System.out.println("--------------请选择----------------");
        int i;
        i = scanner.nextInt();
        switch (i) {
            case 1:
                System.out.println("------------------------------------------");
                boundary2();
                choose2();
                break;
            case 2:
                System.out.println("------------------------------------------");
                gold_coin();
                boundary1();
                choose1();
                break;
            case 3:
                System.out.println("------------------------------------------");
                exit();
                break;
            default:
                System.out.println("-------输入错误！重新输入！---------");
                choose1();
                break;
        }
    }

    public void gold_coin() {
        System.out.println("------------金币余额----------");
        System.out.print("玩家1的金币仓库： ");
        System.out.println(sum_priece_play1);
        System.out.println("---------------------");
        System.out.print("玩家2的金币仓库： ");
        System.out.println(sum_priece_play2);
    }

    //进入开始游戏后的选择
    public void boundary2() {
        System.out.println("-------------1.角色选择--------------");
        System.out.println("-------------2.开始战斗--------------");
        System.out.println("-------------3.返回上层--------------");

    }

    public void choose2() {
        System.out.println("--------------请选择----------------");
        int i;
        i = scanner.nextInt();
        switch (i) {
            case 1:
                System.out.println("------------------------------------------");
                role();
                System.out.println("------------------------------------------");
                System.out.println("------------------------------------------");
                System.out.println("------------------------------------------");
                if (j == 3) {
                    if (map1.isEmpty()) {
                        System.out.println("两位玩家都未购买武器！");
                    }
                    if (map2.isEmpty()) {
                        System.out.println("两位玩家都未购买防具！");
                    }
                    System.out.print("角色--->>>");
                    System.out.println(map);
                    System.out.print("武器--->>>");
                    System.out.println(map1);
                    System.out.print("防具--->>>");
                    System.out.println(map2);
                    System.out.println("两位玩家角色，武器，防具都已备齐！！！");
                    System.out.println("两位玩家即将回到新手村哦......");
                } else {
                }
                boundary2();
                choose2();
                break;
            case 2:
                System.out.println("------------------------------------------");
                if (map.isEmpty()) {
                    System.out.println("请玩家先选择角色然后战斗哦！");
                    boundary2();
                    choose2();
                } else {
                    battle();
                }
                boundary1();
                choose1();
                break;
            case 3:
                boundary1();
                choose1();
                break;
            default:
                System.out.println("-------输入错误！重新输入！---------");
                choose2();
                break;
        }
    }

    //购买装备
    //武器商店(武器有属性)
    class Attribute1 {
        private String name;//武器名
        private double physis_aggressivity;//物理攻击力
        private double magic_aggressivity;//法术攻击力
        private int durability1;//耐久度
        private int critical_hit;//触发暴击
        private double critical;//暴击率
        private int priece;//价格


        public Attribute1(String name, double physis_aggressivity, double magic_aggressivity, int durability1, int critical_hit, double critical, int priece) {
            this.name = name;
            this.physis_aggressivity = physis_aggressivity;
            this.magic_aggressivity = magic_aggressivity;
            this.durability1 = durability1;
            this.critical_hit = critical_hit;
            this.critical = critical;
            this.priece = priece;
        }

        @Override
        public String toString() {
            return "Attribute1{" +
                    "name='" + name + '\'' +
                    ", physis_aggressivity=" + physis_aggressivity +
                    ", magic_aggressivity=" + magic_aggressivity +
                    ", durability1=" + durability1 +
                    ", critical_hit=" + critical_hit +
                    ", priece=" + priece +
                    '}';
        }

        public double getCritical() {
            return critical;
        }

        public void setCritical(double critical) {
            this.critical = critical;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPriece() {
            return priece;
        }

        public void setPriece(int priece) {
            this.priece = priece;
        }

        public double getPhysis_aggressivity() {
            return physis_aggressivity;
        }

        public void setPhysis_aggressivity(double physis_aggressivity) {
            this.physis_aggressivity = physis_aggressivity;
        }

        public double getMagic_aggressivity() {
            return magic_aggressivity;
        }

        public void setMagic_aggressivity(double magic_aggressivity) {
            this.magic_aggressivity = magic_aggressivity;
        }

        public int getDurability1() {
            return durability1;
        }

        public void setDurability1(int durability1) {
            this.durability1 = durability1;
        }

        public int isCritical_hit() {
            return critical_hit;
        }

        public void setCritical_hit(int critical_hit) {
            this.critical_hit = critical_hit;
        }
    }

    Attribute1 attribute11 = new Attribute1("护魂法杖", 0, 100, 30, (int) (Math.random() * 10), 0.5, 100);
    Attribute1 attribute12 = new Attribute1("流光星陨刀", 100, 0, 30, (int) (Math.random() * 10), 0.5, 100);
    Attribute1 attribute13 = new Attribute1("散月拳套", 200, 0, 40, (int) (Math.random() * 8), 0.7, 200);
    Attribute1 attribute14 = new Attribute1("念珠", 0, 200, 40, (int) (Math.random() * 8), 0.7, 200);

    public void weapons_store(User user) {
        int i;

        System.out.println("------------------------------------------");
        System.out.println("1" + "--->>>" + attribute11);
        System.out.println("------------------------------------------");
        System.out.println("2" + "--->>>" + attribute12);
        System.out.println("------------------------------------------");
        System.out.println("3" + "--->>>" + attribute13);
        System.out.println("------------------------------------------");
        System.out.println("4" + "--->>>" + attribute14);
        System.out.println("------------------------------------------");
        System.out.println("5" + "--->>>" + "不购买武器");
        System.out.println("请输入相应武器编号选择要购买的武器！（注意根据自己所选角色的属性购买哦）");
        hhh1(user);//先购买武器
    }

    Map<String, String> map1 = new HashMap<>();//武器存储
    int a;

    public void hhh1(User user) {


        System.out.println("请玩家" + j + "进行武器购买");
        a = scanner.nextInt();
        switch (a) {
            case 1:
                if (j == 1) {
                    if (sum_priece_play1 >= attribute11.getPriece()) {
                        map1.put("玩家1的武器", attribute11.getName());
                        map001.put(player1, attribute11);
                        player1.setPhysis_hurt((attribute11.getPhysis_aggressivity() + user.getPhysis_base_aggressivity()));
                        player1.setMagic_hurt((attribute11.getMagic_aggressivity() + user.getMagic_base_aggressivity()));
                        player1.setWuqi_durability(attribute11.getDurability1());
                        player1.setCritical((user.getMagic_base_aggressivity() * attribute11.getCritical()));

                        sum_priece_play1 -= attribute11.getPriece();
                        System.out.println(map1);
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                    }

                } else if (j == 2) {
                    if (sum_priece_play2 >= attribute11.getPriece()) {
                        map1.put("玩家2的武器", attribute11.getName());
                        map001.put(player2, attribute11);
                        player2.setPhysis_hurt((attribute11.getPhysis_aggressivity() + user.getPhysis_base_aggressivity()));
                        player2.setMagic_hurt((attribute11.getMagic_aggressivity() + user.getMagic_base_aggressivity()));
                        player2.setWuqi_durability(attribute11.getDurability1());
                        player2.setCritical(user.getMagic_base_aggressivity() * attribute11.getCritical());

                        sum_priece_play2 -= attribute11.getPriece();
                        System.out.println(map1);
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                    }
                } else {
                }

                break;
            case 2:
                if (j == 1) {
                    if (sum_priece_play1 >= attribute12.getPriece()) {
                        map1.put("玩家1的武器", attribute12.getName());
                        map001.put(player1, attribute12);
                        player1.setPhysis_hurt((attribute12.getPhysis_aggressivity() + user.getPhysis_base_aggressivity()));
                        player1.setMagic_hurt((attribute12.getMagic_aggressivity() + user.getMagic_base_aggressivity()));
                        player1.setWuqi_durability(attribute12.getDurability1());
                        player1.setCritical(user.getMagic_base_aggressivity() * attribute12.getCritical());

                        sum_priece_play1 -= attribute12.getPriece();
                        System.out.println(map1);
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                    }

                } else if (j == 2) {
                    if (sum_priece_play2 >= attribute12.getPriece()) {
                        map1.put("玩家2的武器", attribute12.getName());
                        map001.put(player2, attribute12);
                        player2.setPhysis_hurt((attribute12.getPhysis_aggressivity() + user.getPhysis_base_aggressivity()));
                        player2.setMagic_hurt((attribute12.getMagic_aggressivity() + user.getMagic_base_aggressivity()));
                        player2.setWuqi_durability(attribute12.getDurability1());
                        player2.setCritical(user.getMagic_base_aggressivity() * attribute12.getCritical());

                        sum_priece_play2 -= attribute12.getPriece();
                        System.out.println(map1);
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                    }
                } else {
                }

                break;
            case 3:
                if (j == 1) {
                    if (sum_priece_play1 >= attribute13.getPriece()) {
                        map1.put("玩家1的武器", attribute13.getName());
                        map001.put(player1, attribute13);
                        player1.setPhysis_hurt((attribute13.getPhysis_aggressivity() + user.getPhysis_base_aggressivity()));
                        player1.setMagic_hurt((attribute13.getMagic_aggressivity() + user.getMagic_base_aggressivity()));
                        player1.setWuqi_durability(attribute13.getDurability1());
                        player1.setCritical(user.getMagic_base_aggressivity() * attribute13.getCritical());

                        sum_priece_play1 -= attribute13.getPriece();
                        System.out.println(map1);
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                    }

                } else if (j == 2) {
                    if (sum_priece_play2 >= attribute13.getPriece()) {
                        map1.put("玩家2的武器", attribute13.getName());
                        map001.put(player2, attribute13);
                        player2.setPhysis_hurt((attribute13.getPhysis_aggressivity() + user.getPhysis_base_aggressivity()));
                        player2.setMagic_hurt((attribute13.getMagic_aggressivity() + user.getMagic_base_aggressivity()));
                        player2.setWuqi_durability(attribute13.getDurability1());
                        player2.setCritical(user.getMagic_base_aggressivity() * attribute13.getCritical());

                        sum_priece_play2 -= attribute13.getPriece();
                        System.out.println(map1);
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                    }
                } else {
                }

                break;
            case 4:
                if (j == 1) {
                    if (sum_priece_play1 >= attribute14.getPriece()) {
                        map1.put("玩家1的武器", attribute14.getName());
                        map001.put(player1, attribute14);
                        player1.setPhysis_hurt((attribute14.getPhysis_aggressivity() + user.getPhysis_base_aggressivity()));
                        player1.setMagic_hurt((attribute14.getMagic_aggressivity() + user.getMagic_base_aggressivity()));
                        player1.setWuqi_durability(attribute14.getDurability1());
                        player1.setCritical(user.getMagic_base_aggressivity() * attribute14.getCritical());

                        sum_priece_play1 -= attribute14.getPriece();
                        System.out.println(map1);
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                    }

                } else if (j == 2) {
                    if (sum_priece_play2 >= attribute14.getPriece()) {
                        map1.put("玩家2的武器", attribute14.getName());
                        map001.put(player2, attribute14);
                        player2.setPhysis_hurt((attribute14.getPhysis_aggressivity() + user.getPhysis_base_aggressivity()));
                        player2.setMagic_hurt((attribute14.getMagic_aggressivity() + user.getMagic_base_aggressivity()));
                        player2.setWuqi_durability(attribute14.getDurability1());
                        player2.setCritical(user.getMagic_base_aggressivity() * attribute14.getCritical());

                        sum_priece_play2 -= attribute14.getPriece();
                        System.out.println(map1);
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                    }
                } else {
                }

                break;
            case 5:
                System.out.println("玩家" + j + "未购买武器");
                break;
            default:
                System.out.println("输入错误！请重新输入！");
                hhh1(user);
                break;
        }
    }

    //防具商店（防具有属性）
    class Attribute2 {
        private String name;
        private int durability1;//耐久度
        private double physics_defensive;//物理防御力
        private double magic_defensive;//法术防御力
        private int dodge;//闪避（触发闪避之后抵御全部攻击）
        private double priece;

        public int getDurability1() {
            return durability1;
        }

        public void setDurability1(int durability1) {
            this.durability1 = durability1;
        }

        public double getPhysics_defensive() {
            return physics_defensive;
        }

        public void setPhysics_defensive(double physics_defensive) {
            this.physics_defensive = physics_defensive;
        }

        public double getMagic_defensive() {
            return magic_defensive;
        }

        public void setMagic_defensive(double magic_defensive) {
            this.magic_defensive = magic_defensive;
        }

        public int isDodge() {
            return dodge;
        }

        public void setDodge(int dodge) {
            this.dodge = dodge;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPriece() {
            return priece;
        }

        public void setPriece(double priece) {
            this.priece = priece;
        }

        public Attribute2(String name, int durability1, double physics_defensive, double magic_defensive, int dodge, double priece) {
            this.name = name;
            this.durability1 = durability1;
            this.physics_defensive = physics_defensive;
            this.magic_defensive = magic_defensive;
            this.dodge = dodge;
            this.priece = priece;
        }

        @Override
        public String toString() {
            return "Attribute2{" +
                    "name='" + name + '\'' +
                    ", durability1=" + durability1 +
                    ", physics_defensive=" + physics_defensive +
                    ", magic_defensive=" + magic_defensive +
                    ", dodge=" + dodge +
                    ", priece=" + priece +
                    '}';
        }
    }

    Attribute2 attribute21 = new Attribute2("血之魂臂铠", 30, 0, 100, (int) (Math.random() * 13), 300);
    Attribute2 attribute22 = new Attribute2("百战盔", 30, 100, 0, (int) (Math.random() * 13), 300);
    Attribute2 attribute23 = new Attribute2("奥尔卡的黑狼图腾 ", 40, 200, 200, (int) (Math.random() * 9), 500);

    public void armor_store(User user) {
        int i;

        System.out.println("------------------------------------------");
        System.out.println("1" + "--->>>" + attribute21);
        System.out.println("------------------------------------------");
        System.out.println("2" + "--->>>" + attribute22);
        System.out.println("------------------------------------------");
        System.out.println("3" + "--->>>" + attribute23);
        System.out.println("------------------------------------------");
        System.out.println("4" + "--->>>" + "不购买防具");
        System.out.println("请输入相应防具编号选择要购买的防具！（注意根据自己所选角色的属性购买哦）");
        hhh2(user);
    }

    int b;
    Map<String, String> map2 = new LinkedHashMap<>();//防具存储

    public void hhh2(User user) {
        System.out.println("请玩家" + j + "进行防具购买");
        b = scanner.nextInt();
        switch (b) {
            case 1:
                if (j == 1) {
                    if (sum_priece_play1 >= attribute21.getPriece()) {
                        map2.put("玩家1的防具：", attribute21.getName());
                        map003.put(player1, attribute21);
                        player1.setPhysis_defend((user.getBase_defensive_physis() + attribute21.getPhysics_defensive()));
                        player1.setMagic_defend((user.getBase_defensive_magic() + attribute21.getMagic_defensive()));
                        player1.setFangju_durability(attribute21.getDurability1());
//                            player1.setDodge(attribute21.isDodge());
                        sum_priece_play1 -= attribute21.getPriece();
                        System.out.println(map2);
                        j++;
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                        j++;
                    }
                } else if (j == 2) {
                    if (sum_priece_play2 >= attribute21.getPriece()) {
                        map2.put("玩家2的防具：", attribute21.getName());
                        map003.put(player2, attribute21);
                        player2.setPhysis_defend((user.getBase_defensive_physis() + attribute21.getPhysics_defensive()));
                        player2.setMagic_defend((user.getBase_defensive_magic() + attribute21.getMagic_defensive()));
                        player2.setFangju_durability(attribute21.getDurability1());
//                            player2.setDodge(attribute21.isDodge());
                        sum_priece_play2 -= attribute21.getPriece();
                        System.out.println(map2);
                        j++;
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                        j++;
                    }
                } else {
                }

                break;
            case 2:
                if (j == 1) {
                    if (sum_priece_play1 >= attribute22.getPriece()) {
                        map2.put("玩家1的防具：", attribute22.getName());
                        map003.put(player1, attribute22);
                        player1.setPhysis_defend((user.getBase_defensive_physis() + attribute22.getPhysics_defensive()));
                        player1.setMagic_defend((user.getBase_defensive_magic() + attribute22.getMagic_defensive()));
                        player1.setFangju_durability(attribute22.getDurability1());
//                            player1.setDodge(attribute22.isDodge());
                        sum_priece_play1 -= attribute22.getPriece();
                        System.out.println(map2);
                        j++;
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                        j++;
                    }
                } else if (j == 2) {
                    if (sum_priece_play2 >= attribute22.getPriece()) {
                        map2.put("玩家2的防具：", attribute21.getName());
                        map003.put(player2, attribute22);
                        player2.setPhysis_defend((user.getBase_defensive_physis() + attribute22.getPhysics_defensive()));
                        player2.setMagic_defend((user.getBase_defensive_magic() + attribute22.getMagic_defensive()));
                        player2.setFangju_durability(attribute22.getDurability1());
//                            player2.setDodge(attribute22.isDodge());
                        sum_priece_play2 -= attribute22.getPriece();
                        System.out.println(map2);
                        j++;
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                        j++;
                    }
                } else {
                }
                break;
            case 3:
                if (j == 1) {
                    if (sum_priece_play1 >= attribute23.getPriece()) {
                        map2.put("玩家1的防具：", attribute23.getName());
                        map003.put(player1, attribute23);
                        player1.setPhysis_defend((user.getBase_defensive_physis() + attribute23.getPhysics_defensive()));
                        player1.setMagic_defend((user.getBase_defensive_magic() + attribute23.getMagic_defensive()));
                        player1.setFangju_durability(attribute23.getDurability1());
//                            player1.setDodge(attribute23.isDodge());
                        sum_priece_play1 -= attribute23.getPriece();
                        System.out.println(map2);
                        j++;
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                        j++;
                    }
                } else if (j == 2) {
                    if (sum_priece_play2 >= attribute23.getPriece()) {
                        map2.put("玩家2的防具：", attribute23.getName());
                        map003.put(player2, attribute23);
                        player2.setPhysis_defend((user.getBase_defensive_physis() + attribute23.getPhysics_defensive()));
                        player2.setMagic_defend((user.getBase_defensive_magic() + attribute23.getMagic_defensive()));
                        player2.setFangju_durability(attribute23.getDurability1());
//                            player2.setDodge(attribute23.isDodge());
                        sum_priece_play2 -= attribute23.getPriece();
                        System.out.println(map2);
                        j++;
                    } else {
                        System.out.println("余额不够哦！请玩家" + j + "先参加战斗赚取金币哦！");
                        j++;
                    }
                } else {
                }
                break;
            case 4:
                System.out.println("玩家" + j + "未购买防具");
                j++;
                break;
            default:
                System.out.println("输入错误！请重新输入！");
                hhh2(user);
                break;
        }
    }

    //开始游戏
    //选择角色
    Map<String, String> map = new LinkedHashMap<String, String>();
    public int j = 1;

    public void role() {
        hhh();
    }

    public void hhh() {
        for (j = 1; j <= 2; ) {
            System.out.println("---------请进行角色选择：----------");
            System.out.println("编号:" + user1.getId() + "--->>>角色：" + user1);
            System.out.println("------------------------------------------");
            System.out.println("编号:" + user2.getId() + "--->>>角色：" + user2);
            System.out.println("------------------------------------------");
            System.out.println("编号:" + user3.getId() + "--->>>角色：" + user3);
            System.out.println("------------------------------------------");
            System.out.println("编号:" + user4.getId() + "--->>>角色：" + user4);
            System.out.println("------------------------------------------");
            System.out.println("编号:" + user5.getId() + "--->>>角色：" + user5);
            System.out.println("------------------------------------------");
            System.out.println("请玩家" + j + "进行角色选择");
            System.out.println("请输入角色对应的编号：");
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    System.out.print("恭喜玩家" + j + "获得角色:--->>>");
                    System.out.println(user1.getName());
                    if (j == 1) {
                        map.put("玩家1", user1.getName());
                        map002.put(player1, user1);
                        player1.setName(user1.getName());
                        player1.setBleed(user1.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user1);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user1);
                    } else if (j == 2) {

                        map.put("玩家2", user1.getName());
                        map002.put(player2, user1);
                        player2.setName(user1.getName());
                        player2.setBleed(user1.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user1);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user1);
                    } else {
                    }
                    break;
                case 2:
                    System.out.print("恭喜玩家" + j + "获得角色:--->>>");
                    System.out.println(user2.getName());
                    if (j == 1) {
                        map.put("玩家1", user2.getName());
                        map002.put(player1, user2);
                        player1.setName(user2.getName());
                        player1.setBleed(user2.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user2);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user2);
                    } else if (j == 2) {
                        map.put("玩家2", user2.getName());
                        map002.put(player2, user2);
                        player2.setName(user2.getName());
                        player2.setBleed(user2.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user2);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user2);
                    } else {
                    }
                    break;
                case 3:
                    System.out.print("恭喜玩家" + j + "获得角色:--->>>");
                    System.out.println(user3.getName());
                    if (j == 1) {
                        map.put("玩家1", user3.getName());
                        map002.put(player1, user3);
                        player1.setName(user3.getName());
                        player1.setBleed(user3.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user3);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user3);
                    } else if (j == 2) {
                        map.put("玩家2", user3.getName());
                        map002.put(player2, user3);
                        player2.setName(user3.getName());
                        player2.setBleed(user3.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user3);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user3);
                    } else {
                    }
                    break;
                case 4:
                    System.out.print("恭喜玩家" + j + "获得角色:--->>>");
                    System.out.println(user4.getName());
                    if (j == 1) {
                        map.put("玩家1", user4.getName());
                        map002.put(player1, user4);
                        player1.setName(user4.getName());
                        player1.setBleed(user4.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user4);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user4);
                    } else if (j == 2) {
                        map.put("玩家2", user4.getName());
                        map002.put(player2, user4);
                        player2.setName(user4.getName());
                        player2.setBleed(user4.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user4);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user4);
                    } else {
                    }
                    break;
                case 5:
                    System.out.print("恭喜玩家" + j + "获得角色:--->>>");
                    System.out.println(user5.getName());
                    if (j == 1) {
                        map.put("玩家1", user5.getName());
                        map002.put(player1, user5);
                        player1.setName(user5.getName());
                        player1.setBleed(user5.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user5);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user5);
                    } else if (j == 2) {
                        map.put("玩家2", user5.getName());
                        map002.put(player2, user5);
                        player2.setName(user5.getName());
                        player2.setBleed(user5.getBase_blood_volume());
                        System.out.println("玩家" + j + "请购买武器");
                        weapons_store(user5);
                        System.out.println("玩家" + j + "请购买防具");
                        armor_store(user5);
                    } else {
                    }
                    break;
                default:
                    System.out.println("-------输入错误！重新输入！---------");
                    hhh();
                    break;
            }
        }
    }

    //攻击
    public int k;
    //每次攻击都会消耗武器的耐久度每次消耗6
    public double sum_attack = 0;

    //每进入一次攻击回合都会对武器的暴击率进行调用
    public void attack(Player player) {

        System.out.println("玩家" + player.getName() + "开始攻击");
        if (player.getWuqi_durability() > 0) {
            System.out.println(map001.get(player).isCritical_hit());
            if (map001.get(player).isCritical_hit() == 3 || map001.get(player).isCritical_hit() == 5 || map001.get(player).isCritical_hit() == 1 || map003.get(player).isDodge() == 7 || map003.get(player).isDodge() == 2 || map003.get(player).isDodge() == 0)//触发暴击
            {
                sum_attack = (player.getPhysis_hurt() + player.getMagic_hurt());
                sum_attack += (sum_attack * player.getCritical());
                System.out.println("玩家" + player.getName() + "触发暴击并造成了" + sum_attack + "伤害");
            } else//未触发
            {
                sum_attack = (player.getPhysis_hurt() + player.getMagic_hurt());
                System.out.println("玩家" + player.getName() + "未触发暴击并造成了" + sum_attack + "伤害");
            }
            player.setWuqi_durability((player.getWuqi_durability() - 4));
        } else//没有武器了
        {
            sum_attack = map002.get(player).getMagic_base_aggressivity() + map002.get(player).getPhysis_base_aggressivity();
            System.out.println("玩家" + player.getName() + "武器报废或者无武器时造成了" + sum_attack + "伤害");
        }
    }

    //防御
    public int h;
    //每次防御都会消耗防具的耐久度为6
    public double sum_defend;

    //每一回合进入防御都会对防具的闪避率进行调用
    public void defend(Player player) {

        System.out.println("玩家" + player.getName() + "开始防御");
        if (player.getFangju_durability() > 0) {
            System.out.println(map003.get(player).isDodge());
            if (map003.get(player).isDodge() == 5 || map003.get(player).isDodge() == 7 || map003.get(player).isDodge() == 1 || map003.get(player).isDodge() == 2)//触发闪避
            {
                System.out.println("玩家" + player.getName() + "触发闪避了，敌方攻击无效！哈哈哈！");
            } else //未触发闪避
            {
                sum_defend = (player.getMagic_defend() + player.getPhysis_defend());
                if (sum_defend <= sum_attack) {
                    System.out.println("玩家" + player.getName() + "的防具抵挡了" + sum_defend + "伤害");
                    player.setBleed((player.getBleed() - (sum_attack - sum_defend)));
                } else//没有防具
                {

                    System.out.println("玩家" + player.getName() + "的防具抵挡了" + sum_defend + "伤害");

                }

            }
            player.setFangju_durability((player.getFangju_durability() - 4));
        } else //没有防具了
        {
            sum_defend = map002.get(player).getBase_defensive_magic() + map002.get(player).getBase_defensive_physis();
            System.out.println("玩家" + player.getName() + "防具报废或无防具时抵挡了" + sum_defend + "伤害");
            player.setBleed((player.getBleed() - (sum_attack - sum_defend)));
        }
    }

    //开始战斗
    public void battle() {
        /*Player player3=new Player(player1.getName(),player1.getBleed(),player1.getPhysis_hurt(),player1.getMagic_hurt(),player1.getPhysis_defend(),player1.getMagic_defend(),player1.getWuqi_durability(),player1.getFangju_durability(),player1.getCritical());//玩家1
        Player player4=new Player(player2.getName(),player2.getBleed(),player2.getPhysis_hurt(),player2.getMagic_hurt(),player2.getPhysis_defend(),player2.getMagic_defend(),player2.getWuqi_durability(),player2.getFangju_durability(),player2.getCritical());//玩家2
        System.out.println(player3.toString());
        System.out.println(player4.toString());*/
        System.out.println(player1);
        System.out.println(player2);
        int i;
        for (i = 1; i <= 15; i++) {
            System.out.println("-------------------------------");
            k = (int) (Math.random() * 2);
            System.out.println("第" + i + "回合开始");
            if (k == 1) {
                attack(player1);
                defend(player2);
            } else {
                attack(player2);
                defend(player1);
            }
            System.out.println("第" + i + "回合结束");
            if (player1.getBleed() <= 0 || player2.getBleed() <= 0) {
                System.out.println("最终的获胜者为：");
                if (player1.getBleed() > player2.getBleed()) {
                    System.out.println(player1.getName());
                    sum_priece_play1 += 200;
                    sum_priece_play2 += 100;
                } else {
                    System.out.println(player2.getName());
                    sum_priece_play1 += 100;
                    sum_priece_play2 += 200;
                }
                System.out.println("比赛第二，友谊第一！！！");
                System.out.println("战斗结束后，不论输赢都有金币奖励哦！快去金币仓库看看吧！");
                System.out.println("即将回到新手村......");
                System.out.println("---------------欢迎回到新手村---------------");
                break;
            } else {
                System.out.println("玩家的血量分别为：");
                System.out.println("玩家1的血量：" + player1.getBleed());
                System.out.println("玩家2的血量：" + player2.getBleed());
                System.out.println("-------------------------------");
            }

        }
        if (player1.getBleed() > 0 && player2.getBleed() > 0) {
            System.out.println("最终的获胜者为：");
            if (player1.getBleed() > player2.getBleed()) {
                System.out.println(player1.getName());
                sum_priece_play1 += 200;
                sum_priece_play2 += 100;
            } else {
                System.out.println(player2.getName());
                sum_priece_play1 += 100;
                sum_priece_play2 += 200;
            }
            System.out.println("比赛第二，友谊第一！！！");
            System.out.println("战斗结束后，不论输赢都有金币奖励哦！快去金币仓库看看吧！");
            System.out.println("即将回到新手村......");
            System.out.println("---------------欢迎回到新手村---------------");
        } else {
        }
    }


    //退出游戏
    public void exit() {
        System.out.println("正在退出游戏...");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("已退出...");
        System.exit(0);
    }
}


