###认证模式

SpringSecurity有2种认证模式：
- fromLogin  表单提交认证模式，自己写表单界面进行登录
- HttpBasic  浏览器与服务器做认证授权的模式，就是浏览器弹出进行登录，企业中用户比较少

RBAC权限模型的核心5张表：
```
用户表            角色表             权限表
  |----用户角色表----|----角色权限表----|
```


