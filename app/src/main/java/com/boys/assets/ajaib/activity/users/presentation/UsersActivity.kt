package com.boys.assets.ajaib.activity.users.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.boys.assets.ajaib.activity.search.vm.SearchViewModel
import com.boys.assets.ajaib.activity.users.model.UsersModel
import com.boys.assets.ajaib.databinding.ActivitySearchBinding
import com.boys.assets.ajaib.helper.InterfaceDialog
import com.boys.assets.ajaib.utils.LogUtil
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class UsersActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private val thisContext = this@UsersActivity

    private lateinit var binding            : ActivitySearchBinding
    private lateinit var interfaceDialog    : InterfaceDialog

    private lateinit var usersAdapter       : UsersAdapter
    private lateinit var usersModel         : UsersModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding             = ActivitySearchBinding.inflate(layoutInflater)

        interfaceDialog     = InterfaceDialog(thisContext)
        usersAdapter        = UsersAdapter()
        usersModel          = UsersModel()

        setContentView(binding.root)
        supportActionBar!!.hide()

        val VM by viewModel<SearchViewModel>()

        setAddressVM(VM, binding, usersAdapter)
        setRequest(VM, binding, usersAdapter)
    }

    /**
     * set view model
     */
    private fun setAddressVM(
        VM: SearchViewModel,
        binding: ActivitySearchBinding,
        usersAdapter: UsersAdapter
    ) {
        with(VM){
            onSuccess.observe(thisContext) {
                usersAdapter.provided(it, thisContext, interfaceDialog)
                addressSearchFunction(it, usersAdapter)
            }
            onError.observe(thisContext) {

            }
        }
    }

    private fun setRequest(
        VM: SearchViewModel,
        binding: ActivitySearchBinding,
        usersAdapter: UsersAdapter
    ) {
        // set loading on ui
        interfaceDialog.showDialogLoading("Loading ...")

        binding.rvSearch.adapter = usersAdapter
        VM.doIt()
    }

    private fun addressSearchFunction(
        searchRespModel: ArrayList<UsersModel>,
        usersAdapter: UsersAdapter
    ) {
        binding.etSearchName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                filter(searchRespModel, usersAdapter, s.toString())
            }
        })
    }

    /**
     * set filter for search bank from local list (after get data)
     */
    private fun filter(
        listModel: ArrayList<UsersModel>,
        usersAdapter: UsersAdapter,
        text: String
    ) {
        val filteredList: ArrayList<UsersModel> = ArrayList()
        for (item in listModel) {
            if (item.login!!.toLowerCase().contains(text.lowercase(Locale.getDefault()))) {
                filteredList.add(item)
            }
        }
        usersAdapter.provided(filteredList, this, interfaceDialog)
    }


    override fun onItemClick(v: View?, position: Int, searchRespModel: UsersModel) {
        LogUtil.e("onItemClick", "Value: " + Gson().toJson(searchRespModel))
    }

}