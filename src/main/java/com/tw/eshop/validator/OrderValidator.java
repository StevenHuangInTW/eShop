package com.tw.eshop.validator;

import com.tw.eshop.model.Order;
import com.tw.eshop.vo.ValidateResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by qbhuang on 16/8/18.
 */

@Component
public class OrderValidator implements Validator<Order>{

    @Override
    public ValidateResult validate(Order data) {
        if(data == null){
            return new ValidateResult(false, "Order cannot be null");
        }

        if(StringUtils.isEmpty(data.getName())){
            return new ValidateResult(false, "There is no order name");
        }

        if(StringUtils.isEmpty(StringUtils.trimAllWhitespace(data.getName()))){
            return new ValidateResult(false, "There is no order name");
        }

        if(data.getPrice() == null){
            return new ValidateResult(false, "There is no order price.");
        }

        if(data.getPrice().doubleValue()<= 0 ){
            return  new ValidateResult(false, "Price should greater than 0.");
        }

        if(data.getQty() == null){
            return new ValidateResult(false, "There is no order qty.");
        }

        if(data.getQty().intValue() <= 0){
            return new ValidateResult(false, "Qty should greater than 0");
        }

        return new ValidateResult(true);
    }
}
