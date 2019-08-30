//曲线图数据初始化
function initCurvePercent(chartName, chartData, company){
	var valueMax = 0.0;
	var valueMin = 1000000.0;
	//json 数据传入后 解析为对应的格式  
	var xAxisData = new Array;
	var legendData = new Array;
	var seriesData = new Array;
	for(var index in chartData){
		//获取单项的名称
		legendData.push(chartData[index].itemName);
		//获取单项的数据
		if(chartData[index].itemData != null){
			var itemData = chartData[index].itemData;
			var serData = new Array;
			for(var item in itemData){
				if(itemData[item].value != null){
					//如果最大值小于当前值, 给最大值为当前值
					var curValue = parseFloat(itemData[item].value);
					if(valueMax - curValue < 0){
						valueMax = curValue;
					}
					if(valueMin - curValue > 0){
						valueMin = curValue;
					}
					serData.push(itemData[item].value);
				}
			}
			seriesData.push(initCurveSeries(chartData[index].itemName,serData));
		}
	}
	//获取时间轴
	var singleData = chartData[0].itemData;
	for(var index in singleData){
		if(singleData[index].name != null){
			xAxisData.push(singleData[index].name);
		}
	}
	//获取最大值,最小值
	var result = calculateMaxMin(valueMax, valueMin, 8).split("_");
	valueMax = result[0];
	valueMin = result[1];
	var option = {
	    title : {
	    	text: chartName,
	    	x:"center",
			textStyle: {
	           fontSize: 22,
	           color: "rgb(51, 51, 51)",
			   fontFamily:"微软雅黑"
			},
			subtext:"\t \t \t \t \t \t \t \t \t \t\t \t \t \t \t\t \t \t \t \t\t \t \t \t \t\t \t \t \t \t\t \t \t \t \t\t \t \t \t \t\t \t \t \t \t \t \t \t \t \t\t \t \t \t 单位："+company,
		    subtextStyle:{
		  	   fontSize: 12,
		         color: "rgb(51, 51, 51)",
				 fontFamily:"微软雅黑"
				  // align：left,	   
		    }
	    },
	    tooltip : {
			trigger: 'axis',
			formatter: function(a){
				var relVal = "";
				relVal = a[0].name;
				for (var i = 0, l = a.length; i < l; i++) {
					if(isNaN(a[i].value)){
						relVal += '<br/>' + a[i].seriesName + ':'+"0%";
					}else{
						if(a[i].value == 0){
							relVal += '<br/>' + a[i].seriesName + ':' + a[i].value;
						}else{
							relVal += '<br/>' + a[i].seriesName + ':' + a[i].value+"%";
						}
					}
	            }
				return relVal;
			}
		},
	    legend: {
	        data: legendData,
	        x : 'center',
	        y : 'bottom',
	        itemGap:12,
			textStyle:{
				fontSize:12,
				fontStyle:"normal",
				color:"auto"
			}
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : xAxisData,
	            boundaryGap: true,
	            axisTick: {
	                show: true,
	                inside: false,
	                interval: 8,
	                length: 6	 			    
	 			},
	 			axisLabel: {
	                show: true,
	                textStyle: {
	                    align: "center"
	                }
	         	},
	         	axisLine:{
	         		show: true,
	         		onZero: false,
	         		lineStyle:{
	         			type: 'solid'
	         		}
	         	}
	        }
	    ],
	    yAxis : [
     		{
     			type : 'value',
     			axisLabel : {
                     formatter: function(value){return  Math.floor(value)+"%";} 
                 },
     			min:valueMin,
     			max:valueMax,
     			splitNumber: 7
     		}
     	],
     	grid: {
            width: 455
        },
     	series : seriesData
	};
    return option;              
}
function initCurveSeries(serName, dataList){
	return {
		name:serName,
        type:'line',
        symbol:'none',
        data:dataList,
        symbolSize: 1.2,
        itemStyle: {
            normal: {
                lineStyle: {
                	 type: "solid",
                     width: 1.5
                }
            }
        }
	};
}
function setCurveChart(divId, option){
	var myChart = echarts.init(document.getElementById(divId));
	 myChart.setOption(option, true);
}
//生成图表，用户指定类型
function curvePercent(divId, chartName, chartData, company){
	var option = initCurvePercent(chartName, chartData, company);
	setCurveChart(divId, option);
}