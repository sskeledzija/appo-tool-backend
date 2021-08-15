package com.bytelogics.webookserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebookServerApplication

fun main(args: Array<String>) {
	runApplication<WebookServerApplication>(*args)
}
