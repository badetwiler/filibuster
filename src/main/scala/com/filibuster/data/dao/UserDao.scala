package com.filibuster.data.dao

import com.filibuster.model.User

trait UserDao
{

  def insert(user:User): Unit

  def selectByUsername(username:String) : Option[User]


}
