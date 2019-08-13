# redis-easy-client

##封装Jedis的方法

##一、使用封装的方法
1. maven坐标
        <dependency>
            <groupId>top.bbqbb</groupId>
            <artifactId>redis-easy-client-starter</artifactId>
            <version>1.0.0-RELEASE</version>
        </dependency>
2. 配置
![](https://github.com/qbbbbqSoft/img/raw/master/redis-easy-client配置.png)
3. 使用默认方法
>> 
    @Autowired
    RedisServiceTool tool;
    public UIDataController() {
    }
    @RequestMapping("/add-data")
        public Res add(@RequestBody RedisAjaxParam param) {
            RedisTR<Boolean> tr = tool.set(new RedisParam(param.getKey(), param.getValue(), param.getTimeout(), param.getTimeUnit()));
            if (tr != null && tr.getR()) {
                return Res.ok("添加成功");
            }
            return Res.error("添加失败");
        }

4. 自己实现
>>
    public class myRedisService implements RedisService {
        //TODO implements methods
    }


##二、封装了Redis缓存web界面
1. maven坐标
        <dependency>
            <groupId>top.bbqbb</groupId>
            <artifactId>redis-easy-client-webjar-ui</artifactId>
            <version>1.0.0-RELEASE</version>
        </dependency>
2. 配置
>> * 配置文件同上(同一个项目只需配置一次)
>> * 配置类上加上注解
>>>> @EnableRedisClientUI
3. 效果
>> * http://ip:port/redis-client-ui.html
>> * 预览
>>> ![](https://github.com/qbbbbqSoft/img/raw/master/preview1.png)
>> * 新增
>>> ![](https://github.com/qbbbbqSoft/img/raw/master/preview-add.png)
>> * 删除
>>> ![](https://github.com/qbbbbqSoft/img/raw/master/preview-del.png)

##三、未完待续
-- 没有全部实现方法，有时间会继续完成项目

##四、心得
-- 第一次封装，大佬轻喷，定加强学习



       

