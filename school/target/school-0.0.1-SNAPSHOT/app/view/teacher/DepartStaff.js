/**
* @class School.view.teacher.DepartStaff
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 部门人员管理的面板，
* 	排版是通过dataview嵌入html代码来实现
*/
Ext.define("School.view.teacher.DepartStaff", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.departstaff",
	closable: true,
	autoShow: true,
	resizable: false,
	title: "编辑部门人员",
	modal: true,
	itemId: "departstaff",
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 720,
	
	initComponent: function() {

		this.items = [
			{
				xtype: "panel",
				items: [
					{
						xtype: "dataview",
						store: Ext.create("School.store.school.TeacherMgr", {
							autoLoad: true,
							pageSize: 1000
						}),
						autoScroll: true,
						itemSelector: "teachers",
						tpl: [
							'<div  class="teachers">',
								'<div class="title">部门人员</div>',
								'<div class="staffs">',
									'<tpl for=".">',
										'<tpl if="this.check(id, 0)">',
											'<div class="checkbox">',
												'<label for="{id}"><input type="checkbox" id="{id}"  />{name}</label>',
												// '<img width="160" heigth="160" id="{id}" src="{path}" data-qtip="文件名：{name}<br /> 提交时间： {lastWriteTime}<br />大小： {length:this.filesize}" /><br />',
												// '<p class="ellipsis">{name}</p>',//防止文字过长得溢出
											'</div>',
										'</tpl>',
									'</tpl>',
								'</div>',
								'<div class="title">其他人员</div>',
								'<div class="others">',
									'<tpl for=".">',
										'<tpl if="this.check(id, 1)">',
											'<div class="checkbox">',																						
												'<label for="{id}"><input type="checkbox" id="{id}"  />{name}</label>',
												// '<img width="160" heigth="160" id="{id}" src="{path}" data-qtip="文件名：{name}<br /> 提交时间： {lastWriteTime}<br />大小： {length:this.filesize}" /><br />',
												// '<p class="ellipsis">{name}</p>',//防止文字过长得溢出
											'</div>',
										'</tpl>',
									'</tpl>',
								'</div>',
							'</div>',
							{
								check: function(id, type) {

									var ids = Ext.ComponentQuery.query("#departstaff")[0].datas.teachersId;
									console.log(ids);

									if(type === 1) {
										if(ids.indexOf(id) === -1) {
											return true;
										} else {
											return false;
										}
									} else {
										if(ids.indexOf(id) === -1) {
											return false;
										} else {
											return true;
										}
									}
									
									
									
								}
							}
						]
					}
				]
			},
			
		];

		this.dockedItems = [
			{
				xtype: "toolbar",
				dock: "bottom",
				items:  [
					{
						xtype: "tbfill"
					},
					{
						xtype:"checkbox",
						boxLabel: "全选部门人员",
						labelWidth:120,
						width: 150,
						itemId: "allDept"
					},
					{
						xtype:"checkbox",
						boxLabel: "全选其他人员",
						labelWidth:120,
						width: 150,
						itemId: "allNotDept"
					},
					{
						xtype: "button",
						text : '添加人员到部门',
						iconCls: "add",
						itemId: "add"
					},
					{
						xtype: "button",
						text : '移除部门人员',
						iconCls: "delete",
						itemId: "delete"
					}
				]
			}
		];

		this.callParent(arguments);
	}
	
	
});