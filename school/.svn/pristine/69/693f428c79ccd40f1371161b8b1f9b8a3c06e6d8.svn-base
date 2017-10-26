/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 我的班级控制器，包含进入我的课程表的入口
 * 
 */
Ext.define("School.controller.clazz.MyClass", {
  extend: "Ext.app.Controller",
  views: [
    "school.MyClass",
    "clazz.PostMgr"
  ],
  init: function(application) {
  
    this.control({
      
      //页面渲染后，是下拉框选择当前用户的的默认班级
      "myclass": {

        afterrender: function(component) {
          //管理员查看班级跟我的班级复用，所以要判断是哪个
          var itemId = component.getItemId();
          if(itemId == 'seeClasses') {
            component.down('toolbar').down('combobox#myclasses').hide();

          } else {
            component.down('toolbar').down('combobox#myclasses').show();
            if(zy_classes.length == 0) {
              component.down("combobox#myclasses").setValue('');
            } else {
              component.down("combobox#myclasses").setValue(zy_classId);
            }
          }
        }
      },

      //换页时，编辑模式取消
      "myclass pagingtoolbar": {
        change: function(self, pageData, eOpts) {
          var grid = Ext.ComponentQuery.query("myclass")[0];
          if(grid.query("toolbar")[0].down("button#editAll")) {
            grid.query("toolbar")[0].down("button#editAll").show();
            grid.query("toolbar")[0].down("button#saveAll").hide();
            grid.query("toolbar")[0].down("button#cancelEdit").hide();
            grid.editingPlugin.editStatus = false;
            
          }
        }
      },

      //查找时，编辑模式取消
      "myclass toolbar combobox#myclasses": {
        change: function(self, newValue, oldValue, eOpts) {
          var grid = Ext.ComponentQuery.query("myclass")[0];
          if(grid.query("toolbar")[0].down("button#editAll")) {
            grid.query("toolbar")[0].down("button#editAll").show();
            grid.query("toolbar")[0].down("button#saveAll").hide();
            grid.query("toolbar")[0].down("button#cancelEdit").hide();
            grid.editingPlugin.editStatus = false;
            
          }
        }
      },

      //打开新增学生窗口
      "myclass toolbar button#addstudent": {
        click: function(button) {
          win = Ext.create("School.view.school.AddStudent", {
            title: "新增学生",
            itemId: "add",
            prevGrid: button.up('grid')
          });         
        }
      },

      //保存新增学生
      // "insertstudent button#save": {
      //  click: function(button) {
      //    this.saveInsertStudent(button);
      //  }
      // },

      // 表格内编辑所有学生
      "myclass toolbar button#editAll": {
        click: function(button) {
          this.editAll(button);
        }
      },

      //表格内修改所有学生，对应的保存按钮
      "myclass toolbar button#saveAll": {
        click: function(button) {
          this.saveAllClass(button);
        }
      },
      //表格内修改所有学生，对应的取消按钮
      "myclass toolbar button#cancelEdit": {
        click: function(button) {
          this.cancelEdit(button);
        }
      },

      //打开编辑窗口
      "myclass toolbar button#edit": {
        click: function(button) {
          this.createEditWin(button);
        }
      },

      //保存新增或者编辑的学生
      "addstudent button#save": {
        click: function(button) {
          this.saveStudent(button);       
        }
      },

       //班级选择下拉框的监听事件
      "myclass combobox#myclasses": {
        change: function(_this, newValue, oldValue, eOpts) {
          this.changeClass(_this, newValue);
        }
      },
      
      //导出学生信息
      "myclass toolbar button#download": {
        click: function(button) {
          this.downloadStudent(button);
        }
      },

      //打开上传学生资料的新窗口
      "myclass toolbar button#upload": {
        click: function(button) {
          var length = button.up('grid').getStore().totalCount;
          if(length > 0) {
            School.util.Util.showWarningMsg('数据已初始化不能再导入数据，请用编辑功能修改。');
            return;
          }
          Ext.create("School.view.school.UploadStudent",{
            itemId:'uploadstudent'
          }).show();
        }
      },

      //提交学生资料
      "uploadstudent#uploadstudent button#save": {
        click: function(button) {
          this.uploadStudent(button);
        }
      },

      //打开课程表
      "myclass button#viewSyllabus": {
        click: function(button) {
          this.viewSyllabus(button);
        }
      },

      //打开班级帖子
      "myclass button#viewPost": {
        click: function(button) {
          this.viewPost(button);
        }
      },

      //删除指定学生
      "myclass actioncolumn": {
        itemclick: function(grid, rowIndex, colIndex) {
          this.deleteStudent(grid, rowIndex, colIndex);                 
        }
      }
    });
  },

  editAll: function(button) {
    var grid = button.up("myclass");
    grid.editingPlugin.editStatus = true;
    this.editingState = true;
    grid.query("toolbar")[0].down("button#editAll").hide();
    grid.query("toolbar")[0].down("button#saveAll").show();
    grid.query("toolbar")[0].down("button#cancelEdit").show();
  },

  saveAllClass: function(button) {
    var grid = button.up("myclass");
    var store = grid ? grid.getStore() : null;
    var mr = store.getModifiedRecords().slice(0);  
    var jsonArray = []; 
    Ext.each(mr,function(item){
      jsonArray.push(item.data);  
    });
    console.log(jsonArray);
    var stringJson = JSON.stringify(jsonArray);
    url = "student/updateStudentInfos.action";

    School.util.Util.confirm("修改学生", function(buttonId) {
      if(buttonId !== "yes") { //如果不确定，则退出删除操作
        return;
      }
      // 如果确定，则开始删除
      
      School.util.Update.onSaveSeveralButtonClick(grid, url, store, stringJson,'','',function(result) {
        //更新老师store
        if(result.success && result.students.length) {
          var msgStr = '<ol style="margin:0; padding:0;">';
          var i = 1;
          result.students.forEach(function(value) {
            msgStr += "<li>" + (i++) + ". 账号: " + value.name + " 已被 " + value.schoolName + " 学校教师录入 ";
          });
          msgStr += '</ol><p>如需修改请联系QQ205303602调整</p>';
          Ext.Msg.alert("提示",msgStr);

          // Ext.Msg.alert("提示","该账号已被XX学校教师录入，如需修改请联系QQ205303602调整");
        }
        zy_teacherStore.reload(); 
        grid.query("toolbar")[0].down("button#editAll").show();
        grid.query("toolbar")[0].down("button#saveAll").hide();
        grid.query("toolbar")[0].down("button#cancelEdit").hide();
        grid.editingPlugin.editStatus = false;
        this.editingState = false;
      });
    });
  },

  cancelEdit: function(button) {
    var grid = button.up("myclass");
    var store = grid ? grid.getStore() : null;
    store.reload();
    grid.query("toolbar")[0].down("button#editAll").show();
    grid.query("toolbar")[0].down("button#saveAll").hide();
    grid.query("toolbar")[0].down("button#cancelEdit").hide();
    grid.editingPlugin.editStatus = false;
    this.editingState = false;
  },


  //创建编辑窗口
  createEditWin: function(button) {

    "use strict";

    var grid = button.up("gridpanel"),
      record = grid.getSelectionModel().getSelection(),
      win = {},
      sex = "",
      birth = ''; 

    if(!record.length) {
      School.util.Util.showWarningMsg("请选择一个需要编辑的学生！");
      return 0;
    }

    win = Ext.create("School.view.school.AddStudent", {
      title: "编辑学生信息",
      itemId: "edit",
      prevGrid: grid,
    });

    win.down("form").loadRecord(record[0]);
    sex = record[0].get("studentGender");
    win.down("radiogroup#student_sex").setValue({studentGender: sex});

    birth = record[0].get('studentBirthday');
    birth = birth.split(' ')[0];
    win.down('datefield#student-Birthday').setValue(birth);

    var stores = grid.getStore();
  },

  saveStudent: function(button) {

    "use strict";
    var win = button.up("window"),
      store = win.prevGrid.getStore(),
      url = "student/updateStudentInfo.action",
      formValues = win.down("form").getForm().getValues(),
      classId = win.prevGrid.down('toolbar').down('combobox').getValue(); 
    // console.log(win.prevGrid);
    if(!win.down("form").getForm().isValid()) {
      School.util.Util.showErrorMsg("请正确填写好表单");
      return ;
    }
    if(win.getItemId() === "add"){
      delete formValues.id;
      formValues.clazzId = classId;
      url="student/addStudentAndParent.action";

    }
    this.onSaveButtonClick(win, url, store, formValues, "", {clazzId: classId});  
  },

  changeClass: function(_this, newValue) {

    "use strict"; //启用严格模式

    //显示当前的班级
    _this.up("myclass").down("label#className").setText(_this.getRawValue());

    //如果课程表已经打开了，则刷新数据
    var mycourseSchedule = document.getElementById("mycourseSchedule");

    if(mycourseSchedule) {
      mycourseSchedule.contentWindow.getCurricula(newValue);
    }
    //刷新班级信息的数据集
    _this.up("myclass").getStore().reload({
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

  uploadStudent: function(button) {

    "use strict";
    var grid = Ext.ComponentQuery.query("myclass")[0];
    // grid.query("toolbar")[0].down("combobox#myclasses")
    var url = "student/saveStudentAndParent.action",
        msg = "该文件类型不是excel文件，请重新选择！",
        classId = grid.down("combobox#myclasses").getValue(),
        store = grid.getStore(),
        params =  {
          file: button.up("form").down("filefield").getValue(),
          clazzId: classId
        };
    this.uploadFile(button, url, params, msg, store);
  },

  downloadStudent: function(button) {

    "use strict";

    var inputs = '',
      url = "student/downloadStudentAndParentExcel.action",
      clazzId = button.up("myclass").down("#myclasses").getValue();

    inputs += '<input type="text" name="clazzId" value="' + clazzId + '"/>';
    School.util.Util.downloadFile(url, inputs);
  },

  viewSyllabus: function(button) {

    "use strict";

    var syllabus = Ext.ComponentQuery.query("syllabus")[0],
      mainPanel = Ext.ComponentQuery.query("mainpanel")[0], 
      title = '我的课表', 
      xtype = "syllabus", 
      itemId = button.up("myclass").down("#myclasses").getValue();

    if(syllabus) {
      syllabus.destroy();
    }

    School.util.AddTab.addTab(mainPanel, title, xtype, "", itemId);
  },

  viewPost: function(button) {

    "use strict";

    var postmgr = Ext.ComponentQuery.query("postmgr")[0],
      mainPanel = Ext.ComponentQuery.query("mainpanel")[0], 
      title = '班级帖子', 
      xtype = "postmgr",  
      itemId = button.up("myclass").down("#myclasses").getValue();

    if(postmgr) {
      postmgr.destroy();
    }

    School.util.AddTab.addTab(mainPanel, title, xtype, "", "postmgr");
  },

  deleteStudent: function(grid, rowIndex, colIndex) {

    "use strict";
    
    var store = grid.getStore(),
      studentId = store.getAt(rowIndex).get("studentId"),
      parentId = store.getAt(rowIndex).get("parentId"),
      requestUrl = "student/deleteStudent.action",
      params = {
        studentId: studentId,
        parentId: parentId
      };
    School.util.Util.confirm("删除学生", function(buttonId) {
      if(buttonId === "yes") {
        School.util.Update.onDeleteButtonClick(store, requestUrl, params);
      }
    }); 
  },

  // override util's method
  onSaveButtonClick : function(win, url, store, params, add, reloadParams, fn) {
    var self = this; // 重写了这一行
    // console.log("override onSaveButtonClick method");

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

        // 如果保存失败，则退出
        if(!result.success) {
          self.showErrorMsg(result, "保存失败! "); // 重写了这一行
          win.close();
          return;         
        }
        win.close();
        School.util.Util.showSuccessMsg("保存成功！" + (add ? add : ""));

        // 重新加载数据
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
  },

  // override util's method
  showErrorMsg: function(result, msg) {
    console.log(result)
    var type = result.teachers ? "教师" : "学生";
    var typeObjArr = result.teachers ? result.teachers : result.students;
    typeObj = typeObjArr[0];
    Ext.Msg.show({
      title: "错误",
      msg: msg + "该账号已被 " + typeObj.schoolName + " 的" + type + "录录入, 如需修改请联系 QQ205303602 调整",
      icon: Ext.Msg.ERROR,
      buttons: Ext.Msg.OK
    });
  },
  // override util's method
  uploadFile: function(button, url, params, msg, store, type, fn) { 
    var self = this  
    var form = button.up('form').getForm(),
        type = type || ".xls", 
        fileValue = button.up("form").down("filefield").getValue(), 
        length = fileValue.length;

    //验证表单是否有效，如果无效则提示
    if( !form.isValid()) {
      School.util.Util.showErrorMsg("请选择文件");
      return 0;
    }

    //判断文件类型是否匹配
    if(type.indexOf(fileValue.substr(length-4, 5).toLowerCase()) === -1) {
      School.util.Util.showErrorMsg(msg);
      return;
    }

    form.submit({
      url: zyHost + url,
      params: params,
      method: "post",
      waitMsg: '正在上传...',
      success: function(response, options) {
        var result = School.util.Util.decodeJSON(options.response.responseText);

        self.showUploadFileSuccessMsg(result.students);
        button.up("window").close();
        
        //如果传进了store,则刷新
        store && store.reload();

        //如果传进了回调函数则执行
        fn && fn();      
      },
      failure: function() {
        School.util.Util.showErrorMsg("文件上传失败！");
      }
    });
  },

  showUploadFileSuccessMsg: function(students) {
    var msg = '上传文件成功! '
    if (students.length > 0) {
      msg += '但以下学生导入失败：<ol style="margin:0; padding:0;">'
      students.forEach(function(student) {
        msg += '<li>' + student.name + ' 已被 ' + student.schoolName + ' 的学生录录入,</li>' 
      })
      msg += '如需修改请联系 QQ205303602 调整</ol>'
    }
    Ext.Msg.show({
      title: "成功",
      msg: msg,
      icon: Ext.Msg.INFO,
      buttons: Ext.Msg.OK
    })
  }
});