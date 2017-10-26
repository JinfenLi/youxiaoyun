package com.topview.school.dao.healthy.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.healthy.HealthyMapper;
import com.topview.school.po.Healthy;

/**
 * 健康dao层接口实现类
 * @ClassName: HealthyMapperImpl 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月18日 下午10:20:19 
 * @version V1.0
 */
@Repository
public class HealthyMapperImpl extends BaseDaoImpl<Healthy> implements HealthyMapper{

	@Override
	public Healthy selectByStudent(String studentId) {
		return template.selectOne(getStatement("selectByStudent"), studentId);
	}

	@Override
	public List<Healthy> selectByClass(String classId) {
		return template.selectList(getStatement("selectByClass"), classId);
	}

	@Override
	public boolean deleteHealthyByStudentId(String studentId) {
			return template.delete(getStatement("deleteHealthyByStudentId"),studentId)>0;
	}

}
