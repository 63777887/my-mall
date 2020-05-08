cd $(dirname $0)
cd ..
DEPLOY_DIR=$(pwd)
MAIN_CLASS='club.banyuan.zgMallMgt.ZgMallMgtApplication'

java -cp $DEPLOY_DIR/lib/*:$DEPLOY_DIR/conf/ -Dlogging.path=$DEPLOY_DIR/logs $MAIN_CLASS > $DEPLOY_DIR/logs/stdout.log 2>&1 &
