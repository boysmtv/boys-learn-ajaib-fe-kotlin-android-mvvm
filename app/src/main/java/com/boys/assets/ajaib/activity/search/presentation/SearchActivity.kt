package com.boys.assets.ajaib.activity.search.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.boys.assets.ajaib.activity.search.model.SearchModel
import com.boys.assets.ajaib.activity.search.vm.SearchViewModel
import com.boys.assets.ajaib.databinding.ActivitySearchBinding
import com.boys.assets.ajaib.helper.InterfaceDialog
import com.boys.assets.ajaib.utils.LogUtil
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity(), SearchOnClickListener<SearchModel> {

    private val TAG = this::class.java.simpleName
    private val thisContext = this@SearchActivity

    private lateinit var binding            : ActivitySearchBinding
    private lateinit var interfaceDialog    : InterfaceDialog

    private lateinit var searchAdapter      : SearchAdapter
    private lateinit var searchModel        : SearchModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding             = ActivitySearchBinding.inflate(layoutInflater)

        interfaceDialog     = InterfaceDialog(thisContext)
        searchAdapter       = SearchAdapter()
        searchModel         = SearchModel()

        setContentView(binding.root)
        supportActionBar!!.hide()

        val VM by viewModel<SearchViewModel>()

        setAddressVM(VM, binding, searchAdapter)
        setRequest(VM, binding, searchAdapter)
    }

    /**
     * set view model
     */
    private fun setAddressVM(
        VM: SearchViewModel,
        binding: ActivitySearchBinding,
        searchAdapter: SearchAdapter
    ) {
        with(VM){
            onSuccess.observe(thisContext) {
                searchAdapter.provided(it, thisContext, interfaceDialog)
                addressSearchFunction(it, searchAdapter)
            }
            onError.observe(thisContext) {

            }
        }
    }

    private fun setRequest(
        VM: SearchViewModel,
        binding: ActivitySearchBinding,
        searchAdapter: SearchAdapter
    ) {
        // set loading on ui
        interfaceDialog.showDialogLoading("Loading ...")

        binding.rvSearch.adapter = searchAdapter
        VM.doIt()
    }

    private fun addressSearchFunction(
        searchRespModel: ArrayList<SearchModel>,
        searchAdapter: SearchAdapter
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
                filter(searchRespModel, searchAdapter, s.toString())
            }
        })
    }

    /**
     * set filter for search bank from local list (after get data)
     */
    private fun filter(
        listModel: ArrayList<SearchModel>,
        searchAdapter: SearchAdapter,
        text: String
    ) {
        val filteredList: ArrayList<SearchModel> = ArrayList()
        for (item in listModel) {
            if (item.login!!.toLowerCase().contains(text.lowercase(Locale.getDefault()))) {
                filteredList.add(item)
            }
        }
        searchAdapter.provided(filteredList, this, interfaceDialog)
    }


    override fun onItemClick(v: View?, position: Int, searchRespModel: SearchModel) {
        LogUtil.e("onItemClick", "Value: " + Gson().toJson(searchRespModel))
    }

}