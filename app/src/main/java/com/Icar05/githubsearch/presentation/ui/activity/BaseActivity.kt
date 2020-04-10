package com.Icar05.githubsearch.presentation.ui.activity

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Icar05.githubsearch.presentation.di.factory.DaggerViewModelFactory
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {
    
    @Inject
    lateinit var factory: DaggerViewModelFactory
    
    protected abstract fun getLayoutId(): Int
    
    protected abstract fun onChildCreate()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        
        supportFragmentManager.addOnBackStackChangedListener(this)
        shouldDisplayHomeUp()
        onChildCreate()
    }
    
    override fun onBackStackChanged() = shouldDisplayHomeUp()
    
    @Suppress("UNCHECKED_CAST")
    fun getViewModel(viewModel: Class<out ViewModel>): ViewModel {
        return ViewModelProvider(this, factory).get(viewModel)
    }
    
    private fun shouldDisplayHomeUp() {
        val canBack: Boolean = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(canBack)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }
    
  
    
    /**
     * https://stackoverflow.com/questions/8697499/hide-keyboard-when-user-taps-anywhere-else-on-the-screen-in-android/31021154#31021154
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        
        val handleReturn = super.dispatchTouchEvent(ev)
        val view = currentFocus
        val x = ev.x.toInt()
        val y = ev.y.toInt()
        
        if (view is EditText) {
            val innerView = currentFocus
            
            if (ev.action == MotionEvent.ACTION_UP && !getLocationOnScreen(innerView as EditText).contains(x, y)) {
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)
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












