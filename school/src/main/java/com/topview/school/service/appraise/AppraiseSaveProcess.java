/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 下午8:18:52 
 * @version V1.0
 */
package com.topview.school.service.appraise;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.dao.appraise.AppraiseMapper;
import com.topview.school.po.Appraise;
import com.topview.school.vo.appraise.AppraiseInfo;

/**
 * @ClassName: AppraiseSaveProcess
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 下午8:18:52
 * 
 */
@Service
public class AppraiseSaveProcess implements AppraiseProcess {

	@Autowired
	private AppraiseMapper appraiseMapper;
	private static final Logger logger = Logger
			.getLogger(AppraiseSaveProcess.class);

	@Override
	public boolean doProcess(AppraiseProcessContext context) {
		try {
			Appraise appraise = AppraiseInfo.changeToPo(context.getInfo());
			appraise.setTime(new Date());
			appraiseMapper.insert(appraise);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("Appraise save process fail");
			context.getResult().setSuccess(false);
			return false;
		}

	}

}
