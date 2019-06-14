package com.ttzf.dao.internetCafe;

import com.ttzf.model.internetCafe.InternetCafeOwnerDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InternetCafeOwnerDao {

    List<InternetCafeOwnerDTO> selectInternetCafeOwnerList(InternetCafeOwnerDTO internetCafeOwnerDTO);

    int insertInternetCafeOwner(InternetCafeOwnerDTO internetCafeOwnerDTO);

    int updateInternetCafeOwner(InternetCafeOwnerDTO internetCafeOwnerDTO);

    int deleteInternetCafeOwner(@Param("id") String id);

}