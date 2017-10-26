package com.topview.multimedia.service.library.video;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
import com.topview.multimedia.dao.MultimediaVideoMapper;
import com.topview.multimedia.po.MultimediaVideo;
import com.topview.multimedia.po.MultimediaVideoLibrary;
import com.topview.multimedia.service.record.RecordUpdateService;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.VideoInfo;

/**
 * 保存视频 项目名称:com.topview.multimedia.service.library.video<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class VideoSaveProcess implements VideoProcess {

	@Resource
	private RecordUpdateService recordUpdateService;
	@Autowired
	private MultimediaVideoMapper multimediaVideoMapper;
	@Autowired
	private MultimediaVideoLibraryMapper multimediaLibraryMapper;

	public boolean doProcess(VideoProcessContext context) {

		try { // TODO 同家长圈、照片上传一样需改进
			MultimediaVideo video = VideoInfo.changeToPo(context.getInfo());
			video.setId(UUIDUtil.getUUID());
			MultimediaVideoLibrary library = multimediaLibraryMapper
					.findById(video.gettMId());
			library.setPhotoCount2(library.getPhotoCount2() + 1);
			if (multimediaVideoMapper.insert(video) > 0) {
				multimediaLibraryMapper.updateByPrimaryKeySelective(library);
				if (library.gettMId().equals(context.getInfo().getZoneId())) { // 在controll里将zoneId设置为用户的schoolId，以此来判断是校园视频的更新还是班级视频
					recordUpdateService.saveOrUpdateRecord(library.gettMId(),
							"schoolVideo");
				} else {
					recordUpdateService.saveOrUpdateRecord(library.gettMId(),
							"clazzVideo");
				}
			}
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
