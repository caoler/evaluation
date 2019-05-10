package cn.caoler.evaluation.controller;

import cn.caoler.evaluation.dataobject.ChannelControl;
import cn.caoler.evaluation.vo.ResultVO;
import cn.caoler.evaluation.dto.ChannelControlDTO;
import cn.caoler.evaluation.service.ChannelControlService;
import cn.caoler.evaluation.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by caole on 2019/4/12 23:05
 */
@RestController
@RequestMapping("/channelControl")
public class ChannelControlController {

    @Autowired
    private ChannelControlService channelControlService;

    @GetMapping("/list")
    public ResultVO<List<ChannelControl>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "limit",defaultValue = "10") Integer limit) {
        Integer count = channelControlService.count();



        PageRequest request = PageRequest.of(page-1,limit);
        Page<ChannelControlDTO> channelControlDTOPage = channelControlService.findAll(request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(channelControlDTOPage.getContent(),count);


    }


    @PostMapping("/change")
    public ResultVO<Null> change(ChannelControlDTO channelControlDTO){
        ChannelControlDTO result = channelControlService.findByChannelControlId(channelControlDTO.getChannelControlId());
        if (result.getChannelState() == 0){
            result.setChannelState(1);
        }else {
            result.setChannelState(0);
        }

        channelControlService.update(result);

        return ResultVOUtil.success();
    }

}
