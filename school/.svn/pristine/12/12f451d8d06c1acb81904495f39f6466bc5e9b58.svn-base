//window.onload = function() {
//
//  var annularNode = document.getElementById('annular'),
//      analyseNode = document.getElementById('analyse'),
//      baseNode = document.getElementById('base'),
//      columnNode = document.getElementById('column'),
//      myAnnular = echarts.init(annularNode),
//      myAnalyse = echarts.init(analyseNode),
//      myDistribution = echarts.init(baseNode),
//      myColumn = echarts.init(columnNode);
//
//
//  var hostAddress = 'http://121.201.16.40:8080/school';
//
//
//
//  //获取 URL 中的参数
//  var params = {}, reg = /([^\=\?\&]+)\=([^&]+)/;
//  var url = window.location.search;
//  while(reg.test(url)){
//    url = url.replace(reg, function(input, $1, $2){
//      params[$1] = $2;
//      return ""
//    });
//  }
//
//  function getMin(arr) {
//    return Math.min.apply(Math,arr);    
//  }
//
//  function remove(thisNode) {
//    thisNode.parentNode.removeChild(thisNode);
//  }
//
//  var num = 10, yData=[];
//  while(num--){
//    yData.push({
//      textStyle: {
//        fontSize: 24
//      }
//    });
//  }
//
//  /*
//  Ajax方法解释
//  第2个参数就是请求的 Action
//  第3个参数就是传给后台的参数，那些字段名我都是根据你文档的写的，如果报错的话，注意字段名一不一样！！！
//  */
//
//  //图一， 
//   Ajax('get', hostAddress+'/score/getFanDiagramByExamId.action', {examId: params.examId, studentId: params.studentId}, function(data){
//    if(data.success) {
//      var renk, top, low, average, scoreText, scored;
//      if(data.score) {
//          rank = data.score.rank ? '排名: 第 ' + data.score.rank + ' 名' + '\n' + '\n' : '',
//          top = data.score.highest ? '最高分: ' + data.score.highest + '\n' + '\n' : '',
//          low = data.score.lowest ? '最低分: ' + data.score.lowest + '\n' + '\n' : '',
//          average = data.score.average ? '平均分: ' + data.score.average.toFixed(2)  : '',
//          scoreText = rank + top + low + average;
//          scored = data.score.value ? data.score.value : 0;
//      }    
//      var annularOption = {
//        title : {
//          text: data.text,
//          subtext: scoreText,
//          subtextStyle: {
//            color: 'black',
//            fontSize: 32
//          },         
//          itemGap: 30,
//          left: '2%',
//          top: '8%',
//          textStyle: {
//            fontSize: 40,  
//          }
//        },
//        legend: {
//          orient: 'vertical',
//          right: '8%',
//          y: 'bottom',
//          textStyle: {
//            fontSize: 36
//          },
//          data: ['得分'],
//        },
//        series : [
//          {
//            name: '成绩',
//            type: 'pie',
//            radius: ['50%', '70%'],
//            center: ['50%', '55%'],
//            animation: false,
//            silent: true,
//            data:[
//              {
//                value: scored ? scored : 0, 
//                name: '得分',
//                itemStyle: {
//                  normal: {
//                    color: 'rgb(234, 115, 33)' 
//                  }
//                },
//                label: {
//                  normal: {
//                    show: true,
//                    textStyle: {
//                      fontSize: 36,
//                    },
//                  } 
//                }     
//                  },
//              {
//                value: (scored && scored < 100) ? 100-scored : 0, 
//                name: scored ? scored + ' 分' : '无数据',
//                itemStyle: {
//                  normal: {
//                    color: 'rgb(234, 183, 54)' 
//                  }
//                },
//                label: {
//                  normal: {
//                    show: true,
//                    textStyle: {
//                      fontSize: 64,
//                      color: 'red',
//                    },
//                    position: 'center'
//                  } 
//                } 
//              }
//            ],
//            itemStyle: {
//              emphasis: {
//                shadowBlur: 10,
//                shadowOffsetX: 0,
//                shadowColor: 'rgba(0, 0, 0, 0.5)'
//              }
//            }
//          }
//        ],
//      };
//      myAnnular.setOption(annularOption);     
//    } else {
//      remove(annularNode);
//    }
//
//  }, function(error){
//      console.log(error);
//  });
//
//  //图二
// Ajax('get', hostAddress+'/score/getScoreTrender.action', {examId: params.examId, clazzId: params.clazzId, termId:params.termId, studentId: params.studentId}, function(data){
//    if(data.success)  {
//      var xData = [], serie = [], color = ['blue', 'green', 'red'], minNum = 100, position = [['-20', '-30'], ['0', '10'], ['10', '-20']];
//      var len, more, scoreLen, legendData = [];
//      nameLen = data.name ? data.name.length : 0;
//      scoreLen = data.score ? data.score.length : 0;
//      len = nameLen > scoreLen ? scoreLen : nameLen;
//      more = nameLen > 3 ? true : false;
//      if(data.name && data.score){
//        for(var i=0; i < nameLen; i++){
//          xData.push({            
//             value:  (function (str) {
//                  return more ? str.split("").join("\n") : str;
//                })(data.name[i]),
//            textStyle: {
//              fontSize: 26
//              }
//          })
//        }       
//        for(var i=0; i < scoreLen; i++) {
//          if(data.score[i].category && data.score[i].value) {
//            serie.push({
//              name:data.score[i].category,
//              type:'line',
//              data:data.score[i].value,
//              label: {
//                normal: {
//                  show: true,
//                  position: position[i],
//                  textStyle: {
//                    fontSize: 22  
//                  }
//               }
//              },
//              lineStyle: {normal:{
//                width: 4,
//                color: color[i],
//              }}
//            });
//            legendData.push({
//              name: data.score[i].category,
//              //icon: 'arrow',
//              textStyle: {
//                color: color[i]
//              }
//            })
//            minNum = getMin(data.score[i].value) < minNum ? getMin(data.score[i].value) : minNum;
//
//          }
//        }
//        
//      }
//
//
//      analyseOption = {
//        title: {
//          text: '成绩趋势对比图',
//          subtext: (data.score && data.name)? null : '暂无数据',
//          subtextStyle: {
//            color: 'black',
//            fontSize: 32
//          },         
//          itemGap: 240,
//          x:'center',
//          textStyle: {
//            fontSize: 40  
//          }
//        },
//        tooltip: {
//            trigger: 'axis',
//            textStyle: {
//            fontSize: 42  
//          }
//        },
//        legend: {
//          data:legendData,
//          top: '10%',
//          textStyle: {
//            fontSize: 28,
//            color: 'red'  
//          }
//        },
//        grid: {
//            left: '6%',
//            right: '10%',
//            top: '30%',
//            containLabel: true
//        },
//        xAxis: {
//            type: 'category',
//            boundaryGap: false,
//            data: xData,
//        },
//        yAxis: {
//            type: 'value',
//            name:'成绩趋势',
//            nameTextStyle: {
//              fontSize: 32  
//            },
//            nameGap: 40,
//            max: 100,
//            min: minNum,
//            data: yData
//        },
//        series: serie
//      };
//      myAnalyse.setOption(analyseOption);    
//    } else {
//      remove(analyseNode);  
//    }
//  }, function(error){
//      console.log(error);
//  });
//
//  //图三
//Ajax('get', hostAddress+'/score/getABCD.action', {clazzId: params.clazzId, examId:params.examId}, function(data){
//    if(data.success)  {
//      distributionOption = {
//        title : {
//          text: '班级考试情况',
//          subtext: data.data ? null : '暂无数据',
//          subtextStyle: {
//            color: 'black',
//            fontSize: 32
//          }, 
//          itemGap: 20,
//          x:'center',
//          textStyle: {
//            fontSize: 40  
//          }
//        },
//        tooltip : {
//          trigger: 'item',
//          formatter: "{a} <br/>{b} : {c} ({d}%)",
//          formatter: function(params) {
//            var name;
//            switch(params.name) {
//              case 'A 等级': name = 'A>=80'; break;
//              case 'B 等级': name = '70<=B<80'; break;
//              case 'C 等级': name = '60<=C<70'; break;
//              case 'D 等级': name = 'D<60'; break;
//            }
//            return params.seriesName + ' <br/>' + name + ' : ' + params.value + ' 人 ' + '(' + params.percent + '%)'
//          },
//          textStyle: {
//            fontSize: 42  
//          }
//        },
//        legend: {
//          orient: 'vertical',
//          left: '4%',
//          top: '10%',
//          data:['A 等级', 'B 等级', 'C 等级', 'D 等级'],
//          // formatter: function (name) {
//
//          //     return echarts.format.truncateText(name, 40);
//          // },
//          // tooltip: {
//          //   show: true,
//          //     data: ['>= 80']
//          // }
//        },
//        series : [
//        {
//          name:'成绩分布',
//          type:'pie',
//          center: ['50%', '55%'],
//          radius: ['0', '70%'],
//          data:[
//              {
//                value: data.data && data.data[0] ? data.data[0].number : 0, 
//                name: (data.data && data.data[0] ? data.data[0].name : 'A') + ' 等级', 
//                itemStyle: {
//                  normal: {
//                    color: '#738ffe'
//                  }
//              }}, {
//                value: data.data && data.data[1] ? data.data[1].number : 0, 
//                name: (data.data && data.data[1] ? data.data[1].name : 'B') + ' 等级',
//                itemStyle: {
//                  normal: {
//                    color: 'rgb(105, 178, 115)' 
//                  }
//              }}, {
//                value: data.data && data.data[2] ? data.data[2].number : 0, 
//                name: (data.data && data.data[2]  ? data.data[2].name : 'C') + ' 等级',
//                itemStyle: {
//                  normal: {
//                    color: 'rgb(225, 96, 67)'
//                  }
//              }}, {
//                value: data.data && data.data[3] ? data.data[3].number : 0, 
//                name: (data.data && data.data[3] ? data.data[3].name : 'D') + ' 等级', 
//                itemStyle: {
//                  normal: {
//                    color: 'rgb(250, 191, 20)' 
//                  }
//              }},
//          ].sort(function (a, b) { return a.value - b.value}),
//        }
//        ],
//        textStyle: {
//        fontSize: 36  
//        }
//      };
//
//      myDistribution.setOption(distributionOption)          
//    } else {
//      remove(baseNode);  
//    }
//  }, function(error){
//      console.log(error);
//  });
//
//  //图四
// Ajax('get', hostAddress+'/score/getExcellentRate.action', {examId: params.examId,termId:params.termId,clazzId: params.clazzId}, function(data){
//    if(data.success) {
//      var xData = [], seriesData=[], nameLen, valueLen, more, len;
//      nameLen = data.name ? data.name.length : 0;
//      valueLen = data.value ? data.value.length : 0;
//      len = nameLen > valueLen ? valueLen : nameLen;
//      more = nameLen > 3 ? true : false;
//      for(var i=0 ; i<len; i++) {
//        xData.push({
//          value:  (function (str) {
//                return more ? str.split("").join("\n") : str;
//              })(data.name[i]),
//          textStyle: {
//            fontSize: 24,
//          },
//        });
//        if(data.value) {
//          seriesData[i] = data.value[i];
//        }
//      }
//      columnOption = {
//        color: ['#3398DB'],
//          title: {
//            text: '优分率情况',
//            subtext: (data.value && data.name) ? null : '暂无数据',
//            subtextStyle: {
//              color: 'black',
//              fontSize: 32
//            }, 
//            itemGap: 20,
//            x:'center',
//            textStyle: {
//              fontSize: 40,
//            }
//          },
//        tooltip : {
//          trigger: 'axis',
//          axisPointer : {      
//            type : 'shadow'        
//          },
//          textStyle: {
//            fontSize: 42  
//          }
//        },
//        grid: {
//          left: '6%',
//          right: '4%',
//          bottom: '3%',
//          top: '30%',
//          containLabel: true
//        },
//        xAxis : [{
//          data: data.value ? xData : null,
//          axisTick: {
//            alignWithLabel: true
//          },
//        }],
//        yAxis : [{
//          type : 'value',
//          name:'优分率: %',
//          nameTextStyle: {
//              fontSize: 32  
//          },
//          nameGap: 40,
//          data: yData,
//        }],
//        series : [
//          {
//          name: '百分比',
//          type: 'bar',
//          barWidth: len > 7 ? 40 : 80,
//          data: seriesData,
//          label: {
//              normal: {
//                show: true,
//                position: 'top',
//             }
//            },
//          }
//        ],
//        textStyle: {
//          fontSize: 22
//        }
//      };
//      myColumn.setOption(columnOption);  
//    } else {
//      remove(columnNode); 
//    }
//  }, function(error){
//    console.log(error);
//  });
//  
//}
window.onload = function() {

	var annularNode = document.getElementById('annular'),
			analyseNode = document.getElementById('analyse'),
			baseNode = document.getElementById('base'),
			columnNode = document.getElementById('column'),
			myAnnular = echarts.init(annularNode),
			myAnalyse = echarts.init(analyseNode),
			myDistribution = echarts.init(baseNode),
			myColumn = echarts.init(columnNode);


	var hostAddress = 'http://121.201.16.40:8080/school';



	//获取 URL 中的参数
	var params = {}, reg = /([^\=\?\&]+)\=([^&]+)/;
	var url = window.location.search;
	while(reg.test(url)){
		url = url.replace(reg, function(input, $1, $2){
			params[$1] = $2;
			return ""
		});
	}

	function getMin(arr) {
		return Math.min.apply(Math,arr);    
	}

	function remove(thisNode) {
		thisNode.parentNode.removeChild(thisNode);
	}

	var num = 10, yData=[];
	while(num--){
		yData.push({
			textStyle: {
				fontSize: 24
			}
		});
	}

	/*
	Ajax方法解释
	第2个参数就是请求的 Action
	第3个参数就是传给后台的参数，那些字段名我都是根据你文档的写的，如果报错的话，注意字段名一不一样！！！
	*/

	//图一， 
	 Ajax('get', '../score/getFanDiagramByExamId.action', {examId: params.examId, studentId: params.studentId}, function(data){
		if(data.success) {
			var renk, top, low, average, scoreText, scored;
			if(data.score) {
					rank = data.score.rank ? '排名: 第 ' + data.score.rank + ' 名' + '\n' + '\n' : '',
					top = data.score.highest ? '最高分: ' + data.score.highest + '\n' + '\n' : '',
					low = data.score.lowest ? '最低分: ' + data.score.lowest + '\n' + '\n' : '',
					average = data.score.average ? '平均分: ' + data.score.average.toFixed(2)  : '',
					scoreText = rank + top + low + average;
					scored = data.score.value ? data.score.value : 0;
			}    
			var annularOption = {
				title : {
					text: data.text,
					subtext: scoreText,
					subtextStyle: {
						color: 'black',
						fontSize: 32
					},         
					itemGap: 30,
					left: '2%',
					top: '8%',
					textStyle: {
						fontSize: 40,  
					}
				},
				legend: {
					orient: 'vertical',
					right: '8%',
					y: 'bottom',
					textStyle: {
						fontSize: 36
					},
					data: ['得分'],
				},
				series : [
					{
						name: '成绩',
						type: 'pie',
						radius: ['50%', '70%'],
						center: ['50%', '55%'],
						animation: false,
						silent: true,
						data:[
							{
								value: scored ? scored : 0, 
								name: '得分',
								itemStyle: {
									normal: {
										color: 'rgb(234, 115, 33)' 
									}
								},
								label: {
									normal: {
										show: true,
										textStyle: {
											fontSize: 36,
										},
									} 
								}     
									},
							{
								value: (scored && scored < 100) ? 100-scored : 0, 
								name: scored ? scored + ' 分' : '无数据',
								itemStyle: {
									normal: {
										color: 'rgb(234, 183, 54)' 
									}
								},
								label: {
									normal: {
										show: true,
										textStyle: {
											fontSize: 64,
											color: 'red',
										},
										position: 'center'
									} 
								} 
							}
						],
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}
				],
			};
			myAnnular.setOption(annularOption);     
		} else {
			remove(annularNode);
		}

	}, function(error){
			console.log(error);
	});

	//图二
 Ajax('get', '../score/getScoreTrender.action', {examId: params.examId, clazzId: params.clazzId, termId:params.termId, studentId: params.studentId}, function(data){
		if(data.success)  {
			var xData = [], serie = [], color = ['blue', 'green', 'red'], minNum = 100, position = [['-20', '-30'], ['0', '10'], ['10', '-20']];
			var len, more, scoreLen, legendData = [];
			nameLen = data.name ? data.name.length : 0;
			scoreLen = data.score ? data.score.length : 0;
			len = nameLen > scoreLen ? scoreLen : nameLen;
			more = nameLen > 3 ? true : false;
			if(data.name && data.score){
				for(var i=0; i < nameLen; i++){
					xData.push({            
						 value:  (function (str) {
									return more ? str.split("").join("\n") : str;
								})(data.name[i]),
						textStyle: {
							fontSize: 26
							}
					})
				}       
				for(var i=0; i < scoreLen; i++) {
					if(data.score[i].category && data.score[i].value) {
						serie.push({
							name:data.score[i].category,
							type:'line',
							data:data.score[i].value,
							label: {
								normal: {
									show: true,
									position: position[i],
									textStyle: {
										fontSize: 22  
									}
							 }
							},
							lineStyle: {normal:{
								width: 4,
								color: color[i],
							}}
						});
						legendData.push({
							name: data.score[i].category,
							//icon: 'arrow',
							textStyle: {
								color: color[i]
							}
						})
						minNum = getMin(data.score[i].value) < minNum ? getMin(data.score[i].value) : minNum;

					}
				}
				
			}


			analyseOption = {
				title: {
					text: '成绩趋势对比图',
					subtext: (data.score && data.name)? null : '暂无数据',
					subtextStyle: {
						color: 'black',
						fontSize: 32
					},         
					itemGap: 240,
					x:'center',
					textStyle: {
						fontSize: 40  
					}
				},
				tooltip: {
						trigger: 'axis',
						textStyle: {
						fontSize: 42  
					}
				},
				legend: {
					data:legendData,
					top: '10%',
					textStyle: {
						fontSize: 28,
						color: 'red'  
					}
				},
				grid: {
						left: '6%',
						right: '10%',
						top: '30%',
						containLabel: true
				},
				xAxis: {
						type: 'category',
						boundaryGap: false,
						data: xData,
				},
				yAxis: {
						type: 'value',
						name:'成绩趋势',
						nameTextStyle: {
							fontSize: 32  
						},
						nameGap: 40,
						max: 100,
						min: minNum,
						data: yData
				},
				series: serie
			};
			myAnalyse.setOption(analyseOption);    
		} else {
			remove(analyseNode);  
		}
	}, function(error){
			console.log(error);
	});

	//图三
Ajax('get', '../score/getABCD.action', {clazzId: params.clazzId, examId:params.examId}, function(data){
		if(data.success)  {
			distributionOption = {
				title : {
					text: '班级考试情况',
					subtext: data.data ? null : '暂无数据',
					subtextStyle: {
						color: 'black',
						fontSize: 32
					}, 
					itemGap: 20,
					x:'center',
					textStyle: {
						fontSize: 40  
					}
				},
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)",
					formatter: function(params) {
						var name;
						switch(params.name) {
							case 'A 等级': name = 'A>=80'; break;
							case 'B 等级': name = '70<=B<80'; break;
							case 'C 等级': name = '60<=C<70'; break;
							case 'D 等级': name = 'D<60'; break;
						}
						// return params.seriesName + ' <br/>' + name + ' : ' + params.value + ' 人 ' + '(' + params.percent + '%)'
						// 2016.11.20 AymaxLi
						return params.seriesName + ' <br/>' + params.name + ' : ' + params.value + ' 人 ' + '(' + params.percent + '%)'
					},
					textStyle: {
						fontSize: 42  
					}
				},
				legend: {
					orient: 'vertical',
					left: '4%',
					top: '10%',
					data:['A 等级', 'B 等级', 'C 等级', 'D 等级'],
					// formatter: function (name) {

					//     return echarts.format.truncateText(name, 40);
					// },
					// tooltip: {
					//   show: true,
					//     data: ['>= 80']
					// }
				},
				series : [
				{
					name:'成绩分布',
					type:'pie',
					center: ['50%', '55%'],
					radius: ['0', '70%'],
					data:[
							{
								value: data.data && data.data[0] ? data.data[0].number : 0, 
								name: (data.data && data.data[0] ? data.data[0].name : 'A') + ' 等级', 
								itemStyle: {
									normal: {
										color: '#738ffe'
									}
							}}, {
								value: data.data && data.data[1] ? data.data[1].number : 0, 
								name: (data.data && data.data[1] ? data.data[1].name : 'B') + ' 等级',
								itemStyle: {
									normal: {
										color: 'rgb(105, 178, 115)' 
									}
							}}, {
								value: data.data && data.data[2] ? data.data[2].number : 0, 
								name: (data.data && data.data[2]  ? data.data[2].name : 'C') + ' 等级',
								itemStyle: {
									normal: {
										color: 'rgb(225, 96, 67)'
									}
							}}, {
								value: data.data && data.data[3] ? data.data[3].number : 0, 
								name: (data.data && data.data[3] ? data.data[3].name : 'D') + ' 等级', 
								itemStyle: {
									normal: {
										color: 'rgb(250, 191, 20)' 
									}
							}},
					].sort(function (a, b) { return a.value - b.value}),
				}
				],
				textStyle: {
				fontSize: 36  
				}
			};

			myDistribution.setOption(distributionOption)          
		} else {
			remove(baseNode);  
		}
	}, function(error){
			console.log(error);
	});

	//图四
 Ajax('get', '../score/getExcellentRate.action', {examId: params.examId,termId:params.termId,clazzId: params.clazzId}, function(data){
		if(data.success) {
			var xData = [], seriesData=[], nameLen, valueLen, more, len;
			nameLen = data.name ? data.name.length : 0;
			valueLen = data.value ? data.value.length : 0;
			len = nameLen > valueLen ? valueLen : nameLen;
			more = nameLen > 3 ? true : false;
			for(var i=0 ; i<len; i++) {
				xData.push({
					value:  (function (str) {
								return more ? str.split("").join("\n") : str;
							})(data.name[i]),
					textStyle: {
						fontSize: 24,
					},
				});
				if(data.value) {
					seriesData[i] = data.value[i];
				}
			}
			columnOption = {
				color: ['#3398DB'],
					title: {
						text: '优分率情况',
						subtext: (data.value && data.name) ? null : '暂无数据',
						subtextStyle: {
							color: 'black',
							fontSize: 32
						}, 
						itemGap: 20,
						x:'center',
						textStyle: {
							fontSize: 40,
						}
					},
				tooltip : {
					trigger: 'axis',
					axisPointer : {      
						type : 'shadow'        
					},
					textStyle: {
						fontSize: 42  
					}
				},
				grid: {
					left: '6%',
					right: '4%',
					bottom: '3%',
					top: '30%',
					containLabel: true
				},
				xAxis : [{
					data: data.value ? xData : null,
					axisTick: {
						alignWithLabel: true
					},
				}],
				yAxis : [{
					type : 'value',
					name:'优分率: %',
					nameTextStyle: {
							fontSize: 32  
					},
					nameGap: 40,
					data: yData,
					// 2016.11.20 AymaxLi add max
					max: 100
				}],
				series : [
					{
					name: '百分比',
					type: 'bar',
					barWidth: len > 7 ? 40 : 80,
					data: seriesData,
					label: {
							normal: {
								show: true,
								position: 'top',
						 }
						},
					}
				],
				textStyle: {
					fontSize: 22
				}
			};
			myColumn.setOption(columnOption);  
		} else {
			remove(columnNode); 
		}
	}, function(error){
		console.log(error);
	});
	
}
