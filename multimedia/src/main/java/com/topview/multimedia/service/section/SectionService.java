package com.topview.multimedia.service.section;

import com.topview.multimedia.vo.SectionInfo;
import com.topview.multimedia.vo.result.SectionInfoResult;

public interface SectionService {
	public SectionInfoResult sectionCreate(SectionInfo info);
	public SectionInfoResult sectionUpdate(SectionInfo info);
	public SectionInfoResult sectionDelete(SectionInfo info);
	public SectionInfoResult sectionFindAll(SectionInfo info);
	public SectionInfoResult sectionFindByType(SectionInfo info);
	
}
