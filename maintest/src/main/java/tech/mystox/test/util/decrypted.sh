#!/bin/bash

# Base16（Hex）编码的密文
#CIPHER_BASE16="$1"

# Base16（Hex）解码为原始字节数据
#CIPHER=$(echo -n "$CIPHER_BASE16" | xxd -r -p)

#CIPHER=$(echo -n "$CIPHER" | base64)
CIPHER="cfrnoI5spJFPJHTj9inAF4Wn+3NMumwrrpnbMhWdmU9Yqgqub0jVwXKiwSnpOOmPftrXa/p1xBH+BBaOl4xKOg==" 
echo $CIPHER
# RSA 私钥文件路径
PRIVATE_KEY_FILE="azkban_private_key.pem"

# 使用 OpenSSL 进行解密
PLAIN=$(echo -n "$CIPHER" | base64 -d | openssl rsautl -decrypt -inkey "$PRIVATE_KEY_FILE")

if [ $? -eq 0 ]; then
  echo "解密成功！解密后的明文：$PLAIN"
else
  echo "解密失败！"
fi
