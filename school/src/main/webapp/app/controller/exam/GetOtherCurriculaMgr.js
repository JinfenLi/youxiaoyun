/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 查看本校所有老师的选课情况的控制器
 * 
 */
Ext.define("School.controller.exam.GetOtherCurriculaMgr", {
	extend: "Ext.app.Controller",
	views: [
		"exam.GetOtherCurriculaMgr"
	],

	init: function(application) {
	
		this.control({
			
			//页面渲染后，设置默认学期
			"getothercurriculamgr": {
				afterrender: function(component) {
					this.setDefaultValue(component);
				}
			},

			//查询按钮的触发事件
			'getothercurriculamgr button#search': {
				click: function(button) {
					this.searchCurriculamgr(button);
				}
			},

			//关联的下拉框更新值
			'getothercurriculamgr combobox#gradeselect': {
				change: function(me, newValue, oldValue) {
					this.changeCombox(me, newValue, oldValue);
				}
			},

			//修改任课老师
			"getothercurriculamgr actioncolumn#editTeacher": {
				itemclick: function(grid, rowIndex, colIndex) {					
					this.editTeacher(grid, rowIndex, colIndex);
				}
			}
		});
	},

	//设置默认学期
	setDefaultValue: function(component) {
		"use strict";

		var termCombo = component.down('combobox#termcombo');

		//设置学期选择的默认值为当前学期(currentSemester===1的学期)
		School.util.Util.setComboboxValue(termCombo, 'currentSemester',
				"id", 1);
	},

	//查询选课情况
	searchCurriculamgr: function(button) {
		var store = button.up('grid').getStore(),
			gradeId = button.up('toolbar').down('#gradeselect').getValue(),
			semesterId = button.up('toolbar').down('#termcombo').getValue(),
			classId = button.up('toolbar').down('#classselect').getValue();
		if (!classId) {
			School.util.Util.showErrorMsg('班级选择为空！');	
			return;
		}
		store.load({
			params: {
				semesterId: semesterId,
				classId: classId,
				showTrunk: 0
			}
		});
	},
 
	//关联下拉框更新值问题
	changeCombox: function(me, newValue, oldValue) {
		me.up('toolbar').down('#classselect').setValue('');
		me.up('toolbar').down('#classselect').getStore().reload();		
	},

	//修改选课老师
	editTeacher: function(grid, rowIndex, colIndex) {
		var records = grid.getStore().getAt(rowIndex);
		var curId = records.get('id'),
			oldTeacherId = records.get('tScTeacherId'),
			oldTeacherName = records.get('teacherName'),
			className = records.get('clazzName'),
			subjectName = records.get('subjectName'),
			curriculaId  = records.get('curriculaId'),
			classId = grid.up('panel').down('toolbar').down('#classselect').getValue(),
			termId = grid.up('panel').down('toolbar').down('#termcombo').getValue();
		Ext.create('Ext.window.Window', {
			title: '修改'+ className + subjectName +'任课老师',
			width: 300,
			height: 150,
			items: [{
				xtype: 'combobox',
				fieldLabel: '任课老师',
				labelWidth: 70,
				editable: false,
				displayField: "name",
				valueField: "id",
				//获取数据集
				store: Ext.create("School.store.school.TeacherMgr"),
				value: oldTeacherName, 
				margin: '15 15 15 15',
				width: 250,
			}],
			buttons: [{
				text: '保存',
				handler: function(btn) {
					var teacher = btn.up('window').down('combobox').getValue(); 
					if(teacher == oldTeacherName) {
						teacher = oldTeacherId;
					}
					Ext.Ajax.request({
						url: zyHost + 'curricula/updateCurriculaInfo.action',
						method: "POST",
						params: {
							id: curId,
							tScClassId: classId,
							tScSemesterId: termId,
							tScCurriculaId: curriculaId,
							tScTeacherId: teacher
						},
						success: function(response, options) {
							if(JSON.parse(response.responseText).success) {
								School.util.Util.showSuccessMsg('修改成功！');
								btn.up('window').close();
								grid.getStore().reload({
									params: {
										semesterId: termId,
										classId: classId,
										showTrunk: 0									
									}
								});
							} else {
								School.util.Util.showErrorMsg('修改成功！');								
							}

						}
					});		
				}
			}, {
				text: '取消',
				handler: function(btn) {
					btn.up('window').close();
				}
			}]
		}).show();
	}
});