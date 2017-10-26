//ScoreMgr.js
Ext.define("School.store.school.ScoreMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.ScoreMgr"
	],
	model: "School.model.school.ScoreMgr",
	
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {examId: zy_examId};
				store.setProxy(School.util.Util.setProxy('score/getScore.action', params , 'result' ));
				// console.log(this.proxy.reader.jsonData);
			}
		},
		load: {
			fn: function(records,options, success,response) {
				var arg = records.proxy.reader.jsonData.average;
				var max = records.proxy.reader.jsonData.maxScore;
				var min = records.proxy.reader.jsonData.minScore;
				var scoremgr = Ext.ComponentQuery.query("scoremgr")[0];
				scoremgr.down("label#average").setText(arg);
				scoremgr.down("label#maxScore").setText(max);
				scoremgr.down("label#minScore").setText(min);				
			}
		}
	}

});