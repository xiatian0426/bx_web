package com.acc.frames.web;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 通过Spring框架在ServletContext层面注入静态资源根路径信息
 * 
 */
public class ResourceExposer implements ServletContextAware {

	private ServletContext application;
	/** 项目根路径 */
	private String webRoot;
	/** 图片资源根路径 **/
	private String imageRoot;
	/** css资源根路径 **/
	private String cssRoot;
	/** 页面工具集根路径 **/
	private String toolRoot;
	/** js资源根路径 **/
	private String jsRoot;

	private String proRoot;

	/*
	 * 初始化方法
	 */
	public void init() throws Exception {
		getServletContext().setAttribute("imageRoot", imageRoot);
		getServletContext().setAttribute("cssRoot", cssRoot);
		getServletContext().setAttribute("jsRoot", jsRoot);
		getServletContext().setAttribute("toolRoot", toolRoot);
		getServletContext().setAttribute("webRoot", webRoot);
        getServletContext().setAttribute("proRoot", proRoot);
		
		System.out.println("===>资源路径初始化."+ getServletContext().getAttribute("jsRoot"));
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		application = servletContext;
	}

	public ServletContext getServletContext() {
		return this.application;
	}

	public String getImageRoot() {
		return imageRoot;
	}

	public void setImageRoot(String imageRoot) {
		this.imageRoot = imageRoot;
	}

	public String getCssRoot() {
		return cssRoot;
	}

	public void setCssRoot(String cssRoot) {
		this.cssRoot = cssRoot;
	}

	public String getJsRoot() {
		return jsRoot;
	}

	public void setJsRoot(String jsRoot) {
		this.jsRoot = jsRoot;
	}

	public String getToolRoot() {
		return toolRoot;
	}

	public void setToolRoot(String toolRoot) {
		this.toolRoot = toolRoot;
	}

	public ServletContext getApplication() {
		return application;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

	public String getWebRoot() {
		return webRoot;
	}

	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}

    public String getProRoot() {
        return proRoot;
    }

    public void setProRoot(String proRoot) {
        this.proRoot = proRoot;
    }
}
