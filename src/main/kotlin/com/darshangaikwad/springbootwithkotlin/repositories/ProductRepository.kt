package com.darshangaikwad.springbootwithkotlin.repositories

import com.darshangaikwad.springbootwithkotlin.model.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<Product, Integer> {

    // Query is search the title which contains %?1% string
    // Query for sorting data asc and desc by Price of the product
//    @Query("select p from Product p where p.title like %?1% or p.description like %?1%")
//    fun search(s: String, sort: Sort): List<Product>

    @Query("select p from Product p where p.title like %?1% or p.description like %?1%")
    fun search(s: String, pageable: Pageable): List<Product>

    @Query("select COUNT(p) from Product p where p.title like %?1% or p.description like %?1%", countQuery = "*")
    fun countSearch(s: String): Int
}