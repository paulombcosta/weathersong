package io.paulocosta.weathersong

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.map.repository.config.EnableMapRepositories

@SpringBootApplication
@EnableMapRepositories
class WeathersongApplication

fun main(args: Array<String>) {
    runApplication<WeathersongApplication>(*args)
}
