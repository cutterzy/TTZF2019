package com.ttzf.service.internetCafeImpl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttzf.dao.internetCafe.InternetCafeDao;
import com.ttzf.enums.CommonEnum;
import com.ttzf.enums.internetCafe.InternetCafeEnum;
import com.ttzf.model.internetCafe.InternetCafeDTO;
import com.ttzf.model.internetCafe.InternetCafeImgDTO;
import com.ttzf.service.internetCafe.InternetCafeService;
import com.ttzf.utils.CommonUtils;
import com.ttzf.utils.PagerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zy on 2019/6/4
 */
@Service("internetCafeService")
public class InternetCafeServiceImpl implements InternetCafeService {

    private static final double EARTH_RADIUS = 6378137;//赤道半径(单位m)

    private static Logger logger = LoggerFactory.getLogger(InternetCafeServiceImpl.class.getName());

    @Autowired
    private InternetCafeDao internetCafeDao;


    @Override
    public Map queryInternetCafePager(InternetCafeDTO internetCafeDTO) throws Exception {
        try {
            PageHelper.startPage(internetCafeDTO.getPage(), internetCafeDTO.getLimit());
            List<InternetCafeDTO> list = internetCafeDao.selectInternetCafePager(internetCafeDTO);
            PageInfo pageInfo = new PageInfo(list);
            return PagerUtils.parserPager("0", "", pageInfo.getTotal(), pageInfo);
        } catch (Exception e) {
            throw new Exception("查询internetCafeList分页出错"+e);
        }
    }

    @Override
    public List<InternetCafeDTO> queryInternetCafeList(InternetCafeDTO internetCafeDTO) throws Exception {
        try {
            return internetCafeDao.selectInternetCafeList(internetCafeDTO);
        } catch (Exception e) {
            throw new Exception("查询internetCafeList出错"+e);
        }
    }

