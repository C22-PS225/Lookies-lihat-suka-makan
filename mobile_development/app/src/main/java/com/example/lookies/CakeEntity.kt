package com.example.lookies

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cakes")
class CakeEntity(
    @field:ColumnInfo(name = "name")
    @field:PrimaryKey
    var name: String,

    @field:ColumnInfo(name = "avatar_url")
    var avatarUrl: String,

    @field:ColumnInfo(name = "desc")
    var desc: String,

    ): Parcelable