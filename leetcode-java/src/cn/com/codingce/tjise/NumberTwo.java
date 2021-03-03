package cn.com.codingce.tjise;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author mxz
 */
public class NumberTwo {
    public static void main(String[] args) {

        test1();
    }

    /**
     * 猜数字
     */
    public static void test1() {
        Scanner sc = new Scanner(System.in);
        int input, num;

        try {
            for (int i = 0; i < 4; ++i) {
                num = (int) (Math.random() * 10);
                if (i == 3) {
                    System.out.println("机会用完了");
                    break;
                }
                System.out.println("欢迎进入游戏, 请输入猜测数字: ");
                input = sc.nextInt();

                if (input > num) {
                    System.out.println("大了\t" + "你还有" + (2 - i) + "次机会");
                    System.out.println("是否再来一次");
                    if ("ok".equals(sc.next()))
                        continue;
                    System.out.println("game over");
                    break;
                }

                if (input < num) {
                    System.out.println("小了\t" + "你还有" + (2 - i) + "次机会");
                    System.out.println("是否再来一次");
                    if ("ok".equals(sc.next()))
                        continue;
                    System.out.println("game over");
                    break;
                }
                System.out.println("猜测正确");
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("输入错误");
            e.printStackTrace();
        }
    }

    /**
     * 100米高空,乒乓球，每次反弹一半，距离地面小于0.1，求反弹次数和总长
     */
    public static void test2() {
        double distance = 100.0;
        for (int i = 0; ; i++) {
            if (distance < 0.1) {
                System.out.println("次数: " + i + "\t长度: " + distance);
                break;
            }
            distance /= 2.0;
        }
    }

    /**
     * 利用变量的输入输出,和计算实现一个购物小票的功能
     */
    public static void test3() {
        /*
         *  商品名称  单价  数量  小计
         *   电视	3.0	5	15.0
         *   冰箱	5.0	4	20.0
         *   洗衣机	6.5	4	26.0
         *   一共消费61.0
         *   实际缴费
         *   100
         *   找钱:39.0
         *   程序结束
         */

        //定义变量 输入信息
        Scanner input = new Scanner(System.in);
        String prodName1;
        String prodName2;
        String prodName3;

        double prodPrice1;
        double prodPrice2;
        double prodPrice3;

        int prodNum1;
        int prodNum2;
        int prodNum3;

        double prodTotal1;
        double prodTotal2;
        double prodTotal3;

        double pordSum;

        int pay;
        double zhaoqian;

        //开始业务
        System.out.println("请输入第1件商品名称");
        prodName1 = input.nextLine();
        System.out.println("请输入第2件商品的名称");
        prodName2 = input.nextLine();
        System.out.println("请输入第3件商品的名称");
        prodName3 = input.nextLine();

        System.out.println("请输入第1件商品的价格");
        prodPrice1 = input.nextDouble();
        System.out.println("请输入第2件商品的价格");
        prodPrice2 = input.nextDouble();
        System.out.println("请输入第3件商品的价格");
        prodPrice3 = input.nextDouble();

        System.out.println("请输入第1件商品的数量");
        prodNum1 = input.nextInt();
        System.out.println("请输入第2件商品的数量");
        prodNum2 = input.nextInt();
        System.out.println("请输入第3件商品的数量");
        prodNum3 = input.nextInt();

        //计算
        prodTotal1 = prodPrice1 * prodNum1;
        prodTotal2 = prodPrice2 * prodNum2;
        prodTotal3 = prodPrice3 * prodNum3;

        pordSum = prodTotal1 + prodTotal2 + prodTotal3;

        System.out.println("");

        //输出
        System.out.println("商品名称\t单价\t数量\t小计");
        System.out.println(prodName1 + "\t" + prodPrice1 + "\t" + prodNum1 + "\t" + prodTotal1);
        System.out.println(prodName2 + "\t" + prodPrice2 + "\t" + prodNum2 + "\t" + prodTotal2);
        System.out.println(prodName3 + "\t" + prodPrice3 + "\t" + prodNum3 + "\t" + prodTotal3);
        System.out.println("一共消费" + pordSum);
        System.out.println("实际缴费");
        pay = input.nextInt();
        zhaoqian = pay - pordSum;
        System.out.println("找钱:" + zhaoqian);
        System.out.println("程序结束");
    }


}
