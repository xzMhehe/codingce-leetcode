package cn.com.codingce.随做.平滑加权轮询算法;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 平滑加权轮询算法
 *
 * @author inke219223m
 */
public class SmoothWeightedRobinTest {

    /**
     * 实例列表
     */
    private List<ServerConfig> serverList = new ArrayList<>();
    /**
     * 最大权重
     */
    private Integer weightSum;


    public SmoothWeightedRobinTest(List<ServerConfig> serverList) {
        this.serverList = serverList;
        this.weightSum = serverList.stream().map(ServerConfig::getWeight).reduce((x, y) -> x += y).get();
    }

    public static void main(String[] args) {
        List<ServerConfig> list = new ArrayList<>();

        list.add(new ServerConfig("192.168.1.1", 5, 5));
        list.add(new ServerConfig("192.168.1.2", 1, 1));
        list.add(new ServerConfig("192.168.1.3", 1, 1));
        // 初始每个实例的当前有效权重为配置权重（初始化权重），并求得配置权重和weightSum；
        //int weightSum = list.stream().map(ServerConfig::getWeight).reduce((x, y) -> x += y).get();
        SmoothWeightedRobinTest test = new SmoothWeightedRobinTest(list);
        test.test();
    }

    // 临时存放每次选中后的当前权重
    List<ServerConfig> tempList = new ArrayList<>();
    // 所有选中的列表
    List<String> chooseList = new ArrayList<>();

    public void test() {
        System.out.println("初始化当前权重：" + serverList.toString());
        System.out.println();
        for (int i = 1; i <= 20; i++) {
            this.nextServerIndex(i);
        }
        System.out.println(chooseList);
    }

    public ServerConfig nextServerIndex(int index) {
        // 选出当前有效权重最大的实例，将当前有效权重currentWeight减去所有实例的"权重和"（weightSum），且变量tmpSv指向此位置；
        ServerConfig max = Collections.max(serverList, Comparator.comparingInt(ServerConfig::getCurrentWeight));
        // 选中的实例
        ServerConfig tmpSv = null;

        for (ServerConfig serverConfig : serverList) {
            if (max.equals(serverConfig)) {
                serverConfig.setCurrentWeight(serverConfig.getCurrentWeight() - weightSum);
                if (tmpSv == null || serverConfig.getCurrentWeight() > tmpSv.getCurrentWeight()) {
                    tmpSv = serverConfig;
                }
            }
            //为了打印
            try {
                tempList.add(serverConfig.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            //将每个实例的当前有效权重currentWeight都加上配置权重weight；
            serverConfig.setCurrentWeight(serverConfig.getCurrentWeight() + serverConfig.getWeight());
        }
        //选中后的当前权重
        System.out.println("第" + index + "次选中后的当前权重:" + tempList.toString());
        tempList.clear();
        // 选中的实例
        System.out.println("第" + index + "次选中的实例:" + tmpSv.getName());
        // 全部选中的实例
        chooseList.add(tmpSv.getName());
        // 选中前的当前权重
        System.out.println("第" + (index + 1) + "次选中前的当前权重:" + serverList.toString());
        System.out.println();

        return tmpSv;
    }


    public static class ServerConfig {

        //服务名称
        public String name;

        //初始权重
        public int weight;

        //当前权重
        public int currentWeight;

        public ServerConfig() {
        }

        public ServerConfig(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public ServerConfig(String name, int weight, int currentWeight) {
            this.name = name;
            this.weight = weight;
            this.currentWeight = currentWeight;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getCurrentWeight() {
            return currentWeight;
        }

        public void setCurrentWeight(int currentWeight) {
            this.currentWeight = currentWeight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "ServerConfig{" + "name='" + name + '\'' + ", curr=" + currentWeight + '}';
        }

        @Override
        public ServerConfig clone() throws CloneNotSupportedException {
//            // 使用Jackson序列化进行深拷贝
//            ObjectMapper objectMapper = new ObjectMapper();
//            User copyUser = objectMapper.readValue(objectMapper.writeValueAsString(user), User.class);

            ServerConfig serverConfig = new ServerConfig();
            serverConfig.setCurrentWeight(this.currentWeight);
            serverConfig.setName(this.name);
            serverConfig.setWeight(this.weight);
            return serverConfig;
        }
    }

}



