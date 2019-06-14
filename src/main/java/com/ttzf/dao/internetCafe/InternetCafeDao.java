package com.ttzf.dao.internetCafe;

import com.ttzf.model.internetCafe.InternetCafeDTO;
import com.ttzf.model.internetCafe.InternetCafeImgDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternetCafeDao {

    List<InternetCafeDTO> selectInternetCafeList(InternetCafeDTO internetCafeDTO);

    List<InternetCafeDTO> selectInternetCafePager(InternetCafeDTO internetCafeDTO);

    /**
     *  通过网咖id查找图片
     * @param cafeId
     * @return
     */
    List<InternetCafeImgDTO> selectInternetCafeImgList(@Param("cafeId") String cafeId);

    InternetCafeDTO selectInternetCafeOne(InternetCafeDTO internetCafeDTO);

    int insertInternetCafe(InternetCafeDTO internetCafeDTO);

    int insertInternetCafeImg(InternetCafeImgDTO internetCafeImgDTO);

    int insertInternetCafeImgBatch(List<InternetCafeImgDTO> list);

    int updateInternetCafe(InternetCafeDTO internetCafeDTO);

    int updateInternetCafeImg(InternetCafeImgDTO internetCafeImgDTO);

    int deleteInternetCafe(@Param("id") String id);



}