// JavaScript Document
// 基于准备好的dom，初始化echarts图表
// --- 折线 ---
var myChart = echarts.init(document.getElementById('lineDay'));
myChart.setOption({
	//title : {
//		text: '图表',
//		x:"center",
//		textStyle: {
//           fontSize: 22,
//           color: "rgb(51, 51, 51)",
//		   fontFamily:"微软雅黑",
//       }
//	},
	tooltip : {
		trigger: 'item',
		formatter: "{a} : {c}<br/>{b}"
	},
	legend: {
       data: ["WTI", "布伦特"],
       y: "bottom",
       x: "center",
       orient: "horizontal",
       itemGap: 50,
       textStyle: {
           color: "rgb(102, 102, 102)",
           fontSize: 12,
           fontWeight: "bold"
		}
	},
	toolbox: {
		show : true,
		feature : {
			//mark : {show: true},
			//dataView : {show: true, readOnly: false},
			magicType : {show: true, type: ['line', 'bar']},
			restore : {show: true},
			saveAsImage : {show: true}
		}
	},
	calculable: false, //是否启用拖拽重计算
	xAxis: [
       {
           type: "category",
           data: ["2015-01", "2015-02", "2015-03", "2015-04", "2015-05"],
           axisTick: {
               show: true,
               inside: false,
               length: 4
           },
           boundaryGap: true,
           position: "bottom",
           axisLabel: {
               textStyle: {
                   color: "rgb(119, 112, 110)"
               }
           },
           axisLine: {
               show: true,
               lineStyle: {
                   color: "rgb(116, 184, 233)",
                   width: 1,
                   type: "solid"
               }
           }
       }
	],
	yAxis: [
       {
           type: "value",
		   name: "单位/万吨",
           nameTextStyle: {
               color: "rgb(71, 138, 189)"
           },
		   min: 0,
           max: 50
       }
	],
	series : [
		{
			name:'WTI',
			type:'line',
			data:[4, 1, 23, 9, 2],
			symbol: "circle",
           	itemStyle: {
               	normal: {
                   	lineStyle: {
                       	color: "rgb(220, 57, 18)"
                   	}
               	}
           	}
			//markPoint : {
//				data : [
//					{type : 'max', name: '最大值'},
//					{type : 'min', name: '最小值'}
//				]
//			},
//			markLine : {
//				data : [
//					{type : 'average', name: '平均值'}
//				]
//			}
		},
		{
			name:'布伦特',
			type:'line',
			data:[36, 38, 22, 9, 29],
           	itemStyle: {
               	normal: {
                   	lineStyle: {
                       	color: "rgb(109, 181, 232)"
                   	}
               	}
           	},
			symbol: "emptyCircle"
			//markPoint : {
//				data : [
//					{name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
//				]
//			},
		}
	],
	//grid: {
//       x: 50,
//       y: 35,
//       x2: 30,
//       y2: 50
//   },
	color: ["rgb(220, 57, 18)", "rgb(109, 181, 232)"]
	
});



