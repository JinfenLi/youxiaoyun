package com.topview.multimedia.service.record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.RecordUpdateMapper;
import com.topview.multimedia.po.RecordUpdate;
import com.topview.multimedia.vo.RecordUpdateVo;

/**
 * @Description 根据学校/年级/班级id查询各个模块的最后更新时间
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年11月11日 下午2:04:01
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class FindRecordUpdateProcess implements RecordUpdateProcess {

	@Resource
	private RecordUpdateMapper recordUpdateMapper;
	
	public boolean doProcess(RecordUpdateProcessContext context) {
		String tMId = context.getRecord().gettMId();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		params.put("t_m_id", tMId);
		param.put("params", params);
		List<RecordUpdate> records = recordUpdateMapper.findByMulti(param);
		context.getResult().setResult(RecordUpdateVo.changeToVo(records));
		context.getResult().setSuccess(true);
		return true;
	}

}
