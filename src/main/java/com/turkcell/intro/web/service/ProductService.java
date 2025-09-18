package com.turkcell.intro.web.service;

import com.turkcell.intro.web.core.exception.type.BusinessException;
import com.turkcell.intro.web.dto.product.request.CreateProductRequest;
import com.turkcell.intro.web.dto.product.request.SearchProductRequest;
import com.turkcell.intro.web.dto.product.response.CreatedProductResponse;
import com.turkcell.intro.web.dto.product.response.GetByIdProductResponse;
import com.turkcell.intro.web.dto.product.response.SearchProductResponse;
import com.turkcell.intro.web.entity.Category;
import com.turkcell.intro.web.entity.Product;
import com.turkcell.intro.web.mapper.ProductMapper;
import com.turkcell.intro.web.repository.ProductRepository;
import com.turkcell.intro.web.rules.CategoryBusinessRules;
import com.turkcell.intro.web.rules.ProductBusinessRules;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service //IoC'e bean olarak ekle.
@Validated
public class ProductService {

    //final -> yalnizca constructor uzerinden set edilir.
    private final ProductRepository productRepository;
    private final ProductBusinessRules productBusinessRules;
    private final CategoryService categoryService;
    private final CategoryBusinessRules categoryBusinessRules;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductBusinessRules productBusinessRules, CategoryService categoryService, CategoryBusinessRules categoryBusinessRules) {
        this.productRepository = productRepository;
        this.productBusinessRules = productBusinessRules;
        this.categoryService = categoryService;
        this.categoryBusinessRules = categoryBusinessRules;
        this.productMapper = ProductMapper.INSTANCE;
    }


    //Servis - Servis cagrisindan dolayi validasyonu service icinde yapmak en dogurusudur
    //Controller'a da eklemek iyi olur.Yani hem Service hem Controller'da @Valid kullan.
    //Fonksiyona gelen her cagri void olmalidir.
    public CreatedProductResponse add(@Valid CreateProductRequest createProductRequest) {

        productBusinessRules.productMustNotExistWithSameName(createProductRequest.getName());
        Category category = categoryBusinessRules.categoryShouldExistWithGivenId(createProductRequest.getCategoryId());

        Product product = productMapper.createProductRequestToProduct(createProductRequest);
        productRepository.save(product);

        return productMapper.productToCreatedProductResponse(product);
    }


    public GetByIdProductResponse getById(int id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Bu id ile bir urun bulunamadi."));

        return new GetByIdProductResponse(product.getId(),
                product.getName(),
                product.getStock(),
                product.getDescription(),
                product.getUnitPrice(),
                product.getCategory().getId(),
                product.getCategory().getName());
    }

    public List<SearchProductResponse> search(SearchProductRequest request){

        List<Product> productList = productRepository.search("%"+request.getName()+"%");

        List<SearchProductResponse> responseList = new ArrayList<>();

        for (Product product : productList){
            SearchProductResponse response = new SearchProductResponse();
            response.setId(product.getId());
            response.setCategoryId(product.getCategory().getId());
            response.setCategoryName(product.getCategory().getName());
            response.setDescription(product.getDescription());
            response.setName(product.getName());

            responseList.add(response);
        }

        return responseList;
    }

}
