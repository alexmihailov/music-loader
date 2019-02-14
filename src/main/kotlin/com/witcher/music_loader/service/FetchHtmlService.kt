package com.witcher.music_loader.service

import com.witcher.music_loader.data.MusicInfo

/**
 * @author Alex Mihailov {@literal <avmikhaylov@phoenixit.ru>}.
 */
interface FetchHtmlService {

    fun fetchMusicInfoFromPage(offset: Long): List<MusicInfo>
}