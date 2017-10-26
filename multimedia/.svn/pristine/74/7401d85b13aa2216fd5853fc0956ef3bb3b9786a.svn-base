package com.topview.multimedia.vo;

public class RichTextInfoWithFile extends RichTextInfo{
	private boolean hasFile;

	public boolean isHasFile() {
		return hasFile;
	}

	public void setHasFile(boolean hasFile) {
		this.hasFile = hasFile;
	}
	
	public static RichTextInfoWithFile changeToRichTextInfoWithFile(RichTextInfo richTextInfo) {
		RichTextInfoWithFile result = new RichTextInfoWithFile();
		result.setCollectid(richTextInfo.getCollectid());
		result.setContext(richTextInfo.getContext());
		result.setCreateTime(richTextInfo.getCreateTime());
		result.setEssence(richTextInfo.getEssence());
		if(richTextInfo.getContext().contains("附件") && richTextInfo.getContext().contains("href=\"http")) {
			result.setHasFile(true);
		}else {
			result.setHasFile(false);
		}
		result.setId(richTextInfo.getId());
		result.setPager(richTextInfo.getPager());
		result.setReadNumber(richTextInfo.getReadNumber());
		result.setStatus(richTextInfo.getStatus());
		result.setSubtitle(richTextInfo.getSubtitle());
		result.setSummary(richTextInfo.getSummary());
		result.setTitle(richTextInfo.getTitle());
		result.setTitlephoto(richTextInfo.getTitlephoto());
		result.settMId(richTextInfo.gettMId());
		result.setTop(richTextInfo.getTop());
		result.setType(richTextInfo.getType());
		result.setUrl(richTextInfo.getUrl());
		result.setZoneId(richTextInfo.getZoneId());
		return result;
	}
}

