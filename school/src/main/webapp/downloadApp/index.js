$(document).ready(function () {
	
	if (isWechat()) {
		$('#layer').css('display', 'block');
		$('html').css('overflow', 'hidden');
		$('body').css('overflow', 'hidden');
	}
	
	var downloadUrl;
	
	$('#submit').click(function () {
		
		var device = $("input[name='device']:checked").val(),
				schoolName = $.trim($("input[name='schoolName']").val());
		
		if (!device) {
			alert('请选择您手机的操作系统');
		} else if (!schoolName) {
			alert('请输入学校名称后再点击搜索');
		} else {
			$.ajax({
				url: '../version/findLikeSchoolName.action',
				//url: 'data.json',
				type: 'post',
				dataType: 'json',
				data: {
					"device": device,
					"schoolName": schoolName
				},
				success: function (data) {
					var version = data.version;
					if (version.length == 0) {
						$('#result').empty();
						$('#result').append('<p>没有查询到相关的学校</p>');
					} else {
						$('#submit').addClass('triangle');
						$('#result').empty();
						version.forEach(function (ele, index, arr) {
							var li = '<li onclick="saveURL(\'' + 
							ele.url + '\', \'' + ele.schoolName + '\')">' + 
							ele.schoolName + '</li>';
							$('#result').append(li);
						});
					}
				},
				error: function () {
					alert('服务器故障，请稍后再试！')
				}
			});
		}
		
	});
	
	$('#download').click(function () {
		var device = $("input[name='device']:checked").val();
		
		if (device == 2) {
			// if is ios
			iosDownloadURL();
			
		} else {
			downloadURL();
		}

	});
	
	$("input[name='device']").on('change', function () {
		window.scrollTo(0, 1000);
	});
	
});

function isWechat () {
	var ua = navigator.userAgent.toLowerCase();
	if (ua.match(/MicroMessenger/i) == "micromessenger") {
		return true;
	} else {
		return false;
	}
}

// li 点击后操作
function saveURL (url, schoolName) {
	
	downloadUrl = url;
	console.log(downloadUrl);
	$("input[name='schoolName']").val(schoolName);
	$('#submit').removeClass('triangle');
	$('#result').empty();
	var device = $("input[name='device']:checked").val(),
		version = '*您选择了';
	device == 1 ? version += "安卓版" : version += "IOS版";
	$('#version').html(version + schoolName);
}

//　点击下载
function downloadURL () {
	try {
		location.href = downloadUrl;
	} catch (e) {
		alert('请按步骤操作！')
	}
	
}

function iosDownloadURL () {
	
	try {	
		window.open('ios.html?' + downloadUrl);
		// location.href = downloadUrl;
	} catch (e) {
		alert('请按步骤操作！')
	}
	
}
