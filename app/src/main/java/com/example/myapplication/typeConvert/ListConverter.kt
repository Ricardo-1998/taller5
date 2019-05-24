package com.example.myapplication.typeConvert

import androidx.room.TypeConverter



private const val SEPARATOR = ","
class ListConverter{
        @TypeConverter
        fun toString(value:MutableList<Int>) : String{
            var string1:String = value.map {
                it
            }.joinToString(separator = SEPARATOR)
            /*var string = ""

            for (lang  in value) {
                string = "$string$lang,"
            }*/
            return string1
        }
        @TypeConverter
        fun toList(value:String) : MutableList<Int> {
            val string: MutableList<Int> = value.split(SEPARATOR).map {
                it.toInt()
            }.toMutableList()
            return string
        }
}
            /*{
            var string : List<String> = value.split("\\s*,\\s*")
            val ints:List<Int> = string.map{
                it.toInt()
            }
            return ints
        }*/
