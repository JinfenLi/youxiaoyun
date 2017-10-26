<!doctype html>
<html>
	<head>
		<title>Chart.js柱形图DEMO演示</title>
		<script src="../Chart.js"></script>
		<meta name = "viewport" content = "initial-scale = 1, user-scalable = no">
		<meta charset="UTF-8">
		<style>
			canvas{
			}
		</style>
	</head>
	<body>
		<canvas id="canvas" height="450" width="600"></canvas>

<div style="text-align:center;clear:both;">
<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
<script src="/follow.js" type="text/javascript"></script>
</div>
	<script>

		var barChartData = {
			labels : [
				"name"
				 <#list scoreInfos as scoreInfo>
				 ,"${scoreInfo.studentName}"
 				 </#list>
			],
			datasets : [
				{
					data : [
						0
						 <#list scoreInfos as scoreInfo>
			    			,${scoreInfo.score}
	 					 </#list>
					]
				}
			]
			
		}

	var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Bar(barChartData);
	
	</script>
	</body>
</html>
