package com.darshangaikwad.springbootwithkotlin.dtos

class PaginatedResponse(
    val data: List<Any>,
    val total: Int,
    val page: Int,
    val lastPage: Int
) {
}