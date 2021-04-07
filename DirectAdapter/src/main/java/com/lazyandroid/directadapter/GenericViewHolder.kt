package com.lazyandroid.directadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
/**
 * Generic view holder to be used by a #[DirectAdapter]
 * @property binding of the layout already inflated in
 * the generic adapter
 */
class GenericViewHolder(v: ViewBinding, itemView: View): RecyclerView.ViewHolder(itemView) {
    val binding = bindByReflection(v,itemView)
}