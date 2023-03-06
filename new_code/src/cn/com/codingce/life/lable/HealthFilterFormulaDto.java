package cn.com.codingce.life.lable;

import java.io.Serializable;
import java.util.List;

/**
 * 健康过滤器公式
 *
 * @author ma
 */
public class HealthFilterFormulaDto implements Serializable {

    private static final long serialVersionUID = 1L;

//    private String target_syscode;       //目标体检类型编码
//    private String logictype;            //逻辑类型 固定为RPN(逆波兰式)
//    private int mainversion;             //计算公式主版本号(1、2、3)
//    private String subversion;           //计算公式子版本号(001、12、108b)
//    private String label;                //标签内容
//    private List<HealthFilterNodeDto> exp;    //公式表达式

    /**
     * 表达式ID
     */
    private int expressId;

    /**
     * 机构ID
     */
    private String orgId;

    /**
     * 目标体检类型编码
     */
    private String targetSysCode;

    /**
     * 逻辑类型 固定为RPN(逆波兰式)
     */
    private String logicType;

    /**
     * 计算公式主版本号(1、2、3)
     */
    private int mainVersion;

    /**
     * 计算公式子版本号(001、12、108b)
     */
    private String subVersion;

    /**
     * 标签内容
     */
    private String labelId;

    /**
     * 标签内容
     */
    private String label;

    /**
     * 关联项目
     */
    private List<ReptIndicatorDataDto> linkItems;

    /**
     * 公式表达式
     */
    private List<HealthFilterNodeDto> expressions;

    public HealthFilterFormulaDto() {
    }

    public HealthFilterFormulaDto(int expressId, String orgId, String targetSysCode, String logicType, int mainVersion, String subVersion, String labelId, String label, List<ReptIndicatorDataDto> linkItems, List<HealthFilterNodeDto> expressions) {
        this.expressId = expressId;
        this.orgId = orgId;
        this.targetSysCode = targetSysCode;
        this.logicType = logicType;
        this.mainVersion = mainVersion;
        this.subVersion = subVersion;
        this.labelId = labelId;
        this.label = label;
        this.linkItems = linkItems;
        this.expressions = expressions;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTargetSysCode() {
        return targetSysCode;
    }

    public void setTargetSysCode(String targetSysCode) {
        this.targetSysCode = targetSysCode;
    }

    public String getLogicType() {
        return logicType;
    }

    public void setLogicType(String logicType) {
        this.logicType = logicType;
    }

    public int getMainVersion() {
        return mainVersion;
    }

    public void setMainVersion(int mainVersion) {
        this.mainVersion = mainVersion;
    }

    public String getSubVersion() {
        return subVersion;
    }

    public void setSubVersion(String subVersion) {
        this.subVersion = subVersion;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ReptIndicatorDataDto> getLinkItems() {
        return linkItems;
    }

    public void setLinkItems(List<ReptIndicatorDataDto> linkItems) {
        this.linkItems = linkItems;
    }

    public List<HealthFilterNodeDto> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<HealthFilterNodeDto> expressions) {
        this.expressions = expressions;
    }
}
