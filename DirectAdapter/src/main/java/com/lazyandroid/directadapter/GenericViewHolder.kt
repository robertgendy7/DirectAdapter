package com.lazyandroid.directadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
/**
 * Generic view holder to be used by a #[DirectAdapter]
 * @property binding of the layout already inflated in
 * the generic adapter
 * @property viewType view type for this view holder
 */
open class GenericViewHolder(v: ViewBinding, itemView: View,val viewType:Int): RecyclerView.ViewHolder(itemView) {
    val binding = bindByReflection(v,itemView)
}