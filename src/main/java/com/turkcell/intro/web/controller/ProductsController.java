package com.turkcell.intro.web.controller;

import com.turkcell.intro.web.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController  //Spring tarafindan RestController olarak taninsin.
@RequestMapping("/api/v1/products") //localhost:port/api/v1/products ile basliyorsa istek buraya gelsin.
public class ProductsController {

    @GetMapping()
    public List<Product> getAll(){
        return null;
    }

    //Ekleme endpointleri ekleme sonrasi durum icin eklenen entity'i geri doner.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //eger islem basarili olursa, status code olarak sunu dön.
    public Product add(@RequestBody Product product){
        Random random = new Random();
        product.setId(random.nextInt(1000));
        return product;

    }

}
