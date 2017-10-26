/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 下午8:18:27 
 * @version V1.0
 */
package com.topview.school.service.appraise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topview.school.service.base.BaseServiceImpl;
import com.topview.school.service.base.Process;
import com.topview.school.util.ExcelUtil;
import com.topview.school.vo.appraise.AppraiseForstudentInfo;
import com.topview.school.vo.appraise.AppraiseInfo;
import com.topview.school.vo.appraise.result.AppraiseInfoResult;

/** 
 * @ClassName: AppraiseServiceImpl 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 下午8:18:27 
 *  
 */
public class AppraiseServiceImpl  extends BaseServiceImpl implements AppraiseService{

	private List<AppraiseProcess> appraiseFindByTeacherProcesses;
	private List<AppraiseProcess> appraiseFindByPartentProcesses;
	private List<AppraiseProcess>  appraiseUpdateProcesses;
	private List<AppraiseProcess> appraiseSaveProcesses;
	private List<Process>  appraiseSaveByExcelProcesses;
    private List<AppraiseProcess> appraiseDeleteProcesses;


	@Override
	public AppraiseInfoResult AppraiseFindByTeacher(AppraiseInfo info) {
		return doProcess(info, appraiseFindByTeacherProcesses);
	}

	public AppraiseInfoResult AppraiseFindByPartent(AppraiseInfo info) {

		return doProcess(info, appraiseFindByPartentProcesses);
	}
	

	public AppraiseInfoResult UpdateAppraiseSelective(AppraiseInfo info) {

		return doProcess(info, appraiseUpdateProcesses);
	}

	@Override
	public AppraiseInfoResult SaveAppraise(AppraiseInfo info) {

		return doProcess(info, appraiseSaveProcesses);
	}

	@Override
	public AppraiseInfoResult DeleteAppraise(AppraiseInfo info) {
		return doProcess(info, appraiseDeleteProcesses);
	}
	
	public List<AppraiseProcess> getAppraiseFindByPartentProcesses() {
		return appraiseFindByPartentProcesses;
	}

	public void setAppraiseFindByPartentProcesses(
			List<AppraiseProcess> appraiseFindByPartentProcesses) {
		this.appraiseFindByPartentProcesses = appraiseFindByPartentProcesses;
	}

	public List<AppraiseProcess> getAppraiseFindByTeacherProcesses() {
		return appraiseFindByTeacherProcesses;
	}



	public void setAppraiseFindByTeacherProcesses(
			List<AppraiseProcess> appraiseFindByTeacherProcesses) {
		this.appraiseFindByTeacherProcesses = appraiseFindByTeacherProcesses;
	}

	public List<AppraiseProcess> getAppraiseUpdateProcesses() {
		return appraiseUpdateProcesses;
	}

	public void setAppraiseUpdateProcesses(
			List<AppraiseProcess> appraiseUpdateProcesses) {
		this.appraiseUpdateProcesses = appraiseUpdateProcesses;
	}
	public List<AppraiseProcess> getAppraiseSaveProcesses() {
		return appraiseSaveProcesses;
	}

	public void setAppraiseSaveProcesses(List<AppraiseProcess> appraiseSaveProcesses) {
		this.appraiseSaveProcesses = appraiseSaveProcesses;
	}



	public List<Process> getAppraiseSaveByExcelProcesses() {
		return appraiseSaveByExcelProcesses;
	}

	public void setAppraiseSaveByExcelProcesses(
			List<Process> appraiseSaveByExcelProcesses) {
		this.appraiseSaveByExcelProcesses = appraiseSaveByExcelProcesses;
	}

	
	
	public List<AppraiseProcess> getAppraiseDeleteProcesses() {
		return appraiseDeleteProcesses;
	}

	public void setAppraiseDeleteProcesses(
			List<AppraiseProcess> appraiseDeleteProcesses) {
		this.appraiseDeleteProcesses = appraiseDeleteProcesses;
	}

	public AppraiseInfoResult doProcess(AppraiseInfo appraise,List<AppraiseProcess> processes) {
		AppraiseProcessContext context = new AppraiseProcessContext();
		AppraiseInfoResult result = new AppraiseInfoResult();
		context.setResult(result);
		context.setInfo(appraise);
		for(AppraiseProcess process : processes){
			if(!process.doProcess(context))
				break;
		}
			
		return context.getResult();
	}
	
	@Override
	public boolean appraiseSave(AppraiseSavaRequest appraiseSavaRequest) {
		return excute(appraiseSavaRequest, appraiseSaveByExcelProcesses);
	}


	@Override
	public boolean createAppraiseExcel(String filePath,
			List<AppraiseForstudentInfo> infos) {
		List<String> headList = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		headList.add("学生姓名");
		headList.add("学生学号");
		headList.add("评价星数");
		headList.add("评价内容");
		headList.add("班級");
		
		map.put("学生姓名", "studentName");
		map.put("学生学号", "studentNumber");
		map.put("评价星数", "star");
		map.put("评价内容", "word");
		map.put("班級", "gclass");
		ExcelUtil poi = new ExcelUtil();
		try {
			poi.exportExcel("Sheet1", filePath, map, headList, infos, 1,
					AppraiseForstudentInfo.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	

}
