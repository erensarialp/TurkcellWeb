package com.turkcell.intro.web.controller;

import com.turkcell.intro.web.entity.Product;
import com.turkcell.intro.web.repository.ProductRepository;
import com.turkcell.intro.web.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController  //Spring tarafindan RestController olarak taninsin.
@RequestMapping("/api/v1/products") //localhost:port/api/v1/products ile basliyorsa istek buraya gelsin.
public class ProductsController {

    //Dependency Injection
    private ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAll(){
        return productService.getAll();
    }

    //Ekleme endpointleri ekleme sonrasi durum icin eklenen entity'i geri doner.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //eger islem basarili olursa, status code olarak sunu dön.
    public Product add(@RequestBody Product product){
        productService.add(product);
        return  product;

    }

}
