package com.ttzf.service.internetCafeImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttzf.dao.internetCafe.InternetCafeOwnerDao;
import com.ttzf.enums.CommonEnum;
import com.ttzf.model.internetCafe.InternetCafeOwnerDTO;
import com.ttzf.service.internetCafe.InternetCafeOwnerService;
import com.ttzf.utils.CommonUtils;
import com.ttzf.utils.PagerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class InternetCafeOwnerServiceImpl implements InternetCafeOwnerService {

    @Autowired
    private InternetCafeOwnerDao internetCafeOwnerDao;

    private static Logger logger = LoggerFactory.getLogger(InternetCafeOwnerServiceImpl.class.getName());


    @Override
    public Map queryInternetCafeOwnerPager(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception {
        try {
            PageHelper.startPage(internetCafeOwnerDTO.getPage(), internetCafeOwnerDTO.getLimit());
            List<InternetCafeOwnerDTO> list = internetCafeOwnerDao.selectInternetCafeOwnerList(internetCafeOwnerDTO);
            PageInfo pageInfo = new PageInfo(list);
            return PagerUtils.parserPager("0", "", pageInfo.getTotal(), pageInfo);
        } catch (Exception e) {
            logger.error(e+"");
            throw new Exception("查询internetCafeOwnerList分页出错"+e);
        }
    }

    @Override
    public List<InternetCafeOwnerDTO> queryInternetCafeOwnerList(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception {
        try {
            return internetCafeOwnerDao.selectInternetCafeOwnerList(internetCafeOwnerDTO);
        } catch (Exception e) {
            logger.error(e+"");
            throw new Exception("查询internetCafeOwnerList出错");
        }
    }

    @Override
    public Map addInternetCafeOwner(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception {
        Map m = new HashMap();
        try {
            if (CommonUtils.isEmpty(internetCafeOwnerDTO.getId())) {
                internetCafeOwnerDTO.setId(UUID.randomUUID().toString());
            }
            if (CommonUtils.isNotEmpty(internetCafeOwnerDTO.getCardId())) {
                String isSex = internetCafeOwnerDTO.getCardId().substring(17, 17);
                if (Integer.parseInt(isSex) / 2 == 0) {
                    //女性
                    internetCafeOwnerDTO.setSex(1);
                } else {
                    //男性
                    internetCafeOwnerDTO.setSex(2);
                }
            }
            internetCafeOwnerDao.insertInternetCafeOwner(internetCafeOwnerDTO);
            m.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
            m.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.INSERT_SUCCESS.getCode());
            return m;
        } catch (Exception e) {
            logger.error(e + "");
            throw new Exception("添加internetCafeOwner出错"+e);
        }
    }

    @Override
    public Map updateInternetCafeOwner(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception {
        Map map = new HashMap();
        try {
            if (CommonUtils.isEmpty(internetCafeOwnerDTO.getId())) {
                map.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.FAIL_CODE.getCode());
                map.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.UPDATE_ERRO.getCode());
                return map;
            }
            internetCafeOwnerDao.updateInternetCafeOwner(internetCafeOwnerDTO);
            map.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
            map.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.UPDATE_SUCCESS.getCode());
            return map;
        } catch (Exception e) {
            logger.error(e + "");
            throw new Exception("更新internetCafeOwner出错"+e);
        }
    }

    @Override
    public Map deleteInternetCafeOwner(String ids) throws Exception {
        Map map = new HashMap();
        try {
            if (CommonUtils.isEmpty(ids)) {
                map.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.FAIL_CODE.getCode());
                map.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.DELETE_ERRO.getCode());
                return map;
            }
            internetCafeOwnerDao.deleteInternetCafeOwner(ids);
            map.put(CommonEnum.CONSTANTS_CODE.getCode(), CommonEnum.SUCCESS_CODE.getCode());
            map.put(CommonEnum.CONSTANTS_MSG.getCode(), CommonEnum.DELETE_SUCCESS.getCode());
            return map;
        } catch (Exception e) {
            logger.error(e+"");
            throw new Exception("删除internetCafeOwner出错" + e);
        }
    }
}
