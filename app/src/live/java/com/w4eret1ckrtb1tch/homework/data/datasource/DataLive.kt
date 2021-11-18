package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem
import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem.BannerItem
import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem.StudentItem

object DataLive {

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
        ),
        StudentItem(
            name = "Семён",
            secondName = "Сёменов",
            description = "Прошёл курсы Skillbox, SkillFactory, SkillShare, но не могу найти работу, помогите мне",
            hasPortfolio = false,
        ),
        StudentItem(
            name = "Андрей",
            secondName = "Андреев",
            description = "Мне не придумали длинного описания",
            hasPortfolio = true,
        ),
        StudentItem(
            name = "Егор",
            secondName = "Егоров",
            description = "Lorem ipsum dolor sit amet ya uchenik mne 19 let",
            hasPortfolio = true,
        )
    ).let { list -> (0..100).map { list }.flatten() }
}