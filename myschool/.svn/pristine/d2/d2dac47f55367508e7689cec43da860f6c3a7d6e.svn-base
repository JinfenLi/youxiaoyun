/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 下午8:21:01 
 * @version V1.0
 */
package com.topview.school.vo.appraise;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.school.po.Appraise;

/** 
 * @ClassName: AppraiseInfo 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 下午8:21:01 
 *  
 */
public class AppraiseInfo {

	  private String id;
	  private String studentId;
	  private String teacherId;
	  private String templateId;
	  private String star;
	  private String word;
	  private String time;
	  private String semester;
	  private String studentName;
	  private String picurl;
	  private String gclass;
	  private String phone;
	  private String subject;
	  private String position;
	  private String name;
	  private String stage;
	  
	  
	  
	  public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String teacherName;
	  private String type;
	  
	  public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	private Pager pager;

	

		public static AppraiseInfo changeToVo(Appraise appraise) {
			AppraiseInfo appraiseInfo = new AppraiseInfo();
			appraiseInfo.setId(appraise.getId());
			appraiseInfo.setStar(appraise.getStar());
			appraiseInfo.setStudentId(appraise.getStudentId());
			appraiseInfo.setTeacherId(appraise.getTeacherId());
			appraiseInfo.setTemplateId(appraise.getTemplateId());
			appraiseInfo.setWord(appraise.getWord());
			appraiseInfo.setType(appraise.getType());
			appraiseInfo.setStage(appraise.getStage());
			appraiseInfo.setSemester(appraise.getSemesterId());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			appraiseInfo.setTime(df.format(appraise.getTime()));
			return appraiseInfo;
		}
	
		public static List<AppraiseInfo> changeToVo(List<Appraise> list) {
			
			List<AppraiseInfo> infos = new ArrayList<AppraiseInfo>();
			if(list.size()>0) {
				int size = list.size();
				for (int i = 0; i < size; i++) {
					infos.add(changeToVo(list.get(i)));
				}
			}
			return infos;
		}
		
	  
		public static Appraise changeToPo(AppraiseInfo appraiseInfo){
		Appraise appraise = new Appraise();
		appraise.setId(appraiseInfo.getId());
		appraise.setStar(appraiseInfo.getStar());
		appraise.setStudentId(appraiseInfo.getStudentId());
		appraise.setTeacherId(appraiseInfo.getTeacherId());
		appraise.setTemplateId(appraiseInfo.getTemplateId());
		appraise.setWord(appraiseInfo.getWord());
		appraise.setStage(appraiseInfo.getStage());
		appraise.setSemesterId(appraiseInfo.getSemester());
		appraise.setType(appraiseInfo.getType());
		appraise.setClassId(appraiseInfo.getGclass());
		return appraise;		
	}
	  
		public static List<Appraise> changeToPo(List<AppraiseInfo> list){
		List<Appraise> appraise = new ArrayList<Appraise>();
		if(list.size()>0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				appraise.add(changeToPo(list.get(i)));
			}
		}
		return appraise;
		
	}
		
	  
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSemester() {
	return semester;
}

public void setSemester(String semester) {
	this.semester = semester;
}

public String getStudentName() {
return studentName;
}

public void setStudentName(String studentName) {
this.studentName = studentName;
}

public String getPicurl() {
return picurl;
}

public void setPicurl(String picurl) {
this.picurl = picurl;
}

public String getGclass() {
return gclass;
}

public void setGclass(String gclass) {
this.gclass = gclass;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public Pager getPager() {
	return pager;
}

public void setPager(Pager pager) {
	this.pager = pager;
}
}
