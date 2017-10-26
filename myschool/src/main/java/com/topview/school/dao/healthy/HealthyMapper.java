package com.topview.school.dao.healthy;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Healthy;

/**
 * 健康dao层
 * @ClassName: HealthyMapper 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月18日 下午10:15:49 
 * @version V1.0
 */
public interface HealthyMapper extends BaseDao<Healthy>{
    
	public Healthy selectByStudent(String studentId);
	
	public List<Healthy> selectByClass(String classId);
}