window.onload = function () {

	var downloadUrl = location.search.split('?')[1],
			btn = document.getElementById('download');
			
	console.log(downloadUrl);

	btn.onclick = function () {
		 //alert(navigator.userAgent);
		 if(navigator.userAgent.indexOf('Safari') != -1 && navigator.platform.indexOf('iPhone') != -1) {
			if(downloadUrl.indexOf('https')!=-1){
			location.href = 'itms-services://?action=download-manifest&url=' + downloadUrl;	
			}
			else if (downloadUrl.indexOf('http')!=-1){
				location.href = downloadUrl ;
			}
		} else {
			alert('请用iphone自带的Safari浏览器打开本页面进行下载！');
		}
	}
};