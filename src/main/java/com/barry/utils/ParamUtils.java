package com.barry.utils;

import com.barry.exceptions.BadRequestException;
import org.apache.commons.lang3.StringUtils;

public class ParamUtils {

    public static void validateCodeMagasin(String code){
        if(StringUtils.isEmpty(code)) {
            throw new BadRequestException("Bad 'code magasin' [{}], it needs to be non empty");
        }

        if(StringUtils.isBlank(code)) {
            throw new BadRequestException("Bad 'code magasin' [{}], it needs to be non blank");
        }
    }
}
