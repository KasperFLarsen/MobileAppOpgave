package com.example.obligatoriskopgave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.obligatoriskopgave.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth


class LogIn : Fragment() {
    private var _binding: FragmentLogInBinding?=null
    private val binding get()=_binding!!

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

    }


}