package com.putragandad.urbanfarm.activity.landingpage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.beranda.FragmentContainerNavbarActivity
import com.putragandad.urbanfarm.databinding.ActivityLandingPageBinding
import com.putragandad.urbanfarm.fragments.dialogfragment.AlphaDialogFragment
import com.putragandad.urbanfarm.fragments.dialogfragment.InDevelopmentLoginFragment

class LandingPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingPageBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get instance of Firebase
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this , gso)

        binding.btnGoogleLanding.setOnClickListener {
            googleSignInClient.signOut().addOnCompleteListener {
                signInGoogle()
            }
        }

        binding.btnSigninLanding.setOnClickListener {
            InDevelopmentLoginFragment().show(supportFragmentManager, "LOGIN_DEV_DIALOG")
        }

        binding.btnSignupLanding.setOnClickListener {
            InDevelopmentLoginFragment().show(supportFragmentManager, "LOGIN_DEV_DIALOG")
        }

        // Check if user already login or not
        if (auth.currentUser != null) {
            goToDashboard()
        }
    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                goToDashboard()
            }else{
                Toast.makeText(this, it.exception.toString() , Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToDashboard() {
        val intent = Intent(this, FragmentContainerNavbarActivity::class.java)
        startActivity(intent)
        finish()
    }
}