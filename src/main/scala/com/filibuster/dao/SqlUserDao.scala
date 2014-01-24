package com.filibuster.dao

import java.sql.ResultSet
import com.filibuster.model.User

class SqlUserDao(template0: RichJdbcTemplate) extends BaseDao(template0) with UserDao
{
  private def _mapper = (rs:ResultSet, _:Int) =>
  {

    val username = rs.getString("username")
    val active = rs.getInt("active")
    val email = rs.getString("email")
    val salt = rs.getString("salt")
    val hash = rs.getString("hash")

    User(username,active,email,salt,hash)

  }

  def insert(user:User): Unit =
  {
    val sql = "INSERT INTO users(username,active,email,salt,hash) VALUES (?, ?, ?, ?, ?)"
    update(sql, user.username, user.active, user.email, user.salt, user.hash)

  }

  def selectByUsername(username:String) : Option[User] =
  {
    _template.queryAndMap("SELECT username,active,email,salt,hash FROM users WHERE username = ?", username)(_mapper).headOption

  }





}