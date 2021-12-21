package com.wtf.bulletinboard


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.wtf.bulletinboard.activity.EditAdvertisementActivity
import com.wtf.bulletinboard.databinding.ActivityMainBinding
import com.wtf.bulletinboard.dialoghelper.DialogConst
import com.wtf.bulletinboard.dialoghelper.DialogHelper

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var tvAccount: TextView
    private lateinit var binding: ActivityMainBinding
    private val dialogHelper = DialogHelper(this)
    lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>
    val mAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onStart() {
        super.onStart()
        accountEmailUpdate(mAuth.currentUser)
    }


    private fun init() {

        setSupportActionBar(binding.mainToolbar.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawer,
            binding.mainToolbar.toolbar,
            R.string.open_toggle,
            R.string.close_toggle
        )
        binding.mainDrawer.addDrawerListener(toggle)
        toggle.syncState()

        binding.mainNavView.setNavigationItemSelectedListener(this)
        tvAccount = binding.mainNavView.getHeaderView(0).findViewById(R.id.tvAccountEmail)

        initGoogleLauncher()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_advertisement -> {
                intent = Intent(this, EditAdvertisementActivity::class.java)
                startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initGoogleLauncher() {

        googleSignInLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    if (account != null) {
                        dialogHelper.accHelper.signInFirebaseWithGoogle(account.idToken)
                        Log.d("MyLog", "Api don't have errors")
                    }
                } catch (e: ApiException) {
                    Log.d("MyLog", "Api error: ${e.message}")
                }
            }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_my_ads -> {
                Toast.makeText(this, "Pressed id_my_ads", Toast.LENGTH_LONG).show()
            }
            R.id.id_car -> {
                Toast.makeText(this, "Pressed id_car", Toast.LENGTH_LONG).show()
            }
            R.id.id_pc -> {
                Toast.makeText(this, "Pressed id_pc", Toast.LENGTH_LONG).show()
            }
            R.id.id_smartphone -> {
                Toast.makeText(this, "Pressed id_smartphone", Toast.LENGTH_LONG).show()
            }
            R.id.id_dm -> {
                Toast.makeText(this, "Pressed id_dm", Toast.LENGTH_LONG).show()
            }
            R.id.id_sign_up -> {
                dialogHelper.createSignDialog(DialogConst.SIGN_UP_STATE)
                accountEmailUpdate(mAuth.currentUser)
            }
            R.id.id_sign_out -> {
                accountEmailUpdate(null)
                mAuth.signOut()
                dialogHelper.accHelper.signOutGoogle()
            }
            R.id.id_sign_in -> {
                dialogHelper.createSignDialog(DialogConst.SIGN_IN_STATE)
                accountEmailUpdate(mAuth.currentUser)
            }

        }
        binding.mainDrawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun accountEmailUpdate(user: FirebaseUser?) {
        tvAccount.text = if (user == null) {
            resources.getString(R.string.sign_in_or_registration)
        } else {
            user.email
        }

    }


}