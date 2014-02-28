package com.filibuster.data.model

import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name="group")
class Group (_groupname:String, _members: List[User])
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var groupname: String = _groupname

  @BeanProperty
  @OneToMany
  var members: java.util.List[User] = _

  def this() = this (null, null)

  override def toString = id.toString + " = " + groupname

}
