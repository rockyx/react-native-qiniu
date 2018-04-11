import {
  NativeModules,
} from 'react-native';

import SHA1 from 'crypto-js/sha1';
import path from 'path';

export const uploadFile = async (fileUrl, token) => {
  const objectKey = SHA1(String(new Date().valueOf())) + path.extname(fileUrl);
  const QiniuManager = NativeModules.QiniuManager;
  await QiniuManager.uploadFile({
    objectKey,
    fileUrl,
    token,
  });
  return objectKey;
};
