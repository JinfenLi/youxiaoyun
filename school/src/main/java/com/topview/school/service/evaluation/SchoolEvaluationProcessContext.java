package com.topview.school.service.evaluation;

import com.topview.multimedia.vo.EvaluationInfo;
import com.topview.multimedia.vo.EvaluationTemplateInfo;
import com.topview.multimedia.vo.GroupInfo;
import com.topview.multimedia.vo.result.EvaluationInfoResult;
import com.topview.multimedia.vo.result.EvaluationTemplateInfoResult;
import com.topview.multimedia.vo.result.GroupInfoResult;

/** * @author  Rachel 
@date 创建时间：2016年9月20日 下午9:13:53 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public class SchoolEvaluationProcessContext {
	
	private EvaluationInfo info;
	private EvaluationTemplateInfo eti;
	private GroupInfo gi;
	
	private EvaluationInfoResult result;
	private EvaluationTemplateInfoResult etir;
	private GroupInfoResult gir;
	public GroupInfo getGi() {
		return gi;
	}
	public void setGi(GroupInfo gi) {
		this.gi = gi;
	}
	public GroupInfoResult getGir() {
		return gir;
	}
	public void setGir(GroupInfoResult gir) {
		this.gir = gir;
	}
	public EvaluationInfo getInfo() {
		return info;
	}
	public void setInfo(EvaluationInfo info) {
		this.info = info;
	}
	public EvaluationTemplateInfo getEti() {
		return eti;
	}
	public void setEti(EvaluationTemplateInfo eti) {
		this.eti = eti;
	}
	public EvaluationInfoResult getResult() {
		return result;
	}
	public void setResult(EvaluationInfoResult result) {
		this.result = result;
	}
	public EvaluationTemplateInfoResult getEtir() {
		return etir;
	}
	public void setEtir(EvaluationTemplateInfoResult etir) {
		this.etir = etir;
	}
	
}
