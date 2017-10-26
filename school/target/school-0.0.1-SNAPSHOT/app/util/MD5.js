/**
* @class School.util.MD5
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 用于返回通过MD5加密的数据，项目里面暂时还没用到
*/
Ext.define("School.util.MD5",{
	statics: {
		encode: function(pass) {
			return $.md5(pass);
		}
	}
	
});