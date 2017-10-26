package com.topview.multimedia.service.section.draft.enums;

public enum RichTextTypeEnum {

	ANNOUNCEMENT("notice", 0), NEWS("news", 1), SUMMY("summy", 2), DRAFT("草稿",
			3), AWARD("reward", 4), TEACHING("teaching", 5), EDUCATE("educate",
			6);

	private String name;
	private Integer code;

	RichTextTypeEnum(String name, Integer code) {
		this.name = name;
		this.code = code;
	}

	public RichTextTypeEnum getName(String name) {

		for (RichTextTypeEnum sEnum : RichTextTypeEnum.values()) {
			if (sEnum.getName().equals(name))
				return sEnum;
		}

		return null;
	}

	public RichTextTypeEnum getCode(Integer code) {

		for (RichTextTypeEnum sEnum : RichTextTypeEnum.values()) {
			if (sEnum.getCode() == code)
				return sEnum;
		}

		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
