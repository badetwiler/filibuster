package com.filibuster.data.model

import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name="groups")
class Group (_groupname:String)
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var groupname: String = _groupname

  @BeanProperty
  @OneToMany(fetch=FetchType.LAZY, mappedBy = "pk.group")
  var groupMemberAssociations: java.util.List[GroupMemberAssociation]  = new java.util.LinkedList[GroupMemberAssociation]()

  def this() = this (null)

  override def toString = id.toString + " = " + groupname

}
