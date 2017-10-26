/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 查看其它考试的store
 * 
 */
Ext.define("School.store.exam.SeeOtherGradesmgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.ExamMgr"
	],
	model: "School.model.school.ExamMgr",
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {
					semesterId: Ext.ComponentQuery.query('seeothergradesmgr')[0].down('toolbar').getComponent('termcombo').getValue(),
					gradeId: Ext.ComponentQuery.query('seeothergradesmgr')[0].down('toolbar').getComponent('gradecombo').getValue(),
					subjectId: Ext.ComponentQuery.query('seeothergradesmgr')[0].down('toolbar').getComponent('subjectcombo').getValue(),
					teacherId:  Ext.ComponentQuery.query('seeothergradesmgr')[0].down('toolbar').getComponent('teachercombo').getValue(),
					type: 0,
			};
				store.setProxy(School.util.Util.setProxy('exam/selectExamRecord.action', params , 'exams' ));
			}
		}
	},
	sorters: {
		property: 'date',
		direction: 'DESC'
	},
});