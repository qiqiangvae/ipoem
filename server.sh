#!/bin/zsh
# 后端部署
cd ipoem & mvn -DskipTests=true  clean package
cp target/ipoem-*.jar /Workspack/chinese_poetry/
nohup java -Xms256m -Xmx256m  -jar ipoem-0.0.2-SNAPSHOT.jar > chinese_poetry.log 2>&1 &
# 前端打包
cd src/ipoem-h5 & npm install & npm run build & cp src/ipoem-h5/build /Workspack/chinese_poetry/build
