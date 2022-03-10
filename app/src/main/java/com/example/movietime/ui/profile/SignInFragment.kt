package com.example.movietime.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movietime.R
import com.example.movietime.databinding.FragmentCalendarBinding
import com.example.movietime.ui.profile.LoginStatus.account
import com.example.movietime.ui.profile.LoginStatus.isLoggedIn
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener


// SIGN OUT
// https://developers.google.com/identity/sign-in/android/disconnect

object LoginStatus {
    var isLoggedIn = false
    var account: GoogleSignInAccount? = null
}

class SignInFragment : Fragment(R.layout.fragment_google_sign_in) {
    private val TAG = "SignInFragment"

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private val RC_SIGN_IN = 0
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        var gso: GoogleSignInOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        val signInButton: SignInButton = view.findViewById(R.id.sign_in_button)
        val signOutButton: Button = view.findViewById(R.id.sign_out_button)
        Log.d(TAG, "OnCreate: " + isLoggedIn)
        if (!isLoggedIn){
            view.findViewById<TextView>(R.id.tv_welcome).visibility = View.INVISIBLE
            signOutButton.visibility = View.INVISIBLE

            view.findViewById<TextView>(R.id.tv_sign_in).visibility = View.VISIBLE
            signInButton.visibility = View.VISIBLE
            signInButton.setSize(SignInButton.SIZE_STANDARD)

            signInButton.setOnClickListener {
                signIn()
                Log.d("", "Clicked Sign in")
            }
        }else{
            val welcome = view.findViewById<TextView>(R.id.tv_welcome)
            welcome.visibility = View.VISIBLE
            welcome.text = "Hello There, "+ account!!.displayName
            signOutButton.visibility = View.VISIBLE

            view.findViewById<TextView>(R.id.tv_sign_in).visibility = View.INVISIBLE
            signInButton.visibility = View.INVISIBLE

            signOutButton.setOnClickListener {
                signOut()
                Log.d("", "Clicked Sign out")
            }
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
        isLoggedIn = false
        account = null
        findNavController().navigateUp()
    }

    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener(requireActivity(), OnCompleteListener<Void?> {
                // remove database entries?
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_SIGN_IN) {
                try {
                    val credential = GoogleSignIn.getLastSignedInAccount(requireContext())
                    isLoggedIn = true
                    account = credential
                    Log.d(TAG, credential.toString())
                    findNavController().navigateUp()
                } catch (e: ApiException) {
                    // The ApiException status code indicates the detailed failure reason.
                    Log.w("TAG", "signInResult:failed code=" + e.statusCode)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}