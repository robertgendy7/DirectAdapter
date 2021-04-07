package com.lazyandroid.directadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.IllegalStateException

/**
 * adapter for single layout
 * @param v view binding of the adapter layout
 * @param count number of items held by the adapter
 * @param f function to be invoked when #[RecyclerView.Adapter.onBindViewHolder] is called
 */
open class SingleLayoutAdapter(v: ViewBinding, count: Int, f: (GenericViewHolder, Int) -> Unit) :
    DirectAdapter(v, count, f) {

    override fun getItemViewType(position: Int): Int {
        return 0
    }
}


/**
 * adapter for multiple layouts
 * @param count number of items held by the adapter
 * @param map map that represent the association of specific view type and
 * a layout view binding all the view types in the map must be a value that
 * 'viewTypes' function return if not an exception will be thrown
 * @param viewTypes function that holds view types logic
 */
class MultiLayoutAdapter(count: Int,private val map:Map<Int,ViewBinding>,viewTypes:(position:Int)->Int,f: (GenericViewHolder, Int) -> Unit) :
    DirectAdapter(null, count, f) {

   init {
       getItemViewTypeAction(viewTypes)
   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        for (type in map.keys){
            if(viewType==type) {
                targetViewBinding=map[type]
                return super.onCreateViewHolder(parent, viewType)
            }
        }
        throw IllegalStateException("Unexpected view type encountered a view type that is not a return of getItemViewType method ")

    }
}