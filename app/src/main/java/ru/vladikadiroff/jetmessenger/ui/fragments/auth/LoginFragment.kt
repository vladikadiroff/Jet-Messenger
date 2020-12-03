package ru.vladikadiroff.jetmessenger.ui.fragments.auth

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_login.*
import ru.vladikadiroff.jetmessenger.R
import ru.vladikadiroff.jetmessenger.domain.auth.keys.AuthenticationStatus
import ru.vladikadiroff.jetmessenger.ui.abstracts.BaseLoginFragment
import ru.vladikadiroff.jetmessenger.utils.extensions.ACTION_TO_ENTER_CODE_FRAGMENT
import ru.vladikadiroff.jetmessenger.utils.extensions.navigateTo
import ru.vladikadiroff.jetmessenger.utils.extensions.navigateToMessengerActivity
import ru.vladikadiroff.jetmessenger.viewmodels.LoginViewModel

class LoginFragment: BaseLoginFragment<LoginViewModel>(R.layout.fragment_login){

    override val isSharedEnterTransition = true
    override val viewModelClass = LoginViewModel::class.java

    override fun initLoginFragment() {
        initView()
        initViewModel()
    }

    private fun initView(){
        buttonLogin.setOnClickListener { login() }
        editTextPhone.doOnTextChanged { _, _, _, _ ->
            if(textInputLayoutPhone.isErrorEnabled) textInputLayoutPhone.error = null
        }
    }

    private fun initViewModel() {
        viewModel.status.observe(this, Observer { status ->
            when(status) {
                is AuthenticationStatus.isSuccessfull -> navigateToMessengerActivity()
                is AuthenticationStatus.onCodeSent -> navigateTo(R.id.loginFragment, ACTION_TO_ENTER_CODE_FRAGMENT)
                is AuthenticationStatus.Exception -> setError(status.message)
            }
        })
    }

    private fun login(){
        enableFields(false)
        if(validateFields()) viewModel.verifyPhoneNumber(editTextPhone.text.toString())
        else setError(getString(R.string.error_empty_field))
    }

    private fun validateFields(): Boolean {
        return !editTextPhone.text.isNullOrEmpty()
    }

    private fun enableFields(enable: Boolean = true){
        buttonLogin.isEnabled = enable
        editTextPhone.isEnabled = enable
    }

    private fun setError(message: String){
        textInputLayoutPhone.error = message
        enableFields()
    }

}