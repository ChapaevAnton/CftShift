package com.w4eret1ckrtb1tch.homework.kaspresso.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.w4eret1ckrtb1tch.homework.R

object CurrencyConversionScreen : Screen<CurrencyConversionScreen>() {

    val charCode = KTextView { withId(R.id.char_code) }
    val name = KTextView { withId(R.id.name) }
    val rate = KTextView { withId(R.id.rate) }
    val value = KTextView { withId(R.id.value) }
    val arrowId = KImageView { withId(R.id.rate_arrow) }
    val amountCurrency = KTextView { withId(R.id.amount_currency) }
    val cash = KEditText { withId(R.id.cash) }

}