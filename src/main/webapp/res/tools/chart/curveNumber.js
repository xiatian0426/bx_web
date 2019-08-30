function initCurveNumSeries(serName, dataList, company) {
	return {
		name : serName,
		type : 'line',
		data : dataList,
		symbol : 'none',
		symbolSize : 1.2,
		itemStyle : {
			normal : {
				lineStyle : {
					type : "solid",
					width : 1.5
				}
			}
		}
	};
}
function setCurveNumberChart(divId, option) {
	var myChart = echarts.init(document.getElementById(divId));
	myChart.setOption(option, true);
}
// 生成图表，用户指定类型
function curveNumber(divId, chartName, chartData, company) {
	var option = initCurveNumber(chartName, chartData, company);
	setCurveNumberChart(divId, option);
}

// 曲线图数据初始化
function initCurveNumber(chartName, chartData, company) {
	var valueMax = 0.0;
	var valueMin = 1000000;
	// json 数据传入后 解析为对应的格式
	var xAxisData = new Array;
	var legendData = new Array;
	var seriesData = new Array;
	for ( var index in chartData) {
		// 获取单项的名称
		legendData.push(chartData[index].itemName);
		// 获取单项的数据
		if (chartData[index].itemData != null) {
			var itemData = chartData[index].itemData;
			var serData = new Array;
			for ( var item in itemData) {
				if (itemData[item].value != null) {
					var curValue = parseFloat(itemData[item].value);
					//获取最大值和最小值
					if (valueMax - curValue < 0) {
						valueMax = curValue;
					}
					if (valueMin - curValue > 0) {
						valueMin = curValue;
					}
					serData.push(itemData[item].value);
				}
			}
			seriesData.push(initCurveNumSeries(chartData[index].itemName, serData));
		}
	}
	// 获取时间轴
	var singleData = chartData[1].itemData;
	for ( var index in singleData) {
		if (singleData[index].name != null) {
			xAxisData.push(singleData[index].name);
		}
	}
	// 原数值上下浮动10%
	valueMax = valueMax + valueMax * 0.1;
	valueMin = valueMin - valueMin * 0.1;
	// 获取最大值,最小值 calculateMaxMin方法来至于 CalculateMaxMin.js 注意加载的先后顺序 此处为方便通用,所以提出来, 可以这个js文化部合并
	var result = calculateMaxMin(valueMax, valueMin, 6).split("_");
	valueMax = result[0];
	valueMin = result[1];
	var option = {
		title : {
			text : chartName,
			textStyle : {
				fontSize : 22,
				color : "rgb(51, 51, 51)",
				fontFamily : "微软雅黑"
			},
			//subtext : "   单位：" + company,
			subtextStyle : {
				fontSize : 12,
				color : "rgb(51, 51, 51)",
				fontFamily : "微软雅黑"
			// align:"left"
			},
			x: 'center'
		},
		tooltip : {
			trigger : 'axis',
			formatter : function(a) {
				var relVal = "";
				relVal = a[0].name;
				var midVal = "";
				for (var i = 0, l = a.length; i < l; i++) {
					if (isNaN(a[i].value)) {
						relVal += '<br/>' + a[i].seriesName + ': -';
					} else {
						relVal += '<br/>' + a[i].seriesName + ': ' + a[i].value;
					}
				}
				return relVal;
			}
		},
		legend : {
			data : legendData,
			x : "center",
			y : "bottom",
			itemGap : 12,
			textStyle : {
				fontSize : 12,
				fontStyle : "normal",
				color : "auto"
			}
		},
		toolbox : {
			show : true,
			feature : {
				saveAsImage : {
					show : true
				}
			}
		},

		calculable : true,
		xAxis : [ {
			type : 'category',
			data : xAxisData,
			axisTick : {
				show : true,
				inside : false,
				interval : 8,
				length : 6
			},
			axisLabel : {
				show : true,
				textStyle : {
					align : "center"
				}
			},
			axisLine : {
				show : true,
				onZero : false,
				lineStyle : {
					type : 'solid'
				}
			}
		} ],
		yAxis : [ {
			type : 'value',
			min : valueMin,
			max : valueMax,
			splitNumber : 5
		// 5:表示5等分 显示6个点
		} ],
		grid : {
			width : 600,
			y2 : 80
		},
		series : seriesData
	};
	return option;
}