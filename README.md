# login

持续更新中...

下面是目前已实现的功能：

spring boot + spring security + mybatis实现如login等一些权限管理。

登录拦截器采用Spring Security来处理，未登录时跳转登录页面，
输入账号密码后提交表单，表单提交的URL交由Spring Security处理。

## Spring Security

Spring Security在导入依赖后默认管理所有接口。

在这里SecurityConfig实现类用于配置策略，并实现 loginSuccessHandler、
loginFailureHandler 等。

DataBaseUserDetailsService是UserDetailsService的实现类，该类主要
是通过`loadUserByUsername(String username)`方法来基于Mapper从
数据库中查询并加载具体的用户。返回对象是UserDetails实现类。

其中UserDetails是提供用户信息的核心接口，通常使用`org.springframework
.security.core.userdetails.User`这个实现类。

## Mybatis

采用了mapper + xml的方式完成数据的持久化。

采用mybatis的generator插件实现 DataBase 到 DAO 的映射，
并初步生成mapper.xml.

Mapper接口通过Mapper.xml实现与数据库的交互。