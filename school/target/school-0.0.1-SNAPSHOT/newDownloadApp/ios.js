window.onload = function () {

	var downloadUrl = location.search.split('?')[1],
			btn = document.getElementById('download');
			
	console.log(downloadUrl);

	btn.onclick = function () {
		btn.src='images/7.png';
		window.open('itms-services://?action=download-manifest&url=' + downloadUrl);
//		location.href = 'itms-services://?action=download-manifest&url=' + downloadUrl;
	}
};