package com.topview.school.controller.multimedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.multimedia.service.record.RecordUpdateService;
import com.topview.multimedia.vo.RecordUpdateVo;
import com.topview.school.util.JSONUtil;

/**
 * @Description 多媒体Controller主要处理各个多媒体子模块共有的业务
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年11月11日 下午2:20:48
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/multimedia", produces = "text/html;chatset=UTF-8")
public class MultimediaController {

	@Resource
	private RecordUpdateService recordUpdateService;

	/**
	 * 获取各个模块的最新更新时间
	 * 
	 * @param schoolId
	 * @param gradeId
	 *            多个年级id以逗号分隔
	 * @param clazzId
	 *            多个班级id以逗号分隔
	 * @return
	 */
	@RequestMapping("/getLastUpdate")
	@ResponseBody
	public String getLastUpdate(String schoolId, String gradeId, String clazzId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<RecordUpdateVo> vos = new ArrayList<RecordUpdateVo>();

		vos.addAll(recordUpdateService.findRecordUpdate(schoolId).getResult()); //查询校园模块更新时间

		if (gradeId != null && !"".equals(gradeId)) { //查询教子学园
			String[] gId = gradeId.split(",");
			for (int i = 0; i < gId.length; i++) {
				vos.addAll(recordUpdateService.findRecordUpdate(gId[i])
						.getResult());
			}
		}

		if (clazzId != null && !"".equals(clazzId)) { //查询班级模块
			String[] cId = clazzId.split(",");
			for (int i = 0; i < cId.length; i++) {
				vos.addAll(recordUpdateService.findRecordUpdate(cId[i]) //TODO 如果有多个班级因为只返回一个所以要返回其中最新的时间，此处还没做处理
						.getResult());
			}
		}
		
		for (RecordUpdateVo vo : vos) {
			resultMap.put(vo.getModule(), vo.getLastUpdate());
		}
		resultMap.put("success", true);
		return JSONUtil.toObject(resultMap).toString();
	}
}
