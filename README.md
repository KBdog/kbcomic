# kb漫画后端
该项目由springboot搭建，提供漫画更新、查找等功能。可与[kb磁力前端](https://github.com/KBdog/magnet-system-web)的漫画爬虫页搭配使用

## 使用技术
spring、springmvc、mybatis、springboot

## 漫画实体存放说明
* 该项目存放的漫画都在本人PC上，通过配置，让漫画地址映射到本机文件夹特定目录。由此，可直接通过http访问本机漫画。
* 以下是映射的实现代码：
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${cbs.imagesPath}")
    private String imagesPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("图片文件路径:"+imagesPath);
        registry.addResourceHandler("/comics/**").addResourceLocations(imagesPath);
    }
}
```
```yaml
cbs:
  imagesPath: file:D:\copymanga\
```

## RESTful API
以下为该项目所有api接口(不含请求参数)

### 查询所有漫画
GET `/api/comic/all`

### 根据id查询漫画简介信息
GET `/api/comic/id?id=`

### 根据漫画关键字查询
GET `/api/comic/keyword?keyword=`

### 根据漫画id查询章节列表
GET `/api/chapter/{comicId}`

### 根据章节id查询该章图片列表
GET `/api/pic/{chapterId}`

### 根据漫画id查询所有该漫画所有信息（漫画章节目录、章节全图片目录）
GET `/message/comic?id=`

### 更新漫画数据库(将本地目标文件夹与数据库对比更新)
POST `/update/validate`