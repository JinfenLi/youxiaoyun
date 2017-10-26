/**
* @class School.view.multimedia.AddAlbum
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增或者修改相册的Ext.Window
*/

Ext.define("School.view.multimedia.AddAlbum", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addalbum",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "创建相册",
	autoShow: true,
	items: [
		{
			xtype: "form",
			border: false,
			style: {
				padding: "10px"
			},
			layout: {
				type: "anchor"
			},
			defaults: {
				xtype: "textfield",
				anchor: "100%",
				labelWidth: 70,
				afterLabelTextTpl : '<span style="color:red">*</span>', 
				labelSeparator: ":",
				allowBlank: false,
				style: {
					marginTop: "20px"
				}
				
			},
			items: [
				{
					name: "schoolId",
					hidden: true,
					fieldLabel: "学校id",
					allowBlank: true
				},
				{
					name: "clazzId",
					hidden: true,
					fieldLabel: "班级id",
					allowBlank: true
				},
				{
					name: "name",
					fieldLabel: "相册名称",
					emptyText: "相册名称不可大于12个字符",
					maxLength: 12
				},
				// {
				// 	name: "type",
				// 	fieldLabel: "相册类型",
				// 	allowBlank: true,
				// 	hidden: true,
				// 	xtype: "combobox",
				// 	displayField: "name",
				// 	valueField: "id",
				// 	editable: false,
				// 	queryMode: "local",
				// 	store: [["common", "普通相册"], ["ViewPager", "轮播相册"]]
				// },
				// {
				// 	xtype: "radiogroup",
				// 	itemId: "range",
				// 	allowBlank: true,
				// 	name: "range",
				// 	fieldLabel: "类型",
				// 	columns: 2,
				// 	labelAlign: "left",
				// 	items: [
				// 		{
				// 			boxLabel: "班级相册",
				// 			name: "range",
				// 			checked: true,
				// 			inputValue: "class"
				// 		},
				// 		{
				// 			boxLabel: "校园相册",
				// 			name: "range",
				// 			inputValue: "school"
				// 		}
						
				// 	]
				// },
				{
					name: "comment",
					allowBlank: true,
					afterLabelTextTpl :"",
					fieldLabel: "相册备注"
				},
				{
					name: "description",
					allowBlank: true,
					afterLabelTextTpl :"",
					fieldLabel: "相册描述"
				}
			],
			//底部的按钮栏
			buttons : [
					{
						text : '保存',
						itemId: "save"
					},
					{
						text : '清空',
						itemId: "reset"
					}, 
					{
						text : '取消',
						itemId: "cancel"
					}
			]
		}
	]
	
});