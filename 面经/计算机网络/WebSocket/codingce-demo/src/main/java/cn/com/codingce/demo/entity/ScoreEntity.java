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
@TableName("tb_score")
public class ScoreEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成绩编号
	 */
	@TableId
	private Integer scoreId;
	/**
	 * 学生编号
	 */
	private Integer studentId;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课程成绩
	 */
	private String score;

}
