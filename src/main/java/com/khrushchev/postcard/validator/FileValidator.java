package com.khrushchev.postcard.validator;

import java.io.File;

public class FileValidator {
    public static  boolean isFiveExistIsFileisNull(String filePath){
        if(filePath!=null)
        {
            File file = new File(filePath);
            if (file.exists() && file.canRead() && file.isFile() && file.length() > 0) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }
}

