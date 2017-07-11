package com.jeff.kotlinproject.utils

import android.content.ContentValues
import android.content.Context


/**
 * Created by .F on 2017/7/11.
 */
class DataBaseUtil(context: Context) {

    private var helper: MyDbHelper? = null

    init {
        helper = MyDbHelper(context, "BookStore.db", null, 1)
    }


    fun add(): Long? {
        var database = helper?.readableDatabase

        var book1 = ContentValues()
        book1.put("bookname", "The Da Vinci Code")
        book1.put("author", "Dan Brown")
        book1.put("pages", 454)
        book1.put("price", 16.96)
        val insert = database?.insert("Book", null, book1)

        book1.clear()
        book1.put("bookname", "The Lost Symbol")
        book1.put("author", "Dan Brown")
        book1.put("pages", 510)
        book1.put("price", 19.95)
        val insert1 = database?.insert("Book", null, book1)

        LogUtils.debug("add")
        return insert
    }


    fun updateData(): Int? {
        var database = helper?.readableDatabase
        var book = ContentValues()
        book.put("price", 10.99)
        val update = database?.update("Book", book, "bookname = ?", arrayOf("The Da Vinci Code"))
        LogUtils.debug("update")
        return update
    }

    fun delete(): Int? {
        var database = helper?.readableDatabase
        val delete = database?.delete("Book", "pages > ?", arrayOf("500"))
        LogUtils.debug(delete.toString())
        return delete
    }


    fun query() {
        LogUtils.debug("search")
        var database = helper?.readableDatabase
        var cursor = database?.query("Book", null, "price = ?", arrayOf("10.99"), "bookname", "author='Dan Brown'", "author")
        if (cursor != null) {
            LogUtils.debug("cursor!=null")
            if (cursor.moveToFirst()) {
                LogUtils.debug("cursor.moveToFirst")
                do {
                    LogUtils.debug("moveNext")
                    val bookname = cursor.getString(cursor.getColumnIndex("bookname"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    LogUtils.debug("书名：$bookname  作者：$author  页数$pages   价格$price")
                }while (cursor.moveToNext())
            }else{
                LogUtils.debug("cursor.moveToFirst=false")
            }
        }else{
            LogUtils.debug("cursor==null")
        }
        cursor?.close()
    }

}