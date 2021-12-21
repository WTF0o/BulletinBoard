package com.wtf.bulletinboard.dialoghelper

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.wtf.bulletinboard.MainActivity
import com.wtf.bulletinboard.R
import com.wtf.bulletinboard.accounthelper.AccountHelper
import com.wtf.bulletinboard.databinding.SignDialogBinding

class DialogHelper(activity: MainActivity) {
    private val activity = activity
    private lateinit var binding: SignDialogBinding
    val accHelper = AccountHelper(activity)

    fun createSignDialog(index: Int) {
        binding = SignDialogBinding.inflate(activity.layoutInflater)
        val builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)
        setDialogState(index)

        val dialog = builder.create()

        binding.btnSignUpIn.setOnClickListener {
          setOnClickSignUpInListener(index, dialog)
        }

        binding.btnForgetPassword.setOnClickListener {
            setOnClickResetPasswordListener(index, dialog)
            dialog.dismiss()
        }

        binding.btnSignInGoogle.setOnClickListener {
            accHelper.signInWithGoogle()
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun setOnClickResetPasswordListener(index: Int, dialog: AlertDialog?) {
        val email = binding.edSignEmail.text
        if(email.isNotEmpty())
        {
            activity.mAuth.sendPasswordResetEmail(email.toString()).addOnCompleteListener { task ->
                if(task.isSuccessful)
                    Toast.makeText(activity, R.string.sent_reset_password, Toast.LENGTH_LONG).show()
            }
        }
        else{
            binding.tvForgetPasswordError.visibility = View.VISIBLE
        }


    }

    private fun setOnClickSignUpInListener(index: Int, dialog: AlertDialog) {
        dialog?.dismiss()
        if (index == DialogConst.SIGN_UP_STATE) {
            accHelper.singUpEmail(
                binding.edSignEmail.text.toString(),
                binding.edSignPassword.text.toString()
            )
        } else {
            accHelper.singInEmail(
                binding.edSignEmail.text.toString(),
                binding.edSignPassword.text.toString())
        }
    }

    private fun setDialogState(index: Int) {
        if (index == DialogConst.SIGN_UP_STATE) {
            binding.tvSignTitle.text = activity.resources.getString(R.string.ac_sign_up)
            binding.btnSignUpIn.text = activity.resources.getString(R.string.sign_up_action)
        } else {
            binding.tvSignTitle.text = activity.resources.getString(R.string.ac_sign_in)
            binding.btnSignUpIn.text = activity.resources.getString(R.string.sign_in_action)
            binding.btnForgetPassword.visibility = View.VISIBLE
        }
    }
}