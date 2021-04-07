package com.lazyandroid.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lazyandroid.screen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment:Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().also {
            fragment=SingleLayoutAdapterExample.getFragment()
            it.replace(binding.fragmentContainer.id,fragment)
            it.commit()
        }

        binding.displayButton.setOnClickListener {

           if(fragment is SingleLayoutAdapterExample){
               fragment=MultiLayoutAdapterExample.getFragment()
           }else if(fragment is MultiLayoutAdapterExample){
               fragment=SingleLayoutAdapterExample.getFragment()
           }
            supportFragmentManager.beginTransaction().also {
                it.replace(R.id.fragmentContainer,fragment)
                it.commit()
            }

        }

    }
}