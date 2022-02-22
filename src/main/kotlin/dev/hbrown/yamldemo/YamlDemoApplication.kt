package dev.hbrown.yamldemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class YamlDemoApplication

fun main(args: Array<String>) {
    runApplication<YamlDemoApplication>(*args)
}
