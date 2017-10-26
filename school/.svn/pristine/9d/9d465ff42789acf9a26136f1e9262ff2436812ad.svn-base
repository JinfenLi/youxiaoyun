/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 下午8:18:12 
 * @version V1.0
 */
package com.topview.school.service.appraise;

import java.util.List;

import com.topview.school.vo.appraise.AppraiseForstudentInfo;
import com.topview.school.vo.appraise.AppraiseInfo;
import com.topview.school.vo.appraise.result.AppraiseInfoResult;

/** 
 * @ClassName: AppraiseService 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 下午8:18:12 
 *  
 */
public interface AppraiseService {

    public AppraiseInfoResult AppraiseFindByTeacher(AppraiseInfo info);
	
	public AppraiseInfoResult AppraiseFindByPartent(AppraiseInfo info);
	
	public AppraiseInfoResult UpdateAppraiseSelective(AppraiseInfo info);

    public AppraiseInfoResult SaveAppraise(AppraiseInfo info);
    
    public AppraiseInfoResult DeleteAppraise(AppraiseInfo info);
    
    public boolean appraiseSave(AppraiseSavaRequest appraiseSavaRequest);
    
	public boolean createAppraiseExcel(String filePath, List<AppraiseForstudentInfo> infos);
    
}
