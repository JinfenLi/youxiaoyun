	/*
 path: view.push.SendHorn
 author: cc
 description: 小喇叭发送
 */

Ext.define('School.view.push.Horn', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.horn',

	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			layout: 'border',
			padding: 15,
			items:[{
				region:'north',
				hight: 200,
				xtype: 'form',
				layout: 'anchor',
				defaults: {
					labelWidth: 70,
					anchor: '100%',
					margin: '10 0'
				},
				items:[{
					xtype: 'datefield',
					fieldLabel: '开始时间',
					format: 'Y-m-d',
					editable: false,
					value: Ext.util.Format.date(new Date(), 'Y-m-d'),
					itemId: 'beginTime',
					allowBlank: false
				}, {
					xtype: 'datefield',
					fieldLabel: '结束时间',
					format: 'Y-m-d',
					editable: false,
					itemId: 'endTime',
					allowBlank: false
				}, {
	        xtype: 'combo',
	        fieldLabel: "学期选择",
	        itemId: 'termCombo',
	        displayField: 'name',
	        valueField: 'id',
	        store: Ext.create('School.store.school.Semester', {
	          autoLoad: false
	        }),
	        hidden: true,
      	}]
			}, {
				xtype: 'form',
				region: 'center',
				layout: 'anchor',
				items: [{
					xtype: 'textarea',
					itemId: 'textarea',
					fieldLabel: '内容',		
					labelWidth: 70,
					anchor: '100%',
					margin: '10 0',
					grow: true,
					allowBlank: false			
				}]
			}],
			buttons: [{
				text: '清空',
				handler: function(btn) {
					btn.up('panel').down('textarea#textarea').setValue('');
				}
			}, {
				text: '发送',
				handler: function(btn) {
					var panel = btn.up('panel');
					var beginTime = panel.down('datefield#beginTime').getRawValue(),
						endTime = panel.down('datefield#endTime').getRawValue(),
						content = panel.down('textarea#textarea').getValue(),
						termCombo = panel.down('combo#termCombo');

					if(!content || !endTime || !beginTime) {
						School.util.Util.showErrorMsg('结束时间和内容不能为空！');
						return;
					}	
					Ext.Ajax.request({
						url: zyHost + 'horn/pushHorn',
						params: {
							message: content,
							beginTime: beginTime,
							endTime: endTime,
							userId: zy_teacherId,
							semesterId: termCombo.getValue(), 							
						},
						success: function (res) {
							School.util.Util.showSuccessMsg('发送小喇叭成功！');
							panel.close();
						}
					});		
				}
			}]
		});

		me.callParent();
	}
});