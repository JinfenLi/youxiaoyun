/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月29日 上午10:52:56 
 * @version V1.0
 */
package com.topview.school.service.collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.service.album.photo.PhotoService;
import com.topview.multimedia.service.library.video.VideoService;
import com.topview.multimedia.service.section.article.ArticleService;
import com.topview.multimedia.vo.CollectInfo;
import com.topview.multimedia.vo.RichTextInfo;
import com.topview.multimedia.vo.VideoInfo;
import com.topview.multimedia.vo.result.CollectInfoResult;
import com.topview.multimedia.vo.result.PhotoInfoResult;
import com.topview.multimedia.vo.result.RichTextInfoResult;
import com.topview.multimedia.vo.result.VideoInfoResult;
import com.topview.multimedia.vo.PhotoInfo;

/** 
 * @ClassName: PersonCollectServiceImpl 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月29日 上午10:52:56 
 *  
 */
@Service
public class PersonCollectServiceImpl implements PersonCollectService{

	private List<PersonCollectProcess> personCollectFindAllProcesses;

	
	@Autowired
	private PhotoService photoService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private ArticleService articleService;
	
	
	
	@Override
	public Map<String, List<Object>> collectFindAll(String userid,Pager pager) {

		PersonCollectProcessContext context = new PersonCollectProcessContext();
		CollectInfoResult collectInfoResult = new CollectInfoResult();
		CollectInfo collectInfo  =  new CollectInfo();
		collectInfo.setPager(pager);
		collectInfo.setUserid(userid);
		context.setInfo(collectInfo);
		context.setResult(collectInfoResult);
		for(PersonCollectProcess process : personCollectFindAllProcesses) {
			if(!process.doProcess(context)) {
				break;
			}
		}
		  String type;
	      String id;
	      String collectid;
		  Map<String, List<Object>> resultmap = new HashMap<String, List<Object>>();
			CollectInfoResult result =new CollectInfoResult();
			result = context.getResult();
			int size = result.getInforesult().size();
			List<PhotoInfo> photolist = new ArrayList<PhotoInfo>();
			List<VideoInfo> videolist = new ArrayList<VideoInfo>();
			List<RichTextInfo> textlist = new ArrayList<RichTextInfo>();
			VideoInfo videoinfo = new VideoInfo();
			PhotoInfo photoinfo = new PhotoInfo();
			RichTextInfo textinfo = new RichTextInfo();
			for(int i=0;i<size;i++) {
				type = result.getInforesult().get(i).getPointtype();
				id =  result.getInforesult().get(i).getPointid();
				collectid = result.getInforesult().get(i).getId();
				if(type.equals("1") ) {
					photoinfo.setId(id);
					PhotoInfoResult photoresult = photoService.photoFind(photoinfo);
//					photoresult.getInfoResult().get(0).setCollectid(collectid);
				
//					photoinfo = photoresult.getInfoResult()!=null?photoresult.getInfoResult().get(0):null;
//					if(photoinfo!=null) {
//						photoinfo.setCollectid(collectid);
//					
//					}
					if(photoresult.getInfoResult()!=null){
						photoresult.getInfoResult().get(0).setCollectid(collectid);
					photolist.add(photoresult.getInfoResult().get(0));
					}
				}
				if(type.equals("2") ) {
					textinfo.setId(id);
					RichTextInfoResult richTextInfoResult =  articleService.articleFind(textinfo);
//					richTextInfoResult.getInfoResult().get(0).setCollectid(collectid);
					if(richTextInfoResult.getInfoResult()!=null){
						richTextInfoResult.getInfoResult().get(0).setCollectid(collectid);
					textlist.add(richTextInfoResult.getInfoResult().get(0));
					}
				}
				if(type.equals("3") ) {
					videoinfo.setId(id);
					VideoInfoResult videoInfoResult =  videoService.videoFind(videoinfo);
//					videoInfoResult.getResult().get(0).setCollectid(collectid);
					if(	videoInfoResult.getResult()!=null) {
						videoInfoResult.getResult().get(0).setCollectid(collectid);
					videolist.add(videoInfoResult.getResult().get(0));
					}
				}
			}

			//photoinfo.convert2Object(photolist)
			resultmap.put("videolist",videoinfo.convert2Object(videolist));
			resultmap.put("photolist",photoinfo.convert2Object(photolist));
			resultmap.put("textlist",textinfo.convert2Object(textlist));
		
		return resultmap;
	}



	public List<PersonCollectProcess> getPersonCollectFindAllProcesses() {
		return personCollectFindAllProcesses;
	}



	public void setPersonCollectFindAllProcesses(
			List<PersonCollectProcess> personCollectFindAllProcesses) {
		this.personCollectFindAllProcesses = personCollectFindAllProcesses;
	}

}