// --- 折线 ---
var myChart2 = echarts.init(document.getElementById('lineWeek'));
myChart2.setOption({
	//title : {
//		text: '图表',
//		x:"center",
//		textStyle: {
//           fontSize: 22,
//           color: "rgb(51, 51, 51)",
//		   fontFamily:"微软雅黑",
//       }
//	},
	tooltip : {
		trigger: 'item',
		formatter: "{a} : {c}<br/>{b}"
	},
	legend: {
       data: ["WTI", "布伦特"],
       y: "bottom",
       x: "center",
       orient: "horizontal",
       itemGap: 50,
       textStyle: {
           color: "rgb(102, 102, 102)",
           fontSize: 12,
           fontWeight: "bold"
		}
	},
	toolbox: {
		show : true,
		feature : {
			//mark : {show: true},
			//dataView : {show: true, readOnly: false},
			magicType : {show: true, type: ['line', 'bar']},
			restore : {show: true},
			saveAsImage : {show: true}
		}
	},
	calculable: false, //是否启用拖拽重计算
	xAxis: [
       {
           type: "category",
           data: ["2015-01", "2015-02", "2015-03", "2015-04", "2015-05"],
           axisTick: {
               show: true,
               inside: false,
               length: 4
           },
           boundaryGap: true,
           position: "bottom",
           axisLabel: {
               textStyle: {
                   color: "rgb(119, 112, 110)"
               }
           },
           axisLine: {
               show: true,
               lineStyle: {
                   color: "rgb(116, 184, 233)",
                   width: 1,
                   type: "solid"
               }
           }
       }
	],
	yAxis: [
       {
           type: "value",
		   name: "单位/万吨",
           nameTextStyle: {
               color: "rgb(71, 138, 189)"
           },
		   min: 0,
           max: 50
       }
	],
	series : [
		{
			name:'WTI',
			type:'line',
			data:[14, 11, 23, 19, 12],
			symbol: "circle",
           	itemStyle: {
               	normal: {
                   	lineStyle: {
                       	color: "rgb(220, 57, 18)"
                   	}
               	}
           	}
			//markPoint : {
//				data : [
//					{type : 'max', name: '最大值'},
//					{type : 'min', name: '最小值'}
//				]
//			},
//			markLine : {
//				data : [
//					{type : 'average', name: '平均值'}
//				]
//			}
		},
		{
			name:'布伦特',
			type:'line',
			data:[36, 38, 22, 22, 29],
           	itemStyle: {
               	normal: {
                   	lineStyle: {
                       	color: "rgb(109, 181, 232)"
                   	}
               	}
           	},
			symbol: "emptyCircle"
			//markPoint : {
//				data : [
//					{name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
//				]
//			},
		}
	],
	//grid: {
//       x: 50,
//       y: 35,
//       x2: 30,
//       y2: 50
//   },
	color: ["rgb(220, 57, 18)", "rgb(109, 181, 232)"]
	
});



// --- 折线 ---
var myChart3 = echarts.init(document.getElementById('lineMonth'));
myChart3.setOption({
	//title : {
//		text: '图表',
//		x:"center",
//		textStyle: {
//           fontSize: 22,
//           color: "rgb(51, 51, 51)",
//		   fontFamily:"微软雅黑",
//       }
//	},
	tooltip : {
		trigger: 'item',
		formatter: "{a} : {c}<br/>{b}"
	},
	legend: {
       data: ["WTI", "布伦特"],
       y: "bottom",
       x: "center",
       orient: "horizontal",
       itemGap: 50,
       textStyle: {
           color: "rgb(102, 102, 102)",
           fontSize: 12,
           fontWeight: "bold"
		}
	},
	toolbox: {
		show : true,
		feature : {
			//mark : {show: true},
			//dataView : {show: true, readOnly: false},
			magicType : {show: true, type: ['line', 'bar']},
			restore : {show: true},
			saveAsImage : {show: true}
		}
	},
	calculable: false, //是否启用拖拽重计算
	xAxis: [
       {
           type: "category",
           data: ["2015-01", "2015-02", "2015-03", "2015-04", "2015-05"],
           axisTick: {
               show: true,
               inside: false,
               length: 4
           },
           boundaryGap: true,
           position: "bottom",
           axisLabel: {
               textStyle: {
                   color: "rgb(119, 112, 110)"
               }
           },
           axisLine: {
               show: true,
               lineStyle: {
                   color: "rgb(116, 184, 233)",
                   width: 1,
                   type: "solid"
               }
           }
       }
	],
	yAxis: [
       {
           type: "value",
		   name: "单位/万吨",
           nameTextStyle: {
               color: "rgb(71, 138, 189)"
           },
		   min: 0,
           max: 50
       }
	],
	series : [
		{
			name:'WTI',
			type:'line',
			data:[41, 11, 23, 15, 21],
			symbol: "circle",
           	itemStyle: {
               	normal: {
                   	lineStyle: {
                       	color: "rgb(220, 57, 18)"
                   	}
               	}
           	}
			//markPoint : {
//				data : [
//					{type : 'max', name: '最大值'},
//					{type : 'min', name: '最小值'}
//				]
//			},
//			markLine : {
//				data : [
//					{type : 'average', name: '平均值'}
//				]
//			}
		},
		{
			name:'布伦特',
			type:'line',
			data:[36, 38, 22, 19, 29],
           	itemStyle: {
               	normal: {
                   	lineStyle: {
                       	color: "rgb(109, 181, 232)"
                   	}
               	}
           	},
			symbol: "emptyCircle"
			//markPoint : {
//				data : [
//					{name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
//				]
//			},
		}
	],
	//grid: {
//       x: 50,
//       y: 35,
//       x2: 30,
//       y2: 50
//   },
	color: ["rgb(220, 57, 18)", "rgb(109, 181, 232)"]
	
});




