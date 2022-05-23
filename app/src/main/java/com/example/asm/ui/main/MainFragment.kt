package com.example.asm.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.asm.R
import com.example.asm.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    private  var inProgerss: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.button.setOnClickListener { change() }
        updateResText()
        return binding.root
    }

    private  fun change(){
        if (!inProgerss) {
            inProgerss = true
            if (binding.checkBox.isChecked)
                Toast.makeText(activity, "Hello toast!", Toast.LENGTH_SHORT).show()

            Thread(Runnable {
                try {
                    var i = 0
                    while (i <= 100) {
                        binding.progressBar.setProgress(i)
                        i++
                        Thread.sleep(20)
                        binding.progressBar.setProgress(0)
                    }
                    this@MainFragment.requireActivity().runOnUiThread(java.lang.Runnable {

                        viewModel.resWord = binding.editText.text.toString()
                        updateResText()
                    })
                    inProgerss = false
                }
                catch (e: Exception){

                }
            }).start()
        }
    }

    private fun updateResText() {
        binding.textView2.text = viewModel.resWord
    }


}