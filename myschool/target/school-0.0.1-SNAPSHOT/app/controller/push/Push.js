/*
 path: controller.push.Push
 author: Drake
 description:
 */

Ext.define('School.controller.push.Push', {
	extend: 'Ext.app.Controller',

	requires: [
		'School.util.Util',
		'School.store.push.Contact'
	],

	views: [
		'push.PushPanel',
		'push.ContactTree'
	],

	stores: [
		//'push.TeacherContact',
		//'push.ParentContact'
	],

	refs: [{
		ref: 'pushPanel',
		selector: 'pushpanel'
	}, {
		ref: 'contactTree',
		selector: 'contacttree'
	}],

	init: function (application) {
		this.control({
			'pushpanel contacttree': {
				render: this.setTreeData,
				checkchange: this.selectReceiver
			},

			'pushpanel button#doSend': {
				click: this.doSendMsg
			},

			'pushpanel button#reset': {
				click: this.resetContent
			}

		})
	},

	// 发送给后台的参数
	paramsToBackend: [],

	// 收件人名字
	receiversName: [],

	// 选择收信人,
	selectReceiver: function (node, checked) {
		var me = this;

		if (!node.raw.leaf) {
			//node.expand();
			Ext.each(node.childNodes, function(childNode) {
				childNode.set('checked', checked);
				me.selectSingle(childNode);
			});

		} else {
			this.selectSingle(node);
		}

		// 显示收件人的名字
		var receiverNameField = me.getPushPanel().down('#receiverName');
		receiverNameField.setValue(Ext.Array.unique(this.receiversName).toString());
	},

	// 只选择一个人的时候
	selectSingle: function (record) {
		// 获取表单
		var form = this.getPushPanel().down('form'),
			receiverNameField = form.down('#receiverName');

		var originReceiverName = receiverNameField.getValue(),
			currentName = record.raw.name,
			receiverId = record.raw.id,
			studentId = record.raw.studentId || ' ';

		// 设置收信人名字以及id
		if (record.get('checked')) {
			this.receiversName.push(currentName);

			// 发送信息的时候遇到传给后台
			this.paramsToBackend.push({
				receiverId: receiverId,
				studentId: studentId
			});

		} else {
			Ext.Array.remove(this.receiversName, currentName);

			// 取消勾选，因此移除对应的
			this.paramsToBackend = this.paramsToBackend.filter(function (item, index, self) {
					return item.receiverId != receiverId;
			});
		}
	},


	// 设置tree panel的节点
	setTreeData: function (self) {
		var store = self.getStore(),
			rootNode = store.getRootNode();
		var contacts = [];


		// 获取对应班级的教师信息
		Ext.Ajax.request({
			url: '/school/contact/getTeacherContactForWeb.action',
			async: false,

			success: function (res) {
				var result = School.util.Util.decodeJSON(res.responseText);
				//if (!result.success) {
				//	School.util.Util.showErrorMsg('获取教师信息失败');
				//	return 0;
				//}

				// 修改字段，因为后台返回的数据格式有点不‘合适’
				// name: 用来显示在tree上，默认是text, 我改了tree panel的'displayField'为'name'
				// leaf: 表示叶节点

				delete result.success;
				Ext.Object.each(result, function (key, value) {
					Ext.each(value, function (item, index) {
						var position = item.position;
						item.name = item.name + (position ? position.toString() : '');
						item.leaf = true;
						item.checked = false;
					});

					contacts.push({
						name: key,
						children: value,
						checked: false
					});
				})
			}
		});

		// 获取对应班级学生的父母的信息
		Ext.Ajax.request({
			url: '/school/contact/getParentContactForWeb.action',
			async: false,

			success: function (res) {
				var result = School.util.Util.decodeJSON(res.responseText);
				//if (!result.success) {
				//	School.util.Util.showErrorMsg('获取家长信息失败');
				//	return 0;
				//}

				// 修改字段，因为后台返回的数据格式有点不‘合适’
				// name: 用来显示在tree上，默认是text, 我改了tree panel的'displayField'为'name'
				// leaf: 表示叶节点

				delete result.success;
				Ext.Object.each(result, function (key, value) {
					Ext.each(value, function (item, index) {
						item.name = item.studentName;
						item.id = item.parentId;
						item.leaf = true;
						item.checked = false;
						//item.icon = 'http://121.201.19.131:8080' + item.picUrl
					});

					contacts.push({
						name: key,
						children: value,
						checked: false
					});
				})
			}
		});


		// 给tree panel动态添加数据
		store.setRootNode({
			expanded: true,
			children: contacts
		})
	},

	// 推送信息
	doSendMsg: function () {
		var me = this,
			form = this.getPushPanel().down('form'),
			formValues = form.getValues();

		// 发送给后台的参数
		var uniqueParams = School.util.Util.unique(me.paramsToBackend);
		var receiversId = [],
			studentsId = [];

		Ext.each(uniqueParams, function (item, index) {
			receiversId.push(item.receiverId);
			studentsId.push(item.studentId);
		});

		var data = {
			senderId: globalUserInfo.user_id, // found in 'global.js'
			content: formValues.content, // 消息正文
			receiversId: receiversId.join(','), // 收件人id
			studentsId: studentsId.join(',')
		};

		var wait = Ext.Msg.wait('正在发送，请耐心等待');

		Ext.Ajax.request({
			url: '/school/push/saveShortMessage.action',
			params: data,
			failure: function (res) {
				var result = School.util.Util.decodeJSON(res.responseText);
				School.util.Util.showErrorMsg(result);
				//wait.hide();
			},

			success: function (res) {
				var result = School.util.Util.decodeJSON(res.responseText),
					recordPanel = Ext.ComponentQuery.query("msglist")[0];

				if (!result.success) {
					School.util.Util.showErrorMsg(result.msg);
					//wait.hide();
					return 0;
				}

				School.util.Util.showSuccessMsg('发送成功');

				recordPanel && recordPanel.getStore() && recordPanel.getStore().reload();

				me.paramsToBackend = [];
				me.receiversName = [];
				form.getForm().reset();
				me.setTreeData(me.getContactTree());

				//wait.hide();
			}
		})
	},

	// 清空正文
	resetContent: function (btn) {
		var contentField = this.getPushPanel().down('[name=content]');
		contentField.setValue('');
	}

});
