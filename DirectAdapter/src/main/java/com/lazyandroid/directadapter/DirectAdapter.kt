package com.lazyandroid.directadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class DirectAdapter(var targetViewBinding: ViewBinding?, private val count:Int, private val f:(ViewBinding, Int)->Unit): RecyclerView.Adapter<GenericViewHolder>() {

    private var onBindViewHolder:((holder: GenericViewHolder, position: Int,payloads: MutableList<Any>)->Unit)?=null
    private var getItemViewType:((position: Int)->Int)?=null
    private var onViewRecycled:((holder: GenericViewHolder)->Unit)?=null
    private var setHasStableIds:((hasStableIds: Boolean)->Unit)?=null
    private var getItemId:((position: Int)->Long)?=null
    private var onFailedToRecycleView:((holder: GenericViewHolder)->Boolean)?=null
    private var onViewAttachedToWindow:((holder: GenericViewHolder)->Boolean)?=null
    private var onViewDetachedFromWindow:((holder: GenericViewHolder)->Boolean)?=null
    private var registerAdapterDataObserver:((observer: RecyclerView.AdapterDataObserver)->Unit)?=null
    private var unregisterAdapterDataObserver:((observer: RecyclerView.AdapterDataObserver)->Unit)?=null
    private var onAttachedToRecyclerView:((recyclerView: RecyclerView)->Unit)?=null
    private var onDetachedFromRecyclerView:((recyclerView: RecyclerView)->Unit)?=null

    fun onBindViewHolderAction(action:((holder: GenericViewHolder, position: Int,payloads: MutableList<Any>)->Unit)){
        onBindViewHolder=action
    }

    fun getItemViewTypeAction(action:((position: Int)->Int)){
        getItemViewType=action
    }

    fun onViewRecycledAction(action:(holder: GenericViewHolder)->Unit){
        onViewRecycled=action
    }

    fun setHasStableIdsAction(action:((hasStableIds: Boolean)->Unit)){
        setHasStableIds=action
    }

    fun getItemIdAction(action:(position: Int)->Long){
        getItemId=action
    }

    fun onFailedToRecycleViewAction(action:(holder: GenericViewHolder)->Boolean){
        onFailedToRecycleView=action
    }

    fun onViewAttachedToWindowAction(action:(holder: GenericViewHolder)->Boolean){
        onViewAttachedToWindow=action
    }

    fun onViewDetachedFromWindow(action:(holder: GenericViewHolder)->Boolean){
        onViewDetachedFromWindow=action
    }

    fun registerAdapterDataObserverAction(action:(observer: RecyclerView.AdapterDataObserver)->Unit){
        registerAdapterDataObserver=action
    }
    fun unregisterAdapterDataObserverAction(action:(observer: RecyclerView.AdapterDataObserver)->Unit){
        unregisterAdapterDataObserver=action
    }
    fun onAttachedToRecyclerViewAction(action:(recyclerView: RecyclerView)->Unit){
        onAttachedToRecyclerView=action
    }
    fun onDetachedFromRecyclerViewAction(action:(recyclerView: RecyclerView)->Unit){
        onDetachedFromRecyclerView=action
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        if(targetViewBinding==null)
            throw IllegalArgumentException("Target view binding is null")

        val binding = inflateByReflection(targetViewBinding!!,parent)
        return GenericViewHolder(targetViewBinding!!, binding.root)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        f.invoke(holder.binding,position)
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(
        holder: GenericViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        onBindViewHolder?.invoke(holder, position, payloads)
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItemViewType==null){
            super.getItemViewType(position)
        }else{
            getItemViewType!!.invoke(position)
        }
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        return if(setHasStableIds==null){
            super.setHasStableIds(hasStableIds)
        }else{
            setHasStableIds!!.invoke(hasStableIds)
        }
    }

    override fun getItemId(position: Int): Long {
        return if(getItemId==null){
            super.getItemId(position)
        }else{
            getItemId!!.invoke(position)
        }
    }


    override fun onViewRecycled(holder: GenericViewHolder) {
        onViewRecycled?.invoke(holder)
        super.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: GenericViewHolder): Boolean {
        return if(onFailedToRecycleView==null){
            super.onFailedToRecycleView(holder)
        }else{
            onFailedToRecycleView!!.invoke(holder)
        }

    }

    override fun onViewAttachedToWindow(holder: GenericViewHolder) {
        onViewAttachedToWindow?.invoke(holder)
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: GenericViewHolder) {
        onViewDetachedFromWindow?.invoke(holder)
        super.onViewDetachedFromWindow(holder)

    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        registerAdapterDataObserver?.invoke(observer)
        super.registerAdapterDataObserver(observer)
    }

    override fun unregisterAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        unregisterAdapterDataObserver?.invoke(observer)
        super.unregisterAdapterDataObserver(observer)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        onAttachedToRecyclerView?.invoke(recyclerView)
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        onDetachedFromRecyclerView?.invoke(recyclerView)
        super.onDetachedFromRecyclerView(recyclerView)
    }
}