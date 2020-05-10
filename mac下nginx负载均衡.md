### 安装nginx

```
brew install nginx
```

### 运行nginx

```
nginx
```

默认是nginx监听8080端口，使用浏览器打开http://localhost:8080/页面

![image-20200329224655609](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200329224655609.png)

查询端口命令

```
 lsof -i:8080
```

输出内容大致如下

```
COMMAND   PID USER   FD   TYPE             DEVICE SIZE/OFF NODE NAME
nginx   58170 liyi    6u  IPv4 0x75c654ab9ea3bbed      0t0  TCP *:http-alt (LISTEN)
nginx   58171 liyi    6u  IPv4 0x75c654ab9ea3bbed      0t0  TCP *:http-alt (LISTEN)
```

### 停止nginx

```
nginx -s stop
```

### nginx路径

```
# nginx配置文件目录   
/usr/local/etc/nginx/

# nginx安装到的目录　
/usr/local/Cellar/nginx  
```

### nginx配置

#### 加载多配置文件

修改`/usr/local/etc/nginx/`下的nginx.conf 文件，在`http` 项目中增加

```
include confs/*.conf;
```

#### 检查配置文件合法性

```
nginx -t
```

#### 重新读取配置文件

```
nginx -s reload
```

### 负载均衡配置

在confs目录下增加balance.conf文件，内容如下

```
upstream sample { 
     server 127.0.0.1:9999 weight=1; 
     server 127.0.0.1:8888 weight=1;  
} 

server { 
      listen 80; 
      server_name 127.0.0.1; 
      location / { 
        proxy_pass http://sample; 
      } 
    } 
```

创建两个服务器监听不同端口如下，分别是8888和9999，示例文件`server.js`

```
var http = require('http');
//需要访问的文件的存放目录
http.createServer(function (req, res) {
  res.write('<head><meta charset="utf-8"></head>');
  res.write("8888端口服务器");
  res.end();
}).listen(8888);
```

启动服务

```
node server.js
```

浏览器输入

![image-20200329232533562](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200329232533562.png)

刷新

![image-20200329232550994](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200329232550994.png)

#### 配置需要注意

`upstream`不能重名，`server_name`和端口不能完全一致

### 静态页面代理

编译静态文件后，生成到路径下

```
/Users/liyi/works/repos/banyuan/pj-mall-admin-web/dist
```

路径结构为

```
dist
├── index.html
└── static
```

confs增加配置文件page.conf

```
server { 
      listen 8090; 
      server_name 127.0.0.1; 
      location / { 
        root /Users/liyi/works/repos/banyuan/pj-mall-admin-web/dist;
				index index.html; 
      } 
    } 
```

浏览器输入localhost:8090

![image-20200329233914591](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200329233914591.png)







