package com.turkcell.intro.web.service;

import com.turkcell.intro.web.dto.product.request.CreateProductRequest;
import com.turkcell.intro.web.dto.product.request.SearchProductRequest;
import com.turkcell.intro.web.dto.product.response.CreatedProductResponse;
import com.turkcell.intro.web.dto.product.response.GetByIdProductResponse;
import com.turkcell.intro.web.dto.product.response.SearchProductResponse;
import com.turkcell.intro.web.entity.Category;
import com.turkcell.intro.web.entity.Product;
import com.turkcell.intro.web.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service //IoC'e bean olarak ekle.
@Validated
public class ProductService {

    //final -> yalnizca constructor uzerinden set edilir.
    private ProductRepository productRepository;
    private CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    //Servis - Servis cagrisindan dolayi validasyonu service icinde yapmak en dogurusudur
    //Controller'a da eklemek iyi olur.Yani hem Service hem Controller'da @Valid kullan.
    public CreatedProductResponse add(@Valid CreateProductRequest createProductRequest){
        Product product = new Product();

        product.setName(createProductRequest.getName());
        product.setUnitPrice(createProductRequest.getUnitPrice());
        product.setStock(createProductRequest.getStock());
        product.setDescription(createProductRequest.getDescription());

        Category category = categoryService
                .findCategoryById(createProductRequest.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Bu id ile bir kategori bulunamadi."));
        product.setCategory(category);

        productRepository.save(product);

        return new CreatedProductResponse(
                product.getId(),
                product.getName(),
                product.getStock(),
                product.getDescription(),
                product.getUnitPrice());
    }


    public GetByIdProductResponse getById(int id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bu id ile bir urun bulunamadi."));

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
