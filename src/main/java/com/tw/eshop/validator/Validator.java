package com.tw.eshop.validator;

import com.tw.eshop.vo.ValidateResult;

/**
 * Created by qbhuang on 16/8/18.
 */
public interface Validator<T> {
    ValidateResult validate(T data);
}
