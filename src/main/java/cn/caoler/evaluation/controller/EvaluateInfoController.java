package cn.caoler.evaluation.controller;

import cn.caoler.evaluation.vo.ResultVO;
import cn.caoler.evaluation.dto.EvaluateInfoDTO;
import cn.caoler.evaluation.dto.UserDTO;
import cn.caoler.evaluation.service.EvaluateInfoService;
import cn.caoler.evaluation.service.RoleService;
import cn.caoler.evaluation.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by caole on 2019/4/16 14:33
 */
@RestController
@RequestMapping("/evaluateInfo")
@Slf4j
public class EvaluateInfoController {

    @Autowired
    private EvaluateInfoService evaluateInfoService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/list")
    public ResultVO<List<List<EvaluateInfoDTO>>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                      @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                                      EvaluateInfoDTO evaluateInfoDTO,
                                                      HttpSession session) throws UnsupportedEncodingException {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        evaluateInfoDTO.setEvaluatorId(userDTO.getUserId());

        Integer count = evaluateInfoService.countForPageByEvaluatorId(evaluateInfoDTO.getTeacherId(),evaluateInfoDTO.getEvaluatorId());

        /** 此处代码作用：  从前端传回来的值会乱码，在这里处理掉乱码问题.*/
        if (evaluateInfoDTO.getTeacherName() != null) {
            String fname =  URLDecoder.decode(evaluateInfoDTO.getTeacherName() , "utf-8");
            evaluateInfoDTO.setTeacherName(fname);
        }


        PageRequest request = PageRequest.of(page-1,limit);
        Page<EvaluateInfoDTO> evaluateInfoDTOPage = evaluateInfoService.findEvaluateInfosByEvaluatorId(evaluateInfoDTO.getTeacherId(),evaluateInfoDTO.getEvaluatorId(),request);
        for (EvaluateInfoDTO edto : evaluateInfoDTOPage.getContent()){
            edto.setRoleName(roleService.findByRoleId(edto.getRoleId()).getRoleName());
        }
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(evaluateInfoDTOPage.getContent(),count);
    }
}
