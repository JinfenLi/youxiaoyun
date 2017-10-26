package com.topview.multimedia.service.post;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.PostAccessoryMapper;
import com.topview.multimedia.dao.PostMapper;
import com.topview.multimedia.dao.PraiseMapper;
import com.topview.multimedia.dao.ReplyMapper;
import com.topview.multimedia.po.Post;
import com.topview.multimedia.po.PostAccessory;
import com.topview.multimedia.vo.PostVo;
import com.topview.multimedia.vo.PraiseVo;
import com.topview.multimedia.vo.ReplyVo;
import com.topview.multimedia.vo.result.PostVoResult;

/**
 * @Description 查看帖子
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月6日 下午4:26:44
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SeePostProcess implements PostProcess {

	@Resource
	private PostMapper postMapper;
	@Resource
	private PostAccessoryMapper postAccessoryMapper;
	@Resource
	private ReplyMapper replyMapper;
	@Resource
	private PraiseMapper praiseMapper;
	
	public boolean doProcess(PostProcessContext context) {

		PostVoResult result = context.getPostVoResult();
		String clazzId = context.getPostVo().gettMId();
		int status = context.getPostVo().getStatus();
		String lastUpdate = context.getPostVo().getCreateTime();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clazzId", clazzId);
		map.put("status", status);
		map.put("createTime", lastUpdate);
		
		//设置分页参数 
		if((context.getLimit()!=null && !context.getLimit().equals(""))&&
				(context.getStart()!=null && !context.getStart().equals(""))){
				map.put("start", Integer.parseInt(context.getStart()));
				map.put("limit", Integer.parseInt(context.getLimit()));
		}
		List<Post> pos = postMapper.selectByClazzIdAndDate(map);
		
		if (pos.size() > 0) {
			List<PostVo> vos = PostVo.changeToVo(pos);
			
			for(int i = 0; i < vos.size(); i++) {
				String postId = vos.get(i).getId();
				HashMap<String, Object> replyParams=new HashMap<String, Object>();
				HashMap<String, Object> praiseParams=new HashMap<String, Object>();
				replyParams.put("praiseTime", lastUpdate);
				replyParams.put("postId",postId);
				praiseParams.put("replyTime", lastUpdate);
				praiseParams.put("postId", postId);
				//查询评论数
				vos.get(i).setRepliesCount(replyMapper.selectCount(postId));
				vos.get(i).setReply(ReplyVo.changeToVo(this.replyMapper.selectByPostIdAndDate(replyParams)));
				//查询点赞数
				vos.get(i).setPraisesCount(praiseMapper.selectCount(postId));
				vos.get(i).setPraise(PraiseVo.changeToVo(this.praiseMapper.selectByPostIdAndDate(praiseParams)));
				
				//查询附件列表
				List<String> urls = new ArrayList<String>();
				List<PostAccessory> accessories = postAccessoryMapper.selectByPostId(vos.get(i).getId()); //查询附件列表
				for(int j = 0; j < accessories.size(); j++) {
					urls.add(accessories.get(j).getUrl());
				}
				vos.get(i).setUrls(urls);
				
				//若附件仅为一张图片，则获取该图分辨率
				if (urls.size() == 1) {
					String path = context.getSession().getServletContext().getRealPath("/");
					path = path.substring(0, path.lastIndexOf(File.separator + "school" + File.separator)) + urls.get(0);
					File file = new File(path);
					BufferedImage image = null;
					try {
						image = ImageIO.read(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (image != null) {
						Map<String, Object> resolution = new HashMap<String, Object>();
						resolution.put("width", image.getWidth());
						resolution.put("height", image.getHeight());
						vos.get(i).setResolution(resolution);
					}
				}
			}
			
			result.setSuccess(true);
			result.setPosts(vos);
			context.setPostVoResult(result);
			return true;
		}
		return false;
	}

}
