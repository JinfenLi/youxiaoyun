package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.topview.multimedia.po.Post;
import com.topview.multimedia.util.UUIDUtil;

/**
 * @Description 主帖VO
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月21日 上午1:28:53
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class PostVo {

	private String id;
	private String title; // 帖子标题
	private String context; // 帖子内容
	private String tMId; // 所属班级id
	private String publisherId; // 发布者id
	private String publisherName; //发布者姓名
	private int status; // 状态：1为可见; 0为不可见（未审核通过）; 2为已删除
	private String type; // 所属类型(暂时没有分类的需求,作为预留字段)
	private String createTime; // 发布时间
	private List<String> urls; // 附件路径
	private int repliesCount; //评论数目
	private int praisesCount; //点赞数目
	private boolean praiseStatus; //是否点过赞
	private List<ReplyVo> reply;
	private List<PraiseVo> praise;
	private Map<String, Object> resolution; //若附件仅为一张图片，则为该图分辨率；否则为null
 
	// private boolean top; //是否置顶，预留
	// private boolean essence; //是否精华，预留
	// private int praiseCount; //赞的人数
	// private int replyCount; //评论的人数

	/**
	 * 将VO对象转为PO对象
	 * 
	 * @param vo
	 * @return
	 */
	public static Post changeToPo(PostVo vo) {

		Post post = new Post();
		post.settMId(vo.gettMId());
		post.setStatus(vo.getStatus());
		post.setId(vo.getId() == null ? UUIDUtil.getUUID() : vo.getId());
		post.setContext(vo.getContext());
		post.setPublisherId(vo.getPublisherId());
		post.setPublisherName(vo.getPublisherName());
		post.setTitle(vo.getTitle());
		post.setType(vo.getType() == null ? " " : vo.getType());
		try {
			if (vo.getCreateTime() != null && !"".equals(vo.getCreateTime())) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				post.setCreateTime(df.parse(vo.getCreateTime()));
			} else {
				post.setCreateTime(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return post;
	}

	public static List<Post> changeToPo(List<PostVo> vos) {
		List<Post> posts = new ArrayList<Post>();
		for (PostVo vo : vos) {
			posts.add(PostVo.changeToPo(vo));
		}
		return posts;
	}

	public static PostVo changeToVo(Post po, List<String> urls) {
		PostVo vo = new PostVo();
		vo.setId(po.getId());
		vo.setContext(po.getContext());
		vo.setPublisherId(po.getPublisherId());
		vo.setStatus(po.getStatus());
		vo.setTitle(po.getTitle());
		vo.settMId(po.gettMId());
		vo.setType(po.getType());
		vo.setPublisherName(po.getPublisherName());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vo.setCreateTime(df.format(po.getCreateTime()));
		vo.setUrls(urls);
		return vo;
	}
	
	public static List<PostVo> changeToVo(List<Post> pos) {
		List<PostVo> vos = new ArrayList<PostVo>();
		for(Post po : pos) {
			vos.add(PostVo.changeToVo(po, null));
		}
		return vos;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String gettMId() {
		return tMId;
	}

	public void settMId(String tMId) {
		this.tMId = tMId;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	// public boolean isTop() {
	// return top;
	// }
	// public void setTop(boolean top) {
	// this.top = top;
	// }
	// public boolean isEssence() {
	// return essence;
	// }
	// public void setEssence(boolean essence) {
	// this.essence = essence;
	// }
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	// public int getPraiseCount() {
	// return praiseCount;
	// }
	// public void setPraiseCount(int praiseCount) {
	// this.praiseCount = praiseCount;
	// }
	// public int getReplyCount() {
	// return replyCount;
	// }
	// public void setReplyCount(int replyCount) {
	// this.replyCount = replyCount;
	// }

	public int getRepliesCount() {
		return repliesCount;
	}

	public void setRepliesCount(int repliesCount) {
		this.repliesCount = repliesCount;
	}

	public int getPraisesCount() {
		return praisesCount;
	}

	public void setPraisesCount(int praisesCount) {
		this.praisesCount = praisesCount;
	}

	public boolean isPraiseStatus() {
		return praiseStatus;
	}

	public void setPraiseStatus(boolean praiseStatus) {
		this.praiseStatus = praiseStatus;
	}

	public Map<String, Object> getResolution() {
		return resolution;
	}

	public void setResolution(Map<String, Object> resolution) {
		this.resolution = resolution;
	}

}
