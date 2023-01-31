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
public class StudentScoreInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer studentId;

    private String studentName;

    private String studentGender;

    private String chineseScore;

    private String mathsScore;

    private String englishScore;

    private String physicsScore;

    private String chemistryScore;

    private String biologyScore;
    
    private String sumScore;

}
