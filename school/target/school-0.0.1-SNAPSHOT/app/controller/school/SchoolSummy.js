/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 校园简介控制器
 * 
 */
Ext.define('School.controller.school.SchoolSummy', {
	extend: 'Ext.app.Controller',
	views: [
		"school.SchoolSummy",
		"school.PublishSchoolSummy"
	],
	
	init: function (application) {
		this.control({
			"schoolsummy": {
				afterRender: function(component) {
					var summyStore = Ext.create("School.store.news.NewsMgr");
					summyStore.load({
						params: {
							schoolId: zy_schoolId,
							type: "summy"
						},
						callback: function() {
							if(summyStore.getCount() === 0) {
								School.util.Util.showErrorMsg("暂时没有学校简介，请到新增学校简介里进行添加！");
								return;
							}

							component.add({
								xtype: "panel",
								html: '<iframe src= ' + zyHost + 'article/getArticle.action?articleId=' + summyStore.getAt(0).get("id") + '  frameborder=0 width=100% height=100%></iframe>'
							});							
						}
					});			
				}
			},

			//新建校园简介
			"schoolsummy button#publish": {
				click: function(button) {
					var mainPanel = Ext.ComponentQuery.query("mainpanel")[0], //容器面板
						title = "新建校园简介", //打开的窗口名字
						xtype = "publishnews"; //打开的窗口类型
					
					//通过addTab函数为mainpanel创建一个新的子项
					School.util.AddTab.addTab(mainPanel, title, xtype, "menu_profile", "publishschoolsummy");
				}
			}


		});
	}
});