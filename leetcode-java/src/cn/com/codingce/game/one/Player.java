package cn.com.codingce.game.one;


public class Player {
    private String name;//角色名称
    private double bleed;//角色血量
    private double physis_hurt;//总共的物理伤害
    private double magic_hurt;//总共的法术伤害
    private double physis_defend;//总共的物理防御
    private double magic_defend;//总共的法术防御
    private double wuqi_durability;//武器的耐久度
    private double fangju_durability;//防具的耐久度
    private boolean dodge;//闪避（若触发true则躲避所有伤害，若触发false则正常受击）
    private boolean critical_hit;//触发暴击（若触发true则武器的暴击率乘以角色的本身的攻击力，否则正常攻击伤害）
    private double critical;//暴击率

    public Player() {
    }

    public Player(String name, double bleed, double physis_hurt, double magic_hurt, double physis_defend, double magic_defend, double wuqi_durability, double fangju_durability) {
        this.name = name;
        this.bleed = bleed;
        this.physis_hurt = physis_hurt;
        this.magic_hurt = magic_hurt;
        this.physis_defend = physis_defend;
        this.magic_defend = magic_defend;
        this.wuqi_durability = wuqi_durability;
        this.fangju_durability = fangju_durability;
    }

    public Player(String name, double bleed, double physis_hurt, double magic_hurt, double physis_defend, double magic_defend, double wuqi_durability, double fangju_durability, double critical) {
        this.name = name;
        this.bleed = bleed;
        this.physis_hurt = physis_hurt;
        this.magic_hurt = magic_hurt;
        this.physis_defend = physis_defend;
        this.magic_defend = magic_defend;
        this.wuqi_durability = wuqi_durability;
        this.fangju_durability = fangju_durability;
        this.critical = critical;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public boolean isCritical_hit() {
        return critical_hit;
    }

    public void setCritical_hit(boolean critical_hit) {
        this.critical_hit = critical_hit;
    }

    public double getCritical() {
        return critical;
    }

    public void setCritical(double critical) {
        this.critical = critical;
    }

    public double getWuqi_durability() {
        return wuqi_durability;
    }

    public void setWuqi_durability(double wuqi_durability) {
        this.wuqi_durability = wuqi_durability;
    }

    public double getFangju_durability() {
        return fangju_durability;
    }

    public void setFangju_durability(double fangju_durability) {
        this.fangju_durability = fangju_durability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBleed() {
        return bleed;
    }

    public void setBleed(double bleed) {
        this.bleed = bleed;
    }

    public double getPhysis_hurt() {
        return physis_hurt;
    }

    public void setPhysis_hurt(double physis_hurt) {
        this.physis_hurt = physis_hurt;
    }

    public double getMagic_hurt() {
        return magic_hurt;
    }

    public void setMagic_hurt(double magic_hurt) {
        this.magic_hurt = magic_hurt;
    }

    public double getPhysis_defend() {
        return physis_defend;
    }

    public void setPhysis_defend(double physis_defend) {
        this.physis_defend = physis_defend;
    }

    public double getMagic_defend() {
        return magic_defend;
    }

    public void setMagic_defend(double magic_defend) {
        this.magic_defend = magic_defend;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", bleed=" + bleed +
                ", physis_hurt=" + physis_hurt +
                ", magic_hurt=" + magic_hurt +
                ", physis_defend=" + physis_defend +
                ", magic_defend=" + magic_defend +
                ", wuqi_durability=" + wuqi_durability +
                ", fangju_durability=" + fangju_durability +
                ", critical=" + critical +
                '}';
    }


}


