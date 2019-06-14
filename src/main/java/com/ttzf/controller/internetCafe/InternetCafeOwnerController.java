package com.ttzf.controller.internetCafe;

import com.ttzf.model.internetCafe.InternetCafeOwnerDTO;
import com.ttzf.service.internetCafe.InternetCafeOwnerService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zy on 2019/6/13
 */
@RestController
@RequestMapping("/internetCafeOwner")
@Api(tags = "internetCafeOwner",description = "网咖老板")
public class InternetCafeOwnerController {

    private static Logger logger = LoggerFactory.getLogger(InternetCafeOwnerController.class.getName());

    @Autowired
    private InternetCafeOwnerService internetCafeOwnerService;

    @PostMapping("/queryPage")
    @ApiOperation(value = "查询分页")
    public Object queryPage(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception{
        try {
            return internetCafeOwnerService.queryInternetCafeOwnerPager(internetCafeOwnerDTO);
        } catch (Exception e) {
            logger.error(e + "");
            throw new Exception(e);
        }
    }

    @PostMapping("/queryList")
    @ApiOperation(value = "查询集合")
    public Object queryList(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception{
        try {
            return internetCafeOwnerService.queryInternetCafeOwnerList(internetCafeOwnerDTO);
        } catch (Exception e) {
            logger.error(e+"");
            throw new Exception(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public Object add(InternetCafeOwnerDTO internetCafeOwnerDTO)throws Exception {
        try {
            return internetCafeOwnerService.addInternetCafeOwner(internetCafeOwnerDTO);
        } catch (Exception e) {
            logger.error(e + "");
            throw new Exception(e);
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Object update(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception{
        try {
            return internetCafeOwnerService.updateInternetCafeOwner(internetCafeOwnerDTO);
        } catch (Exception e) {
            logger.error(e + "");
            throw new Exception(e);
        }
    }
}
