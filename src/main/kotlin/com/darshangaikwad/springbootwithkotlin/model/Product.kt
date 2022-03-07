package com.darshangaikwad.springbootwithkotlin.model

import javax.persistence.*

@Entity
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var title: String = ""

    @Column
    var description: String = ""

    @Column
    var image: String = ""

    @Column
    var price: Int = 0
}