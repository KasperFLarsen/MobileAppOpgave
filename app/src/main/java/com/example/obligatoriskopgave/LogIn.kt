package com.example.obligatoriskopgave

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.obligatoriskopgave.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth


class LogIn : Fragment() {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            val email = binding.edittextUsername.text.trim().toString()
            if (email.isEmpty()) {
                binding.edittextUsername.error = "no email"
                return@setOnClickListener
            }

            val password = binding.edittextPassword.text.trim().toString()
            if (password.isEmpty()) {
                binding.edittextPassword.error = " no password"
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.d("APPLE", "createUserWithEmail:success")
                        val user = auth.currentUser
                        findNavController().navigate(R.id.action_logIn_to_listOfItems)
                    } else {
                        Log.w("APPLE", "createUserWithEmail:failure", task.exception)

                    }
                }
        }
        binding.buttonRegister.setOnClickListener {
            val email = binding.edittextUsername.text.trim().toString()
            if (email.isEmpty()) {
                binding.edittextUsername.error = "No email"
                return@setOnClickListener
            }
            val password = binding.edittextPassword.text.trim().toString()
            if (password.isEmpty()) {
                binding.edittextPassword.error = "No password"
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("APPLE", "createUserWithEmail:success")
                        val user = auth.currentUser
                        //updateUI(user)
                        findNavController().navigate(R.id.action_logIn_to_listOfItems)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("APPLE", "createUserWithEmail:failure", task.exception)

                    }
                }
        }
    }
}




