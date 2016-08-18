package com.tw.eshop.validator;

import com.tw.eshop.vo.ValidateResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by qbhuang on 16/8/18.
 */

@Component
public class NotBlankValidator implements Validator<String > {
    @Override
    public ValidateResult validate(String data) {
        if(StringUtils.isEmpty(data)){
            return new ValidateResult(false, "Empty String");
        }

        if(StringUtils.isEmpty(StringUtils.trimAllWhitespace(data))){
            return new ValidateResult(false, "Empty String");
        }

        return new ValidateResult(true);
    }
}
