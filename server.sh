#!/bin/zsh
# 后端部署
echo '编译后端'
mvn -DskipTests=true  clean package
cp target/ipoem-*.jar /Workspack/chinese_poetry/
nohup java -Xms256m -Xmx256m  -jar ipoem-0.0.2-SNAPSHOT.jar > chinese_poetry.log 2>&1 &
echo '后台启动成功'
# 前端打包
echo '打包前端'
npm install & npm run build & cp src/ipoem-h5/dist /Workspace/chinese_poetry/dist
echo '前端打包完成'
