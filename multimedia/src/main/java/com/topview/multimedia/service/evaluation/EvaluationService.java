package com.topview.multimedia.service.evaluation;

import java.util.List;
import java.util.Map;

import com.topview.multimedia.vo.EvaluationInfo;
import com.topview.multimedia.vo.EvaluationTemplateInfo;
import com.topview.multimedia.vo.GroupInfo;
import com.topview.multimedia.vo.result.EvaluationInfoResult;
import com.topview.multimedia.vo.result.EvaluationTemplateInfoResult;
import com.topview.multimedia.vo.result.GroupInfoResult;

/** * @author  Rachel 
@date 创建时间：2016年9月20日 下午8:36:50 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public interface EvaluationService {
	
	/**
	 * 保存评价
	 * @param info
	 * @return
	 */
	public EvaluationInfoResult evaluationSave(EvaluationInfo info);
	/**
	 * 删除模板
	 * @param info
	 * @return
	 */
	public EvaluationInfoResult evaluationDelete(EvaluationInfo info); 
	/**
	 * 分类型评价计分
	 * @param info
	 * @return
	 */
	public List<Map<String,Object>> evaluationSorted(Map<String,Object> map); 
	/**
	 * 获取学生某段时间总分
	 * @param info
	 * @return
	 */
	public Integer stuTotalScore(Map<String,Object> map); 
	
	/**
	 * 保存评价模板
	 * @param info
	 * @return
	 */
	public EvaluationTemplateInfoResult evaluationTemplateSave(EvaluationTemplateInfo info);
	
	/**
	 * 判断模板上传者
	 * @param info
	 * @return
	 */
	public EvaluationTemplateInfoResult templateJudgeOwner(EvaluationTemplateInfo info);
	/**
	 * 修改模板
	 * @param info
	 * @return
	 */
	public EvaluationTemplateInfoResult templateUpdate(EvaluationTemplateInfo info);
	/**
	 * 删除模板
	 * @param info
	 * @return
	 */
	public EvaluationTemplateInfoResult templateDeleteByTeacher(EvaluationTemplateInfo info);
	/**
	 * 保存小组
	 * @param info
	 * @return
	 */
	public GroupInfoResult  groupSave(GroupInfo info);
	/**
	 * 修改小组信息
	 * @param info
	 * @return
	 */
	public GroupInfoResult  groupUpdate(GroupInfo info);
	/**
	 * 查看班级和自己创建的所有小组
	 * @param info
	 * @return
	 */
	public GroupInfoResult groupFindAll(GroupInfo info);
	/**
	 * 查看某个学生的评价
	 * @param info
	 * @return
	 */
	public EvaluationInfoResult evaluationFindByStudent(EvaluationInfo info);
	/**
	 * 通过小组Id查询学生id
	 * @param info
	 * @return
	 */
	public GroupInfoResult studentFindByGroupId(GroupInfo info);
	/**
	 * 根据模板id查看模板
	 * @param info
	 * @return
	 */
	public  EvaluationTemplateInfoResult templateFindById(EvaluationTemplateInfo info);
	/**
	 * 查找公共模板
	 * @param info
	 * @return
	 */
	public  EvaluationTemplateInfoResult templateFindByUnpass(EvaluationTemplateInfo info);
	/**
	 * 按类型查找
	 * @param info
	 * @return
	 */
	public  EvaluationTemplateInfoResult templateFindByType(EvaluationTemplateInfo info);
	/**
	 * 获取个人模板个数
	 * @param info
	 * @return
	 */
	public  EvaluationTemplateInfoResult templateCountByUser(EvaluationTemplateInfo info);
	/**
	 * 删除审核未通过模板
	 * @param info
	 * @return
	 */
	public  EvaluationTemplateInfoResult templateRemove(EvaluationTemplateInfo info);
	/**
	 * 获取用户能用的模板
	 * @param info
	 * @return
	 */
	public  EvaluationTemplateInfoResult templateAvailable(EvaluationTemplateInfo info);
	/**
	 * 删除小组
	 * @param info
	 * @return
	 */
	public GroupInfoResult groupDelete(GroupInfo info);
	/**
	 * 查看个人模板个数
	 * @param uploaderId
	 * @return
	 */
	public int selectCount(String uploaderId);
    /**
     * 判断是否是小组评价
     * @param info
     * @return
     */
	public boolean isGroupEvaluation(EvaluationInfo info);
	/**
	 * 判断是否为自己作出的评价
	 * @param info
	 * @return
	 */
	public boolean isOwnEvaluation(EvaluationInfo info);

}
