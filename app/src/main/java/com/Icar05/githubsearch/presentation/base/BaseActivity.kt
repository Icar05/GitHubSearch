package com.Icar05.githubsearch.presentation.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import dagger.android.AndroidInjection
import javax.inject.Inject



@SuppressLint("Registered")
open class BaseActivity<T : BasePresenter<out BaseView>> : AppCompatActivity() {

    @field:Inject
    lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }


    /*
     https://stackoverflow.com/questions/8697499
    /hide-keyboard-when-user-taps-anywhere-else-on-the-screen-in-android/31021154#31021154
    */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {

        val handleReturn = super.dispatchTouchEvent(ev)

        val view = currentFocus

        val x = ev.x.toInt()
        val y = ev.y.toInt()

        if (view is EditText) {
            val innerView = currentFocus

            if (ev.action == MotionEvent.ACTION_UP && !getLocationOnScreen(innerView as EditText).contains(x, y)) {

                val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                input.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)
            }
        }

        return handleReturn
    }

    private fun getLocationOnScreen(mEditText: EditText): Rect {
        val mRect = Rect()
        val location = IntArray(2)

        mEditText.getLocationOnScreen(location)

        mRect.left = location[0]
        mRect.top = location[1]
        mRect.right = location[0] + mEditText.width
        mRect.bottom = location[1] + mEditText.height

        return mRect
    }

}