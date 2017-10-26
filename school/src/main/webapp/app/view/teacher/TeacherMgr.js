/**
* @class School.view.teacher.
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 教师管理的主面板
*/

Ext.define("School.view.teacher.TeacherMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.TeacherMgr"
	],
	alias:"widget.teachermgr",
	itemId: "teachermgr",
	forceFit: true,
	initComponent: function() {
		var store = Ext.create("School.store.school.TeacherMgr", {
			pageSize: 15
		});
		var params =  {
			schoolId: zy_schoolId
		};

		store.setProxy(School.util.Util.setProxy('teacher/getAllTeacher.action', params, 'teachers', "totalCount"));
		this.store = store;

		this.editStatus = false;
		var cellEditingPlugin = Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit: 1,
			editStatus: false,
			listeners: { 
				beforeedit: function( editor, e, eOpts ) {
					// if(this.editStatus == false) return false;
					if(e.field == "email") {
						var wth = e.column.getWidth();
						var tips = Ext.create("Ext.tip.ToolTip", {
							html: "格式：example@example.com",
							style: {
								padding: "5px 10px"
							},
							title: "输入提示",
							maxWidth: wth,
							anchor: "right",
							autoHide: true,
							draggable: true             //可以允许被拖动
						});
						tips.showAt([e.column.getX() - e.column.getWidth() - 10, e.column.getY() + (e.column.getHeight()-2) * (e.rowIdx + 1)]);
					}
					// return true;
					return this.editStatus;
				},
				validateedit: function(editor, e, eOpts) {
					if(e.field == "email") {
						var originalVal = e.originalValue;
						var inputVal = e.value;
						var pattern = /^\w+([.-]\w+)*@\w+([.-]\w+)*\.\w+$/;
						if(pattern.test(inputVal)) {
							return true;
						}
						else {
							School.util.Util.showErrorMsg("请输入正确的邮箱格式。例如: example@example.com");
							return false;
						}

					}
					return true;
				}
			}
		});
		this.plugins = [cellEditingPlugin];
		

		this.columns = [
		
			{
				text: "教师id",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "班级id",
				hidden: true,
				dataIndex: "tScClassId"
			},
			{
				text: "年级id",
				hidden: true,
				dataIndex: "tScGradeId"
			},
			{
				text: "学校id",
				hidden: true,
				dataIndex: "tScSchoolId"
			},
			{
				text: "教工号",
				dataIndex: "idcard",
				editor: {
						xtype: "textfield",
						regex: /^[0-9]*$/,
						allowBlank: true
				}	
			},
		
			{
				text: "姓名",
				dataIndex: "name",
				editor: {
						xtype: "textfield",
						allowBlank: true
				}	
			},
			
			{
				text: "性别",
				dataIndex: "sex",
				editor: {
						xtype: 'combobox',
						emptyText: "请选择...",
    				hideLabel: true,
            lazyRender: true, //值为true时阻止ComboBox渲染直到该对象被请求
            displayField: "sex",
            valueField: "sex",
            mode: "local",
            editable: false,
            triggerAction: "all",
						store: Ext.create("School.store.SexStore")
				}	
			},
			{
				text: "联系电话",
				dataIndex: "phone",
				editor: {
						xtype: "textfield",
						regex: /^[0-9]*$/,
						allowBlank: true
				}	
			},
			{
				text: "电子邮箱",
				dataIndex: "email",
				editor: {
						xtype: "textfield",
						allowBlank: true
				}	
			},
			{
				text: "管理员",
				hidden: true,
				align: "center",
				dataIndex: "isAuthc",
				renderer: function(value) {
					return (parseInt(value) === 1 ? "是" : "否");
				}
			},
			{
				xtype: "actioncolumn",
				itemId: "resetPsd",
				header: "修改密码",
				permissionId: 'resetPsd',
				align: "center",
				items: [
					{
						iconCls: "edit",
						handler: function(grid, rowIndex, colIndex) {
							var id = grid.getStore().getAt(rowIndex).get("id");
							this.fireEvent("itemclick", id);
						}
					}
				]
			}
			// {
			// 	text: "图片",
			// 	dataIndex: "picUrl"
			// },

		];

		//固定菜单栏
		this.dockedItems = [
			{
				xtype: "toolbar",
				flex: 1,
				dock: "top",
				items: [
					{
						fieldLabel: "搜索教师",
						width: 200,
						labelWidth: 60,
						itemId: "searchKeywords",
						xtype: "textfield",
						emptyText: "请输入教师名字"
					},
					{
						xtype: "button",
						text: "查找",
						itemId: "searchTeachers",
						permissionId: 'downloadTeacher',
						iconCls: "key_go"
					},
					{
						xtype: "button",
						text: "查找所有",
						itemId: "getAllTeachers",
						permissionId: 'downloadTeacher',
						iconCls: "key_go"
					},
					"-",
					{
						xtype: "button",
						text: "导出教师",
						itemId: "download",
						permissionId: 'downloadTeacher',
						iconCls: "download"
					},
					{
						xtype:  "button",
						text: "导入教师",
						permissionId: 'uploadTeacher',
						itemId: "upload",
						iconCls: "upload"
					},
					{
						xtype: "button",
						text: "添加教师",
						permissionId: 'addTeacher',
						itemId: "add",
						iconCls: "add"
					},
					{
						xtype: "button",
						text: "编辑教师",
						permissionId: 'editTeacher',
						itemId: "edit",
						iconCls: "edit"
					},
					{
						xtype: "button",
						text: "删除教师",
						permissionId: 'deleteTeacher',
						itemId: "delete",
						iconCls: "delete"
					},
					{
						xtype: "button",
						text: "编辑所有教师",
						permissionId: 'editTeacher',
						itemId: "editAll",
						iconCls: "edit"
					},
					{
						xtype: "button",
						hidden: true,
						text: "保存",
						itemId: "saveAll",
						permissionId: 'editTeacher',
						iconCls: "edit"
					},
					{
						xtype: "button",
						hidden: true,
						text: "取消",
						itemId: "cancelEdit",
						permissionId: 'editTeacher',
						iconCls: "delete"
					}
				]
			},
			{  
		        xtype: 'pagingtoolbar',  
		        //pageSize: 10, //每页显示几条数据  
		        store: this.getStore(),  // same store GridPanel is using  
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];




		this.callParent(arguments);
	}

});