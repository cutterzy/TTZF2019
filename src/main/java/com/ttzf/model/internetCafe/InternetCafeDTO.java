package com.ttzf.model.internetCafe;

import com.ttzf.model.Pager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by zy on 2019/6/4
 *  网咖信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternetCafeDTO extends Pager implements Serializable{

    private String id;
    //网咖名
    private String cafeName;
    //网咖规格
    private String cafeSize;
    //网咖规格单位
    private String cafeUnit;
    //网咖法人id
    private String cafeManagerId;
    //网咖法人名
    private String cafeManagerName;
    //网咖等级 S:钻石网咖 A:金牌网咖  B:银牌网咖 C:铜牌网咖 D:普通网咖
    private String cafeLevel;
    //网咖热度 >30 *1    >50 **2   >70 ***3 >90****4 >95 *****5
    private Integer cafeGrade;
    //网咖营业时间 hh:ss:mm-hh:ss:mm
    private String cafeWorkTime;
    //网咖公告
    private String cafeNotice;
    //网咖图片
    private String cafeImg;
    //网咖维度
    private Double cafeDimension;
    //网咖经度
    private Double cafeTranslate;
    //网咖创建时间
    private String cafeCreateTime;
    //网咖操作员
    private String cafeCreateOperate;
    //网咖状态 1正常 2异常
    private String cafeState;
    //网咖标签
    private String cafeTag;
    //网咖价格 /按每小时算
    private Double cafePrice;

    private Double minPrice;

    private Float maxPrice;

    //当前纬度
    private Double currentDimension;

    //当前经度
    private Double currentTranslate;

    //距离 km
    private Double kilometre;

    /**
     *  小程序筛选条件 用“，”分割
     *  离我最近 nearnest
     *  价格最低 cheapest
     *  热度最高
     */
    private String condition;


}
