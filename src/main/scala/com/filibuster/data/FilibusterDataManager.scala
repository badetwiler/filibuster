package com.filibuster.data

import com.filibuster.data.dao.UserDao
import com.filibuster.model.User


class FilibusterDataManager(userDao:UserDao)
{


  def userExists(username:String) : Boolean =
  {
    userDao.selectByUsername(username) match
    {
      case Some(x) => true
      case None => false
    }
  }

  def insertUser(user:User) =
  {
    userDao.insert(user)

  }


}
