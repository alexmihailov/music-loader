package com.witcher.music_loader.setting

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * @author Alex Mihailov {@literal <avmikhaylov@phoenixit.ru>}.
 */

@Component
class AppSetting {

    @Value("\${app.user.id}")
    lateinit var userId: String

    @Value("\${app.base.url}")
    lateinit var baseUrl: String

    @Value("\${app.page.url}")
    lateinit var pageUrl: String

    @Value("\${app.path.saved.files}")
    lateinit var pathToSaveFiles: String
}