package com.turkcell.intro.web.repository;

import com.turkcell.intro.web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository -> ORM Tool'unun ilgili nesnenin (tablo) icersiinde islem yapabilmesini saglayan nesne.
//@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
