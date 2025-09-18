package com.turkcell.intro.web.mapper;

import com.turkcell.intro.web.dto.category.request.CreateCategoryRequest;
import com.turkcell.intro.web.dto.category.request.UpdateCategoryRequest;
import com.turkcell.intro.web.dto.category.response.CreatedCategoryResponse;
import com.turkcell.intro.web.dto.category.response.GetByIdCategoryResponse;
import com.turkcell.intro.web.dto.category.response.GetListCategoryResponse;
import com.turkcell.intro.web.dto.category.response.UpdatedCategoryResponse;
import com.turkcell.intro.web.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<GetListCategoryResponse> toGetListCategoryResponse(List<Category> categoryList);

    GetByIdCategoryResponse toGetByIdCategoryResponse(Category category);

    Category toCategory(CreateCategoryRequest createCategoryRequest);

    CreatedCategoryResponse toCreatedCategoryResponse(Category category);

    //@Mapping(target = "id", ignore = true)
    Category toCategory(UpdateCategoryRequest updateCategoryRequest);

    UpdatedCategoryResponse toUpdatedCategoryResponse(Category category);
}
