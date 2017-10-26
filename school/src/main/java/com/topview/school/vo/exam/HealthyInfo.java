package com.topview.school.vo.exam;

import com.topview.school.po.Healthy;

/**
 * 健康信息
 * @ClassName: HealthyInfo 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月19日 下午1:29:10 
 * @version V1.0
 */
public class HealthyInfo {

	//id
    private String id;
    //学生姓名
    private String studentName;
    //学生证
    private String studentId;
    //身高
    private String height;
    //体重
    private String weight;
    //血型
    private String bloodType;
    //血压
    private String bloodPressure;
    //左视力
    private String leftVision;
    //右视力
    private String rightVision;
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
    private String bodyTem;
    //备注
    private String remarks;
    //**************IOS要求加的字段**************
   //出生年月
    private String feteday;
    //学生头像
    private String picUrl; 
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public String getLeftVision() {
		return leftVision;
	}
	public void setLeftVision(String leftVision) {
		this.leftVision = leftVision;
	}
	public String getRightVision() {
		return rightVision;
	}
	public void setRightVision(String rightVision) {
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
	public String getBodyTem() {
		return bodyTem;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getFeteday() {
		return feteday;
	}
	public void setFeteday(String feteday) {
		this.feteday = feteday;
	}
	public void setBodyTem(String bodyTem) {
		this.bodyTem = bodyTem;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void setHealthy(Healthy h){
		this.studentId = h.getStudentId();
		this.allergy = h.getAllergy();
		this.bloodPressure = h.getBloodPressure()+"";
		this.bloodType = h.getBloodType();
		this.bodyTem = h.getBodyTem()+"";
		this.height = h.getHeight()+"";
		this.historyMedical = h.getHistoryMedical();
		this.id = h.getId();
		this.leftListen = h.getLeftListen();
		this.leftVision = h.getLeftVision()+"";
		this.oral = h.getOral();
		this.remarks = h.getRemarks();
		this.rightListen = h.getRightListen();
		this.rightVision = h.getRightVision()+"";
		this.weight = h.getWeight()+"";
	}
	
	public Healthy getHealthy(HealthyInfo hi){
		Healthy h = new Healthy();
		h.setAllergy(hi.getAllergy());
		h.setBloodPressure(hi.getBloodPressure() == null ? 0 : Double.valueOf(hi.getBloodPressure()));
		h.setBloodType(hi.getBloodType());
		h.setBodyTem(hi.getBodyTem() == null ? 0 : Double.valueOf(hi.getBodyTem()));
		h.setHeight(hi.getHeight() == null ? 0 : Double.valueOf(hi.getHeight()));
		h.setHistoryMedical(hi.getHistoryMedical());
		h.setLeftListen(hi.getLeftListen());
		h.setLeftVision(hi.getLeftVision() == null ? 0 : Double.valueOf(hi.getLeftVision()));
		h.setOral(hi.getOral());
		h.setRemarks(hi.getRemarks());
		h.setRightListen(hi.getRightListen());
		h.setRightVision(hi.getRightVision() == null ? 0 : Double.valueOf(hi.getRightVision()));
		h.setWeight(hi.getWeight() == null ? 0 : Double.valueOf(hi.getWeight()));
		return h;
	}
	
}
