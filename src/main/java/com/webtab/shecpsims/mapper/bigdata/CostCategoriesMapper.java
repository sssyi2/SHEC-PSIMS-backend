package com.webtab.shecpsims.mapper.bigdata;

import com.webtab.shecpsims.model.entity.bigdata.CostCategories;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CostCategoriesMapper {

    @Select("SELECT * FROM cost_categories WHERE is_active = 1 ORDER BY display_order")
    List<CostCategories> findAllActiveCategories();
}