### minio下载

官网路径  [https://min.io/](https://min.io/) ，选择合适的操作系统进行下载。

### 启动minio

linux或Mac操作系统。进入minio文件所在位置，然后执行命令：

```kotlin
chmod +x minio # 给文件赋予可执行权限
./minio server ./data # 启动minio服务，并制定保存文件对象路径为当前路径下的data目录
```

windows操作系统，开启cmd命令行进入minio.exe所在文件夹，然后执行，其中F:\Data可设置成本地磁盘其他位置

```css
minio.exe server F:\Data
```

minio服务启动成功，将显示如下内容

```
Unable to set system resources to maximum invalid argument
Endpoint:  http://192.168.43.62:9000  http://127.0.0.1:9000              
AccessKey: minioadmin 
SecretKey: minioadmin 

Browser Access:
   http://192.168.43.62:9000  http://127.0.0.1:9000              

Command-line Access: https://docs.min.io/docs/minio-client-quickstart-guide
   $ mc config host add myminio http://192.168.43.62:9000 minioadmin minioadmin

Object API (Amazon S3 compatible):
   Go:         https://docs.min.io/docs/golang-client-quickstart-guide
   Java:       https://docs.min.io/docs/java-client-quickstart-guide
   Python:     https://docs.min.io/docs/python-client-quickstart-guide
   JavaScript: https://docs.min.io/docs/javascript-client-quickstart-guide
   .NET:       https://docs.min.io/docs/dotnet-client-quickstart-guide
Detected default credentials 'minioadmin:minioadmin', please change the credentials immediately using 'MINIO_ACCESS_KEY' and 'MINIO_SECRET_KEY'
```

### 浏览器管理页面

minio服务启动后，默认端口是9000，可以访问[http://127.0.0.1:9000](http://127.0.0.1:9000)，在登录页输入access-key和secret-key，进入浏览器的管理后台

### 自定义端口号

使用`--address "127.0.0.1:9099"` 指定端口号。如下

```
./minio server ./data --address "127.0.0.1:9099"
```

### minio后台运行

启动minio之后，控制台处于阻塞状态，如果关闭控制台窗口则服务停止。使用如下的方式让minio运行在后台，并且将原本输出到控制台的内容输出到当前文件夹的minio.log文件中

```kotlin
nohup ./minio server  ./data > ./minio.log 2>&1 &
```

### 自定义端口号

使用`--address "127.0.0.1:9099"` 指定端口号。如下

```
./minio server ./data --address "127.0.0.1:9099"
```

### 默认的key

minio server启动之后，将使用默认的access和sercet key，用于上传或下载文件，以及登录浏览器管理界面。可以通过控制台信息来查看默认的access和sercet key的值

```
AccessKey: minioadmin 
SecretKey: minioadmin 
```

### 自定义access和sercet

在启动minio之前先配置环境变量如下，可以使用自定义的access和sercet key

```bash
export MINIO_ACCESS_KEY=自定义的access
export MINIO_SECRET_KEY=自定义的sercet
```

注意导入环境变量之后，启动时指定的数据文件夹中保存了定义的access和sercet key，这个数据目录将和配置的access & sercet key 绑定。如果下次启动时，使用了不同的access 或sercet key，并且同时指定了老的数据目录。将会出现错误。表示使用的数据目录绑定的access和sercet key和本次启动时指定的不符。

```
Unable to initialize server switching into safe-mode: Unable to handle encrypted backend for config, iam and policies: Credentials missing
```

### Linux或mac下完整的启动脚本文件

将如下内容保存为`start.sh`，赋予可执行权限后(`chmod +x start.sh`)可以用于启动minio服务

```
export MINIO_ACCESS_KEY=banyuan
export MINIO_SECRET_KEY=minio@banyuan
# minio文件所在的路径
export MINIO_BASE="/Users/edz/bucket"

nohup ${MINIO_BASE}/minio server ${MINIO_BASE}/data  > ${MINIO_BASE}/minio.log 2>&1 & 
```

### 管理服务器

浏览器中输入服务器的ip和端口号，可以进入到浏览器的管理界面，例如输入127.0.0.1:9000 会自动跳转到登录页

![image-20200403102245317](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403102245317.png)

根据配置输入access key和sercet key登录管理页面

![image-20200403102518611](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403102518611.png)

创建桶用来保存文件

![image-20200403102553001](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403102553001.png)

创建桶

![image-20200403102614956](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403102614956.png)

输入桶名称之后回车

![image-20200403102635612](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403102635612.png)



创建了一个名为dev的桶

![image-20200403102754602](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403102754602.png)



上传文件

![image-20200403105414615](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403105414615.png)



文件上传成功

![image-20200403105449208](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403105449208.png)



生成图片的分享链接

![image-20200403105522401](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403105522401.png)

使用minio分享的文件链接通常带有有效期限（最长7天），如果想要使用长期有效的链接，需要将桶配置为公共策略。

![image-20200403105621343](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403105621343.png)



![image-20200403105643160](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403105643160.png)



设置一个策略

![image-20200403105804894](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403105804894.png)

添加策略后

![image-20200403105823325](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403105823325.png)

这样可以直接使用桶路径的文件名称链接来获取这个文件。例如

```
http://127.0.0.1:9000/dev/web.png
```

### 客户端

minio提供了两种软件的下载，一种是minio，作为文件服务器。一种是mc，作为服务器管理软件，用来代替浏览器登入之后的管理功能。运行mc程序关联需要进行管理的服务器。

```
./mc config host add minio http://127.0.0.1:9099 accesskey sercetkey
```

设置policy权限为public，等同于浏览器管理页面设置，用于生成公共链接。可选项(`public`，`download`，`none`)

```
./mc policy set public data/dev
```

### 帮助文档

帮助文档 [https://docs.min.io/](https://docs.min.io/) 中文版[https://docs.min.io/cn/](https://docs.min.io/cn/)



### 遇到的问题

客户端修改policy的时候会连带着修改对应桶文件夹的读写权限，如果目录没有写入权限，再从页面上上传文件将会报错。

```
ls -l

dr-xr-xr-x  3 liyi  staff  96  4  3 10:54 dev
```

如下

![image-20200403112300598](https://raw.githubusercontent.com/by-codes/pj-mall-doc/master/img/image-20200403112300598.png)

日志中将输出如下内容

```
Time: 11:22:47 CST 04/03/2020
DeploymentID: ca46360e-28fd-4fe8-ac01-aca2aca3dac8
Error: rename /Users/liyi/Desktop/projects_mall/minio/Mac/data/.minio.sys/tmp/9c116061-3b7f-4914-938c-6af018d00a0b/daf6f2ec-59ae-4743-a0db-b1935b110c72 /Users/liyi/Desktop/projects_mall/minio/Mac/data/dev/5ad44fa8Nfcf71c10.jpg: permission denied
       5: cmd/api-errors.go:1807:cmd.toAPIErrorCode()
       4: cmd/api-errors.go:1832:cmd.toAPIError()
       3: cmd/web-handlers.go:2210:cmd.toWebAPIError()
       2: cmd/web-handlers.go:2219:cmd.writeWebErrorResponse()
       1: cmd/web-handlers.go:1125:cmd.(*webAPIHandlers).Upload()
```

将权限设置为可写来解决这个问题。

```
chmod 755 dev
```









