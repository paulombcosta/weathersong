package io.paulocosta.weathersong.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping("/hai")
    fun hello(): String {
        return "hai"
    }

}