package ru.vladikadiroff.jetmessenger.repository

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.FirebaseDatabase
import ru.vladikadiroff.jetmessenger.utils.NODE_USERS
import ru.vladikadiroff.jetmessenger.utils.USER_ID
import ru.vladikadiroff.jetmessenger.utils.USER_PHONE
import ru.vladikadiroff.jetmessenger.utils.USER_USERNAME
import java.util.*
import java.util.concurrent.TimeUnit

class FirebaseAuthProvider(private val firebaseAuth: FirebaseAuth,
                           private val firebaseDatabase: FirebaseDatabase,
                           private val activity: Activity) {

    companion object {
        private lateinit var phone: String
        private lateinit var credentialID: String
        private lateinit var credentialToken: PhoneAuthProvider.ForceResendingToken
    }

    private var listener: OnAuthenticationListener? = null

    init {
        firebaseAuth.setLanguageCode(Locale.getDefault().language)
    }

    fun getPhoneNumber() = phone

    fun verifyPhoneNumber(phoneNumber: String){
        phone = phoneNumber
        PhoneAuthProvider.verifyPhoneNumber(PhoneAuthOptions
                .newBuilder(firebaseAuth)
                .setActivity(activity)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setPhoneNumber(phoneNumber)
                .setCallbacks(getFireBaseAuthCallback())
                .build())
    }

    fun resendVerificationCode(){
        PhoneAuthProvider.verifyPhoneNumber(PhoneAuthOptions
            .newBuilder(firebaseAuth)
            .setActivity(activity)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setPhoneNumber(phone)
            .setCallbacks(getFireBaseAuthCallback())
            .setForceResendingToken(credentialToken)
            .build())
    }

    fun verifyVerificationCode(code: String){
        val credential = PhoneAuthProvider.getCredential(credentialID, code)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                firebaseAuth.currentUser?.let { updateCurrentUser(it.uid) }
                listener?.onSuccessful()
            }
            else listener?.onVerificationFailed(task.exception?.message.toString())
        }
    }

    fun isAuthenticate():Boolean{
        return firebaseAuth.currentUser != null
    }

    fun addOnAuthenticationListener(listener: OnAuthenticationListener){
        this.listener = listener
    }

    private fun getFireBaseAuthCallback() =
            object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
                if(task.isSuccessful) listener?.onSuccessful()
                else listener?.onVerificationFailed(task.exception?.message.toString())
            }
        }

        override fun onVerificationFailed(exception: FirebaseException) {
            when(exception){
                is FirebaseAuthInvalidCredentialsException ->
                    // Неправильный реквест
                    listener?.onVerificationFailed(exception.message.toString())
                is FirebaseTooManyRequestsException ->
                    listener?.onVerificationFailed(exception.message.toString())
                else ->
                    listener?.onVerificationFailed(exception.message.toString())
            }
        }

        override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
            credentialID = id
            credentialToken = token
            listener?.onCodeSent()
        }
    }

    private fun  updateCurrentUser(uid: String) {
        val dataUser = mutableMapOf<String, Any>()
        dataUser[USER_ID] = uid
        dataUser[USER_PHONE] = phone
        firebaseDatabase.reference.child(NODE_USERS).child(uid).updateChildren(dataUser)
    }

   interface OnAuthenticationListener {
       fun onSuccessful()
       fun onCodeSent()
       fun onVerificationFailed(exception: String)
   }

}