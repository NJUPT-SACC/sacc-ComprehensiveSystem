# sacc-ComprehensiveSystem

## 1。建库
运行根目录下的init.sql

## 2.目录结构
<details>
<summary>展开查看</summary>
<pre><code>.
├── sql
├── web
│   ├── common 公共组件
│   ├── admin 
│   │   ├── service 
│   │   ├── shiro 权限认证
│   │   ├── sys 用户相关
│   │   └── Utils 
│   │── config Java配置
│   │── modules 四个模块
│   │   ├── assignment 作业
│   │   ├── competition 比赛
│   │   ├── home 主页
│   │   └── management 管理
</code></pre>
</details>  

## 3.应从本地推到自己的分支上，然后再merge到master分支，需要review

## 4.commit规范  
### type：用于说明 commit 的类别，只允许使用下面7个标识。

feat：新功能（feature）  
fix：修补bug   
docs：文档（documentation）  
style： 格式（不影响代码运行的变动）  
refactor：重构（即不是新增功能，也不是修改bug的代码变动）  
test：增加测试  
chore：构建过程或辅助工具的变动  

然后再跟上描述