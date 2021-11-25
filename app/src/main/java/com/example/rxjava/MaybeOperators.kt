package com.example.rxjava

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlin.math.sign

object MaybeOperators {

    object Task1 {

        /**
         * Вернуть пустой Maybe, который мгновенно завершит работу.
         */
        fun solve(): Maybe<String> =
            Maybe.empty()
    }

    object Task2 {

        /**
         * Преобразовать результат получаемый из [worker] в Maybe поток.
         */
        fun solve(worker: Worker): Maybe<String> =
            Maybe.create { emitter ->
                val listener =
                    { str: String? -> str?.let { emitter.onSuccess(str) } ?: emitter.onComplete() }
                worker.setResultListener(listener)
            }

        interface Worker {

            fun setResultListener(listener: (String?) -> Unit)
        }
    }

    object Task3 {

        /**
         * Завершать поток, если в [source] будет отрицательное число.
         * Возвращать значение, если оно будет положительным.
         */
        fun solve(source: Single<Int>): Maybe<Int> =
            source.toMaybe().filter { it.sign == 1 }
    }

    object Task4 {

        /**
         * Если [first] поток не вернет значение, то переключить на [second] поток.
         */
        fun solve(first: Maybe<Int>, second: Single<Int>): Single<Int> =
            first.switchIfEmpty(second)
    }
}