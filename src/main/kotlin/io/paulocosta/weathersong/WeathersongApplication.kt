package io.paulocosta.weathersong

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeathersongApplication

fun main(args: Array<String>) {
    runApplication<WeathersongApplication>(*args)
}
