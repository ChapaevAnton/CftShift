package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem
import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem.BannerItem
import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem.StudentItem

object DataDev {

    val data: List<ListItem> = listOf(
        StudentItem(
            name = "Иван",
            secondName = "Иванов",
            description = "Только что выпустился из универа, с Android знаком не сильно",
            hasPortfolio = true,
        ),
        BannerItem(
            title = "Новая заявка",
            description = "Здравствуйте, меня зовут Глеб, ещё не поздно подать заявку?"
        ),
        StudentItem(
            name = "Пётр",
            secondName = "Петров",
            description = "Сеньор-помидор, 30 лет опыта С++, хочу попробовать себя в новом направлении",
            hasPortfolio = false,
        )
    ).let { list -> (0..100).map { list }.flatten() }

}