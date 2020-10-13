#!/bin/zsh
# 使用命令
USAGE="Usage: startup.sh {start|stop|restart}"

# 参数个数
exec_param_count=$#
# 如果不是 stop 命令，那么就是需要启动启动服务，此时参数不能少于3个，如果小于3，则提示使用命令
if [ $1 != "stop" -a $exec_param_count -lt 1 ]; then
  echo $USAGE
  exit
fi

cd $(dirname $0)
WORKSPACE=$(pwd)

# 启动命令
start() {
  # 根据配置文件找出 pid
  echo "WORKSPACE=$WORKSPACE"
  PIDS=$(ps -ef | grep java | grep -v grep | grep "$WORKSPACE" | awk '{print $2}')
  if [ -n "$PIDS" ]; then
    echo "ERROR: The server already started!"
    echo "PID: $PIDS"
    exit 1
  fi

  # 获取 jar 文件
  JAR_NAME=$(ls $WORKSPACE | grep .jar$ | awk '{print "'$LIB_DIR'/"$0}')
  FULL_JAR_NAME="$WORKSPACE$JAR_NAME"

  # 启动
  echo "准备启动$FULL_JAR_NAME"
  echo "nohup java -Xmx256m -Xms256m -Xmn256m -jar $FULL_JAR_NAME >/dev/null 2>&1 &"
  nohup java -Xmx256m -Xms256m -Xmn256m -jar "$FULL_JAR_NAME" >server.log 2>&1 &

  # 等待启动
  COUNT=0
  WAIT_TIME=0
  while [ $COUNT -lt 1 ]; do
    echo "......"
    sleep 1
    WAIT_TIME=$(expr $WAIT_TIME + 1)
    COUNT=$(ps -ef | grep java | grep -v grep | grep "$FULL_JAR_NAME" | awk '{print $2}' | wc -l)
    if [ $COUNT -gt 0 ]; then
      break
    fi
    if [ $WAIT_TIME -gt 120 ]; then
      echo "启动超时"
      exit
    fi
  done
  echo "............"
  echo "The server start OK!"
  PIDS=$(ps -ef | grep java | grep -v grep | grep "$FULL_JAR_NAME" | awk '{print $2}')
  echo "PID: $PIDS"
}

case $1 in
start)
  start
  ;;
stop)
  stop
  ;;
restart)
  echo "############ Application of '"$MAIN_CLASS"' restarting....############"
  stop
  sleep 1
  start
  ;;
*)
  echo $USAGE
  ;;
esac
exit 0
