package cn.com.codingce.设计模式.奖励的发放策略.v1;

public class FoodService {
    public String params;

    public void setWaimaiReq(String params) {
        this.params = params;
    }

    public void issueWaimai() {
        System.out.println("Waimai");
    }
}
