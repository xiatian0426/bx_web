package com.acc.frames.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.acc.frames.web.FilterUtil;
import com.acc.frames.web.ResourceExposer;
import com.acc.util.Constants;


public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ResourceExposer resourceExposer;

	/**
	 * 验证URl的工具
	 */
	private FilterUtil filterUtil = new FilterUtil();

	/**
	 * 忽略的URl表达式 优先级低于 filterList 例如一个URl既在noFilterList 又在filterList那么该URl仍需校验
	 */
	private List<String> noFilterList = new ArrayList<String>();

	/**
	 * 必须校验的URl 优先级高于 noFilterList 例如一个URl既在noFilterList 又在filterList那么该URl仍需校验
	 */
	private Map<String, String[]> filterMap = new HashMap<String, String[]>();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object chain) throws Exception {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			String serviceUrl = httpRequest.getRequestURL().toString();// 获取url但不带问号及其参数
			String path = httpRequest.getRequestURI();
			String param = httpRequest.getQueryString();// 获取问号后的参数
			String returnUrl = request.getParameter("from");
			if (StringUtils.isEmpty(returnUrl)) returnUrl = "";
			String returnUrlPar = !"".equals(returnUrl) ? "?from=" + java.net.URLEncoder.encode(returnUrl) : "";
			if (StringUtils.isNotBlank(param)) {
				param = "?" + param;
			} else {
				param = "";
			}
			String webRoot = resourceExposer.getWebRoot();
			boolean isUnFilter = filterUtil.filterUrl("/" + path, noFilterList, filterMap);
			if (isUnFilter) {
				return true;
			} else {
                return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	public void setNoFilterList(List<String> noFilterList) {
		this.noFilterList = noFilterList;
	}

	public void setFilterMap(Map<String, String[]> filterMap) {
		this.filterMap = filterMap;
	}
}
