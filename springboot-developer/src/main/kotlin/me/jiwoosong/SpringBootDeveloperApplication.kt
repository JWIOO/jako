package me.jiwoosong

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
object SpringBootDeveloperApplication {
    @kotlin.jvm.JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(SpringBootDeveloperApplication::class.java, *args)
    }
}