package com.lazyandroid.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.lazyandroid.directadapter.GenericViewHolder
import com.lazyandroid.directadapter.SingleLayoutAdapter
import com.lazyandroid.directadapter.setLinearLayoutManager
import com.lazyandroid.screen.databinding.AdapterLayoutBinding
import com.lazyandroid.screen.databinding.FragmentSingleLayoutAdapterExampleBinding

class SingleLayoutAdapterExample:Fragment() {
    lateinit var binding:FragmentSingleLayoutAdapterExampleBinding
    lateinit var adapterBinding:AdapterLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentSingleLayoutAdapterExampleBinding.inflate(layoutInflater,container,false)

        adapterBinding= AdapterLayoutBinding.inflate(layoutInflater)

        binding.recyclerView.apply {
            setLinearLayoutManager(requireContext())
            adapter = SingleLayoutAdapter(adapterBinding,100){ holder: GenericViewHolder, i: Int ->
               val layout=holder.binding as AdapterLayoutBinding
               layout.textView.text="UI $i"
            }
        }
        return binding.root
    }



    companion object{
        val getFragment=fun():SingleLayoutAdapterExample{
            return SingleLayoutAdapterExample()
        }
    }
}