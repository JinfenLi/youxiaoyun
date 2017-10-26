/*
 path: controller.push.Push
 author: Drake 
 description:消息推送模块，新增一个发送到具体年级

 */

Ext.define('School.controller.push.Push', {
	extend: 'Ext.app.Controller',

	requires: [
		'School.util.Util',
		'School.store.push.Contact',
		'School.store.push.SchoolContact'
	],

	views: [
		'push.PushPanel',
		'push.ContactTree',
		'push.Horn'
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
	},{
		ref:'schoolContact',
		selector:'schoolcontact'
	}],

	init: function (application) {
		this.control({
			//小喇叭发送板块
			'horn': {
				afterrender: function(component) {
					var termCombo = component.down('combo#termCombo');
					termCombo.getStore().load({
						callback: function() {
							School.util.Util.setComboboxValue(termCombo,'currentSemester', 'id', 1);							
						}
					});
				}
			},

			'pushpanel contacttree': {
				render: this.setTreeData,
				// checkchange: this.selectReceiver
			},

			//选中联系人
			
			'pushpanel contacttree	treepanel#schoolcontact': {
				checkchange: this.gradeSelectReceiver
			},
      'pushpanel contacttree treepanel#contactPanel':{
          checkchange: this.contactSelectReceiver
      },
			'pushpanel button#doSend': {
				click: this.doSendMsg
			},

			'pushpanel button#resetContent': {
				click: this.resetContent
			},

			//发送至全校学生的勾选	,这部分改用schoolcontact		
			// 'pushpanel contacttree checkbox#sendMessageToStudent': {
			// 	change: this.selectAll
			// }
		})
	},

	//联系人列表 发送给后台的参数
	paramsToBackend: [],

	// 收件人名字
	receiversName: [],
    //发送至全校学生接口的传给后台的参数
    gradeParams:[],
    //点击学校年级
    gradeSelectReceiver:function(node,checked){
    	var me = this;
      this.selectReceiver(node,checked,"gradeParams");
    },
    //点击联系人列表
    contactSelectReceiver:function(node,checked){
    	var me = this;
      this.selectReceiver(node,checked,"paramsToBackend");
    },
	//选择收信人的时候，paramsArray分别对应gradeParams(对应发送年级)和paramsToBackend(发送联系人)
	selectReceiver: function (node, checked,paramsArray) {
		var me = this;
		School.util.Util.cascadeNode(node,checked);//父子节点级联，如果不清楚可以去了解Ext中tree的checked属性
		if (!node.raw.leaf) {//点击非叶子的时候
			//node.expand();
			Ext.each(node.childNodes, function(childNode) {
				// childNode.set('checked', checked);
				me.selectSingle(childNode,paramsArray);
			});

		} else {//点击叶子的时候
			this.selectSingle(node,paramsArray);
		
		}
		// 显示收件人的名字
		var receiverNameField = me.getPushPanel().down('#receiverName');
		receiverNameField.setValue(Ext.Array.unique(this.receiversName).toString());
	},

	// 只选择一个人的时候
	//paramsArray的值只能是paramsToBackend，gradeParams
	selectSingle: function (record,paramsArray) {
		// 获取表单
		var form = this.getPushPanel().down('form'),
			receiverNameField = form.down('#receiverName');
		var originReceiverName = receiverNameField.getValue(),
			currentName = record.raw.name,
			receiverId = record.raw.id,
			studentId = record.raw.studentId || ' ';

		// 设置收信人名字以及id
	if (record.get('checked')) {//选中的时候
		this.receiversName.push(currentName);
			// 发送信息的时候遇到传给后台
		if(paramsArray == "paramsToBackend"){//选中联系人列表树时
			this.paramsToBackend.push({
				receiverId: receiverId,
				studentId: studentId
			});
		}else {//选中全校班级树时
			this.gradeParams.push(receiverId);	
		}

	} else {//没有选中的时候
			Ext.Array.remove(this.receiversName, currentName);

			// 取消勾选，因此移除对应的
			if(paramsArray == "paramsToBackend"){
				
			this.paramsToBackend = this.paramsToBackend.filter(function (item, index, self) {
					return item.receiverId != receiverId;
			});
		    }else{
			
				this.gradeParams= this.gradeParams.filter(function(item){
					 return item != receiverId;
				});
			}
		}
	},


	// 设置tree panel的节点
	setTreeData: function (self) {
        var contactStore = self.down('treepanel#schoolcontact').getStore();
        contactStore.load({
        	params:{
        		schoolId:zy_schoolId
        	}
        });
        //考虑关闭tab,又重新打开tab,关闭之前可能有些checkbox已选
        this.gradeParams = [];
        this.paramsToBackend = [];
        this.receiversName = [];

		var store = self.down('treepanel#contactPanel').getStore(),
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

	// 推送信息，点击发送按钮
	doSendMsg: function (button) {
		var me = this,
			form = this.getPushPanel().down('form'),
			formValues = form.getValues();
		// var sendAll = button.up('panel#pushpanel').down('checkbox#sendMessageToStudent');
		//若没有收件人也不发送给全校学生，取消并且出错提示if(!formValues.name && !sendAll.checked) {
		if(!formValues.name) {
			School.util.Util.showErrorMsg("请选择收件人！");
			return;
		}

			//若选择发送了全校学生，则发送
		// if(sendAll.checked) {
		// 	Ext.Ajax.request({
		// 		url: 'push/sendMessagesToAllStudent.action',
		// 		params: {
		// 			senderId: zy_teacherId,
		// 			schoolId: zy_schoolId,
		// 			content: formValues.content,
		// 			kindId: formValues.kindId,
		// 		},
		// 		failure: function() {
		// 			School.util.Util.showErrorMsg("发送失败！");
		// 			return;					
		// 		}
		// 	});					
		// }
		var wait = Ext.Msg.wait('正在发送，请耐心等待');

      //发送至全校各年级的接口(新增)
      if(this.gradeParams.length != 0){
       var classIdArr = School.util.Util.unique(this.gradeParams);
      	var params = {
      		senderId:globalUserInfo.user_id,
      		content:formValues.content,
      		schoolId:zy_schoolId,
      		kindId:formValues.kindId,
      		gradeIds: classIdArr.join('.')
      	}
      	Ext.Ajax.request({
      		url:'/school/push/sendMessagesToOneGrade',
      		params: params,
      		success:function(res){
               	 School.util.Util.showSuccessMsg('年级发送成功');
               	var recordPanel = Ext.ComponentQuery.query("msglist")[0];
               	recordPanel && recordPanel.getStore() && recordPanel.getStore().reload();
               	me.gradeParams = [];
				me.receiversName = [];
				form.getForm().reset();
				// me.setTreeData(me.getContactTree());
      		},
      		failure:function(){
      			School.util.Util.showErrorMsg('年级发送失败！');
      			return ;
      		}
      	})
      }



		// 发送给后台的参数，以下部分是关于联系人列表接口的内容
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
			studentsId: studentsId.join(','),
			kindId: formValues.kindId,//通知类型
		};
       if(receiversId!= ""&&receiversId!=null){
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
		});
		}
		return ;
	},

	// 清空正文
	resetContent: function (btn) {
		var contentField = this.getPushPanel().down('[name=content]');
		contentField.setValue('');
	},

});

