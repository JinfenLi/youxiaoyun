Ext.define("School.view.area.RegisterSchool", {
	extend: "Ext.window.Window",
	alias: "widget.registerschool",
	autoShow: true,
	closeAction: "destroy",
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
						afterLabelTextTpl : '<span style="color:red">*</span>',
						labelSeparator: ":",
						allowBlank: false,
						style: {
							marginTop: "20px"
						}
						
					},
					items: [
						{
							fieldLabel: "id",
							allowBlank: true,
							name: "id",
							hidden: true
						},
						{
							fieldLabel: "学校名称",
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
							afterLabelTextTpl: "",
							allowBlank: true,
							regex: /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/,
							regexText: "请输入正确的网址！",
							name: "website"
						},
						{
							xtype: "filefield",
							fieldLabel: "学校logo",
							afterLabelTextTpl: "",
							allowBlank: true,
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
								{ name: 'subject', boxLabel: '语文',  inputValue: '1' },
					            { name: 'subject', boxLabel: '数学',  inputValue: '2' },
					            { name: 'subject', boxLabel: '英语',  inputValue: '3' },
					            { name: 'subject', boxLabel: '音乐',  inputValue: '4' },
					            { name: 'subject', boxLabel: '美术',  inputValue: '5' },
					            { name: 'subject', boxLabel: '体育',  inputValue: '6' },
					            { name: 'subject', boxLabel: '品德',  inputValue: '7' },
					            { name: 'subject', boxLabel: '信息',  inputValue: '8' },
					            { name: 'subject', boxLabel: '书法',  inputValue: '9' },
					            { name: 'subject', boxLabel: '实践',  inputValue: '10' },
					            { name: 'subject', boxLabel: '科学',  inputValue: '11' },
					            { name: 'subject', boxLabel: '校本',  inputValue: '12' },
					            { name: 'subject', boxLabel: '心理',  inputValue: '13' }

							]  //checkboxgroup's items end
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
								{ name: 'grade', boxLabel: '一年级',  inputValue: '1' },
					            { name: 'grade', boxLabel: '二年级',  inputValue: '2' },
					            { name: 'grade', boxLabel: '三年级',  inputValue: '3' },
					            { name: 'grade', boxLabel: '四年级',  inputValue: '4' },
					            { name: 'grade', boxLabel: '五年级',  inputValue: '5' },
					            { name: 'grade', boxLabel: '六年级',  inputValue: '6' },
					            { name: 'grade', boxLabel: '七年级',  inputValue: '7' },
					            { name: 'grade', boxLabel: '八年级',  inputValue: '8' },
					            { name: 'grade', boxLabel: '九年级',  inputValue: '9' }

							]  //checkboxgroup's items end
						}


					]//fieldset's items end
				} 

			] //form's items end 
		},
			
	], //window's items end

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
});
