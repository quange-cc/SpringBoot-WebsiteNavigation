package com.quange.service;

import com.quange.domain.Settings;

import java.util.List;

public interface SettingService {


    boolean updateSettings(String s);

    List<Settings> getAll();
}
