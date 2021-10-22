package com.w4eret1ckrtb1tch.homework.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.w4eret1ckrtb1tch.homework.kaspresso.config.*
import com.w4eret1ckrtb1tch.homework.kaspresso.config.ConfigTest.INPUT_CASH
import com.w4eret1ckrtb1tch.homework.kaspresso.config.ConfigTest.INPUT_CASH_ZERO
import com.w4eret1ckrtb1tch.homework.kaspresso.config.ConfigTest.TRANSACTION_INDEX
import com.w4eret1ckrtb1tch.homework.kaspresso.data.Transaction
import com.w4eret1ckrtb1tch.homework.kaspresso.data.TransactionData
import com.w4eret1ckrtb1tch.homework.kaspresso.screen.CurrencyConversionScreen
import com.w4eret1ckrtb1tch.homework.kaspresso.screen.MainScreen
import com.w4eret1ckrtb1tch.homework.presentation.BaseActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyConversionTest : KTestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(BaseActivity::class.java)

    @Test
    @TestCase(
        name = "Test-2",
        description = "Check the efficiency of the conversion logic"
    )
    fun checkCurrencyConversion() {

        before {
            checkClickTransaction(TRANSACTION_INDEX)
        }.after {

        }.run {

            step("Check transactions detailed content") {
                checkDetailTransaction(TransactionData.transaction[TRANSACTION_INDEX])
            }

            step("Check conversion logic") {
                checkConversionLogic(TransactionData.transaction[TRANSACTION_INDEX])
            }
        }

    }

    private fun checkClickTransaction(index: Int) {
        MainScreen {
            transactionList {
                childAt<MainScreen.TransactionItem>(index) {
                    click()
                }
            }
        }
    }

    private fun checkDetailTransaction(transaction: Transaction) {
        CurrencyConversionScreen {
            charCode {
                isDisplayed()
                hasText(transaction.charCode)
            }

            name {
                isDisplayed()
                hasText(transaction.name)
            }

            rate {
                isDisplayed()
                hasText(transaction.rate.toRate())
            }

            value {
                isDisplayed()
                hasText(transaction.value.toValue())
            }

            arrowId {
                isDisplayed()
                hasDrawable(transaction.arrowId)
            }

            amountCurrency {
                hasText(INPUT_CASH_ZERO.toAmountCurrency())
            }
        }
    }

    private fun checkConversionLogic(transaction: Transaction) {
        CurrencyConversionScreen {

            cash {
                typeText(INPUT_CASH.toString())
            }

            amountCurrency {
                isDisplayed()
                hasText(transaction.amountCurrency.toAmountCurrency())
            }
        }

    }

}