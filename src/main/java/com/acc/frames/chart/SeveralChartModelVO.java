package com.acc.frames.chart;

import java.io.Serializable;
import java.util.List;


/**
 * 多项图数据模型
 * @author TANGCY
 *
 */
public class SeveralChartModelVO  implements Serializable{

	private static final long serialVersionUID = 1L;
	//选项的名称
	private String itemName;
	//当前选项的数据
	private List<ChartModelVO> itemData;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public List<ChartModelVO> getItemData() {
		return itemData;
	}
	public void setItemData(List<ChartModelVO> itemData) {
		this.itemData = itemData;
	}
}
