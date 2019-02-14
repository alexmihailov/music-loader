package com.witcher.music_loader.service

import com.witcher.music_loader.data.MusicInfo

/**
 * @author Alex Mihailov {@literal <avmikhaylov@phoenixit.ru>}.
 */
interface LoaderService {

    fun loadMusic(musicInfo: MusicInfo)
}