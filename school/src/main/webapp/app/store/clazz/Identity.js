/*
	path:
  author: Drake
	description: 评价身份
*/

Ext.define('School.store.clazz.Identity', {
  extend: 'Ext.data.Store',

  requires: [
  ],

  fields: [
    'name', 'subject', 'type'
  ],

  //autoLoad: false,
  listeners: {
    beforeload: {
      fn: function(store) {
        var params = {
           classId: zy_classId,
           semesterId: zy_termId
        };
        store.setProxy(School.util.Util.setProxy('appraiseSubject/getSubject.action', params , 'result' ));
      }
    }
  }
});