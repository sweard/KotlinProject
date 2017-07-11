package com.jeff.kotlinproject.utils

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteCursorDriver
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by .F on 2017/7/10.
 */
class MyDbHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    val Create_Book = "create table if not exists Book (" +
            "id integer primary key autoincrement, " +
            "author text, " +
            "price real, " +
            "pages integer, " +
            "bookname text)"

    override fun onCreate(p0: SQLiteDatabase?) {
        LogUtils.debug("create")
        p0?.execSQL(Create_Book)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}