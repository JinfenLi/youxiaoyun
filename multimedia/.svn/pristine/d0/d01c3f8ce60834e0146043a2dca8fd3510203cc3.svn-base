/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月27日 上午11:27:22 
 * @version V1.0
 */
package com.topview.multimedia.service.collect;

import java.util.List;

import com.topview.multimedia.vo.CollectInfo;
import com.topview.multimedia.vo.result.CollectInfoResult;

/**
 * @ClassName: CollectServiceImpl
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月27日 上午11:27:22
 * 
 */
public class CollectServiceImpl implements CollectService {

	private List<CollectProcess> collectDeleteProcesses;
	private List<CollectProcess> collectFindProcesses;
	private List<CollectProcess> collectSaveProcesses;
	private List<CollectProcess> collectFindAllProcesses;

	public List<CollectProcess> getCollectDeleteProcesses() {
		return collectDeleteProcesses;
	}

	public void setCollectDeleteProcesses(
			List<CollectProcess> collectDeleteProcesses) {
		this.collectDeleteProcesses = collectDeleteProcesses;
	}

	public List<CollectProcess> getCollectFindProcesses() {
		return collectFindProcesses;
	}

	public void setCollectFindProcesses(
			List<CollectProcess> collectFindProcesses) {
		this.collectFindProcesses = collectFindProcesses;
	}

	public List<CollectProcess> getCollectSaveProcesses() {
		return collectSaveProcesses;
	}

	public void setCollectSaveProcesses(
			List<CollectProcess> collectSaveProcesses) {
		this.collectSaveProcesses = collectSaveProcesses;
	}

	public List<CollectProcess> getCollectFindAllProcesses() {
		return collectFindAllProcesses;
	}

	public void setCollectFindAllProcesses(
			List<CollectProcess> collectFindAllProcesses) {
		this.collectFindAllProcesses = collectFindAllProcesses;
	}

	public CollectInfoResult collectSave(CollectInfo collect) {
		return doProcess(collect, collectSaveProcesses);
	}

	public CollectInfoResult collectDelete(CollectInfo collect) {

		return doProcess(collect, collectDeleteProcesses);
	}

	public CollectInfoResult collectFind(CollectInfo collect) {
		return doProcess(collect, collectFindProcesses);
	}

	public CollectInfoResult collectFindAll(CollectInfo collect) {

		return doProcess(collect, collectFindAllProcesses);
	}

	public CollectInfoResult doProcess(CollectInfo collect,
			List<CollectProcess> processes) {
		CollectProcessContext context = new CollectProcessContext();
		CollectInfoResult result = new CollectInfoResult();
		context.setResult(result);
		context.setInfo(collect);
		for (CollectProcess process : processes) {
			if (!process.doProcess(context))
				break;
		}

		return context.getResult();
	}

}
