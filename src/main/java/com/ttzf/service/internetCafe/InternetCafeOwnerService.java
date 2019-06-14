package com.ttzf.service.internetCafe;

import com.ttzf.model.internetCafe.InternetCafeOwnerDTO;

import java.util.List;
import java.util.Map;

public interface InternetCafeOwnerService {

    Map queryInternetCafeOwnerPager(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception;

    List<InternetCafeOwnerDTO> queryInternetCafeOwnerList(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception;

    Map addInternetCafeOwner(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception;

    Map updateInternetCafeOwner(InternetCafeOwnerDTO internetCafeOwnerDTO) throws Exception;

    Map deleteInternetCafeOwner(String ids) throws Exception;
}
