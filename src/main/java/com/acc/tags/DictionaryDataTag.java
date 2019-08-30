package com.acc.tags;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据字典 自定义标签类
 */
public class DictionaryDataTag extends TagSupport{
	
	private static final Log logger = LogFactory.getLog(DictionaryDataTag.class);
	
	/*
	 * 数据字典类型名
	 */
	private String typeName;
	/*
	 * 对应编码或id
	 */
	private String typeId;
	/*
	 * 是否匹配map数据
	 */
	private String isMap;
	
	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}


	public String getIsMap() {
		return isMap;
	}


	public void setIsMap(String isMap) {
		this.isMap = isMap;
	}


	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			String desc="";
			if(isMap == null){
				desc = getDesc(typeName, typeId);
			}else{
				desc = getDesc2(typeName, typeId);
			}
			out.print(desc);
		} catch (Exception e) {
			logger.error("自定义标签，获取中文描述 异常：");
		}
		return SKIP_BODY;
	}
	

	/**
	 * 根据类型名称、编码 解析数据字典，并返回对应描述
	 * @param tn
	 * @param id
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	private String getDesc(String tn,String id) throws Exception{
		String desc="";
		return desc;
	}
	
	/**
	 * 根据类型名称、编码 解析数据字典，并返回对应描述
	 * @param tn
	 * @param id
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private String getDesc2(String tn,String id) throws Exception{
		String desc="";
		return desc;
	}

}
