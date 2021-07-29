package me.darthwithap.invapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.darthwithap.api.models.requests.LoginRequest
import me.darthwithap.invapp.data.repository.AuthRepository
import me.darthwithap.invapp.utils.Result
import me.darthwithap.invapp.R
import me.darthwithap.invapp.data.domain.utils.UserDtoMapper
import me.darthwithap.invapp.ui.login.LoginFormState
import me.darthwithap.invapp.ui.login.LoginResult

class AuthViewModel : ViewModel() {

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(username: String, password: String, shop: String) {
        _isLoading.postValue(true)

        viewModelScope.launch {
            when (val result =
                AuthRepository.login(LoginRequest(username, password, shop))) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _token.postValue(result.data.token)
                    _loginResult.value = LoginResult(
                        success = result.data.token?.let { UserDtoMapper(it).mapToDomainModel(result.data.user) }
                    )
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _loginResult.value = LoginResult(error = result.exception.message)
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun loginDataChanged(username: String, password: String, shop: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else if (!isShopValid(shop)) {
            _loginForm.value = LoginFormState(shopError = R.string.invalid_shop_code)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // TODO logout functionality in ViewModel
    fun logout() {
        _token.postValue(null)
    }

    fun setToken(token: String?) {
        _token.postValue(token)
        _loginResult.value = LoginResult(error = "User logged out")
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    // A placeholder shopCode validation check
    private fun isShopValid(shop: String): Boolean {
        return (shop.isNotBlank() && shop.length > 2)
    }
}