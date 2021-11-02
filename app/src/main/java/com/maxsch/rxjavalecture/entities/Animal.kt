package com.maxsch.rxjavalecture.entities

interface Animal {

	val name: String
	val age: String

	fun voice(): String
}

data class Cat(
	override val name: String,
	override val age: String,
) : Animal {

	override fun voice(): String =
		"I'm a cat. My name is $name, I'm $age."
}

data class Dog(
	override val name: String,
	override val age: String,
) : Animal {

	override fun voice(): String =
		"I'm a dog. My name is $name, I'm $age."
}

data class Rat(
	override val name: String,
	override val age: String,
) : Animal {

	override fun voice(): String =
		"I'm a rat. My name is $name, I'm $age."
}