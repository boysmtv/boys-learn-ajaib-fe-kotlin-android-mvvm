package com.boys.assets.ajaib.activity.users.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boys.assets.ajaib.activity.search.model.UsersModel
import com.boys.assets.ajaib.activity.users.model.UsersModel
import com.boys.assets.ajaib.databinding.ActivitySearchListItemBinding
import com.boys.assets.ajaib.helper.InterfaceDialog
import com.bumptech.glide.Glide

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.AddressHolder>() {

    private var listModel = mutableListOf<UsersModel>()

    fun provided(
        model: ArrayList<UsersModel>,
        context: SearchActivity,
        interfaceDialog: InterfaceDialog
    ) {
        this.listModel = model.toMutableList()
        this.listener = context

        interfaceDialog.dismisDialogLoading()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.AddressHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ActivitySearchListItemBinding.inflate(inflater, parent, false)
        return AddressHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.listModel.size
    }

    override fun onBindViewHolder(holder: UsersAdapter.AddressHolder, position: Int) {
        val model = this.listModel[position]
        holder.bind(position, model, listener)
    }

    inner class AddressHolder(binding: ActivitySearchListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val thisBinding: ActivitySearchListItemBinding

        fun bind(position: Int, model: UsersModel, listener: SearchOnClickListener<UsersModel>) {

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