$(function() {
	var parent = window.parent, 
		Ext = parent.Ext,
		classId = Ext.ComponentQuery.query("myclass")[0].down("#myclasses").getValue();
		// console.log(classId);
	//调用getCurricula函数，加载课程表
	getCurricula(classId);	
});

//获取课表
function getCurricula(classId) {
		$.ajax({
			type : 'post',
			url : "curricula/getCurricula.action",
			data: {
				clazzId: classId
			},
			success: function (response, status, xhr) {
				response = JSON.parse(response);
				console.log(response.success);
				if(!response.success) {
					parent.School.util.Util.showErrorMsg("获取数据失败！");
				}



				console.log(response);
				var courses = response.result,
					length = courses.length;

				for(var i = 0 ; i < length; i++) {
					var selector = ".section" + courses[i].section + " .week" + courses[i].week;
					$(selector).html(courses[i].name);
				}

				//显示备注信息
				$(".comment").html(response.comment);
			},
			error: function () {
				parent.School.util.Util.showErrorMsg("连接服务器失败！");
			}
		}); 
	}