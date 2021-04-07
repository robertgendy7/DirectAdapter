package com.lazyandroid.directadapter

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setLinearLayoutManager(context: Context): LinearLayoutManager {
    val lm= LinearLayoutManager(context)
    this.layoutManager=lm
    return lm
}

fun RecyclerView.setHorizontalLayoutManager(context: Context): LinearLayoutManager {
    val lm= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    this.layoutManager=lm
    return lm
}

fun RecyclerView.setGridLayoutManager(context: Context, count:Int): GridLayoutManager {
    val lm= GridLayoutManager(context,count)
    this.layoutManager=lm
    return lm
}