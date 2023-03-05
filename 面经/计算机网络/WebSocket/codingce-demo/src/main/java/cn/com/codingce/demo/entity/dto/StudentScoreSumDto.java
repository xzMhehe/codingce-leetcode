package cn.com.codingce.demo.entity.dto;

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
public class StudentScoreSumDto implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private Integer studentId;

    private String studentName;

    private String sumScore;

}
