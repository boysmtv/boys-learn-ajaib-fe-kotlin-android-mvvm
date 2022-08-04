package com.boys.assets.ajaib.activity.users.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boys.assets.ajaib.activity.users.model.UsersModel
import com.boys.assets.ajaib.activity.users.vm.UsersViewModel
import com.boys.assets.ajaib.databinding.ActivityUsersBinding
import com.boys.assets.ajaib.helper.InterfaceDialog
import com.boys.assets.ajaib.utils.LogUtil
import com.bumptech.glide.Glide
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private val thisContext = this@UsersActivity

    private lateinit var binding            : ActivityUsersBinding
    private lateinit var interfaceDialog    : InterfaceDialog

    private lateinit var reposAdapter       : ReposAdapter
    private lateinit var usersModel         : UsersModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding             = ActivityUsersBinding.inflate(layoutInflater)

        interfaceDialog     = InterfaceDialog(thisContext)
        reposAdapter        = ReposAdapter()
        usersModel          = UsersModel()

        setContentView(binding.root)
        supportActionBar!!.hide()

        val VM by viewModel<UsersViewModel>()

        getIntentExtra()
        setAddressVM(VM, binding, reposAdapter)
        setRequest(VM, binding, reposAdapter)
    }

    /**
     * set view model
     */
    private fun setAddressVM(
        VM: UsersViewModel,
        binding: ActivityUsersBinding,
        reposAdapter: ReposAdapter
    ) {
        with(VM){
            onSuccessUsers.observe(thisContext) {
                setProfile(it, binding, VM)
            }
            onErrorUsers.observe(thisContext) {
                val confirmDialog = interfaceDialog.showDialogConfirmWarning("Warning!", "Failed get profile")
                confirmDialog.setConfirmClickListener {
                    confirmDialog.dismiss()
                }
                confirmDialog.show()
            }
            onSuccessRepo.observe(thisContext) {
                reposAdapter.provided(it, thisContext, interfaceDialog)
            }
            onErrorRepo.observe(thisContext) {
                val confirmDialog = interfaceDialog.showDialogConfirmWarning("Warning!", "Failed get repos")
                confirmDialog.setConfirmClickListener {
                    confirmDialog.dismiss()
                }
                confirmDialog.show()
            }
        }
    }

    private fun setProfile(model: UsersModel, binding: ActivityUsersBinding, VM: UsersViewModel) {
        interfaceDialog.dismisDialogLoading()

        binding.tvUsersName.text = model.name
        binding.tvUsersLogin.text = "@${model.login}"
        binding.tvUsersFollowers.text = model.followers.toString()
        binding.tvUsersFollowing.text = model.following.toString()
        binding.tvUsersCompany.text = model.company ?: "This is default cause the field is null"
        binding.tvUsersLocation.text = model.location ?: "This is default cause the field is null"
        binding.tvUsersEmail.text = model.email ?: "This is default cause the field is null"
        Glide.with(binding.root).load(model.avatar_url).into(binding.icPhoto)

        binding.rvUsers.adapter = reposAdapter
        interfaceDialog.showDialogLoading("Loading ...")

        VM.getRepo(usersModel.login)
    }

    private fun setRequest(
        VM: UsersViewModel,
        binding: ActivityUsersBinding,
        reposAdapter: ReposAdapter
    ) {
        // set loading on ui
        interfaceDialog.showDialogLoading("Loading ...")

        VM.getUser(usersModel.login)
    }

    private fun getIntentExtra(){
        val intent = intent
        usersModel.login = intent.getStringExtra("login")

        LogUtil.e(TAG, "model: ${usersModel.login}")
    }
}