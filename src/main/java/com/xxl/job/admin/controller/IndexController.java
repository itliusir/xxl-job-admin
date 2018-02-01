package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermessionLimit;
import com.xxl.job.admin.controller.interceptor.PermissionInterceptor;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * index controller
 * @author xuxueli 2015-12-19 16:13:16
 */
@RestController
@Api(description="首页接口")
public class IndexController {

	@Resource
	private XxlJobService xxlJobService;

	@ApiOperation(value="dashboard", notes="")
	@GetMapping("")
	public Map<String, Object> index() {
		Map<String, Object> dashboardMap = xxlJobService.dashboardInfo();
		return dashboardMap;
	}

	@ApiOperation(value="调度报表日期选择", notes="")
	@GetMapping("/triggerChartDate")
	public ReturnT<Map<String, Object>> triggerChartDate(@RequestParam Date startDate,@RequestParam Date endDate) {
        ReturnT<Map<String, Object>> triggerChartDate = xxlJobService.triggerChartDate(startDate, endDate);
        return triggerChartDate;
    }
	
	/*@RequestMapping("/toLogin")
	@PermessionLimit(limit=false)
	public String toLogin(Model model, HttpServletRequest request) {
		if (PermissionInterceptor.ifLogin(request)) {
			return "redirect:/";
		}
		return "login";
	}
	
	@RequestMapping(value="login", method= RequestMethod.POST)
	@PermessionLimit(limit=false)
	public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember){
		// valid
		if (PermissionInterceptor.ifLogin(request)) {
			return ReturnT.SUCCESS;
		}

		// param
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
			return new ReturnT<String>(500, I18nUtil.getString("login_param_empty"));
		}
		boolean ifRem = (StringUtils.isNotBlank(ifRemember) && "on".equals(ifRemember))?true:false;

		// do login
		boolean loginRet = PermissionInterceptor.login(response, userName, password, ifRem);
		if (!loginRet) {
			return new ReturnT<String>(500, I18nUtil.getString("login_param_unvalid"));
		}
		return ReturnT.SUCCESS;
	}
	
	@RequestMapping(value="logout", method= RequestMethod.POST)
	@PermessionLimit(limit=false)
	public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response){
		if (PermissionInterceptor.ifLogin(request)) {
			PermissionInterceptor.logout(request, response);
		}
		return ReturnT.SUCCESS;
	}*/
	
	/*@RequestMapping("/help")
	public String help() {

		*//*if (!PermissionInterceptor.ifLogin(request)) {
			return "redirect:/toLogin";
		}*//*

		return "help";
	}*/

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
