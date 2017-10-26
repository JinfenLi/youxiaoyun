package com.topview.school.service.school.area;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.school.AreaMapper;
import com.topview.school.po.Area;

/**
 * @Description 区域service层
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年9月13日 下午2:04:06
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Resource
	private AreaMapper areaMapper;
	
	@Override
	public List<Area> findJuniorArea(Integer parentId, Integer leave) {
		return areaMapper.findJuniorArea(parentId, leave);
	}

	@Override
	public Area selectAreaByPrimaryKey(Integer node) {
		return areaMapper.selectByPrimaryKey(node);
	}

}
