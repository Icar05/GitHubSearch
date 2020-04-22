package com.icar05.githubsearch.presentation.util

import android.content.Context
import com.icar05.githubsearch.R
import java.io.IOException
import java.net.SocketTimeoutException

class ErrorHandler {
    
    fun prepareTextError(error: Throwable?, context: Context): String {
        
        return when (error) {
            is retrofit2.HttpException -> error.localizedMessage!!
            is SocketTimeoutException -> context.getString(R.string.trouble_internet_message)
            is IOException -> context.getString(R.string.trouble_internet_message)
            is SecurityException -> error.localizedMessage!!
            else -> context.getString(R.string.unknown_error)
        }
    }
    
}