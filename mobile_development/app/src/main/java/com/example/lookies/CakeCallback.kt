package com.example.lookies

import androidx.recyclerview.widget.DiffUtil

class CakeCallback(private val mOldGithubUserList: ArrayList<CakeEntity>, private val mNewGithubUserList: ArrayList<CakeEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldGithubUserList.size
    }

    override fun getNewListSize(): Int {
        return  mNewGithubUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldGithubUserList[oldItemPosition].name == mNewGithubUserList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}