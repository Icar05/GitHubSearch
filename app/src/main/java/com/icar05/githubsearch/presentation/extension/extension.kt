package com.icar05.githubsearch.presentation.extension

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.icar05.githubsearch.R


fun EditText.onTextChanges(unit: (chars: CharSequence?) -> Unit){
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            unit(p0)
        }
    })
}

fun View.setLoadingBackground(value: Boolean){
    if(value){
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
    }else{
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }
}

fun View.showOrHide(show: Boolean){
    visibility = if(show){
        View.VISIBLE
    }else{
        View.GONE
    }
}