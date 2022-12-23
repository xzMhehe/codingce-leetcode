package cn.com.codingce.数组与矩阵.字符串相加;

import java.util.HashSet;
import java.util.Set;

public class SolutionJq {

    public static void main(String[] args) {
        String items[][] = {
                {"财经1", "财经2", "财经3", "财经4", "财经5"},
                {"时政1", "时政2", "时政3", "时政4", "时政5"},
                {"体育I", "体育II", "体育III", "体育IV", "体育V", "体育VI", "体育VII"}
        };
        int weight[] = {2, 1, 3};
        show(items, weight);
    }

    /**
     * String items[][]={
     * {"财经1", "财经2", "财经3", "财经4", "财经5"},
     * {"时政1", "时政2", "时政3", "时政4", "时政5"},
     * {"体育I", "体育II", "体育III", "体育IV", "体育V", "体育VI", "体育VII"}
     * };
     * int weight[] = {2, 1, 3};
     * 输出：一维string数组
     * {
     * "财经1", "财经2", "时政1", "体育I", "体育II", "体育III",
     * "财经3", "财经4", "时政2", "体育IV", "体育V", "体育VI",
     * "财经5", "时政3", "体育VII",
     * "时政4",
     * "时政5"
     * }
     */
    public static String[] show(String items[][], int weight[]) {
        String[] ret = new String[]{};
        Set<String> set = new HashSet<>();
        int len = items.length;
        int fL = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < weight[i]; j++) {
                set.add(items[i][j]);
            }
        }

        return ret;
    }
}
