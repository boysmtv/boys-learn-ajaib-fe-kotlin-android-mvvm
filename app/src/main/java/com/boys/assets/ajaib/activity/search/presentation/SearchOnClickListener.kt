package com.boys.assets.ajaib.activity.search.presentation

import android.view.View
import com.boys.assets.ajaib.activity.search.model.SearchModel

interface SearchOnClickListener<T> {
    fun onItemClick(v: View?, position: Int, searchRespModel: SearchModel)
}