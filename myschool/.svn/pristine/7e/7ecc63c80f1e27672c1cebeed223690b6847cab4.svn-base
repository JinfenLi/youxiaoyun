/**
* @class School.model.menu.Item
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 菜单树的model
*/

Ext.define("School.model.menu.Item",{
	extend: 'Ext.data.Model',
	uses:[
		'School.model.menu.Root'
	],
	idProperty: 'id',
	fields: [
		{
			name: 'text'
		},{
			name: 'iconCls'
		},{
			name: 'className'
		},{
			name: 'id'
		},{
			name: 'parent_id'
		}
	],
	belongsTo: {
		model: 'School.model.menu.Root',
		foreignKey: 'parent_id'
	}
});