package cn.com.codingce.life.label;

import java.io.Serializable;

public class VariableTestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String value;
    private String remark;

    public VariableTestDto() {
    }

    public VariableTestDto(String name, String value, String remark) {
        this.name = name;
        this.value = value;
        this.remark = remark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
