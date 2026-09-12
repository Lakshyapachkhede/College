package com.pachkhede.practiceexercise4

data class Student(var name: String,var roll_no:Int, var age :Int, var course:String)
{
    override fun toString(): String {
        return name
    }
}




