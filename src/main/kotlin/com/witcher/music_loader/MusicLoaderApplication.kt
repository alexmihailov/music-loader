package com.witcher.music_loader

import com.witcher.music_loader.service.FetchHtmlService
import com.witcher.music_loader.service.LoaderServiceImp
import com.witcher.music_loader.setting.AppSetting
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MusicLoaderApplication {


	@Bean
	fun init(service: FetchHtmlService, loader: LoaderServiceImp) = CommandLineRunner {

		val incrementOffset = 100L

		var offest = incrementOffset
		var musicList = service.fetchMusicInfoFromPage(offest)
		do {
			for(music in musicList) {
				print("Download: ${music.title} ... ")
				try {
					loader.loadMusic(music)
					print("Done.")
				} catch (t: Throwable) {
					print("Failure.")
				}
				println()
			}
			offest += incrementOffset
			musicList = service.fetchMusicInfoFromPage(offest)
		} while(musicList.isNotEmpty())
	}
}

fun main(args: Array<String>) {
	runApplication<MusicLoaderApplication>(*args)
}

