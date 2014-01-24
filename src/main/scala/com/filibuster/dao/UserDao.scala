package com.filibuster.dao

import com.filibuster.model.User

trait UserDao
{

  def insert(user:User): Unit

  def selectByUsername(username:String) : Option[User]


}
