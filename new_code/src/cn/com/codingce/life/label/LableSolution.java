package cn.com.codingce.life.label;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class LableSolution {

    private HealthFilterFormulaDto pubFormula;
    private ArrayList<VariableTestDto> pubVariableTest = new ArrayList<>();

    public static void main(String[] args) {
        double matchesA = 12.1;
        String pattern = "[^-?\\d+.?\\d+]|([-+])";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(String.valueOf(matchesA));
        System.out.println(m.matches() + "       ================================");

        LableSolution lableSolution = new LableSolution();
        // 准备数据
        lableSolution.getDefaultFormula();
        // 按公式计算
        lableSolution.setVariable();
    }

    /**
     * getDefaultFormula 准备数据
     */
    public void getDefaultFormula() {
        List<HealthFilterNodeDto> exp = new ArrayList<>();
        exp.add(new HealthFilterNodeDto("var", "sbp001", "收缩压"));
        exp.add(new HealthFilterNodeDto("const", "120", "mmHg"));
        exp.add(new HealthFilterNodeDto("op", "GT", "大于"));

        exp.add(new HealthFilterNodeDto("var", "sbp001", "收缩压"));
        exp.add(new HealthFilterNodeDto("const", "140", "mmHg"));
        exp.add(new HealthFilterNodeDto("op", "LE", "小于等于"));

        exp.add(new HealthFilterNodeDto("op", "AND", "并且"));

        exp.add(new HealthFilterNodeDto("var", "dbp001", "舒张压"));
        exp.add(new HealthFilterNodeDto("const", "80", "mmHg"));
        exp.add(new HealthFilterNodeDto("op", "GT", "大于"));

        exp.add(new HealthFilterNodeDto("op", "AND", "并且"));

        exp.add(new HealthFilterNodeDto("var", "dbp001", "舒张压"));
        exp.add(new HealthFilterNodeDto("const", "100", "mmHg"));
        exp.add(new HealthFilterNodeDto("op", "LE", "小于等于"));

        exp.add(new HealthFilterNodeDto("op", "AND", "并且"));

        exp.add(new HealthFilterNodeDto("var", "fpg", "空腹血糖"));
        exp.add(new HealthFilterNodeDto("const", "6.1", "mmol/L"));
        exp.add(new HealthFilterNodeDto("op", "GT", "大于"));

        exp.add(new HealthFilterNodeDto("op", "AND", "并且"));

        exp.add(new HealthFilterNodeDto("var", "fpg", "空腹血糖"));
        exp.add(new HealthFilterNodeDto("const", "7", "mmol/L"));
        exp.add(new HealthFilterNodeDto("op", "LE", "小于等于"));

        exp.add(new HealthFilterNodeDto("op", "AND", "并且"));

        exp.add(new HealthFilterNodeDto("var", "conclusion", "结论"));
        exp.add(new HealthFilterNodeDto("const", "高血压", ""));
        exp.add(new HealthFilterNodeDto("op", "LIKE", "包含"));

        exp.add(new HealthFilterNodeDto("op", "AND", "并且"));

        HealthFilterFormulaDto formula = new HealthFilterFormulaDto();
        formula.setExpressions(exp);
        formula.setTargetSysCode("bshygmzb_tj");
        formula.setLogicType("RPN");
        formula.setMainVersion(1);
        formula.setSubVersion("01");
        formula.setLabel("高血压合并糖尿病");

        pubFormula = formula;

        /**
         * 准备数据字典
         */
        VariableTestDto variableTestDto1 = new VariableTestDto("sbp001", "130", "收缩压");
        VariableTestDto variableTestDto2 = new VariableTestDto("dbp001", "90", "舒张压");
        VariableTestDto variableTestDto3 = new VariableTestDto("fpg", "6.5", "空腹血糖");
        VariableTestDto variableTestDto4 = new VariableTestDto("conclusion", "该患者患有多年高血压，并伴随糖尿病和血脂异常。", "结论");
        pubVariableTest.add(variableTestDto1);
        pubVariableTest.add(variableTestDto2);
        pubVariableTest.add(variableTestDto3);
        pubVariableTest.add(variableTestDto4);

        System.out.println("done");
    }

    /**
     * calculation 按公式进行计算
     */
    public void setVariable() {
        Stack<HealthFilterNodeDto> stack = new Stack<>();

        for (HealthFilterNodeDto node : pubFormula.getExpressions()) {
            switch (node.getNodetype()) {
                case "var":
                    stack.push(node);
                    break;
                case "const":
                    stack.push(node);
                    break;
                case "op": {
                    HealthFilterNodeDto y = stack.pop();
                    HealthFilterNodeDto x = stack.pop();
                    stack.push(computeNode(node, x, y));
                }
                break;
                default:
                    break;
            }
        }

        System.out.println("按公式进行计算... done");
    }

    /**
     * 节点计算
     *
     * @param op
     * @param x
     * @param y
     * @return
     */
    private HealthFilterNodeDto computeNode(HealthFilterNodeDto op, HealthFilterNodeDto x, HealthFilterNodeDto y) {
        String xval = getNodeString(x);
        String yval = getNodeString(y);
        String result = "";

        switch (op.getNodecontent()) {
            case "GT": {
                double dx = Double.parseDouble(xval);
                double dy = Double.parseDouble(yval);
                result = String.valueOf(dx > dy);
            }
            break;
            case "LE": {
                double dx = Double.parseDouble(xval);
                double dy = Double.parseDouble(yval);
                result = String.valueOf(dx <= dy);
            }
            break;
            case "LT": {
                double dx = Double.parseDouble(xval);
                double dy = Double.parseDouble(yval);
                result = String.valueOf(dx < dy);
            }
            break;
            case "GE": {
                double dx = Double.parseDouble(xval);
                double dy = Double.parseDouble(yval);
                result = String.valueOf(dx >= dy);
            }
            break;
            case "EQ": {
                result = String.valueOf(xval.equals(yval));
            }
            break;
            case "NE": {
                result = String.valueOf(!xval.equals(yval));
            }
            break;
            case "AND": {
                Boolean bx = Boolean.parseBoolean(xval);
                Boolean by = Boolean.parseBoolean(yval);
                result = String.valueOf(bx && by);
            }
            break;
            case "OR": {
                Boolean bx = Boolean.parseBoolean(xval);
                Boolean by = Boolean.parseBoolean(yval);
                result = String.valueOf(bx || by);
            }
            break;
            case "LIKE": {
                result = String.valueOf(xval.contains(yval));
            }
            break;
            default:
                break;
        }
        HealthFilterNodeDto node = new HealthFilterNodeDto("const", result, "temp");

//        DataRow row = this.m_ComputeTemp.NewRow();
//        row["操作数X"] = xval;
//        row["操作符"] = op.nodecontent;
//        row["操作数Y"] = yval;
//        row["结果"] = node.nodecontent;
//        this.m_ComputeTemp.Rows.Add(row);
        System.out.println("==============================" + xval + "\t" + op.getNodecontent() + "\t" + yval + "\t" + node.getNodecontent() + "==============================");

        return node;
    }

    /**
     * 获取变量值
     *
     * @param x
     * @return
     */
    private String getNodeString(HealthFilterNodeDto x) {
        if ("const".equals(x.getNodetype())) {
            return x.getNodecontent();
        } else if ("var".equals(x.getNodetype())) {
            System.out.println(x.getNodecontent() + "\t");
            for (VariableTestDto str : pubVariableTest) {
                if (str.getName().equals(x.getNodecontent())) {
                    return str.getValue();
                }
            }
//            for (DataRow row in this.m_VarDict.Rows) {
//                if (row["变量名"].ToString() == x.nodecontent)
//                {
//                    return row["变量值"].ToString();
//                }
//            }
            return "";
        } else {
            return "";
        }
    }
    /*******************************WinForm Achieve End********************************/

}
