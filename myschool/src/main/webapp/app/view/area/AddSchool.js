/**
* @class School.view.area.AddSchool
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: a Ext.Window of Adding School
*/
Ext.define("School.view.area.AddSchool", {
	extend: "Ext.window.Window",
	alias: "widget.addschool",
	autoShow: true,
	closeAction: "destroy",
	autoShow: true,
	modal: true,
	width: 700,
	title: "注册学校",
	layout: 'fit',
	items: [
		{
			xtype: "form",
			layout: {
				type: "hbox"
			},
			frame: false,
			defaults: {
				xtype: 'fieldset',
				margin: "10 20"
			},
			items: [

				//第一列
				{
					flex: 10,
					title: "基本信息",
					layout: {
						type: "anchor"
					},
					style: {
						padding: "10px"
					},
					defaults: {
						xtype: "textfield",
						labelWidth: 70,
						anchor: "100%",
						style: {
							marginTop: "20px"
						}						
					},
					items: [
						{
							fieldLabel: "id",
							name: "id",
							hidden: true
						},
						{
							fieldLabel: "学校名称",
							allowBlank: false,
							afterLabelTextTpl: School.util.Util.required,
							name: "name"
						},
						{
							fieldLabel: "学校地址",
							name: "address"
						},
						{
							fieldLabel: "联系电话",
							name: "phone"
						},
						{
							fieldLabel: "邮政编码",
							regex: /[1-9]\d{5}(?!\d)/,
							regexText: "请输入正确的邮政编码",
							name: "postcode"
						},
						{
							fieldLabel: "电子邮箱",
							regex: /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/,
							regexText: "请输入正确格式的邮箱",
							name: "email"
						},
						{
							fieldLabel: "学校主页",
							regex: /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/,
							regexText: "请输入正确的网址！",
							name: "website"
						},
						{
							xtype: "filefield",
							fieldLabel: "学校logo",
							name: "file"
						}
					]
				},


				//第二列
				{
					flex: 10,
					title: "其他信息",
					style: {
						padding: "10px"
					},
					items: [

						//科目信息
						{
							xtype: "checkboxgroup",
							labelWidth: 50,
							fieldLabel: '科目',       
					        columns: 4,
					        defaults: {
					        	margin: "15 0"
					        },
					        // vertical: true,
							items: [
								{ name: 'subjectsName', boxLabel: '语文',  inputValue: '语文' },
					            { name: 'subjectsName', boxLabel: '数学',  inputValue: '数学' },
					            { name: 'subjectsName', boxLabel: '英语',  inputValue: '英语' },
					            { name: 'subjectsName', boxLabel: '音乐',  inputValue: '音乐' },
					            { name: 'subjectsName', boxLabel: '美术',  inputValue: '美术' },
					            { name: 'subjectsName', boxLabel: '体育',  inputValue: '体育' },
					            { name: 'subjectsName', boxLabel: '品德',  inputValue: '品德' },
					            { name: 'subjectsName', boxLabel: '信息',  inputValue: '信息' },
					            { name: 'subjectsName', boxLabel: '书法',  inputValue: '书法' },
					            { name: 'subjectsName', boxLabel: '实践',  inputValue: '实践' },
					            { name: 'subjectsName', boxLabel: '科学',  inputValue: '科学' },
					            { name: 'subjectsName', boxLabel: '校本',  inputValue: '校本' },
					            { name: 'subjectsName', boxLabel: '心理',  inputValue: "心理" }
					        ]
					     },
					            
						//年级信息
						{
							xtype: "checkboxgroup",
							labelWidth: 50,
							fieldLabel: '年级',       
					        columns: 3,
					        defaults: {
					        	margin: "15 0"
					        },
					        // vertical: true,
							items: [
								{ name: 'gradesName', boxLabel: '一年级',  inputValue: '一年级' },
					            { name: 'gradesName', boxLabel: '二年级',  inputValue: '二年级' },
					            { name: 'gradesName', boxLabel: '三年级',  inputValue: '三年级' },
					            { name: 'gradesName', boxLabel: '四年级',  inputValue: '四年级' },
					            { name: 'gradesName', boxLabel: '五年级',  inputValue: '五年级' },
					            { name: 'gradesName', boxLabel: '六年级',  inputValue: '六年级' },
					            { name: 'gradesName', boxLabel: '七年级',  inputValue: '七年级' },
					            { name: 'gradesName', boxLabel: '八年级',  inputValue: '八年级' },
					            { name: 'gradesName', boxLabel: '九年级',  inputValue: '九年级' }

							]  //checkboxgroup's items end
						}


					]//fieldset's items end
				} 

			], //form's items end 
			//底部的按钮栏
			buttons: [
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
			
	] //window's items end

});
