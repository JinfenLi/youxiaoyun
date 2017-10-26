//访问量的store
Ext.define("School.store.area.VisitorVolumn", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.area.VisitorVolumn"
	],
	model: "School.model.area.VisitorVolumn",
	data: [
		{name: "课程表", count: 87}, 
		{name: "教师端学生健康", count: 83}, 
		{name: "视频库", count: 79}, 
		{name: "班级时光相册", count: 72}, 
		{name: "家长端学生成绩", count: 178}, 
		{name: "独立用户数", count: 152}, 
		{name: "我的短信", count: 0}, 
		{name: "校园公告", count: 43}, 
		{name: "家长圈", count: 106}, 
		{name: "教师端学生评价", count: 104}, 
		{name: "校园相册", count: 33}, 
		{name: "教师端学生成绩", count: 74}, 
		{name: "家长端学生健康", count: 32}, 
		{name: "教育热讯", count: 19}, 
		{name: "荣誉之窗", count: 59}, 
		{name: "家园桥", count: 5}, 
		{name: "教子学园", count: 50}
	]
	// autoLoad: false,
	// listeners: {
	// 	beforeload: {
	// 		fn: function(store) {
	// 			var url = 'pv/getPv.action',
	// 				root = "schools",
	// 				params = {
	// 					beginTime: "2015-10-01",
	// 					endTime: Ext.util.Format.date(new Date(), "Y-m-d")
	// 				};
	// 			store.setProxy(School.util.Util.setProxy(url, params , root ));
	// 		}
	// 	}
	// }
});