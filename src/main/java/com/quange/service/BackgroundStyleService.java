package com.quange.service;


import com.quange.domain.BackgroundStyle;

import java.util.List;

public interface BackgroundStyleService {

    List<BackgroundStyle> getBackgroundStyles();

    boolean addBackgroundStyle(String s);

    boolean deleteBackgroundStyle(Integer id);
}
