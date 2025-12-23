#!/bin/sh

##############################################################################
# Appassembler startup script
##############################################################################


APP_NAME="@APP_NAME@"
APP_HOME="$(cd "$(dirname "$0")/.." && pwd)"
if [ -z "$BASEDIR" ]; then
    BASEDIR="$APP_HOME"
fi
LOG_DIR="$BASEDIR/logs"
PID_FILE="$BASEDIR/$APP_NAME.pid"
if [ -z "$REPO" ]; then
    REPO="$BASEDIR/lib"
fi

JAVA_CMD="java"
JAVA_OPTS=""
MAIN_CLASS="@MAINCLASS@"
CLASSPATH="@CLASSPATH@"

mkdir -p "$LOG_DIR"

is_running() {
  if [ -f "$PID_FILE" ]; then
    PID=$(cat "$PID_FILE")
    if kill -0 "$PID" >/dev/null 2>&1; then
      return 0
    fi
  fi
  return 1
}

start() {
  if is_running; then
    echo "$APP_NAME already running (PID=$(cat $PID_FILE))"
    exit 0
  fi

  echo "Start $APP_NAME..."
  nohup "$JAVA_CMD" $JAVA_OPTS \
    -cp "$CLASSPATH" \
    "$MAIN_CLASS" \
    > "$LOG_DIR/stdout.log" 2>&1 &

  PID=$!
  echo "$PID" > "$PID_FILE"
  echo "$APP_NAME started (PID=$PID)"
}

stop() {
  if ! is_running; then
    echo "$APP_NAME not running"
    exit 0
  fi

  PID=$(cat "$PID_FILE")
  echo "Stopping $APP_NAME (PID=$PID)..."
  kill "$PID"

  for i in 1 2 3 4 5; do
    if kill -0 "$PID" >/dev/null 2>&1; then
      sleep 1
    else
      rm -f "$PID_FILE"
      echo "Stopped"
      return
    fi
  done

  echo "Force killing $PID"
  kill -9 "$PID"
  rm -f "$PID_FILE"
}

status() {
  if is_running; then
    echo "$APP_NAME is running (PID=$(cat $PID_FILE))"
  else
    echo "$APP_NAME is stopped"
  fi
}

restart() {
  stop
  start
}

case "$1" in
  start) start ;;
  stop) stop ;;
  restart) restart ;;
  status) status ;;
  *)
    echo "Usage: $0 {start|stop|restart|status}"
    exit 1
    ;;
esac
