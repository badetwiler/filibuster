package com.filibuster.data.model

import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name = "user")
class User (_username:String, _hashed_password:String, _email_address:String)
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var username: String = _username

  @BeanProperty
  var hashedPassword: String = _hashed_password

  @BeanProperty
  var emailAddress: String = _email_address

  @BeanProperty
  @OneToMany
  var groups: java.util.List[Group] = _


  def this() = this (null, null, null)

  override def toString = id.toString + " = " + username + " : " + emailAddress

}