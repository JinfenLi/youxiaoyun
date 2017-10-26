/**
* @class School.model..menu.Root
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 模块的model
*/

Ext.define("School.model.menu.Root",{
	extend: 'Ext.data.Model',
	//在需要实例化时才会加载该类进来
	uses: [
		'School.model.menu.Item'
	],
	idProperty: "id",
	fields: [
		{
			name: 'text'
		},{
			name: 'iconCls'
		},{
			name: 'id'
		}
	],
	hasMany: {
		model: 'School.model.menu.Item',
		foreignKey: 'parent_id',
		name: 'items'
	}
})
//*/