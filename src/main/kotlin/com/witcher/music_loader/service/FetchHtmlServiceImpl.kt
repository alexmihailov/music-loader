package com.witcher.music_loader.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.witcher.music_loader.data.HtmlResponse
import com.witcher.music_loader.data.MusicInfo
import com.witcher.music_loader.setting.AppSetting
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

/**
 * @author Alex Mihailov {@literal <avmikhaylov@phoenixit.ru>}.
 */

@Service
class FetchHtmlServiceImpl : FetchHtmlService {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var setting: AppSetting

    override fun fetchMusicInfoFromPage(offset: Long): List<MusicInfo> {

        val pageUrl = setting.pageUrl
        val userId = setting.userId

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val map = LinkedMultiValueMap<String, String>()
        map.add("method", "audio.get")
        map.add("user_id", userId)
        map.add("count", "100")
        map.add("offset", offset.toString())

        val request = HttpEntity<MultiValueMap<String, String>>(map, headers)
        val response = restTemplate.postForEntity(pageUrl, request, String::class.java)

        val responseEntity = ObjectMapper().readValue<HtmlResponse>(response.body, HtmlResponse::class.java)

        val elements = Jsoup.parse(responseEntity.html).select("div.info")

        val musicInfoList = mutableListOf<MusicInfo>()

        val it = elements.iterator()
        while(it.hasNext()) {
            val element = it.next()
            val title = element.select("div.title").text()
            val artist = element.select("div.artist").text()

            val link = element.select("a").first().attr("href")

            val musicInfo = MusicInfo("$title-$artist", getDownloadLinkFromRedirect(link) ?: "")
            musicInfoList.add(musicInfo)
        }

        return musicInfoList
    }

    fun getDownloadLinkFromRedirect(firstUrl: String): String? {
        val response = restTemplate.getForEntity("${setting.baseUrl}/$firstUrl", String::class.java)
        return response.headers["Location"]?.first()
    }
}