package com.witcher.music_loader.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

/**
 * @author Alex Mihailov {@literal <avmikhaylov@phoenixit.ru>}.
 */

@Configuration
class AppConfig {

    @Bean
    fun restTemplate():RestTemplate = RestTemplate()
}