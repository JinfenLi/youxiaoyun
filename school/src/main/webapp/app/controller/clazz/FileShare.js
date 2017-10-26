/*
*author：小玉
*description:文件共享
*/
Ext.define('School.controller.clazz.FileShare',{
	 extend:'Ext.app.Controller',
	 requires: [
	 'School.view.clazz.FileShare'
	 ],
	 init:function(application){
	 	this.control({
	 		'fileshare':{
	 			afterrender:function(component){
	 				this.renderFileTab(component)
	 			}
	 		},
	 		'fileshare  toolbar button#upload':{
                 click:function(button){
                 	if(this.idArr.length>0){
                 	Ext.create('School.view.school.UploadStudent',{
                 		title:'上传文件',
                 		itemId:'uploadfile'
                 	}).show();//复用了我的班级里导入学生的窗口                 		
                 }else{
                 	School.util.Util.showErrorMsg("请先在右边选择文件共享人！");

                 }

                 }
	 		},
	 		//编辑
	 		'fileshare gridpanel toolbar button#edit':{
                  click: function(button){
                  	this.createEditWin(button)
                  }
	 		},
	 		'fileshare combobox#myclasses':{
				change: function(_this, newValue, oldValue, eOpts) {
					this.changeClass(_this, newValue);
				}
	 		},
	 		//下载列,这个是通过后台接口下载
	 		// 'fileshare actioncolumn#download':{
				// itemclick: function(grid, rowIndex, colIndex) {
				// 	this.downloadFile(grid, rowIndex, colIndex);									
				// }
	 		// },
	 		//删除列
	 		'fileshare actioncolumn#delete':{
				itemclick: function(grid, rowIndex, colIndex) {
					this.deleteFile(grid, rowIndex, colIndex);									
				}
	 		},
	 		'uploadstudent#uploadfile button#save':{
	 			click:function(button){
	 				this.uploadsharefile(button)
	 			}
	 		},
	 		//编辑窗口保存
	 		'fileshareeditwin button#save':{
                 click:function(button){
                      this.changeFileInfo(button)
                 }
	 		},
	 		'fileshare treepanel#fileContact':{
	 			checkchange: this.selectSharePeople
	 		}
	 	})
	 },
	 renderFileTab:function(component){
	 	   var contactTree = component.down('treepanel#fileContact'),
	 	       contactStore = contactTree.getStore(),
	 	       grid = component.down('gridpanel#filegrid'),
	 	       gridStore = grid.getStore();
	 	       gridStore.reload();
	 	    component.down("combobox#myclasses").setValue(zy_classId);
           contactStore.load({
		       	params:{
		       		schoolId: zy_schoolId
		       	},
		       	callback:function(){
		       		contactTree.expandAll();
		       	}
           });
	 },
	 //编辑文件弹出窗
	 createEditWin: function(button){
        var  grid = button.up("gridpanel"),
	        record = grid.getSelectionModel().getSelection(),
		         win = Ext.create('School.view.clazz.FileShareEditWin',{
		        	prevGrid:button.up('gridpanel'),
		        	fileId: record[0].get('id')
		         });
		         console.log(record[0].get('id'))
	        win.down("form").loadRecord(record[0]);
	        win.show()

	 },
	changeClass: function(_this, newValue) {

		"use strict"; //启用严格模式

		//刷新班级信息的数据集
		_this.up("fileshare").down('gridpanel#filegrid').getStore().reload({
			params: {
				clazzId: newValue
			},
			//请求成功后
			callback: function(records, options, success) {

				//如果请求失败, 则提示用户，并且退出
				if(!success) {
					School.util.Util.showErrorMsg("获取数据失败!");
					return;
				}									
			}
		});
	},
  //    downloadFile:function(grid, rowIndex, colIndex){
		// var inputs = '',
		//     store = grid.getStore(),
  //           name = store.getAt(rowIndex).get("name"),
		// 	url = "file/downloadFile";//删除文件的接口

		// inputs += '<input type="text" name="fileName" value="' + name + '"/>';
		// School.util.Util.downloadFile(url, inputs);
  //    },
     deleteFile:function(grid, rowIndex, colIndex){
           var store = grid.getStore(),
               id = store.getAt(rowIndex).get("id"),
               filePath = store.getAt(rowIndex).get("filePath"),
               url = "file/deleteFile",
               params = {
               	id:id,
               	filePath:filePath
               },
               clazzId  = grid.up('fileshare').down('combobox#myclasses').getValue();

		School.util.Util.confirm("删除文件", function(buttonId) {
			if(buttonId === "yes") {
				School.util.Update.onDeleteButtonClick(store, url, params,{clazzId: clazzId});
			}
		});	
     },
	 //上传文件的保存按钮
	 uploadsharefile:function(button){
	 	"use strict";

         var url =  zyHost +'file/uploadFile',//上传文件路径
             type = '.pdf,.xls,.doc,.ppt,.jpg',
             grid = Ext.ComponentQuery.query("fileshare")[0].down("gridpanel"), 
             store = grid.getStore(),
             msg = "该文件类型不是excel,word,pdf,ppt,jpg文件，请重新选择！",
             params= {
             	clazzId: this.idArr.join(',')
             };
        School.util.Util.uploadFile(button,url,params,msg,store,type);
	    
	 },
	 //编辑窗口保存按钮
	 changeFileInfo:function(button){
	 	var win = button.up("window"),
	 	    form = button.up("form"),
	 	    url = "file/updateFile",//修改文件的url
	 	    store = win.prevGrid.getStore(),
			params = {};
			params.name = form.getForm().getValues();
			params.fileId = win.fileId;
	    if(!form.getForm().isValid()){
	    	School.util.Util.showErrorMsg("请正确填写好表单");
			return ;
	    }
	    School.util.Update.onSaveButtonClick(win, url, store, params, "", {clazzId: zy_classId});
	 },
	 //返回所有共享人的id
     idArr:[],
	 selectSharePeople:function(node,checked){
        School.util.Util.cascadeNode(node,checked);
        var checkedNodeArr = Ext.ComponentQuery.query('fileshare')[0].down('treepanel#fileContact').getChecked();//所有选中的节点
	    this.idArr = [];
	    for(var i = 1; i<checkedNodeArr.length;i++){
	      if(checkedNodeArr[i].isLeaf()){
	      	var nodeId = checkedNodeArr[i].raw.id;
	      	this.idArr.push(nodeId);
	      }
	    }
	 }
})