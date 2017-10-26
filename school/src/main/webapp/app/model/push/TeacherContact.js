/*
	path: model.push.TeacherContact
  author: Drake
	description: 教师联系人model
*/

Ext.define('School.model.push.TeacherContact', {
	extend: 'Ext.data.Model',

	fields: [{
		name: 'classId'
	}, {
		name: 'department'
	}, {
		name: 'headTeacher'
	}, {
		name: 'id'
	}, {
		name: 'lastUpdate'
	}, {
		name: 'name'
	}, {
		name: 'phone'
	}, {
		name: 'picUrl'
	}, {
		name: 'position'
	}, {
		name: 'sex'
	}, {
		name: 'studentName'
	}, {
		name: 'subject'
	}]
});