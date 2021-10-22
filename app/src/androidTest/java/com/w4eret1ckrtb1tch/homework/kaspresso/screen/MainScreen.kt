package com.w4eret1ckrtb1tch.homework.kaspresso.screen

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.w4eret1ckrtb1tch.homework.R
import org.hamcrest.Matcher

object MainScreen : Screen<MainScreen>() {


    val titleInfo = KTextView { withId(R.id.date) }
    val updateButton = KButton { withId(R.id.load_currencies) }

    val transactionList = KRecyclerView(
        builder = {
            withId(R.id.list_of_currencies)
        },
        itemTypeBuilder = { itemType(::TransactionItem) }
    )

    class TransactionItem(parent: Matcher<View>) : KRecyclerItem<TransactionItem>(parent) {
        val charCode = KTextView(parent) { withId(R.id.char_code) }
        val name = KTextView(parent) { withId(R.id.name) }
        val rate = KTextView(parent) { withId(R.id.rate) }
        val value = KTextView(parent) { withId(R.id.value) }
        val arrowId = KImageView(parent) { withId(R.id.rate_arrow) }
    }

}