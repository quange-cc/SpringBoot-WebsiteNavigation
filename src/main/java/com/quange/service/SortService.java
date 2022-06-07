package com.quange.service;

import com.quange.domain.ParentCategory;
import com.quange.domain.SubCategory;

import java.util.List;

public interface SortService {

    List<ParentCategory> getParentAll();

    List<SubCategory> getSubById(Integer id);


    boolean addSubSort(String s);

    boolean delSubSort(Integer id);

    boolean updateSubSort(String s);

}
