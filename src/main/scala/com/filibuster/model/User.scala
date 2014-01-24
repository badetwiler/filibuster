package com.filibuster.model


case class User (username:String, active:Int, email:String, salt: String, hash:String)

