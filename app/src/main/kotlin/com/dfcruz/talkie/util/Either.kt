package com.dfcruz.talkie.util

import com.dfcruz.talkie.util.Either.Left
import com.dfcruz.talkie.util.Either.Right
import kotlin.coroutines.cancellation.CancellationException

sealed class Either<out A, out B> {

    abstract val isLeft: Boolean
    abstract val isRight: Boolean

    data class Left<out A>(val value: A) : Either<A, Nothing>() {
        override val isLeft
            get() = true
        override val isRight
            get() = false

        override fun toString(): String = "Either.Left($value)"

        companion object {
            operator fun <A> invoke(a: A): Either<A, Nothing> = Left(a)
        }
    }

    data class Right<out B>(val value: B) : Either<Nothing, B>() {
        override val isLeft
            get() = false
        override val isRight
            get() = true

        override fun toString(): String = "Either.Right($value)"

        companion object {
            operator fun <B> invoke(b: B): Either<Nothing, B> = Right(b)
        }
    }

    inline fun <C> fold(ifLeft: (A) -> C, ifRight: (B) -> C): C {
        return when (this) {
            is Left -> ifLeft(this.value)
            is Right -> ifRight(this.value)
        }
    }


    inline fun <C> map(f: (B) -> C): Either<A, C> = flatMap { Right(f(it)) }

    fun orNull(): B? = fold({ null }, { it })

    inline fun ifLeft(f: (A) -> Unit): Either<A, B> = fold({ f(it); Left(it) }, { Right(it) })

    inline fun ifRight(f: (B) -> Unit): Either<A, B> = fold({ Left(it) }, { f(it); Right(it) })

    companion object {
        inline fun <R> catch(f: () -> R): Either<Throwable, R> =
            try {
                f().right()
            } catch (t: Throwable) {
                t.nonFatalOrThrow().left()
            }
    }
}

fun <A> A.left(): Either<A, Nothing> = Either.Left(this)

fun <A> A.right(): Either<Nothing, A> = Either.Right(this)

fun Throwable.nonFatalOrThrow(): Throwable =
    if (isNonFatal(this)) this else throw this

private fun isNonFatal(t: Throwable): Boolean =
    when (t) {
        is VirtualMachineError, is ThreadDeath, is InterruptedException, is LinkageError, is CancellationException -> false
        else -> true
    }

inline fun <A, B, C> Either<A, B>.flatMap(f: (right: B) -> Either<A, C>): Either<A, C> {
    return when (this) {
        is Right -> f(this.value)
        is Left -> this
    }
}


inline fun <A, B, C> Either<A, B>.handleErrorWith(f: (A) -> Either<C, B>): Either<C, B> {
    return when (this) {
        is Left -> f(this.value)
        is Right -> this
    }
}

inline infix fun <A, B> Either<A, B>.getOrElse(default: (A) -> B): B {
    return when (this) {
        is Left -> default(this.value)
        is Right -> this.value
    }
}

fun <A> identity(a: A): A = a

fun <A, B> Either<A, Either<A, B>>.flatten(): Either<A, B> = flatMap(::identity)
