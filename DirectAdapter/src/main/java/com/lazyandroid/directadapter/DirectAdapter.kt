package com.lazyandroid.directadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class DirectAdapter(var targetViewBinding: ViewBinding?, private val count:Int, private val f:(GenericViewHolder, Int)->Unit): RecyclerView.Adapter<GenericViewHolder>() {

    private var onBindViewHolder:((holder: GenericViewHolder, position: Int,payloads: MutableList<Any>)->Unit)?=null
    private var getItemViewType:((position: Int)->Int)?=null
    private var onViewRecycled:((holder: GenericViewHolder)->Unit)?=null
    private var getItemId:((position: Int)->Long)?=null
    private var onFailedToRecycleView:((holder: GenericViewHolder)->Boolean)?=null
    private var onViewAttachedToWindow:((holder: GenericViewHolder)->Unit)?=null
    private var onViewDetachedFromWindow:((holder: GenericViewHolder)->Unit)?=null
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



    fun getItemIdAction(action:(position: Int)->Long){
        getItemId=action
    }

    fun onFailedToRecycleViewAction(action:(holder: GenericViewHolder)->Boolean){
        onFailedToRecycleView=action
    }

    fun onViewAttachedToWindowAction(action:(holder: GenericViewHolder)->Unit){
        onViewAttachedToWindow=action
    }

    fun onViewDetachedFromWindow(action:(holder: GenericViewHolder)->Unit){
        onViewDetachedFromWindow=action
    }


    fun onAttachedToRecyclerViewAction(action:(recyclerView: RecyclerView)->Unit){
        onAttachedToRecyclerView=action
    }
    fun onDetachedFromRecyclerViewAction(action:(recyclerView: RecyclerView)->Unit){
        onDetachedFromRecyclerView=action
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        if(targetViewBinding==null)
            throw IllegalArgumentException("Target view binding is null ")

        val binding = inflateByReflection(targetViewBinding!!,parent)
        return GenericViewHolder(targetViewBinding!!, binding.root,viewType)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        f.invoke(holder,position)
    }



    override fun getItemCount(): Int {
        return count
    }


    override fun onBindViewHolder(
        holder: GenericViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        onBindViewHolder?.invoke(holder, position, payloads)
        super.onBindViewHolder(holder, position, payloads)
    }


   /**
    * super returns 0 #[RecyclerView.Adapter.getItemViewType]
    */
    override fun getItemViewType(position: Int): Int {
        return if(getItemViewType==null){
            super.getItemViewType(position)
        }else{
            getItemViewType!!.invoke(position)

        }
    }

    /**
     * super returns #[RecyclerView.NO_ID] == -1 #[RecyclerView.Adapter.getItemId]
     */
    override fun getItemId(position: Int): Long {
        return if(getItemId==null){
            super.getItemId(position)
        }else{
            getItemId!!.invoke(position)
        }
    }


    /**
     * super does nothing #[RecyclerView.Adapter.onViewRecycled]
     */
    override fun onViewRecycled(holder: GenericViewHolder) {
        onViewRecycled?.invoke(holder)
    }

    /**
     * super returns false  #[RecyclerView.Adapter.onFailedToRecycleView]
     */
    override fun onFailedToRecycleView(holder: GenericViewHolder): Boolean {
        return if(onFailedToRecycleView==null){
            super.onFailedToRecycleView(holder)
        }else{
            onFailedToRecycleView!!.invoke(holder)
        }

    }

    /**
     * super does nothing #[RecyclerView.Adapter.onViewAttachedToWindow]
     */
    override fun onViewAttachedToWindow(holder: GenericViewHolder) {
        onViewAttachedToWindow?.invoke(holder)
    }

    /**
     * super does nothing #[RecyclerView.Adapter.onViewDetachedFromWindow]
     */
    override fun onViewDetachedFromWindow(holder: GenericViewHolder) {
        onViewDetachedFromWindow?.invoke(holder)
    }

    /**
     * super does nothing #[RecyclerView.Adapter.onAttachedToRecyclerView]
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        onAttachedToRecyclerView?.invoke(recyclerView)
    }

    /**
     * super does nothing #[RecyclerView.Adapter.onDetachedFromRecyclerView]
     */
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        onDetachedFromRecyclerView?.invoke(recyclerView)
    }
}


