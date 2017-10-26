package com.topview.school.service.clazz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.multimedia.service.account.AccountService;
import com.topview.multimedia.service.album.AlbumService;
import com.topview.multimedia.vo.AccountInfo;
import com.topview.multimedia.vo.AlbumInfo;
import com.topview.school.dao.school.ClazzMapper;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.Clazz;
import com.topview.school.util.UUIDUtil;

@Service
public class ClazzServiceImpl implements ClazzService {

	@Resource
	private ClazzMapper clazzMapper;
	@Resource
	private AccountService accountService;
	@Resource
	private AlbumService albumService;
	@Resource
	private TeacherMapper teacherMapper;
	
	/**
	 * 根据班级id获取班级信息
	 */
	@Override
	public Clazz clazzFind(String id) {
		return clazzMapper.selectByPrimaryKey(id);
	}

	/**
	 * 新建班级
	 */
	@Override
	public boolean createClazz(Clazz clazz) {
			//注册多媒体空间
			AccountInfo info = new AccountInfo();
			info.setId(clazz.getId());
			info.setAccountStatus(1);
			info.setComment(clazz.getName()+"多媒体空间");
			accountService.register(info);
			//创建班级时光用于审核相片的默认相册
			AlbumInfo albumInfo = new AlbumInfo();
			albumInfo.setId(UUIDUtil.getUUID());
			albumInfo.settMId(clazz.getId());
			albumInfo.setComment(clazz.getName() + "默认相册");
			albumInfo.setDescription("用于存放未审核相片");
			albumInfo.setName("未审核相册");
			albumInfo.setType("未审核"); //类型必须为文字"未审核"
			albumService.albumCreate(albumInfo);
			return clazzMapper.insert(clazz) > 0 ? true : false;
	}

	/**
	 * 删除班级
	 */
	@Transactional
	@Override
	public boolean deleteClazz(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clazzId", id);
		try{
			teacherMapper.cancelPosition(map); //取消已有的班主任
			return clazzMapper.deleteByPrimaryKey(id) > 0 ? true : false;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 根据年级id获取班级
	 */
	@Override
	public List<Clazz> getClazzByGradeId(String gradeId) {
		return clazzMapper.getClazzByGradeId(gradeId);
	}

	/**
	 * 更新班级信息
	 */
	@Override
	public boolean updateClazz(Clazz clazz) {
		return clazzMapper.updateByPrimaryKeySelective(clazz) > 0 ? true : false;
	}

	/**
	 * 根据教师id和学期id查询教师所带班级
	 */
	@Override
	public List<Clazz> selectTeacherClazzs(String teacherId, String semesterId) {
		return clazzMapper.selectTeacherClazzs(teacherId, semesterId);
	}

	/* 
	 * @time 上午11:31:56
	 * @author hcdn
	 * @description 根据年级id和班级名字判断
	 */
	@Override
	public List<Clazz> isExist(String grade_id, String name) {
		return clazzMapper.isExist(grade_id,name);
	}

}
