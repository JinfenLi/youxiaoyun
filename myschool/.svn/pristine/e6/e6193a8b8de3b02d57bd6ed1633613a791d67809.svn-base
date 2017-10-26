//Menu.js
Ext.define("School.store.Menu", {
	extend : 'Ext.data.Store',
	requires : [ 'School.model.menu.Root' ],
	model : "School.model.menu.Root",
	proxy : {
		type : "ajax",
		// url: 'data/menu.json',
		url : zyHost + 'module/user_module_list.action',
		reader : {
			type : 'json',
			root : 'items'
		}
	},
	listeners : {
		exception : function(proxy, response, operation) {
			alert(operation.getError());
			Ext.MessageBox.show({
				title : 'REMOTE EXCEPTION',
				msg : operation.getError(),
				icon : Ext.MessageBox.ERROR,
				buttons : Ext.Msg.OK
			});
		}
	}
});