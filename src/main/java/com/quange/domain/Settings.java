package com.quange.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Settings {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;
    private String domain;
    private int cache;
    private String title;
    private String des;
    private String keyword;
    private String bottom;
}
