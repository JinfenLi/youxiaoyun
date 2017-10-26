/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年9月4日 下午8:50:19 
 * @version V1.0
 */
package com.topview.school.vo.appraise;

import java.util.ArrayList;
import java.util.List;

import com.topview.school.po.AppraiseSubjectTemplate;

/**
 * @ClassName: AppraiseSubjectInfo
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年9月4日 下午8:50:19
 * 
 */
public class AppraiseSubjectInfo {

	private String id;
	private String school_id;
	private String subject;
	private String word;
	public String teacher_id;
	private String star;
	private String sign;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public static AppraiseSubjectInfo changeToVo(AppraiseSubjectTemplate info) {
		AppraiseSubjectInfo appraiseSubjectInfo = new AppraiseSubjectInfo();
		appraiseSubjectInfo.setWord(info.getWord());
		appraiseSubjectInfo.setStar(info.getStar());
		return appraiseSubjectInfo;
	}

	public static List<AppraiseSubjectInfo> changeToVo(
			List<AppraiseSubjectTemplate> list) {
		List<AppraiseSubjectInfo> infos = new ArrayList<AppraiseSubjectInfo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToVo(list.get(i)));
			}
		}
		return infos;
	}

	public static AppraiseSubjectTemplate changeToPo(AppraiseSubjectInfo info) {
		AppraiseSubjectTemplate appraiseSubjectTemplate = new AppraiseSubjectTemplate();
		appraiseSubjectTemplate.setId(info.getId());
		appraiseSubjectTemplate.setSubject(info.getSubject());
		appraiseSubjectTemplate.setWord(info.getWord());
		appraiseSubjectTemplate.setStar(info.getStar());
		appraiseSubjectTemplate.setSign(info.getSign());
		return appraiseSubjectTemplate;

	}

	public static List<AppraiseSubjectTemplate> changeToPo(
			List<AppraiseSubjectInfo> infos) {
		List<AppraiseSubjectTemplate> list = new ArrayList<AppraiseSubjectTemplate>();
		if (infos.size() > 0) {
			int size = infos.size();
			for (int i = 0; i < size; i++) {
				list.add(changeToPo(infos.get(i)));
			}
		}
		return list;
	}

}
