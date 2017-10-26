/**
 * 
 * Time: 2015/09/07
 * Author: Jason Liao
 * Contact: lmovingon2014@gmail.com
 * Path: School.view.multimedia.UploadResources
 * Description: 学习园地界面
 * 
 */

Ext.define("School.view.multimedia.UploadResources", {
	extend: "Ext.window.Window",
	alias: "widget.uploadresources",
	
//	requires: [
//		"School.store.multimedia.VideolibMgr"
//	],
	
	itemId: "uploadresources",
	width: 450,
	height: 250,
	autoShow: true,
	modal: true,
	buttons: [{
		text: '上传',
		itemId: 'upload'
	}],
	layout: 'fit',
	initComponent: function() {
		
		var me = this;
		
		Ext.apply(me, {
			items: [{
				xtype: 'form',
				layout: 'border',
				items: [{
					xtype: 'form',
					region: 'north',
					layout: {
						type: 'hbox',
						align: 'stretch'
					},
					defaults: {
						padding: 10,
						anchor: '0',
						labelWidth: 60,
						flex: 1
					},
					items: [{
						xtype: 'combobox',
						fieldLabel: '选择年级',
						emptyText: "请选择...",
						editable: false,
						labelWidth: 60,
						store: Ext.create("School.store.school.GradeMgr"),
						valueField: 'id',
						displayField: 'name',
						queryMode: "remote",
						name: 'gradeId',
						allowBlank: false,
						msgTarget: 'side'
					}, {
						xtype: 'combobox',
						fieldLabel: '资源类型',
						emptyText: "请选择...",
						editable: false,
						labelWidth: 60,
						store: Ext.create("School.store.multimedia.LearningCenter"),
						valueField: 'id',
						displayField: 'name',
						queryMode: "remote",
						name: 'typeId',
						allowBlank: false,
						msgTarget: 'side'
					}]
				}, {
					region: 'center',
					xtype: 'form',
					defaults: {
						padding: 10,
						anchor: '0',
						labelWidth: 60
					},
					items: [{
						xtype: 'textfield',
						fieldLabel: '资源名称',
						name: 'name',
						allowBlank: false,
						msgTarget: 'side'
					}, {
						xtype: 'radiogroup',
						fieldLabel: '资源分类',
						columns: 2,
						vertical: true,
						items: [{
							boxLabel: '站内文件',
							name: 'isLink',
							itemId: 'file',
							inputValue: false,
							checked: true
						}, {
							boxLabel: '站外链接',
							itemId: 'link',
							name: 'isLink',
							inputValue: true
						}]
					}, {
						xtype: 'filefield',
						filedLabel: '资源文件',
						buttonText: '选择文件',
						name: 'file',
						msgTarget: 'side'
					}, {
						xtype: 'textfield',
						fieldLabel: '资源路径',
						name: 'resourcepath',
						msgTarget: 'side',
						hidden: true
					}]
				}]
			}]
		});
		
		me.callParent(arguments);
	}
});
