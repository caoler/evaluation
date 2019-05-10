package cn.caoler.evaluation.controller;

import cn.caoler.evaluation.dataobject.ActionRole;
import cn.caoler.evaluation.dataobject.Role;
import cn.caoler.evaluation.vo.ResultVO;
import cn.caoler.evaluation.dto.ActionRoleDTO;
import cn.caoler.evaluation.enums.ResultEnum;
import cn.caoler.evaluation.exception.EvaluationException;
import cn.caoler.evaluation.service.ActionRoleService;
import cn.caoler.evaluation.service.RoleService;
import cn.caoler.evaluation.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by caole on 2019/4/3 17:12
 */
@RestController
@RequestMapping("/actionRole")
@Slf4j
public class ActionRoleController {

    @Autowired
    private ActionRoleService actionRoleService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/list")
    public ResultVO<List<ActionRole>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                           @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                           Role role) throws UnsupportedEncodingException {

        Integer count = actionRoleService.countActionRoleForPage(role.getRoleName());

        /** 此处代码作用：  从前端传回来的值会乱码，在这里处理掉乱码问题.*/
        if (role.getRoleName() != null) {
            String fname =  URLDecoder.decode(role.getRoleName(), "utf-8");
            role.setRoleName(fname);
        }

        PageRequest request = PageRequest.of(page-1,limit);
        Page<ActionRoleDTO> actionRoleDTOPage = actionRoleService.findRoleActionIfRoleNameIsNotNull(role.getRoleName(),request);
        for (int i=0;i<actionRoleDTOPage.getContent().size();i++){
            actionRoleDTOPage.getContent().get(i).setActionNames(actionRoleService.findActionNamesByRoleId(actionRoleDTOPage.getContent().get(i).getRoleId()));
            actionRoleDTOPage.getContent().get(i).setRoleName(roleService.findByRoleId(actionRoleDTOPage.getContent().get(i).getRoleId()).getRoleName());
        }
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(actionRoleDTOPage.getContent(),count);


    }




    @PostMapping("/save")
    public ResultVO<Null> save(ActionRoleDTO actionRoleDTO){

        if (actionRoleDTO != null && actionRoleDTO.getActionRoleId() != null){
            ActionRoleDTO updateResult = actionRoleService.update(actionRoleDTO);
            if (updateResult == null){
                throw new EvaluationException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            ActionRoleDTO createResult = actionRoleService.create(actionRoleDTO);
            if (createResult == null){
                throw new EvaluationException(ResultEnum.CREATE_ERROR);
            }
        }

        return ResultVOUtil.success();
    }




    @GetMapping("/findByActionRoleId")
    public ResultVO<ActionRoleDTO> findByActionId(Long actionRoleId){
        ActionRoleDTO actionRoleDTO =  actionRoleService.findByActionRoleId(actionRoleId);
        return ResultVOUtil.success(actionRoleDTO);
    }


    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems,Long id){
        if (delitems != null) { /*判断时单行删除还是批量删除*/
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                actionRoleService.deleteActionRolesByRoleId(a);
            }
        } else {
            actionRoleService.deleteActionRolesByRoleId(id);
        }
        return ResultVOUtil.success();
    }

}
