/**
* @class School.view.news.PublishNews
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 发布新闻的GridPanel
*/

Ext.define("School.view.news.PublishNews", {
	extend: "Ext.panel.Panel",
	alias: "widget.publishnews",
	layout: {
		type: "border"
	},
	items: [
		{
			region: "center",
			itemId: "center",
			layout: "fit",
			dockedItems: [
				{
					xtype: "toolbar",
					layout: "fit",
					items: [{
						xtype: "form",
						layout: "anchor",
						defaults: {
							xtype: "textfield",
							margin: "10 10",
							anchor:　"100%",
							allowBlank: false,
							labelWidth: 70
						},

						items: [
							{
								xtype: "textfield",
								itemId: "selectedschools",
								fieldLabel: "可见学校",
								emptyText: "请从窗口右边选择相应的地区学校",
								readOnly: true
							},
							{
								itemId: "title",
								fieldLabel: "新闻标题",
								name: "title",
								emptyText: "请输入新闻标题"
								
							},
							{
								itemId: "date",
								name: "createTime",
								fieldLabel: "发布日期",
								xtype: "datefield",
								editable: false,
								value: Ext.util.Format.date(new Date()),
								format: "Y-m-d",
								emptyText: "请输入发布日期，格式如：2015-08-01"
							},

							{
								fieldLabel: "新闻类别",
								name: "type",
								itemId: "newscombo",
								xtype: "combo",
								triggerAction: "all",
								emptyText: "请选择...",
								editable: false,
								displayField: "name",
								valueField: "id",
								value: "news",
								store: [["news","校园新闻"], ["notice","校园公告"], ["reward","荣誉之窗"]],
								queryMode: "local"
							},
							{
								xtype: "label",
								text: "文章内容:"
							}
						]
					}]
				}
			]
		}

	],

	//预览新闻的按钮
	buttons: [
		{
			text : '预览&发布',
			itemId: "previewbtn",
			permissionId: 'doPublishNews',
			height: 30,
			width: "100%"
		}
	]
	
});