package com.lazyandroid.directadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * calls inflate function of a view binding using reflection
 */
fun inflateByReflection(v: ViewBinding, parent: ViewGroup): ViewBinding {
    return v.javaClass.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java,Boolean::class.java)
        .invoke(v, LayoutInflater.from(parent.context),parent,false) as ViewBinding
}
/**
 * calls bind function on a view binding using reflection
 */
fun bindByReflection(v: ViewBinding, view: View): ViewBinding {
    return v.javaClass.getDeclaredMethod("bind", View::class.java).invoke(v, view) as ViewBinding
}