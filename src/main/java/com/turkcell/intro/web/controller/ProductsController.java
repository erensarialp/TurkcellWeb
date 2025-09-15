package com.turkcell.intro.web.controller;

import com.turkcell.intro.web.dto.product.request.CreateProductRequest;
import com.turkcell.intro.web.dto.product.request.SearchProductRequest;
import com.turkcell.intro.web.dto.product.response.CreatedProductResponse;
import com.turkcell.intro.web.dto.product.response.GetByIdProductResponse;
import com.turkcell.intro.web.dto.product.response.SearchProductResponse;
import com.turkcell.intro.web.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //Spring tarafindan RestController olarak taninsin.
@RequestMapping("/api/v1/products") //localhost:port/api/v1/products ile basliyorsa istek buraya gelsin.
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public GetByIdProductResponse getById(@PathVariable int id){
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse add(@RequestBody CreateProductRequest request){
        return productService.add(request);
    }

    @GetMapping("search")
    public List<SearchProductResponse> search(SearchProductRequest request){

        return productService.search(request);
    }



}
