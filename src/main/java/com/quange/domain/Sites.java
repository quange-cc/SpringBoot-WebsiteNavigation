package com.quange.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Sites{
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("subsort")
    private Integer subSort;

    private String title;
    private String description;
    private String url;
    private String icon;


    // 外键
    @TableField(select = false)
    private String parent;

    // 外键
    @TableField(select = false)
    private Integer parentId;

    // 外键
    @TableField(select = false)
    private String sort;

    @TableField(select = false)
    private String name;


}
