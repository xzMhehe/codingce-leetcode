package cn.com.codingce.demo.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生编号
     */
    private Integer studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生性别
     */
    private String studentGender;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date studentBirth;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班主任姓名
     */
    private String headTeacher;

    /**
     * 年级
     */
    private String classGrade;

}
