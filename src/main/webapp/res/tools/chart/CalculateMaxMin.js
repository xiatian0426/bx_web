/**
 * 根据当前需要等分为多少分, 计算出最大和最小的值
 * @param valueMax 当前获取到的最大值
 * @param valueMin 当前获取到的最小值
 * @param portion  需要显示为多少个点的数值
 * @returns {String} 返回计算结果 并以"_"连接  前边为最大值, 后边为最小值
 */
function calculateMaxMin(valueMax, valueMin, portion){
	var aliquots = portion - 1;
	//修改最大值最小值 ， 分别取整数
	valueMax = calculateValue(valueMax, "max");
	valueMin = calculateValue(valueMin, "min");
	//Y轴的最大值最小值计算
	if(valueMax - valueMin < aliquots){ //当前的最大值最先的差不够等分数
		//说明:Math.ceil 为向上取整, Math.floor为向下取整
		//当前不足等分, 需要在最大和最小值的两边分别加
		var s = Math.ceil((aliquots - valueMax + valueMin)/2);
		if(valueMin < 0){//当最小值小于0, 不考虑最小值向下扩展小于0
			if(Math.floor((aliquots - valueMax + valueMin)%2) == 1){
				valueMin = valueMin - s +1;
			}else{
				valueMin = valueMin - s;
			}
			valueMax = valueMax + s;
		}else{//考虑最小值向下扩展小于0
			if(s > valueMin){//满足当前条件说明当前值如果向下扩展会出现负数, 则最小值取0, 把剩余的加到最大值上边 向上扩展
				if(Math.floor((portion - valueMax + valueMin)%2) == 1){
					valueMax = valueMax + s + (s - valueMin);
				}else{
					valueMax = valueMax + s + (s - valueMin) -1;
				}
				valueMin = 0;
			}else{//当前最小值向下扩展不会出现负数
				if(Math.floor((portion - valueMax + valueMin)%2) == 1){
					valueMax = valueMax + s;
					valueMin = valueMin - s;
				}else{
					valueMax = valueMax + s -1;
					valueMin = valueMin - s;
				}
			}
		}
	}else{//当前的最大值最小值的差大于等分数, 约定只向下扩展, 最小值不足,则向上扩展 
		if((valueMax - valueMin)%aliquots > 0){// 差值不能被等分
			if(valueMin >= (aliquots -(valueMax - valueMin)%aliquots)){//最小值向下扩展不会出现负数
				valueMin = valueMin - (aliquots -(valueMax - valueMin)%aliquots);
			}else{//最小值向下扩展会出现小数
				if(valueMin >= 0){//如果当前最小值大于等于0,向下扩展最小为0
					valueMax = valueMax + ((aliquots -(valueMax - valueMin)%aliquots) - valueMin);
					valueMin = 0;
				}else{//可以随意扩展
					valueMin = valueMin - (aliquots -(valueMax - valueMin)%aliquots);
				}
			}
		}
	}
	return valueMax+"_"+valueMin;
}
/**
 * 当前的值取整数, 最大值向上取整, 最小值向下取整
 * @param value 当前值
 * @param calculateType 获取的值类型(决定是向上还是向下) 取值为  'min'或'max'
 * @returns {Number} 返回取整后的结果
 */
function calculateValue(value, calculateType){
	var result = 0;
	if("min" == calculateType){//计算最小值
		if(value < 0){
			result = value - 1;
			result = Math.floor(result);
		}else{
			if(value - 1 < 0){
				result = 0;
			}else{
				result = value - 1;
				result = Math.floor(result);
			}
		}
	}else if("max" == calculateType){//计算最大值
		result = Math.ceil(value);
	}else{
		result = value;
	}
	return result;
}