package cn.com.codingce.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mxz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChineseScoreDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级编号
     */
    private Integer classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 年级
     */
    private String classGrade;

    /**
     * 班主任姓名
     */
    private String headTeacher;

    private String minScore;

    private String maxScore;

    private String avgScore;

}
