package com.stream.pull_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PullApiApplication

fun main(args: Array<String>) {
	runApplication<PullApiApplication>(*args)
}
