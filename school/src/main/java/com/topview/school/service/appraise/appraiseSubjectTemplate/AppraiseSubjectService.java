/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年9月4日 下午4:26:55 
 * @version V1.0
 */
package com.topview.school.service.appraise.appraiseSubjectTemplate;

import com.topview.school.vo.appraise.AppraiseSubjectInfo;
import com.topview.school.vo.appraise.result.AppraiseSubjectInfoResult;

/** 
 * @ClassName: AppraiseSubjectService 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年9月4日 下午4:26:55 
 *  
 */
public interface AppraiseSubjectService {

	public AppraiseSubjectInfoResult getAppraiseSubject(AppraiseSubjectInfo info);
	
	public AppraiseSubjectInfoResult  saveAppraiseSubject(AppraiseSubjectInfo info);
	
	public AppraiseSubjectInfoResult  updateAppraiseSubject(AppraiseSubjectInfo info);
	
	public boolean appraiseSubjectSaveByExcel(AppraiseSubjectRequest appraiseSubjectSavaRequest);
	
	public boolean DeleteappraiseSubject(AppraiseSubjectRequest appraiseSubjectDeleteRequest);

	public boolean appraiseSubjectExportByExcel(String path);


}
