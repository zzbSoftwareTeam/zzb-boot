
# 基础镜像
FROM maven:3.5.0-jdk-8-alpine
 
# 作者
MAINTAINER liweichao
 
# 添加当前目录到镜像中
ADD . /tmp/app/
 
# 构建应用，串写指令，压缩镜像大小。
RUN cd /tmp/app && mvn clean package -X &&\
    # 拷贝编译结果到指定目录
    mv /tmp/app/target/*.jar /var/lib/app.jar &&\
    #清理编译痕迹
    rm -rf /tmp/app
 
# 设置编码格式
ENV LANG="zh_CN.UTF-8"
 
#设置环境变量
ENV spring.profiles.active="pro"
 
# 日志挂载
VOLUME ["/logs"]
 
# 暴露端口
EXPOSE 9090
 
# 启动执行
ENTRYPOINT java -server -Dfile.encoding=UTF-8 -Xmx1025m -Xss256k -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=50983 -Djava.security.egd=file:/dev/./urandom -jar /var/lib/app.jar
