package com.example.testlistdogskonfio.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testlistdogskonfio.R
import com.example.testlistdogskonfio.utils.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , ListDogsFragment.ViewCallBacks {

    private var loadingDialog : LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingDialog = LoadingDialog(this@MainActivity)
        startListDogs()
    }

    private fun startListDogs(){
        val fragmentTo = ListDogsFragment()
        val bundle = Bundle()
        fragmentTo.arguments = bundle
        setFragment(fragmentTo, ListDogsFragment.TAG)
    }

    private fun setFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, fragment)
            .commit()
    }

    override fun showLoader(showIt: Boolean) {
        when {
            showIt -> {
                loadingDialog!!.startLoadingDialog()
            }
            else -> {
                loadingDialog!!.dismisDialog()
            }
        }
    }

    override fun showMessage(message: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.button_text_accept)) { _, _ -> }
        val alert = dialogBuilder.create()
        alert.setTitle("List Dogs Konfio")
        alert.show()
    }

    override fun onBackPressed() {
        finish()
    }

}