package com.topview.school.service.school;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.multimedia.service.account.AccountService;
import com.topview.multimedia.service.album.AlbumService;
import com.topview.multimedia.service.section.SectionService;
import com.topview.multimedia.vo.AccountInfo;
import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.SectionInfo;
import com.topview.school.dao.school.SchoolMapper;
import com.topview.school.dao.school.SemesterMapper;
import com.topview.school.po.School;
import com.topview.school.po.Semester;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.school.SchoolInfo;

@Service
public class SchoolSaveProcess implements SchoolProcess {
	private static final Logger logger = Logger
			.getLogger(SchoolSaveProcess.class);

	@Autowired
	private SchoolMapper schoolMapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private SectionService sectionService;
	@Resource
	private SemesterMapper SemesterMapper;

	@Transactional
	@Override
	public boolean doProcess(SchoolProcessContext context) {
		try {
			// 创建学校
			SchoolInfo info = context.getInfo();
			School school = SchoolInfo.changeToPo(info);
			schoolMapper.insert(school);

			// 创建多媒体空间
			AccountInfo accountInfo = new AccountInfo();
			accountInfo.setAccountStatus(1);
			accountInfo.setComment(school.getName() + "多媒体空间");
			accountInfo.setId(school.getId());
			if(!accountService.register(accountInfo).isSuccess()) {
				throw new RuntimeException();
			}

			// 创建首页轮播的相册
			AlbumInfo albumInfo = new AlbumInfo();
			albumInfo.setId(UUIDUtil.getUUID());
			albumInfo.setComment(school.getName() + "首页轮播相册");
			albumInfo.setDescription("");
			albumInfo.setName("viewPager");
			albumInfo.settMId(school.getId());
			albumInfo.setType("ViewPager");
			if(!albumService.albumCreate(albumInfo).isSuccess()) {
				throw new RuntimeException();
			}

			// 创建校园新闻板块
			SectionInfo sectionInfo = new SectionInfo();
			sectionInfo = new SectionInfo();
			sectionInfo.setId(UUIDUtil.getUUID());
			sectionInfo.setDescription2(school.getName() + "校园新闻板块");
			sectionInfo.setIcon("");
			sectionInfo.setName2("news");
			sectionInfo.settMId(school.getId());
			sectionInfo.setType("news");
			if(!sectionService.sectionCreate(sectionInfo).isSuccess()) {
				throw new RuntimeException();
			}

			//校园简介
			sectionInfo = new SectionInfo();
			sectionInfo.setId(UUIDUtil.getUUID());
			sectionInfo.setDescription2(school.getName() + "校园简介");
			sectionInfo.setIcon("");
			sectionInfo.setName2("summy");
			sectionInfo.settMId(school.getId());
			sectionInfo.setType("summy");
			if(!sectionService.sectionCreate(sectionInfo).isSuccess()) {
				throw new RuntimeException();
			}
			
			// 创建校园公告板块
			sectionInfo = new SectionInfo();
			sectionInfo.setId(UUIDUtil.getUUID());
			sectionInfo.setDescription2(school.getName() + "校园公告板块");
			sectionInfo.setIcon("");
			sectionInfo.setName2("notice");
			sectionInfo.settMId(school.getId());
			sectionInfo.setType("notice");
			if(!sectionService.sectionCreate(sectionInfo).isSuccess()) {
				throw new RuntimeException();
			}

			// 创建荣誉之窗板块
			sectionInfo = new SectionInfo();
			sectionInfo.setId(UUIDUtil.getUUID());
			sectionInfo.setDescription2(school.getName() + "荣誉之窗板块");
			sectionInfo.setIcon("");
			sectionInfo.setName2("reward");
			sectionInfo.settMId(school.getId());
			sectionInfo.setType("reward");
			if(!sectionService.sectionCreate(sectionInfo).isSuccess()) {
				throw new RuntimeException();
			}

			// 创建教子良方板块
			sectionInfo = new SectionInfo();
			sectionInfo.setId(UUIDUtil.getUUID());
			sectionInfo.setDescription2(school.getName() + "教子良方板块");
			sectionInfo.setIcon("");
			sectionInfo.setName2("teaching");
			sectionInfo.settMId(school.getId());
			sectionInfo.setType("teaching");
			if(!sectionService.sectionCreate(sectionInfo).isSuccess()) {
				throw new RuntimeException();
			}

			//创建20个学期  TODO 如何智能匹配当前学期，自动切换学期
			Semester semester = new Semester();
			semester.setId(UUIDUtil.getUUID());
			semester.setCreateTime(new Date());
			semester.setCurrentSemester(1);
			semester.setEndTime(new Date());
			semester.setName("2015年秋上学期");
			semester.setStartTime(new Date());
			semester.settScSchoolId(school.getId());
			semester.setWeek(20);
			SemesterMapper.insert(semester);
			
			for(int i = 0,year = 2016; i < 10; i++, year++) {
				semester = new Semester();
				semester.setId(UUIDUtil.getUUID());
				semester.setCreateTime(new Date());
				semester.setCurrentSemester(0);
				semester.setEndTime(new Date());
				semester.setName(year + "年春下学期");
				semester.setStartTime(new Date());
				semester.settScSchoolId(school.getId());
				semester.setWeek(20);
				SemesterMapper.insert(semester);
			}
			for(int i = 0, year = 2016; i < 9; i++, year++) {
				semester = new Semester();
				semester.setId(UUIDUtil.getUUID());
				semester.setCreateTime(new Date());
				semester.setCurrentSemester(0);
				semester.setEndTime(new Date());
				semester.setName(year + "年秋上学期");
				semester.setStartTime(new Date());
				semester.settScSchoolId(school.getId());
				semester.setWeek(20);
				SemesterMapper.insert(semester);
			}
			
			context.getResult().getResult().add(info);
			context.getResult().setSuccess(true);
			return true;

		} catch (Exception e) {
			logger.error("school save fail" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(); // 回滚事务
		}
	}
}
