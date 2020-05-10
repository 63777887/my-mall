### 下载redis

https://redis.io/

window 版本下载路径：https://github.com/MicrosoftArchive/redis/releases

### MacOS下安装redis

将redis安装包解压到 /usr/local

![image-20200330212641122](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200330212641122.png)



进入安装包

```cd /usr/local/redis-5.0.8/ ```

编译测试 

```sudo make test ```

编译安装 

```sudo make install```

redis将被安装到了 

```/usr/local/bin```

### 启动redis

```
redis-server
```

启动之后，将阻塞命令窗口，如果窗口关闭程序就退出了

![image-20200330212859817](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200330212859817.png)

如果设置reids.conf里的属性daemonize为yes则可以后台运行，默认daemonize是no。我们需要手动将其设置为yes。redis.conf这个文件在我们下载的reids的安装包里

![image-20200330213028173](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200330213028173.png)

编辑redis.conf 

![image-20200330213149985](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200330213149985.png)

运行redis并指定配置文件

```
redis-server /usr/local/redis-5.0.8/redis.conf
```

### 查看redis的运行状态

```
 ps -ef | grep redis
```

显示如下

```
  501  3895     1   0  9:32下午 ??         0:00.09 redis-server 127.0.0.1:6379 
  501  3903  3783   0  9:33下午 ttys000    0:00.01 grep redis
```

或者显示redis的监听端口，默认监听6379

```
lsof -i :6379
```

显示如下

```
COMMAND    PID USER   FD   TYPE             DEVICE SIZE/OFF NODE NAME
redis-ser 3942 liyi    6u  IPv4 0xb3aa737832b9f213      0t0  TCP localhost:6379 (LISTEN)
```

### 关闭redis

```
redis-cli shutdown
```

### 常用的redis-cli命令

开启命令行工具，连接redis服务，直接运行以下命令

```
redis-cli
```

进入到命令行模式，默认cli连接127.0.0.1:6379

![image-20200330214157557](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200330214157557.png)

可以指定ip和端口号如下

```
redis-cli -h {ip} -p {端口}
```

例如

```
redis-cli -h 127.0.0.1 -p 6379
```

 这个命令也可以访问远程的redis服务。

### 常用命令

```
keys *						# 查询所有的key
keys by* 					# 模糊查询以by开头的key
del key1 key2 		# 删除key，可以传入多个key
exists key 				# 是否存在key
set key value			# 设置key
get key 					# 查询key
flushdb				# 删除当前数据库中的所有Key
flushall			# 删除所有数据库中的key
```



### redis数据库

登陆redis，默认选择了数据库0，如果需要切换到其它数据库使用select 索引值，如select 1表示切换到索引值为1的数据库。例如

```
127.0.0.1:6379> select 1
OK
```

切换之后就会一直在操作的是新数据库，直到下次切换生效。


springboot指定redis数据库，yml文件配置

```
  redis:
    database: 0 # Redis数据库索引（默认为0）

```