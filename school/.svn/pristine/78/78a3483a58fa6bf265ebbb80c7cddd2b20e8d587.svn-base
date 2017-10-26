package com.topview.school.service.post;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.PostMapper;
import com.topview.multimedia.dao.ReplyMapper;
import com.topview.multimedia.vo.PostVo;
import com.topview.multimedia.vo.PraiseVo;
import com.topview.multimedia.vo.ReplyVo;
import com.topview.school.dao.appraise.AppraiseMapper;
import com.topview.school.dao.user.ParentMapper;
import com.topview.school.dao.user.StudentMapper;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.Student;
import com.topview.school.po.Teacher;
import com.topview.school.vo.post.NewPostVo;

@Service
public class GetNewPostVoProcess implements NewPostVoProcess {

	@Resource
	private PostMapper postMapper;
	
	@Resource
	private TeacherMapper teacherMapper;
	
	@Resource
	private StudentMapper studentMapper;
	
	@Resource
	private ParentMapper parentMapper;
	
	@Resource
	private ReplyMapper replyMapper;
	
	@Resource
	private AppraiseMapper appraiseMapper;
	
	@Override
	public boolean doProcess(NewPostVoProcessContext context) {
		List<PostVo> list = context.getVo().getVoList();
		List<PraiseVo> appraiseList = null;
		PostVo vo = null;
		Teacher teacher1 = null,teacher2=null;
		List<Student> studentList = null;
		List<ReplyVo> replyList = null;
		ReplyVo replyVo = null;
		PraiseVo appraiseVo = null;
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				vo = list.get(i);
				if(vo != null) {
					teacher1 = teacherMapper.selectByPrimaryKey(vo.getPublisherId());
					if(teacher1 != null) {	
						list.get(i).setPicUrl(teacher1.getPicUrl());
						list.get(i).setTeacher(true);
					}
					else {
						studentList = studentMapper.findByParentId(vo.getPublisherId());
						if(studentList != null && !studentList.isEmpty()) {
							list.get(i).setPicUrl(studentList.get(0).getPicurl());
							list.get(i).setTeacher(false);
						}
					}
					appraiseList = vo.getPraise();
					replyList = vo.getReply();
					if(replyList != null && !replyList.isEmpty()) {
						for(int j=0; j<replyList.size() ;j++) {
							replyVo = vo.getReply().get(j);
							teacher2 = teacherMapper.selectByPrimaryKey(replyVo.getReplyerId());
							if(teacher2 != null) {
								list.get(i).getReply().get(j).setPicUrl(teacher2.getPicUrl());
							} else {
								studentList = studentMapper.findByParentId(replyVo.getReplyerId());
								list.get(i).getReply().get(j).setPicUrl(studentList.get(0).getPicurl());
							}
						}
					}
					if(appraiseList != null && !appraiseList.isEmpty()) {
						for(int j=0;j<appraiseList.size();j++) {
							appraiseVo = appraiseList.get(j);
							teacher2 = teacherMapper.selectByPrimaryKey(appraiseVo.getPraiserId());
							if(teacher2 != null) {
								list.get(i).getPraise().get(j).setPicUrl(teacher2.getPicUrl());
							} else {
								studentList = studentMapper.findByParentId(appraiseVo.getPraiserId());
								list.get(i).getPraise().get(j).setPicUrl(studentList.get(0).getPicurl());
							}
							
						}
					}
					
				}
			}
		}
		NewPostVo newPostVo = new NewPostVo();
		newPostVo.setVoList(list);
		context.getResult().setResult(newPostVo);
		return true;
	}

}
