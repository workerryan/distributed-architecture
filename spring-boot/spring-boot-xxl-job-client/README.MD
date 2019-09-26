### xxl-job服务端的部署
- 去 [官网](http://www.xuxueli.com/xxl-job/#/ "官网") 的github链接下载源码包。[github地址](https://github.com/xuxueli/xxl-job/)，这里下载的版本为2.1.1-SNAPSHOT
- 下载源码包后，执行doc/db/tables_xxl_job.sql里面的SQL语句
- 修改xxl-job-admin/src/main/resources/application.properties里面的sql链接
- 执行`mvn package`命令得到jar包，然后启动即可


###xxl-job客服端的部署
- 新建springboot工程，新增依赖：
```
<dependency>
    <groupId>com.xuxueli</groupId>
    <artifactId>xxl-job-core</artifactId>
    <version>2.1.1-SNAPSHOT</version>
</dependency>
```
- 在配置文件`application.properties`中增加xxl的配置：
```
### xxl-job admin address list, such as "http://address" or "http://address01,http://address02"
xxl.job.admin.addresses=http://127.0.0.1:8080/xxl-job-admin

### xxl-job executor address
xxl.job.executor.appname=xxl-job-executor-sample
xxl.job.executor.ip=
## xxl启动的时候，会通过netty创建一个端口，这个端口是接收请求执行的端口
xxl.job.executor.port=9999

### xxl-job, access token
xxl.job.accessToken=

### xxl-job log path
xxl.job.executor.logpath=jobhandler
### xxl-job log retention days
xxl.job.executor.logretentiondays=-1
```
- 增加config类，见`com.dragon.xxlclient.config.XxlJobConfig`
- 书写handler类，该类要继承`com.xxl.job.core.handler.IJobHandler`,在该类上增加`@JobHandler(value="myJobHandler")`注解，xxl使用反射，所以该注解里面value的值为类型名称，第一个字符小写

