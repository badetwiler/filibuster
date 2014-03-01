package com.filibuster.data.model

import javax.persistence._
import scala.beans.BeanProperty
import org.hibernate.annotations.Cascade
import java.util

@Entity
@Table(name = "users")
class User (_username:String, _hashed_password:String, _email_address:String)
{

  def this() = this (null, null, null)

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
  @OneToMany(fetch=FetchType.LAZY, mappedBy = "pk.user", cascade = Array(CascadeType.PERSIST, CascadeType.MERGE), orphanRemoval = true)
  @Cascade(Array( org.hibernate.annotations.CascadeType.SAVE_UPDATE) )
  val groupMemberAssociations: java.util.List[GroupMemberAssociation] = new java.util.LinkedList[GroupMemberAssociation]()

  def addGroupMemberAssociation(assoc: GroupMemberAssociation)
  {
    this.groupMemberAssociations.add(assoc)
  }

  def setGroupMemberAssociations(assoc: java.util.Collection[GroupMemberAssociation])
  {
    this.groupMemberAssociations.clear()
    this.groupMemberAssociations.addAll(assoc)
  }

  override def toString = id.toString + " = " + username + " : " + emailAddress

}