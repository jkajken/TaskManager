package com.jk.taskmanager.ui.auth


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.jk.taskmanager.databinding.FragmentAuthBinding
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private var verId: String=""
    private var auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.smsBtn.setOnClickListener {
                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(binding.etVerPhoneNumber.text.toString())       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(requireActivity())                 // Activity (for callback binding)
                    .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
                binding.phoneProgressBar.visibility = View.VISIBLE
        }
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            val code: String? = credential.smsCode
            if (code != null) verifyCode(code)
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Log.d("TAG", "onVerificationFailed", e)
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Log.d("TAG", "onVerificationFailed", e)
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(verificationId, token)
            verId = verificationId
            binding.textInputPhoneNumber.visibility = View.INVISIBLE
            binding.smsBtn.visibility = View.INVISIBLE
            binding.etVerSmsCode.visibility = View.VISIBLE
            binding.codeBtn.visibility = View.VISIBLE
            binding.phoneProgressBar.visibility = View.INVISIBLE

            binding.codeBtn.setOnClickListener {
                if (binding.etVerSmsCode.text.toString().isEmpty()) {
                    binding.etVerSmsCode.error = "An incorrect code.Try again"
                } else {
                    verifyCode(binding.etVerSmsCode.text.toString())
                }
            }
        }

        private fun verifyCode(code: String) {
            val credential = PhoneAuthProvider.getCredential(verId,code)
            signInWithPhoneAuthCredential(credential)
        }

        private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
            auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    Toast.makeText(
                        requireActivity(), "Authenticate Successfully ", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d("TAG", "SignInWithCredential${task.exception.toString()}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(requireActivity(), "Verification/*Failed", Toast.LENGTH_SHORT).show()
                    }
                    // Update UI
                }
            }
        }
    }
}
