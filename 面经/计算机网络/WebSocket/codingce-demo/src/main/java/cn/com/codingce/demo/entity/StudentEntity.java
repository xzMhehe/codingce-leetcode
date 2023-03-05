package cn.com.codingce.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author mxz
 */
@Data
@TableName("tb_student")
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生编号
     */
    @TableId
    private Integer studentId;
    /**
     * 班级编号
     */
    private Integer classId;
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

}
