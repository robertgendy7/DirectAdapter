package com.lazyandroid.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.lazyandroid.directadapter.GenericViewHolder
import com.lazyandroid.directadapter.SingleLayoutAdapter
import com.lazyandroid.directadapter.inflateByReflection
import com.lazyandroid.directadapter.setLinearLayoutManager
import com.lazyandroid.screen.databinding.AdapterLayoutBinding
import com.lazyandroid.screen.databinding.FragmentCustomViewHolderExampleBinding

class CustomViewHolderExample : Fragment() {

    private lateinit var binding: FragmentCustomViewHolderExampleBinding
    lateinit var adapterBinding: AdapterLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCustomViewHolderExampleBinding.inflate(layoutInflater, container, false)
        adapterBinding = AdapterLayoutBinding.inflate(layoutInflater)

        binding.recyclerView.apply {
            setLinearLayoutManager(requireContext())
            adapter = CustomSingleAdapter(adapterBinding, 10) { holder: GenericViewHolder, i: Int ->
                holder as CustomGenericViewHolder
                val b = holder.binding as AdapterLayoutBinding
                b.textView.text = holder.msg
            }
        }

        return binding.root
    }

    /**
     * inherit from #[GenericViewHolder]
     * and return it from the 'onCreateViewHolder' method
     * of the adapter make sure you provide the same super implementation
     * changing only the return class unless you intend otherwise
     */
    class CustomGenericViewHolder(v: ViewBinding, itemView: View, viewType: Int) :
        GenericViewHolder(v, itemView, viewType) {
        var msg = "This is custom view holder"
    }

    class CustomSingleAdapter(v: ViewBinding, count: Int, f: (GenericViewHolder, Int) -> Unit) :
        SingleLayoutAdapter(v, count, f) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
            if (targetViewBinding == null)
                throw IllegalArgumentException("Target view binding is null ")

            val binding = inflateByReflection(targetViewBinding!!, parent)
            // return the new type instead of the default
            return CustomGenericViewHolder(targetViewBinding!!, binding.root, viewType)
        }
    }

    companion object {
        val getFragment = fun(): CustomViewHolderExample {
            return CustomViewHolderExample()
        }


    }
}