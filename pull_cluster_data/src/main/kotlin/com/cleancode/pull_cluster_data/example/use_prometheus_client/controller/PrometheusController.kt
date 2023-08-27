package com.cleancode.pull_cluster_data.example.use_prometheus_client.controller

import com.cleancode.pull_cluster_data.example.use_prometheus_client.service.PrometheusService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//TODO [ALL] 라우터 v1과 메트릭을 하나의 구성으로 작성
@RestController
@RequestMapping("/v1/metrics")
class PrometheusController(private val prometheusService: PrometheusService) {

    @GetMapping("/pods")
    fun getPodMetrics(): String {
        return prometheusService.getPodMetrics()
    }
}