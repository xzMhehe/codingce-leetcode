package cn.com.codingce.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author mxz
 */
@Data
@TableName("tb_class")
public class ClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 班级编号
	 */
	@TableId
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

}
