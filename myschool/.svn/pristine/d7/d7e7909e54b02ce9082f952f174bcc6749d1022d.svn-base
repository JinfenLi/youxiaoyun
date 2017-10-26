package com.topview.school.controller.version;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.po.VersionInfo;
import com.topview.school.service.version.VersionInfoService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.UUIDUtil;

/**
 * 
 * @author dr 747184616@qq.com
 * @description 用户登录后被调用，用于记录用户当前的使用的APP的版本
 *
 */

@RequestMapping("/version")
@Controller
public class VersionRecordController {
	
			@Resource
			private VersionInfoService versionInfoService;
	
			@RequestMapping("/recordVersion")
			@ResponseBody
			public String versionRecord(String userMobile,String version,String deviceName){
				
				List<VersionInfo> recordList=this.versionInfoService.selectByUserMobile(userMobile);				
				Map<String,Object> resultMap=new HashMap<String,Object>();
				if("".equals(userMobile)||"".equals(version)||"".equals(deviceName)||
						null==userMobile||null==version||null==deviceName){
					resultMap.put("success", false);
					return JSONUtil.toObject(resultMap).toString();
				}
				VersionInfo versionInfo=new VersionInfo();
				VersionInfo temp_info=new VersionInfo();
				versionInfo.setId(UUIDUtil.getUUID());
				versionInfo.setAppVersion(version);
				versionInfo.setUserMobile(userMobile);
				versionInfo.setDeviceName(deviceName);
				versionInfo.setRecordTime(new Date());
				if(recordList.size()==0){
					this.versionInfoService.insert(versionInfo);
					resultMap.put("success",true);
					return JSONUtil.toObject(resultMap).toString();
				}
				int i=0;
				for(i=0;i<recordList.size();i++){
					temp_info=recordList.get(i);
					if(temp_info.getUserMobile().equals(versionInfo.getUserMobile())&&
							temp_info.getAppVersion().equals(versionInfo.getAppVersion())&&
								temp_info.getDeviceName().equals(versionInfo.getDeviceName())){	
									resultMap.put("success", true);
									return JSONUtil.toObject(resultMap).toString();
					}
				}
						this.versionInfoService.insert(versionInfo);
				resultMap.put("success", true);
				return JSONUtil.toObject(resultMap).toString();
			}

}
