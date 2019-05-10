package cn.caoler.evaluation.utils;

import cn.caoler.evaluation.vo.ResultVO;

/**
 * Created by caole on 2019/3/17 11:53
 */
public class ResultVOUtil {

    public static ResultVO success(Object object,Integer count){        //为layui服务
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCount(count);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(Object object,Long teacherId){        //为layui服务
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setTeacherId(teacherId);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }


    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
