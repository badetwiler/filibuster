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
class GroupMemberAssociation
{

  @BeanProperty
  @EmbeddedId
  var pk:GroupMemberPk = new GroupMemberPk

  @BeanProperty
  var isProjectLead:Boolean = _

  @BeanProperty
  @Transient
  var user:User = _

  @BeanProperty
  @Transient
  var group:Group = _


  override def hashCode:Int =
  {
      pk == null match
      {
          case true => 0
          case false => pk.hashCode
      }

  }

}

