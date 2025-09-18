package com.turkcell.intro.web.repository;

import com.turkcell.intro.web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// JpaRepository -> ORM Tool'unun ilgili nesnenin (tablo) icersiinde islem yapabilmesini saglayan nesne.
//@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Derived Query Methods
    List<Product> findByNameLikeIgnoreCase(String name); //Derived Query

    // native query false : JPQL
    // native query true : SAF SQL
    @Query(value ="Select p from Product p Where UPPER(p.name) LIKE UPPER(:name)", nativeQuery = false) //NativeQuery -> SAF SQL - JPA + SQL = JPQL
    List<Product> search(String name);

    @Query(value ="Select * from products where LOWER(name) LIKE LOWER(:name)", nativeQuery = true) //NativeQuery -> SAF SQL - JPA + SQL = JPQL
    List<Product> searchSql(String name);

    // Optional -> Sonuc Bulunmayabilir?
    Optional<Product> findTop1ByNameIgnoreCase(String name);

}
