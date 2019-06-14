package com.ttzf.model.internetCafe;

import com.ttzf.model.Pager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  网咖所有者
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InternetCafeOwnerDTO extends Pager implements Serializable {

    private String id ;

    private String name;

    //1女 2男
    private Integer sex;

    private String cardId;

    private String personImgUrl;

    private String businessImgUrl;
}
