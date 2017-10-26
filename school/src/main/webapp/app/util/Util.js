/**
* @class School.util.Util
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 这是一个工具类，主要是提供一些常用的操作方法：
* 	decodeJSON: 把后台返回的json字符串转成json对象
*	createEditWin: 实例化一个编辑窗口
* 	showErrorMsg: 提示错误信息
*	showWarnigMsg: 提示警告信息
*	showSuccessMsg: 提示成功信息
* 	confirm: 弹出一个确认框，一般用于删除操作
*	setProxy: 设置store的数据代理
*	downloadFile: 用于下载文件
*	uplaodFile: 用于上传文件
*	getInner: 获取浏览器窗口的大小
*	unique: 数组去掉重复的元素
*	getOtherValue: 通过一个键值获取该对象的另外一个键值
*   setComboboxValue:设置combobox的默认值
*   cascadeNode：treepanel中checkbox父子级联
*   compressImg: 压缩图片
*/
Ext.define("School.util.Util", {

	statics: {

		//静态属性，表单必选项后面的那个小红星
		required: '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>',
		
		/**
		* @method decodeJSON
		* @param text: 需要转换成JSON对象的JSON字符串
		* @description: 用阿里把json字符串转成json对象
		*/
		decodeJSON: function(text) {
			var result  = Ext.JSON.decode(text,true);
			if(!result) {
				result = {};
				result.success = false;
				result.msg = text;
			}
			return result;
		},


		/**
		* @method createEditWin
		* @param grid: 当前的gridPanel面板
		* @param className: 需要创建的窗口的类名
		* @param title: 创建的窗口的标题
		* @param msg: 如果没有选中数据时的提示信息
		* @description: 用来实例化一个编辑窗口的，比如： 
			编辑学校，编辑学生，编辑教师等等
		*/
		createEditWin: function(grid, className, title, msg) {

			var record = grid.getSelectionModel().getSelection(), 
				editWindow = {}; 

			//如果用户没有选择任何记录，则提示并且推出编辑操作
			if( !record[0] ) {
				School.util.Util.showWarningMsg(msg);
				return 0;
			}
		
			editWindow = Ext.create(className, {
				itemId: "edit",
				title: title,
				autoShow: true
			});

			//用loadRecord把用户选中的记录填入到表单当中
			editWindow.down("form").loadRecord(record[0]);
		},

		/**
		* @method showErrorMsg
		* @param text: 提示的错误信息
		* @description: 用来实例化一个提示错误信息的窗口
		*/
		showErrorMsg: function(text) {
			Ext.Msg.show({
				title: "错误",
				msg: text,
				icon: Ext.Msg.ERROR,
				buttons: Ext.Msg.OK
			});
		},

		/**
		* @method showSuccessMsg
		* @param text: 提示的成功信息
		* @description: 用来实例化一个提示成功信息的窗口
		*/
		showSuccessMsg: function(text) {
			Ext.Msg.show({
				title: "成功",
				msg: text,
				icon: Ext.Msg.INFO,
				buttons: Ext.Msg.OK
			});
		},

		/**
		* @method showWarningMsg
		* @param text: 提示的警告信息
		* @description: 用来实例化一个提示警告信息的窗口
		*/
		showWarningMsg: function(text) {
			Ext.Msg.show({
				title: "警告",
				msg: text,
				icon: Ext.Msg.WARNING,
				buttons: Ext.Msg.OK
			});
		},

		/**
		* @method confirm
		* @param msg: 确认的提示信息
		* @param callback: 确认之后的回调函数
		* @description: 创建一个确认窗口
		*/
		confirm: function (msg, callback) {
			Ext.Msg.confirm({
				title: '警告',
				msg: "您确定要" + msg + "?",
				icon: Ext.Msg.WARNING,
				buttons: Ext.Msg.YESNO,
				fn: callback // callback(buttonId), 'yes/no'
			})
		},

		/**
		* @method setProxy
		* @param url: store的数据代理的请求地址
		* @param params: 数据代理请求参数集合
		* @param root: 数据代理的返回数据的根节点
		* @param total: 分页参数，每页显示的数据条数
		* @return {proxy} 数据代理对象;
		* @description: 用来设置store的数据代理proxy
		*/
		setProxy: function(url, params, root, total, countName) {
			total = (total ? total : 25);
			countName = (countName? countName:'totalCount')
			var proxy = {
				type: "postproxy",
				url: zyHost + url,
				reader: {
					type: 'json',
					// totalProperty: total, 
					totalProperty: countName, 
					root: root
				}
			};

			if(params) {
				proxy.extraParams = params;
			}
			return proxy;
		},
		
		/**
		* @method downloadFile
		* @param url: 下载文件的地址
		* @param params: 需要提交给后台的参数(通过input标签进行包装)
		* @description: 用来下载文件，只需要提供下载地址和参数即可，用法示例：
		*	var url = "curricula/downloadCurricula.action",
		*		params = '<input type="text" name="clazzId" value="123456"/>';
		*	School.util.Util.downloadFile(url, params);
		*
		*	后台需要穿多少个参数，那么params就应该包含多少个input标签
		*	其中input的name属性值为参数名，value值为参数值
		*/
		downloadFile: function(url, params) {
			//创建表单
			 if (!Ext.fly('downForm')) {      
		           var downForm = document.createElement('form');      
		           downForm.id = 'downForm';      
		           downForm.name = 'downForm';      
		           downForm.className = 'x-hidden';  
		           downForm.action = zyHost + url;  
		           downForm.method = 'post';  
		           document.body.appendChild(downForm );      
		       }  
		       
		       //如果有附加参数，则追加到form表单里面
		       if(params) {
		       		$(downForm).html(params);
		       }
		       //提交表单
		       Ext.fly('downForm').dom.submit(); 
		       //删除表单
		       if (Ext.fly('downForm')) {      
		          document.body.removeChild(downForm);      
		      }
		},

		/**
		* @method uplaodFile
		* @param button: 点击上传的那个按钮
		* @param url: 上传的地址
		* @param params: 需要传给后台的参数集合
		* @param msg: 文件类型不正确的错误提示信息
		* @param store: 文件上传成功后需要刷新的store
		* @param type: 符合条件的文件类型(一般为4位，如果不够4位，则前缀.),
		*	如图像类型："jpeg,.jpg,.png,.gif" 
		* @params fn: 回调函数，当文件上传成功后需要执行的函数
		* @description: 上传文件
		*/
		uploadFile: function(button, url, params, msg, store, type, fn) {	

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
		        success: function(fp, o) {

			        School.util.Util.showSuccessMsg("提交成功！");
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

		/**
		* @method getInner
		* @return {width, height}: 浏览器的宽和高
		* @description: 用来获取浏览器窗口大小
		*/
		getInner: function () {
			if (typeof window.innerWidth != 'undefined') {
				return {
					width : window.innerWidth,
					height : window.innerHeight
				}
			} else {
				return {
					width : document.documentElement.clientWidth,
					height : document.documentElement.clientHeight
				}
			}
		},

		/**
		* @method unique
		* @param array: 需要去除重复元素的数组
		* @return []: 去除了重复元素后的数组
		* @description: 去除数组中重复的元素
		*/
		unique: function (array) {
			var unique = [];

			unique = array.filter(function(item, pos, self) {
				return self.indexOf(item) == pos;
			});

			return unique;
		},

		/**
		* @method getOtherValue
		* @param knownValue: 已知的字段值
		* @param records: 记录集合，一般是Ext.data.Store的实例
		* @param key1: 已知值的字段名
		* @param key2: 待求字段值的字段名
		* @description: 通过一个字段的值获取同一条记录里面的另外一个字段的值
			比如，通过学生学号可以调用该方法获取该学生的姓名
		*/
		getOtherValue: function(knownValue, records, key1, key2) {

			var length = records.getCount();
			key1 = key1 ? key1 : "id";
			key2 = key2 ? key2 : "name";

			for(var i = 0; i < length; i++) {
				if(records.getAt(i).get(key1) === knownValue) {
					return records.getAt(i).get(key2);
					break;
				}
			}
			return "";
		},


		/**
		* @method setDefaultValue
		* @param combobox: 需要设置默认值的combobox实例
		* @param key1: 约束条件的属性名
		* @param key2: combobox的valueField的值
		* @param value1: key1的值
		* @description: 设置combobox的默认值，比如：
			要设置默认教师下拉框的值为王老师，则可以
			setDefalutValue(teacherCombo, "name", "id", "王老师");
		*/
		setComboboxValue: function(combobox, key1, key2, value1) {
			var store = combobox.getStore();
			store.reload({
				callback: function() {
					for(var i = 0, len = store.getCount(); i < len; i++) {
						if(store.getAt(i).get(key1) === value1) {
							combobox.setValue(store.getAt(i).get(key2));
							break;
						}
					}
				}
			});
		},
   /*
     *功能：checkbox父子级联
     *node:选中的节点
     *checked:节点的状态
     */
    cascadeNode: function(node, checked) {
        var pNode = node.parentNode;
        node.checked = checked;
        var isLeaf = node.isLeaf();
        if (!isLeaf) {
            for (var i = 0; i < node.childNodes.length; i++) {
                node.childNodes[i].checked = checked;
            }
            node.cascadeBy(function(node) {
                node.set('checked', checked);
            });
        }
        if (checked == true) {
            for (; pNode != null && !pNode.get('checked'); pNode = pNode.parentNode) {
                pNode.set("checked", true);
            }
        } else {
            for (; pNode != null; pNode = pNode.parentNode) {
                if (hasCheckedNode(pNode)) {
                    break;
                } else {
                    pNode.set('checked', false);
                }
            }
        }
        function hasCheckedNode(node) {
            var has = false;
            if (node.hasChildNodes()) {
                Ext.each(node.childNodes, function(item) {
                    if (item.isLeaf()) {
                        if (has = item.get('checked')) {
                            return false;
                        }
                    } else if (has = hasCheckedNode(item)) {
                        return false;
                    }
                });
            }
            return has;
        }
    },


    // 获取上传文件的大小
		getFileSize: function (target){  
			var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
			var fs = 0;  
			if (isIE && !target.files) {  
				var filePath = target.value;  
				var fileSystem = new ActiveXObject("Scripting.FileSystemObject");  
				var file = fileSystem.GetFile (filePath);  
				fs = file.Size;   
			}else if(target.files && target.files.length > 0){  
				fs = target.files[0].size;  
			}else{  
				fs = 0;  
			}  
			if(fs > 0){  
				fs = fs / 1024;  
			}  
			return fs;  
		},

    
    //这里是压缩图片的，代码可以重构精简下，先去做其他需求
    compressImg: function(url){
    	var image = new Image(),
    	    width ,
    	    height ,
    	    thumbW ,
    	    thumbH ,
    	    multiple ,//长宽压缩比例,
    	    quality = 0.75,//质量压缩
    	    dataUrl,
    	    base64,
    	    size,
            canvas = document.createElement('canvas'),
            context = canvas.getContext('2d');
        canvas.style.display = "none";

        width = image.width;
        height = image.height;
        thumbW = width;
        thumbH = height;

        //若宽大于高，则互换width和height方便进行计算,thumbW、thumbH不变，
        //保留文件的真实处理
        //保证width/height <= 1
        if(thumbH < thumbW){
        	height = thumbW;
        	width = thumbH;
        }
        //scale压缩比例
        var scale = width/height;
  //最终压缩生成的文件最小的大小，单位(KB)
        var outputFileMaxLength = 60,
            inSampleSize;

        if(scale > 0.5625 && scale <= 1){//介于方图和9/16
         

             //分级计算压缩后的大小和图片尺寸
             if(height < 1664){
               outputFileMaxLength = (thumbW*thumbH) / Math.pow(1664,2)*150;
               //最小不能小于60K
               outputFileMaxLength = Math.max(60,outputFileMaxLength);
            }else if(height >= 1664&&height<4990){
            	//长宽均减半
            	thumbW = width/2;
            	thumbH = height/2;
            	inSampleSize = 2;
            	outputFileMaxLength = (thumbW*thumbH) / Math.pow(2495,2)*300;
            	outputFileMaxLength = Math.max(60,outputFileMaxLength);
            }else if(height >= 4990 && height < 10240){
            	//长宽均为原图的1/4
            	thumbW = width/4;
            	thumbH = height/4;
            	inSampleSize = 4;
                outputFileMaxLenght = (thumbW * thumbH) / Math.pow(2560, 2) * 300;
                outputFileMaxLenght = Math.max(100, outputFileMaxLenght);
            }else{
            	//超过10240的图 根据高为1280来计算压缩
            	multiple = height / 1280 == 0? 1 : height / 1280;
                thumbW = width / multiple;
                thumbH = height / multiple;
                inSampleSize = multiple;
                outputFileMaxLenght = (thumbW * thumbH) / Math.pow(2560, 2) * 300;
                outputFileMaxLenght = Math.max(100, outputFileMaxLenght);            	  
            }

        }else if( scale > 0.5 && scale <= 0.5625){//介于9/16和1/2，小长图
                multiple = height/1280 == 0 ? 1: height / 1280;
                thumbW = width / multiple;
                thumbH = height / multiple;
                inSampleSize = multiple;
                outputFileMaxLenght = (thumbW * thumbH) / (1440.0 * 2560.0) * 300;
                outputFileMaxLenght = Math.max(100, outputFileMaxLenght);                

        }else{//大于1/2的图，即长图或者宽图
        	//根据高为1280来计算压缩比例
        	multiple = Math.ceil(width/1280.0);
        	thumbW = width / multiple;
        	thumbH = height / multiple;
        	inSampleSize = multiple;
        	outputFileMaxLenght = 500;
        }
        //尺寸压缩，让对加载后的图片进行操作
        image.onload = function(){
          context.clearRect(0,0,canvas.width,canvas.height),
          canvas.width = image.width;
          canvas.height = image.height;
          context.drawImage(image,0,0,thumbW,thumbH);
         dataUrl = canvas.toDataURL("image/jpg",1);
        base64 = dataUrl.replace(/^data:image\/(png|jpg);base64,/, "");
        }
        image.src = url;
        size = b64toBlob(base64,"image/jpg").size;
        while(size > 200 * 1024){
          context.clearRect(0,0,canvas.width,canvas.height),        	
            thumbH = thumbH / 1.1;
            thumbW = thumbW / 1.1;
           quality -= 0.06;
           canvas.width = image.width;
           canvas.height = image.height;
           context.drawImage(image,0,0,thumbW,thumbH);
          dataUrl = canvas.toDataURL("image/jpg",quality);
          base64 = dataUrl.replace(/^data:image\/(png|jpg);base64,/, "");
          size = b64toBlob(base64,"image/jpg").size;
        }
         image.src = dataUrl;
         return dataUrl;
//         
//这个函数是吧Base64编码形式转换成Blob对象
 function b64toBlob(b64Data, contentType, sliceSize) {
    contentType = contentType || '';
    sliceSize = sliceSize || 512;

    var byteCharacters = atob(b64Data);
    var byteArrays = [];

    for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
        var slice = byteCharacters.slice(offset, offset + sliceSize);

        var byteNumbers = new Array(slice.length);
        for (var i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }

        var byteArray = new Uint8Array(byteNumbers);

        byteArrays.push(byteArray);
    }

    var blob = new Blob(byteArrays, {type: contentType});
    return blob;
}		

    },


	}
})