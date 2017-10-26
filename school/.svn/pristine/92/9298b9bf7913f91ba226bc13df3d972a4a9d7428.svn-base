/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年9月21日 下午4:31:47 
 * @version V1.0
 */
package com.topview.school.util;

import java.util.List;

import com.topview.message.service.PushMsgService;
import com.topview.message.vo.OfflineMessageVo;

/**
 * @ClassName: PushThreadUtil
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年9月21日 下午4:31:47
 * 
 */
public class PushThreadUtil implements Runnable {

	public PushThreadUtil() {

	}

	public PushThreadUtil(PushMsgService pushMsgService,
			List<OfflineMessageVo> vos) {
		this.pushMsgService = pushMsgService;
		this.vos = vos;
	}

	private PushMsgService pushMsgService;
	private List<OfflineMessageVo> vos;

	@Override
	public void run() {
		for (com.topview.message.vo.OfflineMessageVo vo : vos) {
			pushMsgService.pushMessage(vo);
		}
	}

	public PushMsgService getPushMsgService() {
		return pushMsgService;
	}

	public void setPushMsgService(PushMsgService pushMsgService) {
		this.pushMsgService = pushMsgService;
	}

	public List<OfflineMessageVo> getVos() {
		return vos;
	}

	public void setVos(List<OfflineMessageVo> vos) {
		this.vos = vos;
	}

}
