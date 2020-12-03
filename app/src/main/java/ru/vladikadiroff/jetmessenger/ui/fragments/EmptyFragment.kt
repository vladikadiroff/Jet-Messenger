package ru.vladikadiroff.jetmessenger.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_empty.*
import ru.vladikadiroff.jetmessenger.R
import ru.vladikadiroff.jetmessenger.repository.FirebaseDatabaseProvider
import ru.vladikadiroff.jetmessenger.utils.extensions.navigateToLoginActivity
import javax.inject.Inject

class EmptyFragment : DaggerFragment(R.layout.fragment_empty){

    @Inject
    lateinit var databaseProvider: FirebaseDatabaseProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentText.text = databaseProvider.getUID()
        databaseProvider.addNewUser()
        fragmentText.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            navigateToLoginActivity()
        }
    }

}