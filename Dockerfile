FROM java:8
MAINTAINER qiqiangvae@foxmail.com
ADD ./target/ipoem*.jar /home/ipoem/ipoem.jar
CMD java -Xms256m -Xmx256mg  -jar  /home/ipoem/ipoem.jar
# nohup java -Xms256m -Xmx256m  -jar ipoem-0.0.2-SNAPSHOT.jar > chinese_poetry.log 2>&1 &
EXPOSE 8111
