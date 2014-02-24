package com.filibuster.data.dao

import com.filibuster.data.model.User

trait UserDao
{

  def save(user:User): Unit

  def find(id: Int): Option[User]

  def getAll: List[User]

  def getByUsername(username:String) : Option[User]


}
