package com.checkout51.challenge.feature.offers

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.checkout51.challenge.R
import com.checkout51.challenge.coretest.BaseRobot

class OffersRobotScreen : BaseRobot() {

    private val textViewMessageId = R.id.tvMessage
    private val progressBarId = R.id.progressBar
    private val recyclerViewOffersId = R.id.rvOffers

    fun launch(): OffersRobotScreen {
        ActivityScenario.launch(OffersActivity::class.java)
        return this
    }

    fun clickSortByNameButton(): OffersRobotScreen {
        clickButton(R.id.btSortByName)
        return this
    }

    fun clickSortByCashBackButton(): OffersRobotScreen {
        clickButton(R.id.btSortByCashBack)
        return this
    }

    fun matchProgressBarVisibility(visible: Boolean): OffersRobotScreen {
        matchVisibility(progressBarId, visible)
        return this
    }

    fun matchTextMessage(message: String): OffersRobotScreen {
        matchText(textViewMessageId, message)
        return this
    }

    fun matchTextVisibility(visible: Boolean): OffersRobotScreen {
        matchVisibility(textViewMessageId, visible)
        return this
    }

    fun matchOfferViewItemAtPosition(position: Int, offerViewItem: OfferViewItem): OffersRobotScreen {
        matchListValue(recyclerViewOffersId, position, withText(offerViewItem.name))
        matchListValue(recyclerViewOffersId, position, withText(offerViewItem.cashBack))
        return this
    }
}