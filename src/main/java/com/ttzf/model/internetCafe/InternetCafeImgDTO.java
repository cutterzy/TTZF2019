package com.ttzf.model.internetCafe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  网咖图片
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InternetCafeImgDTO implements Serializable {

    private String id ;

    private String cafeId;

    private String imgUrl;

    private String imgSort;

    private String imgName;

    private String createTime;

    private String modifyTime;

    //是否是主图片  1主图片
    private Integer major;
}
