package cn.caoler.evaluation.exception;

import cn.caoler.evaluation.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by caole on 2019/3/17 15:21
 */
@Getter
public class EvaluationException extends RuntimeException {

    private Integer code;

    public EvaluationException(ResultEnum resultEnum){
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }


    public EvaluationException(Integer code,String message){
        super(message);
        this.code = code;
    }





}
