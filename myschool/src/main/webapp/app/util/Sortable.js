/**
* @class School.util.Sortable
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 用来对中文排序的，项目里暂时没用到
*/

//重写了Ext对中文的排序方法
Ext.define("School.util.Sortable", {
	statics: {
		overrideSort: function() {
			//设置中文排序
	    	Ext.data.Store.prototype.createComparator = function(sorters) {  
	            return function(r1, r2){  
	                var s = sorters[0], f=s.property;  
	                var v1 = r1.data[f], v2 = r2.data[f];  
	                  
	                var result = 0;  
	                if(typeof(v1) == "string"){  
	                    result = v1.localeCompare(v2);  
	                    if(s.direction == 'DESC'){  
	                        result *=-1;  
	                    }  
	                } else {  
	                    result =sorters[0].sort(r1, r2);  
	                }  
	                  
	                var length = sorters.length;  
	                  
	                for(var i = 1; i<length; i ++){  
	                    s = sorters[i];  
	                    f = s.property;  
	                    v1 = r1.data[f];  
	                    v2 = r2.data[f];  
	                    if(typeof(v1) == "string"){  
	                        result = result || v1.localeCompare(v2);  
	                        if(s.direction == 'DESC'){  
	                            result *=-1;  
	                        }  
	                    } else {  
	                        result = result || s.sort.call(this, r1, r2);  
	                    }  
	                }  
	                return result;  
	            };  
	        };  

		}
	}
});