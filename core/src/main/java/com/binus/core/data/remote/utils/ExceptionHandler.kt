package com.binus.core.data.remote.utils

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import timber.log.Timber

suspend fun <T> tryCatch(
    httpCall: suspend () -> ApiResponse<T>
): ApiResponse<T> =
    try {
        httpCall()
    } catch (e: RedirectResponseException) {
        Timber.e("Error: ${e.response.status.description}")
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: ClientRequestException) {
        Timber.e("Error: ${e.response.status.description}")
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: ServerResponseException) {
        Timber.e("Error: ${e.response.status.description}")
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: NoTransformationFoundException) {
        Timber.e("Error: ${e.message}")
        ApiResponse.Error("Error: ${e.message}")
    }catch (e: Exception) {
        Timber.e("Error: ${e.message}")
        ApiResponse.Error("Error: ${e.message}")
    }