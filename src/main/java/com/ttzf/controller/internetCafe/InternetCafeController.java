package com.ttzf.controller.internetCafe;

import com.ttzf.model.internetCafe.InternetCafeDTO;
import com.ttzf.model.internetCafe.InternetCafeImgDTO;
import com.ttzf.service.internetCafe.InternetCafeService;
import com.ttzf.utils.CommonUtils;
import com.ttzf.utils.UploadUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by zy on 2019/6/4
 */
@RestController
@RequestMapping("/internetCafe")
@Api(tags = "internetCafe",description = "智慧网咖")
public class InternetCafeController {

    private static Logger logger = LoggerFactory.getLogger(InternetCafeController.class.getName());

    @Autowired
    private InternetCafeService internetCafeService;


    @PostMapping("/queryList")
    @ApiOperation(value = "网咖详情")
    public Object queryList(@Valid  InternetCafeDTO internetCafeDTO, BindingResult result) throws Exception {
        CommonUtils.validDate(result);
        try {
            return internetCafeService.queryInternetCafePager(internetCafeDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
    }

    @GetMapping("/queryB2C")
    @ApiOperation(value = "小程序调用展示接口")
    public Object queryB2C(InternetCafeDTO internetCafeDTO) throws Exception {
        try {
            return internetCafeService.queryInternetCafeB2C(internetCafeDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
    }


    @PostMapping("/add")
    @ApiOperation(value = "网咖新增")
    public Object add(InternetCafeDTO internetCafeDTO) throws Exception {
        try {
            return internetCafeService.addInternetCafe(internetCafeDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "网咖修改")
    public Object update(InternetCafeDTO internetCafeDTO) throws Exception {
        try {
            return internetCafeService.updateInternetCafe(internetCafeDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
    }

    @PostMapping("/uploadPic")
    @ApiOperation(value = "网咖门店上传")
    public Object uploadPic(@RequestParam("file") MultipartFile[] files, @RequestParam("cafeId") String cafeId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            if (files != null) {
                //判断上传的文件是否为空
                List<InternetCafeImgDTO> list = new ArrayList<>();
                Arrays.stream(files).forEach(e->{
                    String imgName = UploadUtils.generateRandonFileName(e.getOriginalFilename());
                    String imgUrl = UploadUtils.generateRandomDir(imgName);
                    InternetCafeImgDTO internetCafeImgDTO = InternetCafeImgDTO.builder().
                            id(UUID.randomUUID().toString()).
                            imgName(imgName).
                            imgUrl(imgUrl).
                            createTime(CommonUtils.getNowDateTime()).
                            modifyTime(CommonUtils.getNowDateTime()).
                            cafeId(cafeId).
                            build();
                    list.add(internetCafeImgDTO);
                });
                return internetCafeService.addInternetCafeImgBatch(list);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
        return null;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "网咖删除")
    public Object delete(@RequestParam("id") String id) throws Exception {
        try {
            return internetCafeService.deleteInternetCafe(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
    }

    @PostMapping("/queryCafeImg")
    @ApiOperation(value = "查看网咖详情图片")
    public Object queryCafeImg(InternetCafeImgDTO internetCafeImgDTO)throws Exception {
        try {
            return internetCafeService.queryInternetCafeImgList(internetCafeImgDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
    }

}
