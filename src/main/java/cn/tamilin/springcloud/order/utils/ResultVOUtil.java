package cn.tamilin.springcloud.order.utils;


import cn.tamilin.springcloud.order.VO.ResultVO;

/**
 * @ClassName ResultVOUtil
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/7 00:34
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("sucess");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        resultVO.setData(null);
        return resultVO;
    }
}
