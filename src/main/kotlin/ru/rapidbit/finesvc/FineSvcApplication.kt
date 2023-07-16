package ru.rapidbit.finesvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FineSvcApplication

fun main(args: Array<String>) {
	runApplication<FineSvcApplication>(*args)
}
