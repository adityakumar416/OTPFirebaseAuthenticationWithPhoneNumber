package com.example.otpverifysample

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.otpverifysample.databinding.FragmentLoginOtpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class loginOtpFragment : Fragment() {

    private lateinit var binding:FragmentLoginOtpBinding
    private lateinit var mAuth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginOtpBinding.inflate(layoutInflater, container, false)
        val mAuth = FirebaseAuth.getInstance()

        if(mAuth.currentUser != null){

           findNavController().navigate(R.id.action_loginOtpFragment_to_dashboardFragment)
        }
        binding.button.setOnClickListener {
            if(binding.editText.text.isEmpty()){
                Toast.makeText(context,"FirstName is Mandatory", Toast.LENGTH_SHORT).show()
                binding.editText.requestFocus()
            }
            else{
                val number = "+91"+ binding.editText.text.toString()
                sendOtp(number)
            }
        }


        return binding.root
    }

    private fun sendOtp(number: String) {
          val  mAuth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(10L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity())                 // Activity (for callback binding)
                        .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    Log.i(TAG, "onCodeSend: $p0")
                    Snackbar.make(binding.button, "OTP Sent.", Snackbar.LENGTH_SHORT).show()

                    val bundle = Bundle()
                    bundle.putString("token", p0)
                    bundle.putString("mobile",number)
                    findNavController().navigate(R.id.action_loginOtpFragment_to_verifyOtpFragment,bundle)
                }

            })
            .build()
            PhoneAuthProvider.verifyPhoneNumber(options)


    }

}