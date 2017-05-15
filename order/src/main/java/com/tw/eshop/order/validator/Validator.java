package com.tw.eshop.order.validator;

import com.tw.eshop.order.vo.ValidateResult;

/**
 * Created by qbhuang on 16/8/18.
 */
@FunctionalInterface
public interface Validator<T> {
    ValidateResult validate(T data);
}
