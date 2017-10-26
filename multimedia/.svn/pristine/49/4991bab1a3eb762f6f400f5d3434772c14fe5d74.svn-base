package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.po.Evaluation;

/** * @author  Rachel 
@date 创建时间：2016年9月20日 下午8:44:02 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public class EvaluationInfo {
	
    private String id;

    private String teacherId;
    
    private String teacherName;

	private String studentId;
	
	private String studentName;

    private String createTime;

    private String comment;

    private String templateId;
    
    private EvaluationTemplateInfo templateInfo;
    
    private Pager pager;
    
    private int stuscore;
    
    private int groupscore;
    
	private String groupId;
	
	private String groupName;
	
	private String startTime;
	
	private String endTime;

    
    public static Evaluation changeToPo(EvaluationInfo info){
    	Evaluation evaluation = new Evaluation();
    	if(info.getId()!=null){
    		info.setId(info.getId());
    	}
    	evaluation.setComment(info.getComment());
        evaluation.setStudentId(info.getStudentId());
        evaluation.setTeacherId(info.getTeacherId());
        evaluation.setTemplateId(info.getTemplateId());
        evaluation.setGroupId(info.getGroupId());
    	return evaluation;
    }
    
    public static List<Evaluation> changeToPo(List<EvaluationInfo> list){
    	
    	List<Evaluation> evaluation = new ArrayList<Evaluation>();
    	
    	if(list.size()>0){
    		int size =list.size();
    		for(int i=0;i<size;i++){
    			evaluation.add(changeToPo(list.get(i)));
    		}
    	}
    	return evaluation;
    	
    }
    
    public static EvaluationInfo changeToVo(Evaluation evaluation){
    	EvaluationInfo info = new EvaluationInfo();
    	info.setId(evaluation.getId());
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	info.setComment(evaluation.getComment());
    	info.setStudentId(evaluation.getStudentId());
    	info.setTeacherId(evaluation.getTeacherId());
    	info.setTemplateId(evaluation.getTemplateId());
    	info.setGroupId(evaluation.getGroupId());
    	info.setCreateTime(df.format(evaluation.getCreateTime()));
    	return info;
    }
    
    public static List<EvaluationInfo> changeToVo(List<Evaluation> evaluation){
    	List<EvaluationInfo> list = new ArrayList<EvaluationInfo>();
    	if(evaluation.size()>0){
    		int size = evaluation.size();
    		for(int i=0;i<size;i++){
    			list.add(changeToVo(evaluation.get(i)));
    		}
    	}
    	return list;
    	
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
    
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public EvaluationTemplateInfo getTemplateInfo() {
		return templateInfo;
	}

	public void setTemplateInfo(EvaluationTemplateInfo templateInfo) {
		this.templateInfo = templateInfo;
	}

		public int getStuscore() {
		return stuscore;
	}

	public void setStuscore(int stuscore) {
		this.stuscore = stuscore;
	}

	public int getGroupscore() {
		return groupscore;
	}

	public void setGroupscore(int groupscore) {
		this.groupscore = groupscore;
	}

		public String getGroupId() {
			return groupId;
		}

		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}

		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}
	

}
