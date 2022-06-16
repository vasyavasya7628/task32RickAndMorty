package com.example.task32rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task32rickandmorty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentHeroes: FragmentHeroes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentHeroes, fragmentHeroes)
                .commit()
        }
    }
}