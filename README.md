# xxl-job-admin 任务调度服务(后台部分)
[原项目地址传送门](https://github.com/xuxueli/xxl-job)
# 依赖更新
- SpringBoot 1.5.6
- JDK 1.8
---------
# Doc
[Swagger-ui](http://localhost:8997/swagger-ui.html)
---------
# Blog
[传送门](http://itliusir.com/2018/scheduler-xxl-job/)

# 后台URL
## 任务管理
- 默认(`/jobinfo`)
- 搜索(`/jobinfo/pageList`)
- 新增任务-保存(`/jobinfo/add`)
- 执行(`/jobinfo/trigger/{id}`)
- 暂停(`/jobinfo/pause/{id}`)
- 日志(`/joblog?jobId=`)
- 编辑-保存(`/jobinfo/reschedule`)
- 删除(`/jobinfo/remove/{id}`)

## 调度日志
- 默认(`/joblog`)
- 搜索(`/joblog/pageList`)
- 清理(`/joblog/clearLog`)

## 执行器管理
- 默认(`/jobgroup`)
- 新增执行器(`/jobgroup/save`)
- 编辑-保存(`/jobgroup/update`)
- 删除(`/jobgroup/remove/{id}`)