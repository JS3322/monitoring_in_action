package com.cleancode.pull_cluster_data.example.use_prometheus_client.service

import io.prometheus.client.CollectorRegistry
import io.prometheus.client.exporter.common.TextFormat
import org.springframework.stereotype.Service
import java.net.HttpURLConnection
import java.net.URL


//TODO [ALL] 프로메테우스 query문 사용
@Service
class PrometheusService {

    fun getPodMetrics(): String {
        val prometheusURL = "http://YOUR_PROMETHEUS_SERVER_URL/api/v1/query?query=up{job=\"kubernetes-pods\"}"
        val connection = URL(prometheusURL).openConnection() as HttpURLConnection

        connection.requestMethod = "GET"

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            return connection.inputStream.bufferedReader().use { it.readText() }
        } else {
            throw RuntimeException("Failed to fetch metrics: ${connection.responseMessage}")
        }
    }
}
//@Service
//class PrometheusService {
//    fun getPodMetrics(): String {
//        val prometheusURL = "http://YOUR_PROMETHEUS_SERVER_URL/metrics"
//        val connection = URL(prometheusURL).openConnection() as HttpURLConnection
//
//        connection.requestMethod = "GET"
//
//        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
//            val registry = CollectorRegistry.defaultRegistry
//            connection.inputStream.use {
//                TextFormat.parse(it, registry)
//            }
//
//            // 여기서 원하는 메트릭을 처리하거나 필터링
//            // 이 예제에서는 단순히 모든 메트릭을 문자열로 반환
//            return connection.inputStream.bufferedReader().use { it.readText() }
//        } else {
//            throw RuntimeException("Failed to fetch metrics: ${connection.responseMessage}")
//        }
//    }
//}