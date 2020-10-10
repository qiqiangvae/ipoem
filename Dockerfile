FROM java:8
MAINTAINER qiqiangvae@foxmail.com
ADD ./target/ipoem*.jar /home/ipoem/ipoem.jar
CMD java -Xms256m -Xmx256mg  -jar  /home/ipoem/ipoem.jar

EXPOSE 8111
