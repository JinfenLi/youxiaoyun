package com.topview.multimedia.service.record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.RecordUpdateMapper;
import com.topview.multimedia.po.RecordUpdate;

/**
 * @Description 保存多媒体模块更新记录
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年11月11日 上午10:33:22
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SaveOrUpdateRecordProcess implements RecordUpdateProcess {

	@Resource
	private RecordUpdateMapper updateRecordMapper; 
	
	public boolean doProcess(RecordUpdateProcessContext context) {
		RecordUpdate record = context.getRecord();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		params.put("t_m_id", record.gettMId());
		params.put("module", record.getModule());
		param.put("params", params);
		
		List<RecordUpdate> records = updateRecordMapper.findByMulti(param);
		if(records != null && records.size() > 0) { //如果数据库中已存在则更新时间,如果没找到则插入新纪录
			record.setId(records.get(0).getId());
			updateRecordMapper.updateByPrimaryKeySelective(record);
		} else {
			updateRecordMapper.insertSelective(record);
		}
		context.getResult().setSuccess(true);
		return true;
	}

}
