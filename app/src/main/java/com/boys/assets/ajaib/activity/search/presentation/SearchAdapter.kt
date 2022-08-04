package com.boys.assets.ajaib.activity.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boys.assets.ajaib.activity.search.model.SearchModel
import com.boys.assets.ajaib.databinding.ActivitySearchListItemBinding
import com.boys.assets.ajaib.helper.InterfaceDialog
import com.bumptech.glide.Glide

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.AddressHolder>() {

    private var listModel = mutableListOf<SearchModel>()
    private lateinit var listener : SearchOnClickListener<SearchModel>

    fun provided(
        model: ArrayList<SearchModel>,
        context: SearchActivity,
        interfaceDialog: InterfaceDialog
    ) {
        this.listModel = model.toMutableList()
        this.listener = context

        interfaceDialog.dismisDialogLoading()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.AddressHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ActivitySearchListItemBinding.inflate(inflater, parent, false)
        return AddressHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.listModel.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.AddressHolder, position: Int) {
        val model = this.listModel[position]
        holder.bind(position, model, listener)
    }

    inner class AddressHolder(binding: ActivitySearchListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val thisBinding: ActivitySearchListItemBinding

        fun bind(position: Int, model: SearchModel, listener: SearchOnClickListener<SearchModel>) {

            thisBinding.tvSearchName.text = model.login

            Glide.with(thisBinding.root).load(model.avatar_url).into(thisBinding.icPhoto)

            // set on click listener
            thisBinding.layoutContent.setOnClickListener { view ->
                listener.onItemClick(
                    view,
                    position,
                    model
                )
            }
        }

        init {
            thisBinding = binding
        }
    }
}