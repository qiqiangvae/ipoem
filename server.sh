#!/bin/zsh
# 使用命令
USAGE="Usage: startup.sh {start|stop|restart} {debug}"

# 参数个数
exec_param_count=$#
# 如果不是 stop 命令，那么就是需要启动启动服务，此时参数不能少于3个，如果小于3，则提示使用命令
if [ $1 != "stop" -a $exec_param_count -lt 1 ]; then
    echo $USAGE
    exit
fi

cd $(dirname $0)
WORKSPACE=$(pwd)
# 获取 jar 文件
JAR_NAME=$(ls $WORKSPACE | grep .jar$ | awk '{print "'$LIB_DIR'/"$0}')
FULL_JAR_NAME="$WORKSPACE$JAR_NAME"

# jvm 参数
JVM_GC_PARAMS=""
JVM_MEMORY_PARAMS="-Xmx256m -Xms256m -Xmn256m"

# 启动命令
start() {
    # 根据配置文件找出 pid
    echo "WORKSPACE=$WORKSPACE"
    PIDS=$(ps -ef | grep java | grep -v grep | grep "$WORKSPACE" | awk '{print $2}')
    if [ -n "$PIDS" ]; then
        echo "启动失败: 该服务已经在运行中!PID: $PIDS"
        exit 1
    fi

    # 启动
    echo "准备启动$FULL_JAR_NAME"
    echo "nohup java $JVM_MEMORY_PARAMS  $JVM_GC_PARAMS -jar $FULL_JAR_NAME -Dspring.config.location=$WORKSPACE/application.yml >/dev/null 2>&1 &"
    nohup java "$JVM_MEMORY_PARAMS" "$JVM_GC_PARAMS" -jar "$FULL_JAR_NAME" -Dspring.config.location="$WORKSPACE/application.yml" >/dev/null 2>&1 &

    # 等待启动完成
    COUNT=0
    WAIT_TIME=0
    echo "...................................."
    while [ $COUNT -lt 1 ]; do
        sleep 1
        WAIT_TIME=$(expr $WAIT_TIME + 1)
        COUNT=$(ps -ef | grep java | grep -v grep | grep "$FULL_JAR_NAME" | awk '{print $2}' | wc -l)
        if [ $COUNT -gt 0 ]; then
            break
        fi
        if [ $WAIT_TIME -gt 120 ]; then
            echo "启动超时"
            exit 1
        fi
    done
    echo "...................................."
    PIDS=$(ps -ef | grep java | grep -v grep | grep "$FULL_JAR_NAME" | awk '{print $2}')
    echo "服务启动成功，PID: $PIDS"
}

stop() {
    # 根据配置文件找出 pid
    echo "WORKSPACE=$WORKSPACE"
    PIDS=$(ps -ef | grep java | grep -v grep | grep "$FULL_JAR_NAME" | awk '{print $2}')
    if [ -z "$PIDS" ]; then
        echo "该服务尚未运行!"
        exit
    fi
    kill $PIDS
    echo "服务停止,PID:$PIDS"
}

case $1 in
start)
    start
    ;;
stop)
    stop
    ;;
restart)
    echo "服务重启中"
    stop
    sleep 1
    start
    ;;
*)
    echo $USAGE
    ;;
esac
exit 0
