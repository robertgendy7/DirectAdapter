package com.lazyandroid.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lazyandroid.directadapter.GenericViewHolder
import com.lazyandroid.directadapter.MultiLayoutAdapter
import com.lazyandroid.directadapter.setLinearLayoutManager
import com.lazyandroid.screen.databinding.AdapterLayoutLeftBinding
import com.lazyandroid.screen.databinding.AdapterLayoutRightBinding
import com.lazyandroid.screen.databinding.FragmentMultiLayoutAdapterFramgentBinding

class MultiLayoutAdapterExample:Fragment() {
    private lateinit var binding:FragmentMultiLayoutAdapterFramgentBinding
    private lateinit var leftBinding: AdapterLayoutLeftBinding
    private lateinit var rightBinding: AdapterLayoutRightBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMultiLayoutAdapterFramgentBinding.inflate(layoutInflater,container,false)

        leftBinding= AdapterLayoutLeftBinding.inflate(layoutInflater)
        rightBinding= AdapterLayoutRightBinding.inflate(layoutInflater)

        val viewTypeFunction=fun(position:Int):Int{
            return if(position%2!=0){
                1
            }else{
                2
            }
        }


        binding.recyclerView.apply {
            setLinearLayoutManager(requireContext())
            adapter=MultiLayoutAdapter(100, mapOf(1 to leftBinding , 2 to rightBinding),viewTypeFunction)
            { holder: GenericViewHolder, i: Int ->
                  when(holder.viewType){
                      1->{
                          val binding=holder.binding as AdapterLayoutLeftBinding
                          binding.textView.text="UI $i"
                      }
                      2->{
                          val binding =holder.binding as AdapterLayoutRightBinding
                          binding.textView.text="UI $i"
                      }
                  }

            }
        }

        return binding.root
    }


    companion object{
        val getFragment=fun():MultiLayoutAdapterExample{
            return MultiLayoutAdapterExample()
        }
    }

}