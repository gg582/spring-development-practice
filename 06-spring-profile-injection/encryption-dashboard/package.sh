#!/bin/bash
set -e

LIB_DIR="$(pwd)/external/libs"
CORE_CLASSES="$(pwd)/target/classes"

mvn clean compile

javac -d "$CORE_CLASSES" \
      -cp "$CORE_CLASSES:$LIB_DIR/bcprov-jdk15to18-1.81.jar" \
      external/encrypt/com/example/app/crypto/algorithms/*.java

jar cvf "$LIB_DIR/encryption-algorithms.jar" \
    -C "$CORE_CLASSES" com/example/app/crypto/algorithms/AESDecryptManager.class \
    -C "$CORE_CLASSES" com/example/app/crypto/algorithms/AESEncryptManager.class \
    -C "$CORE_CLASSES" com/example/app/crypto/algorithms/ChachaDecryptManager.class \
    -C "$CORE_CLASSES" com/example/app/crypto/algorithms/ChachaEncryptManager.class \
    -C "$CORE_CLASSES" com/example/app/crypto/algorithms/SEEDDecryptManager.class \
    -C "$CORE_CLASSES" com/example/app/crypto/algorithms/SEEDEncryptManager.class \
    -C "$CORE_CLASSES" com/example/app/crypto/algorithms/KISA_SEED_CBC.class

rm -f $CORE_CLASSES/com/example/app/crypto/algorithms/AES*
rm -f $CORE_CLASSES/com/example/app/crypto/algorithms/Chacha*
rm -f $CORE_CLASSES/com/example/app/crypto/algorithms/SEED*
rm -f $CORE_CLASSES/com/example/app/crypto/algorithms/KISA_SEED_CBC*

javac -d "$CORE_CLASSES" \
      -cp "$CORE_CLASSES:$LIB_DIR/encryption-algorithms.jar" \
      external/factory/com/example/app/crypto/factory/*.java

jar cvf "$LIB_DIR/encryption-factory.jar" \
    -C "$CORE_CLASSES" com/example/app/crypto/factory/

rm -rf $CORE_CLASSES/com/example/app/crypto/factory

mvn package -DskipTests
