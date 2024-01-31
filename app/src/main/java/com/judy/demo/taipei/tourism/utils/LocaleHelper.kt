package com.judy.demo.taipei.tourism.utils

import android.content.Context
import com.judy.demo.taipei.tourism.R
import com.judy.demo.taipei.tourism.repository.languageType.LanguageType
import java.util.*

fun Context.setAppLocale(language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return createConfigurationContext(config)
}


fun getLocaleTag(type:LanguageType):String{
    return when(type){
        LanguageType.ZH_TW -> "zh-rTW"
        LanguageType.EN -> "en"
        LanguageType.ES -> "es"
        LanguageType.ID -> "in"
        LanguageType.JA -> "ja"
        LanguageType.KO -> "ko"
        LanguageType.TH -> "th"
        LanguageType.VI -> "vi"
        LanguageType.ZH_CN -> "zh-rCN"
        else -> "zh-rTW"
    }
}