    @Override
    public Map<String, String> queryInternetCafeB2C(InternetCafeDTO internetCafeDTO) throws Exception {
        Map map = new HashMap();
        try {
            if (CommonUtils.isEmpty(internetCafeDTO.getCurrentDimension()) || CommonUtils.isEmpty(internetCafeDTO.getCurrentTranslate())) {
                map.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.FAIL_CODE.getCode());
                map.put(CommonEnum.CONSTANTS_MSG.getCode(), "未获取到当前用户定位");
                return map;
            }
            List<InternetCafeDTO> list = internetCafeDao.selectInternetCafeList(internetCafeDTO);
            //计算每个门店与当前用户的距离
            list.stream().forEach(e -> {
                e.setKilometre(getCurrentDist(internetCafeDTO.getCurrentDimension(),internetCafeDTO.getCurrentTranslate(),e.getCafeDimension(),internetCafeDTO.getCafeTranslate()));
            });
            Stream<InternetCafeDTO> stream = null;
            if (CommonUtils.isNotEmpty(internetCafeDTO.getCondition())) {
                if (InternetCafeEnum.CAFE_NEAREST.getName().indexOf(internetCafeDTO.getCondition()) > -1) {
                    //离我最近
                    stream = list.stream().sorted(Comparator.comparing(InternetCafeDTO::getKilometre));
                }
                if (InternetCafeEnum.CAFE_CHEAPEST.getName().indexOf(internetCafeDTO.getCondition()) > -1) {
                    //最便宜
                    stream = stream.sorted(Comparator.comparing(InternetCafeDTO::getCafePrice));
                }
                if (InternetCafeEnum.CAFE_HOTEST.getName().indexOf(internetCafeDTO.getCondition()) > -1) {
                    //热度最高
                    stream = stream.sorted(Comparator.comparing(InternetCafeDTO::getCafeGrade).reversed());
                }
            }
            List<InternetCafeDTO> data = stream.collect(Collectors.toList());
            map.put(CommonEnum.CONSTANTS_DATA.getCode(), JSON.toJSON(data));
            map.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
        } catch (Exception e) {
            throw new Exception("小程序查询网咖出错"+e);
        }
        return map;
    }

    @Override
    public List<InternetCafeImgDTO> queryInternetCafeImgList(InternetCafeImgDTO internetCafeImgDTO) throws Exception {
        try {
            return internetCafeDao.selectInternetCafeImgList(internetCafeImgDTO.getCafeId());
        } catch (Exception e) {
            logger.error(e + "");
            throw new Exception("查询网咖图片出错" + e);
        }
    }

    @Override
    public Map addInternetCafe(InternetCafeDTO internetCafeDTO) throws Exception {
        Map m = new HashMap();
        try {
            if (CommonUtils.isEmpty(internetCafeDTO.getId())) {
                internetCafeDTO.setId(UUID.randomUUID().toString());
            }
            if (CommonUtils.isEmpty(internetCafeDTO.getCafeCreateTime())) {
                internetCafeDTO.setCafeCreateOperate(CommonUtils.getNowDateTime());
            }
            //第一次创建热度默认给80分
            if (CommonUtils.isEmpty(internetCafeDTO.getCafeGrade())) {
                internetCafeDTO.setCafeGrade(80);
            }
            //网咖管理者
            if (CommonUtils.isNotEmpty(internetCafeDTO.getCafeManagerId())) {
                //todo
            }
            if (CommonUtils.isEmpty(internetCafeDTO.getCafeWorkTime())) {
                internetCafeDTO.setCafeWorkTime("00:00:00-23:59:59");
            }
            if (CommonUtils.isEmpty(internetCafeDTO.getCafeNotice())) {
                internetCafeDTO.setCafeNotice(InternetCafeEnum.CAFE_NOTICE_ZANWU.getName());
            }
            if (CommonUtils.isEmpty(internetCafeDTO.getCafeCreateOperate())) {
                internetCafeDTO.setCafeCreateOperate("admin");
            }
            if (CommonUtils.isEmpty(internetCafeDTO.getCafeTag())) {
                internetCafeDTO.setCafeTag(InternetCafeEnum.CAFE_NOTICE_ZANWU.getName());
            }
            if (CommonUtils.isNotEmpty(internetCafeDTO.getCafeState())) {
                if ("on".equals(internetCafeDTO.getCafeState())) {
                    internetCafeDTO.setCafeState("1");
                } else {
                    internetCafeDTO.setCafeState("2");
                }
            }
            internetCafeDTO.setCafeCreateTime(CommonUtils.getNowDateTime());
            InternetCafeDTO verfity = InternetCafeDTO.builder().cafeName(internetCafeDTO.getCafeName()).build();
            InternetCafeDTO verDTO = internetCafeDao.selectInternetCafeOne(verfity);
            if (CommonUtils.isNotEmpty(verDTO)) {
                m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.INSERT_ERRO.getCode());
                m.put(CommonEnum.CONSTANTS_MSG.getCode(), "当前已存在【" + internetCafeDTO.getCafeName() + "】");
                return m;
            }
            internetCafeDao.insertInternetCafe(internetCafeDTO);
            m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
            m.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.INSERT_SUCCESS.getCode());
            return m;
        } catch (Exception e) {
            logger.error(e+"");
            throw new Exception("插入网咖出错"+e);
        }
    }

    @Override
    public Map addInternetCafeImg(InternetCafeImgDTO internetCafeImgDTO) throws Exception {
        Map m = new HashMap();
        try {
            if (CommonUtils.isEmpty(internetCafeImgDTO)) {
                internetCafeImgDTO.setId(UUID.randomUUID().toString());
            }
            internetCafeDao.insertInternetCafeImg(internetCafeImgDTO);
            m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
            m.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.SUCCESS_CODE.getMsg());
            return m;
        } catch (Exception e) {
            logger.error(e + "");
            throw new Exception("插入网咖图片报错");
        }
    }

    @Override
    public Map addInternetCafeImgBatch(List<InternetCafeImgDTO> list) throws Exception {
        Map m = new HashMap();
        try {
            internetCafeDao.insertInternetCafeImgBatch(list);
            m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
            m.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.SUCCESS_CODE.getMsg());
            return m;
        } catch (Exception e) {
            logger.error(e + "");
            throw new Exception("插入网咖图片报错");
        }
    }

    @Override
    public Map updateInternetCafe(InternetCafeDTO internetCafeDTO) throws Exception {
        Map m = new HashMap();
        try {
            if (CommonUtils.isEmpty(internetCafeDTO.getId())) {
                m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.FAIL_CODE.getCode());
                m.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.UPDATE_ERRO.getCode());
                return m;
            }
            internetCafeDao.updateInternetCafe(internetCafeDTO);
            m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
            m.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.UPDATE_SUCCESS.getCode());
            return m;
        } catch (Exception e) {
            throw new Exception("修改网咖出错"+e);
        }
    }

    @Override
    public Map updateInternetCafeImg(InternetCafeImgDTO internetCafeImgDTO) throws Exception {
        Map m = new HashMap();
        try {
            if (CommonUtils.isEmpty(internetCafeImgDTO.getId()) && CommonUtils.isEmpty(internetCafeImgDTO.getCafeId())) {
                m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.FAIL_CODE.getCode());
                m.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.UPDATE_ERRO.getCode());
                return m;
            }
            internetCafeDao.updateInternetCafeImg(internetCafeImgDTO);
            m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
            m.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.UPDATE_SUCCESS.getCode());
            return m;
        } catch (Exception e) {
            logger.error(e + "");
            throw new Exception("修改网咖信息出错" + e);
        }
    }

    @Override
    public Map deleteInternetCafe(String id) throws Exception {
        Map m = new HashMap();
        try {
            if (CommonUtils.isNotEmpty(id)) {
                internetCafeDao.deleteInternetCafe(id);
                m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
                m.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.DELETE_SUCCESS.getCode());
                return m;
            }
        } catch (Exception e) {
            throw new Exception("删除网咖出错" + e);
        }
        return null;
    }

    //计算两经纬度的距离
    private static double getCurrentDist(double currentDimension, double currentTranslate, double cafeDimension, double cafeTranslate) {
        double radLat1 = currentDimension * Math.PI / 180.0;
        double radLat2 = cafeDimension * Math.PI / 180.0;
        double radLon1  = currentTranslate * Math.PI / 180.0;
        double radLon2  = cafeTranslate * Math.PI / 180.0;

        if (radLat1 < 0)
            radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
        if (radLat1 > 0)
            radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
        if (radLon1 < 0)
            radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
        if (radLat2 < 0)
            radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
        if (radLat2 > 0)
            radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
        if (radLon2 < 0)
            radLon2 = Math.PI * 2 - Math.abs(radLon2);// west

        double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = EARTH_RADIUS * Math.cos(radLat1);

        double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = EARTH_RADIUS * Math.cos(radLat2);

        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));
        //余弦定理求夹角
        double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
        double dist = theta * EARTH_RADIUS;
        return dist;
    }
}
