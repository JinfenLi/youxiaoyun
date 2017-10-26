/**
* @class School.util.AddTab
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 为tabPanel增加子面板的工具类
* 	该类具有的方法：addTab
*/
Ext.define("School.util.AddTab", {

	statics: {

		/**
		* @method addTab
		* @param mainPanel: 窗口中间的tabPanel
		* @param title: 新建子面板的标题
		* @param xtype: 新建字面板的xtype(别名)
		* @param iconCls：面板标题前面的那个小图标的样式,可选参数
		* @param itemId: 新建子面板的itemId,可选参数
		* @param theElse: 新建面板的自定义属性，是一个对象,可选参数
		**/
		addTab: function(mainPanel, title, xtype, iconCls, itemId, theElse) {

			var cls = iconCls ? iconCls : "menu_profile", //iconCls的默认值
				newTab = mainPanel.items.findBy(function(tab) { 
					return tab.title === title;
				});
			
			//如果newTab不存在，则新建一个
			if( !newTab ) {
				var config = {
					xtype: xtype,
					closable: true,
					iconCls: cls,
					title: title
				};

				if(theElse) {
					for(var key in theElse) {
						config.key = theElse.key;
					}				
				}
				//如果itemId有值，则赋给config.itemId;
				if(itemId) {
					config.itemId = itemId;
				}

				//把新的面板追加到mainpanel里面
				newTab = mainPanel.add(config);
			}

			//激活当前面板
			mainPanel.setActiveTab(newTab);

			return newTab;
		}
	}
});