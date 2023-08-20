package com.cleancode.pull_cluster_data

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PullClusterDataApplication

fun main(args: Array<String>) {
	runApplication<PullClusterDataApplication>(*args)
}
