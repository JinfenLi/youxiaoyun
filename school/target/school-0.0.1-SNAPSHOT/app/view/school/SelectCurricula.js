/**
* @class School.view.school.SelectCurricula
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 选课管理的主面板
*/

Ext.define("School.view.school.SelectCurricula", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.SelectCurricula",
		//"Ext.ux",
		"School.util.ComponentColumn"
	],
	//itemId: "selectcurricula",
	alias:"widget.selectcurricula",
	//selType: "checkboxmodel",
	plugins: {
        ptype: 'cellediting',
        clicksToEdit: 1
   },
	forceFit: true,
	initComponent: function() {
		this.store = Ext.create("School.store.school.SelectCurricula");
		this.columns = [
		
			{
				text: "班级名称",
				dataIndex: "name"
			},
			{
				dataIndex: "id",
				hidden: true,
				text: "id"

			},
			{
				text: "所属年级",
				dataIndex: "tScGradeId",
				renderer: function(value) {
					return (School.util.Util.getOtherValue(value, zy_gradeRec));
				}
			},
			{
				text: "班级简称",
				//hidden: true,
				dataIndex: "sortName"
			},
			{
				text: "班级简介",
				hidden: true,
				dataIndex: "comment"
			},
			{
				text: "班主任",
				hidden: true,
				dataIndex: "headTeacher"
			},
			{
				text: "备注",
				hidden: true,
				dataIndex: "info"
			},
			{
				xtype: 'componentcolumn', 
 				text: "任课老师",
        renderer: function(status, datas, me) { 
          return { 
            name: "headTeacher",
						allowBlank: true,
						afterLabelTextTpl: "",
						itemId: "selectTeacher",
						xtype: "combobox",
						triggerAction: "all",
						emptyText: "请选择...",
						store: zy_teacherStore,
						editable: true,
						typeAhead: true,
						typeAheadDelay: 50,
						forceSelection : true,
						displayField: "name",
						valueField: "id",
						queryMode: "local",
						value: me.data.curriculaTeacherId
					}; 
				} 
			}
		];

		//固定菜单栏
		this.dockedItems = [
			{
				xtype: "toolbar",
				flex: 1,
				dock: "top",
				items: [
					{
						fieldLabel: "学科选择",
						labelWidth:60,
						itemId: "subjectcombo",
						xtype: "combo",
						triggerAction: "all",
						width: 200,
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.school.CourseMgr"),
						queryMode: "remote"
					},
					{
						fieldLabel: "年级选择",
						labelWidth:60,
						itemId: "gradecombo",
						xtype: "combo",
						triggerAction: "all",
						width: 150,
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.school.GradeMgr"),
						queryMode: "remote"
					},
					{
						fieldLabel: "学期选择",
						labelWidth: 60,
						itemId: "termcombo",
						xtype: "combobox",
						triggerAction: "all",
						width: 200,
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "name",
						//获取数据集
						store: Ext.create("School.store.school.Semester"),
						queryMode: "remote"
					},
					{
						xtype: "button",
						iconCls: "key_go",
						text: "查询",
						itemId: "queryCurricula"
					},
					{
						xtype: "tbseparator"
					},
					{
						xtype: "label",
						margin: "0 0 0 20",
						text: "当前课程："
					},
					{
						xtype: "label",
						style: {
							color: "red",
							fontWeight: 800
						},
						itemId: "curriculaname"
					}
				
				]
			}
		
		];

		this.buttons = [
			{
				xtype: "tbfill"
			},
			{
				text : '保存选课',
				itemId: "save",
				height: 30,
				width: "100%"
			}
		];
		
		this.callParent(arguments);
	}

});

