package cn.com.codingce.game.one;

//角色
public class User {
    private int id;
    private String name;
    private double base_blood_volume;//血量
    private double physis_base_aggressivity;//基础物理攻击
    private double magic_base_aggressivity;//基础法术攻击
    private double base_defensive_physis;//基础物理防御
    private double base_defensive_magic;//基础法术防御

    public User() {
    }

    /*public User(int id, String name, double base_blood_volume, double physis_base_aggressivity, double magic_base_aggressivity, double base_defensive_power) {
        this.id = id;
        this.name = name;
        this.base_blood_volume = base_blood_volume;
        this.physis_base_aggressivity = physis_base_aggressivity;
        this.magic_base_aggressivity = magic_base_aggressivity;
        this.base_defensive_physis = base_defensive_power;
    }*/

    public User(int id, String name, double base_blood_volume, double physis_base_aggressivity, double magic_base_aggressivity, double base_defensive_physis, double base_defensive_magic) {
        this.id = id;
        this.name = name;
        this.base_blood_volume = base_blood_volume;
        this.physis_base_aggressivity = physis_base_aggressivity;
        this.magic_base_aggressivity = magic_base_aggressivity;
        this.base_defensive_physis = base_defensive_physis;
        this.base_defensive_magic = base_defensive_magic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBase_blood_volume() {
        return base_blood_volume;
    }

    public void setBase_blood_volume(double base_blood_volume) {
        this.base_blood_volume = base_blood_volume;
    }

    public double getPhysis_base_aggressivity() {
        return physis_base_aggressivity;
    }

    public void setPhysis_base_aggressivity(double physis_base_aggressivity) {
        this.physis_base_aggressivity = physis_base_aggressivity;
    }

    public double getMagic_base_aggressivity() {
        return magic_base_aggressivity;
    }

    public void setMagic_base_aggressivity(double magic_base_aggressivity) {
        this.magic_base_aggressivity = magic_base_aggressivity;
    }

    public double getBase_defensive_physis() {
        return base_defensive_physis;
    }

    public void setBase_defensive_physis(double base_defensive_physis) {
        this.base_defensive_physis = base_defensive_physis;
    }

    public double getBase_defensive_magic() {
        return base_defensive_magic;
    }

    public void setBase_defensive_magic(double base_defensive_magic) {
        this.base_defensive_magic = base_defensive_magic;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", base_blood_volume=" + base_blood_volume +
                ", physis_base_aggressivity=" + physis_base_aggressivity +
                ", magic_base_aggressivity=" + magic_base_aggressivity +
                ", base_defensive_physis=" + base_defensive_physis +
                ", base_defensive_magic=" + base_defensive_magic +
                '}';
    }
}

