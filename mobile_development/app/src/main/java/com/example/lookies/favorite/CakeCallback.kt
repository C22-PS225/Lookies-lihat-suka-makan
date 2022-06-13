package com.example.lookies.favorite

import androidx.recyclerview.widget.DiffUtil

class CakeCallback(
    private val mOldFavCakeList: ArrayList<CakeEntity>,
    private val mNewFavCakeList: ArrayList<CakeEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavCakeList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavCakeList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavCakeList[oldItemPosition].name == mNewFavCakeList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}