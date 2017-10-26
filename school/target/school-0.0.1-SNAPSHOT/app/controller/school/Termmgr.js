/*
* School.view.school.Termmgr
* author:小玉
* description:学校管理下的学期管理controller
*/
Ext.define("School.controller.school.Termmgr",{
	extend:"Ext.app.Controller",
	views:[
      "school.Termmgr"
	],
	init:function(application){

		"use strict";

		this.control({
			"termmgr":{
				afterrender:function(component){
					this.onRender(component);
				}
			},
			//新增窗口
			"termmgr toolbar button#add":{
				click:function(button){
					Ext.create("School.view.school.AddTerm",{
						itemId:"add"
					}).show();
				}
			},
			//编辑窗口
			"termmgr toolbar button#edit":{
				click:function(button){
                    this.createEditWin(button)
				}
			},
			//设置当前学期
			"termmgr toolbar button#set":{
                click:function(button){
                    this.setTerm(button);
                }
			},
			//保存信息
			"addterm form button#save":{
				click:function(button){
					this.saveTermInfo(button);
				}
			}

		});
	},
	onRender: function(component){
		var store = component.getStore(),
		    name = "";
		   store.reload({
		   		   	callback:function(){
		   		var length = store.data.items.length;
		   		for(var i =0;i<length;i++){
		   			if(store.data.items[i].data.currentSemester == 1){
		                  name = store.data.items[i].data.name;
		                component.down("toolbar").down("label#currentSemesterName").setText("当前学期：" + name);   
		   			}
		   		}		   		
		   		   	}});
	},
	createEditWin: function(button){
		
		"use strict";

               var grid = button.up("grid"),
			       record = grid.getSelectionModel().getSelection(), 
			       win = {};

		//如果没有选择需要编辑的学期，则提示用户
		if(!record[0]) {
			School.util.Util.showErrorMsg("请选择一个需要编辑的学期！");
			return 0;
		}

         win = Ext.create("School.view.school.AddTerm",{
						itemId:"edit",
						title:"编辑学期"
					});
        win.down("form").queryById("isCurrent").hide();
        win.down("form").queryById("isCurrent").allowBlank =  true;
        win.down("form").loadRecord(record[0]);

		},

	setTerm: function(button){

	 	"use strict";

        var grid = button.up("grid"),
			record = grid.getSelectionModel().getSelection(),
			store = grid.getStore(), 
			currentSemesterName ="" ,
			win = {},
			url = "semester/setCurrentSemester.action",
			params = {} ;
       console.log(currentSemesterName)
		//如果没有选择需要设置的学期，则提示用户
		if(!record[0]) {
			School.util.Util.showErrorMsg("请选择一个需要设置的学期！");
			return 0;
		}else{
			currentSemesterName = record[0].get("name");
			params = {
				semesterId :record[0].get("id")
			};
		}
        School.util.Util.confirm("设置为当前学期，修改后不可返回当前状态",function(buttonId){
          if(buttonId == "yes"){
    		Ext.Ajax.request({
    			url:url,
    			params:params,
    			success: function(conn, response, options, eOpts) {
    				var result = School.util.Util.decodeJSON(conn.responseText);
    				    
    				    if (result.success) {
    				    	School.util.Util.showSuccessMsg("设置当前学期成功！");
    				        store.reload();
    				        grid.down("toolbar").down("label#currentSemesterName").setText("当前学期：" + currentSemesterName)
    				    }
    			},
    			failure: function(conn, response, options, eOpts){
    				School.util.Util.showErrorMsg("连接服务器失败！");
    			}
    		});	 
    		}       	
        });

	
		},

	saveTermInfo: function(button){

		"use strict";

       var win  = button.up("window"),
           store = Ext.ComponentQuery.query("termmgr")[0].getStore(),
           url = "semester/updateSemesterById.action",
           formValues = win.down("form").getForm().getValues();
	
		if(!win.down("form").getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填写好表单");
			return ;
		}
		if(win.getItemId() === "add"){
			delete formValues.id;
			formValues.tScSchoolId = zy_schoolId;
			url = "semester/createSemester.action";			
		}
School.util.Update.onSaveButtonClick(win, url, store, formValues);		
	}
})