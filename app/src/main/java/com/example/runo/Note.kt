package com.example.runo

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Notes")

@Parcelize
data class Note (@ColumnInfo(name = "title")val noteTitle :String,
            @ColumnInfo(name = "description")val noteDescription :String,
            @ColumnInfo(name = "createdBy")val createdBy :String,
                 @ColumnInfo(name = "updatedBy")val updatedBy :String?,
            @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "ID")val id :Int?) : Parcelable {

}