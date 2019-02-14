package com.witcher.music_loader.service

import com.witcher.music_loader.data.MusicInfo
import com.witcher.music_loader.setting.AppSetting
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.net.URL

/**
 * @author Alex Mihailov {@literal <avmikhaylov@phoenixit.ru>}.
 */

@Service
class LoaderServiceImp : LoaderService {

    @Autowired
    lateinit var setting: AppSetting

    override fun loadMusic(musicInfo: MusicInfo) {
        val urlForDownload = generateEndpointForDownload(musicInfo)
        FileUtils.copyURLToFile(URL(urlForDownload), File("${setting.pathToSaveFiles}/${musicInfo.title}.mp3"))
    }

    fun generateEndpointForDownload(musicInfo: MusicInfo): String {
        val urlForCasting = URL(musicInfo.downloadUrl)
        val pairsQueryParam = urlForCasting.query.split("&")
        val queryValues = mutableMapOf<String, String>()
        for (pair in pairsQueryParam) {
            val idx = pair.indexOf("=")
            queryValues[pair.substring(0, idx)] = pair.substring(idx + 1)
        }

        return "${urlForCasting.protocol}://${urlForCasting.host}${urlForCasting.path}?url=${queryValues["url"]}"
    }
}