package com.maxsch.rxjavalecture.api

interface ResultListener<T> {

	fun onSuccess(value: T)

	fun onError(error: Throwable)
}