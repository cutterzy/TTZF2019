package com.ttzf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 *  分页
 */
public class Pager {

    private Integer page;
    private Integer limit;
}
