package com.quange.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("background_style")
public class BackgroundStyle {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String style;
}
