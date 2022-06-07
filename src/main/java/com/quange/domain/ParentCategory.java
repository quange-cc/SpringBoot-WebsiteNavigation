package com.quange.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("parent_category")
public class ParentCategory {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "parentname")
    private String parentName;


    @TableField(select = false)
    private Integer tag;

    @TableField(select = false)
    private List<ParentCategory> children;


}
