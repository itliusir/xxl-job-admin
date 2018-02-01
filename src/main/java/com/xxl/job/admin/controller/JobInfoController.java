package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.enums.ExecutorFailStrategyEnum;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.route.ExecutorRouteStrategyEnum;
import com.xxl.job.admin.mapper.XxlJobGroupMapper;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * index controller
 * @author xuxueli 2015-12-19 16:13:16
 */
@RestController
@RequestMapping("/jobinfo")
@Api(description="任务管理接口")
public class JobInfoController {

	@Resource
	private XxlJobGroupMapper xxlJobGroupMapper;
	@Resource
	private XxlJobService xxlJobService;

	@ApiOperation(value="查询任务管理接口", notes="")
	@GetMapping("")
	public Map<String,Object> index(@RequestParam(required = false, defaultValue = "-1") int jobGroup) {
		Map<String,Object> map = new HashMap<>();
		// 枚举-字典
		map.put("ExecutorRouteStrategyEnum", ExecutorRouteStrategyEnum.values());	// 路由策略-列表
		map.put("GlueTypeEnum", GlueTypeEnum.values());								// Glue类型-字典
		map.put("ExecutorBlockStrategyEnum", ExecutorBlockStrategyEnum.values());	// 阻塞处理策略-字典
		map.put("ExecutorFailStrategyEnum", ExecutorFailStrategyEnum.values());		// 失败处理策略-字典

		// 任务组
		List<XxlJobGroup> jobGroupList =  xxlJobGroupMapper.findAll();
		map.put("JobGroupList", jobGroupList);
		map.put("jobGroup", jobGroup);

		return map;
		//return "jobinfo/jobinfo.index";
	}

	@ApiOperation(value="搜索任务接口", notes="")
	@GetMapping("/pageList")
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,  
			@RequestParam(required = false, defaultValue = "10") int length,
			int jobGroup, String jobDesc, String executorHandler, String filterTime) {
		
		return xxlJobService.pageList(start, length, jobGroup, jobDesc, executorHandler, filterTime);
	}

	@ApiOperation(value="新增任务管理接口", notes="")
	@PostMapping("/add")
	public ReturnT<String> add(@RequestBody XxlJobInfo jobInfo) {
		return xxlJobService.add(jobInfo);
	}

	@ApiOperation(value="编辑任务管理接口", notes="")
	@PostMapping("/reschedule")
	public ReturnT<String> reschedule(@RequestBody XxlJobInfo jobInfo) {
		return xxlJobService.reschedule(jobInfo);
	}

	@ApiOperation(value="删除任务管理接口", notes="")
	@DeleteMapping("/remove/{id}")
	public ReturnT<String> remove(@PathVariable int id) {
		return xxlJobService.remove(id);
	}

	@ApiOperation(value="暂停任务管理接口", notes="")
	@GetMapping("/pause/{id}")
	public ReturnT<String> pause(@PathVariable int id) {
		return xxlJobService.pause(id);
	}

	@ApiOperation(value="恢复任务管理接口", notes="")
	@GetMapping("/resume/{id}")
	public ReturnT<String> resume(@PathVariable int id) {
		return xxlJobService.resume(id);
	}

	@ApiOperation(value="执行任务管理接口", notes="")
	@GetMapping("/trigger/{id}")
	public ReturnT<String> triggerJob(@PathVariable int id) {
		return xxlJobService.triggerJob(id);
	}
	
}
