package com.ttzf.service.internetCafe;

import com.ttzf.model.internetCafe.InternetCafeDTO;
import com.ttzf.model.internetCafe.InternetCafeImgDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by zy on 2019/6/4
 */
public interface InternetCafeService {

    /**
     * 查询网咖详情分页
     * @param internetCafeDTO
     * @return
     * @throws Exception
     */
    Map queryInternetCafePager(InternetCafeDTO internetCafeDTO) throws Exception;

    /**
     *  查询网咖集合
     * @param internetCafeDTO
     * @return
     * @throws Exception
     */
    List<InternetCafeDTO> queryInternetCafeList(InternetCafeDTO internetCafeDTO) throws Exception;

    /**
     *  小程序调用网咖显示接口
     * @param internetCafeDTO
     * @return
     * @throws Exception
     */
    Map<String, String> queryInternetCafeB2C(InternetCafeDTO internetCafeDTO) throws Exception;

    /**
     *  查询网咖图片集合
     * @param internetCafeImgDTO
     * @return
     * @throws Exception
     */
    List<InternetCafeImgDTO> queryInternetCafeImgList(InternetCafeImgDTO internetCafeImgDTO) throws Exception;

    /**
     *  新增网咖
     * @param internetCafeDTO
     * @return
     * @throws Exception
     */
    Map addInternetCafe(InternetCafeDTO internetCafeDTO) throws Exception;

    /**
     *  新增网咖图片
     * @param internetCafeImgDTO
     * @return
     * @throws Exception
     */
    Map addInternetCafeImg(InternetCafeImgDTO internetCafeImgDTO) throws Exception;

    /**
     *  批量新增图片
     * @param list
     * @return
     * @throws Exception
     */
    Map addInternetCafeImgBatch(List<InternetCafeImgDTO> list) throws Exception;

    /**
     *  修改网咖信息
     * @param internetCafeDTO
     * @return
     * @throws Exception
     */
    Map updateInternetCafe(InternetCafeDTO internetCafeDTO) throws Exception;

    /**
     * 修改网咖图片信息
     *
     * @param internetCafeImgDTO
     * @return
     */
    Map updateInternetCafeImg(InternetCafeImgDTO internetCafeImgDTO) throws Exception;

    /**
     * 删除网咖信息
     * @param id
     * @return
     * @throws Exception
     */
    Map deleteInternetCafe(String id) throws Exception;



}
