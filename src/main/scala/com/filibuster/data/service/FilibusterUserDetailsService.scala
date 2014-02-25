package com.filibuster.data.service

import com.filibuster.data.model.User


trait FilibusterUserDetailsService
{

  def createNewUser(user: User)

  def getAllUsers: List[User]

}
