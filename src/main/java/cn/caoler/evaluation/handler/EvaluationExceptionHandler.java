package cn.caoler.evaluation.handler;

import cn.caoler.evaluation.exception.EvaluationException;
import cn.caoler.evaluation.vo.ResultVO;
import cn.caoler.evaluation.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  异常处理
 * Created by caole on 2019/3/20 1:27
 */
@ControllerAdvice
public class EvaluationExceptionHandler {

    @ExceptionHandler(value = EvaluationException.class)
    @ResponseBody
    public ResultVO handlerEvaluation(EvaluationException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
