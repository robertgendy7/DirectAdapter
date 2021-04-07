package com.lazyandroid.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.lazyandroid.directadapter.SingleLayoutAdapter
import com.lazyandroid.directadapter.setLinearLayoutManager
import com.lazyandroid.screen.databinding.ActivityMainBinding
import com.lazyandroid.screen.databinding.AdapterLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterLayoutBinding: AdapterLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        adapterLayoutBinding= AdapterLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter=SingleLayoutAdapter(adapterLayoutBinding,10){ viewBinding: ViewBinding, i: Int ->
            viewBinding as AdapterLayoutBinding
            viewBinding.textView.text="UI:${i+1}"
        }.also {
            it.onFailedToRecycleViewAction {
                TODO()
            }

            it.onViewRecycledAction {

            }
        }

        binding.recyclerView.apply {
            setLinearLayoutManager(this@MainActivity)
            adapter=mAdapter
        }
    }
}