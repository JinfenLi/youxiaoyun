package com.topview.school.po;

/**
 * 健康类
 * @ClassName: Healthy 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月18日 下午10:10:03 
 * @version V1.0
 */
public class Healthy {
    //id
    private String id;
    //身高
    private Double height;
    //体重
    private Double weight;
    //血型
    private String bloodType;
    //血压
    private Double bloodPressure;
    //左视力
    private Double leftVision;
    //右视力
    private Double rightVision;
    //左听力
    private String leftListen;
    //右听力
    private String rightListen;
    //口腔
    private String oral;
    //既往病史
    private String historyMedical;
    //过敏反应
    private String allergy;
    //体检体温
    private Double bodyTem;
    //备注
    private String remarks;
    //学生id
    private String studentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(Double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Double getLeftVision() {
        return leftVision;
    }

    public void setLeftVision(Double leftVision) {
        this.leftVision = leftVision;
    }

    public Double getRightVision() {
        return rightVision;
    }

    public void setRightVision(Double rightVision) {
        this.rightVision = rightVision;
    }

    public String getLeftListen() {
        return leftListen;
    }

    public void setLeftListen(String leftListen) {
        this.leftListen = leftListen;
    }

    public String getRightListen() {
        return rightListen;
    }

    public void setRightListen(String rightListen) {
        this.rightListen = rightListen;
    }

    public String getOral() {
        return oral;
    }

    public void setOral(String oral) {
        this.oral = oral;
    }

    public String getHistoryMedical() {
        return historyMedical;
    }

    public void setHistoryMedical(String historyMedical) {
        this.historyMedical = historyMedical;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public Double getBodyTem() {
        return bodyTem;
    }

    public void setBodyTem(Double bodyTem) {
        this.bodyTem = bodyTem;
    }

    
    public String getRemarks() {
        return remarks;
    }

    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

   
    public String getStudentId() {
        return studentId;
    }

    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}