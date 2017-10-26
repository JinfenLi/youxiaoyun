/**
* @class School.util.Update
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 通过ajax更新数据，提供了两个方法
	onDeleteButtonClick：删除
	onSaveButtonClick: 新增或者修改
*/
Ext.define("School.util.Update", {
	statics: {

		/*
		* @method onDeleteButtonClick
		* @param store: 删除成功后需要更新的Store实例,如果没有，可以传null过来
		* @param requestUrl: 请求地址
		* @param params: 传给后台的json数据格式的参数
		* @param reloadParams: store刷新所需要的参数集合
		* @description: 用来删除gridPanel的数据
		*/

		onDeleteButtonClick: function(store, requestUrl, params, reloadParams, fn) {

				Ext.Ajax.request({
					url: zyHost + requestUrl,
					timeout: 60000,
					params: params,

					success: function(conn,response,options,eOpts) {
						
						var result = School.util.Util.decodeJSON(conn.responseText),
							msg = result.msg || "";

						//如果删除失败，则退出
						if(!result.success) {
							School.util.Util.showErrorMsg("删除失败!" + msg);
							return ;							
						}

						if(store) {
							reloadParams = reloadParams || {};						
							store.reload({
								params: reloadParams
							});							
						}

						if(fn) {
							fn();
						}							
						School.util.Util.showSuccessMsg("删除成功!");
					},

					failure: function(conn,response,options,eOpts) {
						store.removeAll();
						School.util.Util.showErrorMsg("链接服务器失败！");
					}

				});
		},

		/**
		* @method onSaveButtonClick
		* @param win: 当前表单的父容器window
		* @param url: 请求地址
		* @param store: 请求成功后需要刷新的store
		* @param params: 需要传递给后台的请求参数
		* @param add：保存成功后的提示文字
		* @param reloadParams: 刷新store需要的参数集合
		*/
		onSaveButtonClick : function(win, url, store, params, add, reloadParams, fn) {

			// 添加一层遮罩
			win.mask("正在保存，请耐心等待...","splashscreen");

			Ext.Ajax.request({
				url: zyHost + url,
				method: "POST",
				timeout: 60000,
				params: params,
				success: function(response, options) {

					var result = School.util.Util.decodeJSON(response.responseText);
						
					win.unmask();

					//如果保存失败，则退出
					if(!result.success) {
						School.util.Util.showErrorMsg("保存失败！");
						win.close();
						return;						
					} 

					win.close();
					School.util.Util.showSuccessMsg("保存成功！" + (add ? add : ""));

					//重新加载数据
					if(store) {
						if(reloadParams) {
							store.load({
								params: reloadParams
							});
						}else {
							store.reload();
						}
					}

					if(fn) {
						fn();
					}
				},

				failure: function(response, options) {
					win.unmask();
					School.util.Util.showErrorMsg("链接服务器失败！");
				}
			});		
		}
	}
});