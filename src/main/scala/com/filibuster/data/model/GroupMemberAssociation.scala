package com.filibuster.data.model

import javax.persistence._
import scala.beans.BeanProperty
import com.filibuster.data.model.pk.GroupMemberPk


@Entity
@Table(name="group_members")
@AssociationOverrides(Array(
  new AssociationOverride(name="pk.user",  joinColumns = Array(new JoinColumn(name="userId"))),
  new AssociationOverride(name="pk.group", joinColumns = Array(new JoinColumn(name="groupId")))
))
class GroupMemberAssociation(_user:User, _group:Group, _isGroupCreator:Boolean)
{

  def this() = this(null,null,false)

  @BeanProperty
  @EmbeddedId
  var pk:GroupMemberPk = new GroupMemberPk(_user,_group)

  @BeanProperty
  var isGroupCreator:Boolean = _isGroupCreator

  @BeanProperty
  @Transient
  var user:User = pk.user

  @BeanProperty
  @Transient
  var group:Group = pk.group


  override def hashCode:Int =
  {
      pk == null match
      {
          case true => 0
          case false => pk.hashCode
      }

  }

}

