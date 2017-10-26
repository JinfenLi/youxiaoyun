package com.topview.message.service;

import java.util.List;

import com.topview.message.vo.OfflineMessageVo;
import com.topview.message.vo.result.OfflineMessageVoResult;

public class PushMsgServiceImpl implements PushMsgService {

	private List<PushProcess> saveOfflineMessageProcesses;
	private List<PushProcess> pushMessageProcesses;
	private List<PushProcess> updateMessageProcesses;
	private List<PushProcess> judgeOnlineProcesses;
	private List<PushProcess> getOfflineMessageProcesses;
	private List<PushProcess> getHistoryMessageProcesses;
	private List<PushProcess> saveMassPushProcesses;
	private List<PushProcess> saveMultiMessageProcesses;
	private List<PushProcess> getMessageByIdProcesses;



	/**
	 * 保存离线消息
	 */
	public OfflineMessageVoResult saveOfflineMessage(OfflineMessageVo vo) {

		PushProcessContext context = new PushProcessContext();
		context.setVo(vo);
		context.setResult(new OfflineMessageVoResult());

		for (PushProcess process : saveOfflineMessageProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 一对一发送离线消息
	 */
	public OfflineMessageVoResult pushMessage(OfflineMessageVo vo) {

		PushProcessContext context = new PushProcessContext();
		context.setVo(vo);
		context.setResult(new OfflineMessageVoResult());
		for (PushProcess process : pushMessageProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 保存群发消息
	 */
	public OfflineMessageVoResult saveMassPush(OfflineMessageVo vo) {
		PushProcessContext context = new PushProcessContext();
		context.setVo(vo);
		context.setResult(new OfflineMessageVoResult());
		for (PushProcess process : saveMassPushProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 判断接收者是否在线
	 */
	public OfflineMessageVoResult judgeOnline(String receiverId) {

		PushProcessContext context = new PushProcessContext();
		OfflineMessageVo vo = new OfflineMessageVo();
		vo.setReceiverId(receiverId);
		context.setVo(vo);
		context.setResult(new OfflineMessageVoResult());

		for (PushProcess process : judgeOnlineProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 修改消息状态
	 */
	public OfflineMessageVoResult updateMessage(String envelopeId) {

		PushProcessContext context = new PushProcessContext();
		OfflineMessageVo vo = new OfflineMessageVo();
		vo.setEnvelopeId(envelopeId);
		context.setVo(vo);
		context.setResult(new OfflineMessageVoResult());

		for (PushProcess process : updateMessageProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 获取离线消息（包括我的短信）
	 */
	public OfflineMessageVoResult getOfflineMessage(String receiverId) {

		PushProcessContext context = new PushProcessContext();
		OfflineMessageVo vo = new OfflineMessageVo();
		vo.setReceiverId(receiverId);
		context.setVo(vo);
		OfflineMessageVoResult result = new OfflineMessageVoResult();
		context.setResult(result);

		for (PushProcess process : getOfflineMessageProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * web端获取我的短信记录
	 */
	public OfflineMessageVoResult getHistoryMessage(String senderId,
			String beginTime, String endTime,int limit, int page) {
		PushProcessContext context = new PushProcessContext();
		OfflineMessageVo vo = new OfflineMessageVo();
		vo.setSenderId(senderId);
		context.setVo(vo);
		context.setBeginTime(beginTime);
		context.setEndTime(endTime);
		context.setLimit(limit);
		context.setPage(page);
		context.setResult(new OfflineMessageVoResult());

		for (PushProcess process : getHistoryMessageProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	@Override
	public int getHisoryMessageCounut(String senderId, String beginTime, String endTime) {
		PushProcessContext context = new PushProcessContext();
		OfflineMessageVo vo = new OfflineMessageVo();
		vo.setSenderId(senderId);
		context.setVo(vo);
		context.setBeginTime(beginTime);
		context.setEndTime(endTime);
		context.setResult(new OfflineMessageVoResult());

		for (PushProcess process : getHistoryMessageProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getTotal();
	}
	
	@Override
	public OfflineMessageVoResult saveMultiMessage(OfflineMessageVo vo) {
		PushProcessContext context = new PushProcessContext();
		context.setVo(vo);
		context.setResult(new OfflineMessageVoResult());
		for (PushProcess process : saveMultiMessageProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	@Override
	public OfflineMessageVoResult getMessageById(OfflineMessageVo vo) {
		PushProcessContext context = new PushProcessContext();
		context.setVo(vo);
		context.setResult(new OfflineMessageVoResult());
		for (PushProcess process : getMessageByIdProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	
	// **********************get/set方法************************************//
	public List<PushProcess> getSaveOfflineMessageProcesses() {
		return saveOfflineMessageProcesses;
	}

	public void setSaveOfflineMessageProcesses(
			List<PushProcess> saveOfflineMessageProcesses) {
		this.saveOfflineMessageProcesses = saveOfflineMessageProcesses;
	}

	public List<PushProcess> getPushMessageProcesses() {
		return pushMessageProcesses;
	}

	public void setPushMessageProcesses(List<PushProcess> pushMessageProcesses) {
		this.pushMessageProcesses = pushMessageProcesses;
	}

	public List<PushProcess> getUpdateMessageProcesses() {
		return updateMessageProcesses;
	}

	public void setUpdateMessageProcesses(
			List<PushProcess> updateMessageProcesses) {
		this.updateMessageProcesses = updateMessageProcesses;
	}

	public List<PushProcess> getGetOfflineMessageProcesses() {
		return getOfflineMessageProcesses;
	}

	public void setGetOfflineMessageProcesses(
			List<PushProcess> getOfflineMessageProcesses) {
		this.getOfflineMessageProcesses = getOfflineMessageProcesses;
	}

	public List<PushProcess> getJudgeOnlineProcesses() {
		return judgeOnlineProcesses;
	}

	public void setJudgeOnlineProcesses(List<PushProcess> judgeOnlineProcesses) {
		this.judgeOnlineProcesses = judgeOnlineProcesses;
	}

	public List<PushProcess> getGetHistoryMessageProcesses() {
		return getHistoryMessageProcesses;
	}

	public List<PushProcess> getSaveMassPushProcesses() {
		return saveMassPushProcesses;
	}

	public void setSaveMassPushProcesses(List<PushProcess> saveMassPushProcesses) {
		this.saveMassPushProcesses = saveMassPushProcesses;
	}

	public void setGetHistoryMessageProcesses(
			List<PushProcess> getHistoryMessageProcesses) {
		this.getHistoryMessageProcesses = getHistoryMessageProcesses;
	}
	public List<PushProcess> getSaveMultiMessageProcesses() {
		return saveMultiMessageProcesses;
	}

	public void setSaveMultiMessageProcesses(List<PushProcess> saveMultiMessageProcesses) {
		this.saveMultiMessageProcesses = saveMultiMessageProcesses;
	}

	public List<PushProcess> getGetMessageByIdProcesses() {
		return getMessageByIdProcesses;
	}

	public void setGetMessageByIdProcesses(List<PushProcess> getMessageByIdProcesses) {
		this.getMessageByIdProcesses = getMessageByIdProcesses;
	}
	
}
