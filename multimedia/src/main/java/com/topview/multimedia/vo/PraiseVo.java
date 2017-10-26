package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.po.Praise;

/**
 * @Description 点赞VO
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月21日 下午2:58:55
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class PraiseVo {

	private String id;
	private String postId; // 所属帖子id
	private String praiserId; // 点赞者id
	private String praiserName; //点赞者姓名
	private String praiserTime; // 点赞时间

	public static PraiseVo changeToVo(Praise praise) {
		PraiseVo vo = new PraiseVo();
		vo.setId(praise.getId());
		vo.setPostId(praise.gettMultimediaPostId());
		vo.setPraiserId(praise.getPraiserId());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH：mm:ss");
		vo.setPraiserTime(df.format(praise.getPraiserTime()));
		vo.setPraiserName(praise.getPraiserName());
		return vo;
	}
	
	public static List<PraiseVo> changeToVo(List<Praise> praises) {
		List<PraiseVo> vos = new ArrayList<PraiseVo>();
		for(Praise p : praises) {
			vos.add(PraiseVo.changeToVo(p));
		}
		return vos;
	}
	
	public static Praise changToPo(PraiseVo vo) {
		Praise p = new Praise();
		p.setId(vo.getId());
		p.setPraiserId(vo.getPraiserId());
		p.settMultimediaPostId(vo.getPostId());
		p.setPraiserName(vo.getPraiserName());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			p.setPraiserTime(df.parse(vo.getPraiserTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return p;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPraiserId() {
		return praiserId;
	}

	public void setPraiserId(String praiserId) {
		this.praiserId = praiserId;
	}

	public String getPraiserTime() {
		return praiserTime;
	}

	public void setPraiserTime(String praiserTime) {
		this.praiserTime = praiserTime;
	}

	public String getPraiserName() {
		return praiserName;
	}

	public void setPraiserName(String praiserName) {
		this.praiserName = praiserName;
	}

}
