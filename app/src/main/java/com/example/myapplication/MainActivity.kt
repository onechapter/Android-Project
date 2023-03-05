package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginEn = "Log in"
        val loginVN = "Đăng Nhập"
        val swEN = "Language: EN"
        val swVN = "Ngôn ngữ: VN"
        val mailEmptyErrMessageEN = "Email can't empty!"
        val mailEmptyErrMessageVN = "Email không được để trống!"
        val mailFormatErrMessageEN = "Email invalidate!"
        val mailFormatErrMessageVN = "Email không đúng định dạng!"
        val passwordEmptyErrMessageEN = "Password invalidate!"
        val passwordEmptyErrMessageVN = "Password không được để trống!"

        val hello = resources.getString(R.string.hello_world)
        TextView(this).apply {
            setText(R.string.app_name)
        }
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPass = findViewById<EditText>(R.id.edtPass)
        val swLanguage = findViewById<Switch>(R.id.swLanguage)
        val imgLogo = findViewById<ImageView>(R.id.imgLogo)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val bgLogin = findViewById<ViewGroup>(R.id.bgLogin)

        bgLogin.setOnClickListener { _ ->
            closeKeyBoard()
        }
        imgLogo.setOnClickListener { _ ->
            closeKeyBoard()
        }

        btnLogin.setOnClickListener { _ ->
            val emailVal = edtEmail.text;
            val edtPass = edtPass.text;
            val messageError:String;
            if(emailVal.trim().isEmpty()){
                messageError = if (!swLanguage.isChecked) resources.getString(R.string.err_mail_empty_message); else resources.getString(R.string.err_mail_empty_message)
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isValidEmail(emailVal.trim())){
                messageError = if (!swLanguage.isChecked) mailFormatErrMessageEN; else mailFormatErrMessageVN
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(edtPass.trim().isEmpty()){
                if (!swLanguage.isChecked) messageError = passwordEmptyErrMessageEN; else messageError = passwordEmptyErrMessageVN
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
        swLanguage.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                btnLogin.text = loginVN
                swLanguage.text = swVN
            } else{
                btnLogin.text = loginEn
                swLanguage.text = swEN
            };
            closeKeyBoard()
        }

    }
    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val edtEmail = findViewById<EditText>(R.id.edtEmail)
            val edtPass = findViewById<EditText>(R.id.edtPass)
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            if (edtEmail.isFocused) {
                edtEmail.clearFocus()
            }
            else if (edtPass.isFocused) {
                edtPass.clearFocus()
            }
        }
    }
    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
