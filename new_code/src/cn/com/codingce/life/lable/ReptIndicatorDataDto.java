package cn.com.codingce.life.lable;

import java.io.Serializable;

/**
 * 报告指标项数据对象
 */
public class ReptIndicatorDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 指标项编码
     */
    private String code;

    /**
     * 结果数据
     */
    private String value;

    public ReptIndicatorDataDto() {
    }

    public ReptIndicatorDataDto(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
