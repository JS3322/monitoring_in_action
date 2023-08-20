package com.cleancode.pull_cluster_data.example

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable

@Serializable
data class PrometheusResponse(
    val status: String,
    val data: PrometheusData
)

@Serializable
data class PrometheusData(
    val resultType: String,
    val result: List<PrometheusResult>
)

@Serializable
data class PrometheusResult(
    val metric: Map<String, String>,
    val value: List<Any>
)

suspend fun fetchMetrics(query: String): PrometheusResponse? {
    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }

    val prometheusURL = "http://YOUR_PROMETHEUS_SERVER:9090/api/v1/query?query=$query"
    val response: HttpResponse = client.get(prometheusURL)

    return response.receive<PrometheusResponse>()
}

suspend fun main() {
    // For example, to fetch up metrics for all instances:
    val metrics = fetchMetrics("up")

    metrics?.data?.result?.forEach {
        println("Metric: ${it.metric}, Value: ${it.value[1]}")
    }
}
