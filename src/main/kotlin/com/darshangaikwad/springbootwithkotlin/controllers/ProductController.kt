package com.darshangaikwad.springbootwithkotlin.controllers

import com.darshangaikwad.springbootwithkotlin.dtos.PaginatedResponse
import com.darshangaikwad.springbootwithkotlin.model.Product
import com.darshangaikwad.springbootwithkotlin.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping("/product/frontend")
    fun frontEnd(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(this.productRepository.findAll());
    }


    @GetMapping("/product/backend")
    fun backEnd(
        @RequestParam("s", defaultValue = "") s: String,
        @RequestParam("sort", defaultValue = "") sort: String,
        @RequestParam("page", defaultValue = "1") page: Int
//    ): ResponseEntity<List<Product>> {
    ): ResponseEntity<PaginatedResponse> {
        // Logic used for sorting by Price
        var direction = Sort.unsorted()
        if (sort == "asc")
            direction = Sort.by(Sort.Direction.ASC, "price")
        else if (sort == "desc")
            direction = Sort.by(Sort.Direction.DESC, "price")
//        return ResponseEntity.ok(this.productRepository.search(s, direction))
        // Logic for pagination. Current default is 1 so start with page - 1 ... N
        val perPage = 9
//        return ResponseEntity.ok(this.productRepository.search(s, PageRequest.of(page - 1, perPage, direction)))
        val total = this.productRepository.countSearch(s)
        return ResponseEntity.ok(
            PaginatedResponse(
                data = this.productRepository.search(s, PageRequest.of(page - 1, perPage, direction)),
                total,
                page,
                lastPage = total / perPage
            )
        )
    }

}