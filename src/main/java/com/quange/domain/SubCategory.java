package com.quange.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("sub_category")
public class SubCategory {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "parentsort")
    private Integer parentSort;

    private String name;


    // 外键
    @TableField(select = false)
    private List<Sites> sites;

}
