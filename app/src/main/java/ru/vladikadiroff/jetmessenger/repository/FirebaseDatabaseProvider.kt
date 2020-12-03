package ru.vladikadiroff.jetmessenger.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.vladikadiroff.jetmessenger.utils.NODE_USERS
import ru.vladikadiroff.jetmessenger.utils.USER_ID
import ru.vladikadiroff.jetmessenger.utils.USER_USERNAME

class FirebaseDatabaseProvider(firebaseDatabase: FirebaseDatabase,
                               private val firebaseAuth: FirebaseAuth) {

    private val database = firebaseDatabase.reference


    fun addNewUser(): Boolean {
        var isAdded = false
        val dataUser = mutableMapOf<String, Any>()
        dataUser[USER_ID] = getUID()
        //dataUser[USER_PHONE] = phone
        dataUser[USER_USERNAME] =  getUID()
        database.child(NODE_USERS).child(getUID()).updateChildren(dataUser)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) isAdded = true
            }
        return isAdded
    }

    fun getUID() = firebaseAuth.currentUser?.uid!!

}