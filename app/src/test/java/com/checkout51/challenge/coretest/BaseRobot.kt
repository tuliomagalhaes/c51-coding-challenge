package com.checkout51.challenge.coretest

import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.checkout51.challenge.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.Matcher
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description


abstract class BaseRobot {

    protected fun fillEditText(resId: Int, text: String): ViewInteraction =
        onView(withId(resId)).perform(ViewActions.replaceText(text), ViewActions.closeSoftKeyboard())

    protected fun clickButton(resId: Int): ViewInteraction = onView((withId(resId))).perform(ViewActions.click())

    protected fun textView(resId: Int): ViewInteraction = onView(withId(resId))

    protected fun matchText(viewInteraction: ViewInteraction, text: String): ViewInteraction = viewInteraction
        .check(ViewAssertions.matches(ViewMatchers.withText(text)))

    protected fun matchText(resId: Int, text: String): ViewInteraction = matchText(textView(resId), text)

    protected fun matchVisibility(resId: Int, visible: Boolean): ViewInteraction {
        val visibility = if (visible) {
            ViewMatchers.Visibility.VISIBLE
        } else {
            ViewMatchers.Visibility.GONE
        }
        return onView(withId(resId)).check(ViewAssertions.matches(withEffectiveVisibility(visibility)))
    }

    protected fun matchListValue(listRes: Int, position: Int, matcher: Matcher<View>): ViewInteraction {
        return onView(withId(listRes)).check(matches(atPosition(position, hasDescendant(matcher))))
    }

    protected fun clickListItem(listRes: Int, position: Int): ViewInteraction {
        return onData(anything())
            .inAdapterView(allOf(withId(listRes)))
            .atPosition(position).perform(ViewActions.click())
    }

    private fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}