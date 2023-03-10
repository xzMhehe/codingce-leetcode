package cn.com.codingce.life.label;

import java.io.Serializable;

/**
 * 体检过滤器操作节点
 */
public class HealthFilterNodeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nodetype;     //节点类型【变量-var、常量-const、运算符-op】
    private String nodecontent;  //节点内容【如果是变量-变量编号，如果是常量-常量值，如果是运算符-缩写】
    private String nodedesc;     //节点注释【如果是变量-变量名称，如果是常量-常量含义，如果是运算符-中文解释】

    /************节点类型************
     ---------------------------------
     缩写 英文          中文
     ---------------------------------
     var variable        变量
     const constant      常量
     op operator         运算符
     *********************************/


    /************运算符*******************************************
     --------------------------------------------------------------
     缩写 英文                  中文 符号  数据类型
     --------------------------------------------------------------
     EQ      EQUAL                   等于		=		String
     NE      NOT EQUAL               不等于      <>      String
     GT      GREATER THAN            大于        >		Double
     LT      LESS THAN               小于        <       Double
     GE      GREATER THAN OR EQUAL   大于等于	>=		Double
     LE      LESS THAN OR EQUAL      小于等于	<=		Double
     OR      OR                      或者		||		Bool
     AND     AND                     并且		&&		Bool
     LIKE    LIKE                    包含        like    String
     **************************************************************/

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNodetype() {
        return nodetype;
    }

    public void setNodetype(String nodetype) {
        this.nodetype = nodetype;
    }

    public String getNodecontent() {
        return nodecontent;
    }

    public void setNodecontent(String nodecontent) {
        this.nodecontent = nodecontent;
    }

    public String getNodedesc() {
        return nodedesc;
    }

    public void setNodedesc(String nodedesc) {
        this.nodedesc = nodedesc;
    }

    public HealthFilterNodeDto(String nodetype, String nodecontent, String nodedesc) {
        this.nodetype = nodetype;
        this.nodecontent = nodecontent;
        this.nodedesc = nodedesc;
    }

    public HealthFilterNodeDto() {
    }
}
