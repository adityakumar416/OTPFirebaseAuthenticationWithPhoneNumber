package com.example.otpverifysample

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.otpverifysample.databinding.FragmentVerifyOtpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class VerifyOtpFragment : Fragment() {
   private lateinit var binding: FragmentVerifyOtpBinding
    private lateinit var mAuth:FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentVerifyOtpBinding.inflate(layoutInflater, container, false)
        val token = requireArguments().getString("token")
        val number = requireArguments().getString("mobile")
        mAuth = FirebaseAuth.getInstance()
        binding.verifyOtpBtn.setOnClickListener {
            if(binding.editTextOtp.text.isEmpty()){
                Toast.makeText(context,"OTP is Mandatory", Toast.LENGTH_SHORT).show()
                binding.editTextOtp.requestFocus()
            }
            else{
                    val otp = binding.editTextOtp.text.toString()
                    verifyOtp(otp,token)
            }
        }

        binding.resendOtp.setOnClickListener {
            resendOtp(number)
        }

        resendOtpCountdown()

        return binding.root

    }

    private fun resendOtpCountdown() {
        object : CountDownTimer(30000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                binding.countdown.setText(" "+millisUntilFinished / 1000)
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {

            }
        }.start()
    }


    private fun resendOtp(number: String?) {
        val  mAuth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(number.toString())       // Phone number to verify
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
                    Snackbar.make(binding.resendOtp, "OTP Resend ho gaya h.", Snackbar.LENGTH_SHORT).show()


                }

            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)


    }

    private fun verifyOtp(otp: String, token: String?) {
        val phoneAuthCredential = PhoneAuthProvider.getCredential(token!!, otp)
        mAuth.signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    findNavController().navigate(R.id.action_verifyOtpFragment_to_dashboardFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }

}