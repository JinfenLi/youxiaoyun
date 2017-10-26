package com.topview.school.service.post;

import java.util.List;

import com.topview.school.vo.post.NewPostVo;
import com.topview.school.vo.post.result.NewPostVoResult;


public class NewPostVoServiceImpl implements NewPostVoService {

	private List<NewPostVoProcess> getNewPostVoProcesses;
	
	@Override
	public NewPostVoResult getNewPostVo(NewPostVo vo) {
		return doNewPostVoProcess(vo,getNewPostVoProcesses);
	}

	public NewPostVoResult doNewPostVoProcess(NewPostVo vo,
			List<NewPostVoProcess> processes) {
		NewPostVoProcessContext context = new NewPostVoProcessContext();
		NewPostVoResult result = new NewPostVoResult();
		context.setVo(vo);
		context.setResult(result);
		for (NewPostVoProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public List<NewPostVoProcess> getGetNewPostVoProcesses() {
		return getNewPostVoProcesses;
	}

	public void setGetNewPostVoProcesses(List<NewPostVoProcess> getNewPostVoProcesses) {
		this.getNewPostVoProcesses = getNewPostVoProcesses;
	}
	

}
