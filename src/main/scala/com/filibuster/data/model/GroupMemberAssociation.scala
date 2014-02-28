package com.filibuster.data.model

import javax.persistence._
import scala.beans.BeanProperty
import com.filibuster.data.model.id.GroupMemberAssociationId

@Entity
@Table(name="group_member")
@IdClass(classOf[GroupMemberAssociationId])
class GroupMemberAssociation
{

  @BeanProperty
  @Id
  var memberId:Long = _

  @BeanProperty
  @Id
  var groupId:Long = _

  @BeanProperty
  var isProjectLead:Boolean = _

  @BeanProperty
  @ManyToOne
  @PrimaryKeyJoinColumn(name="memberId", referencedColumnName = "id")
  var user:User = _

  @BeanProperty
  @ManyToOne
  @PrimaryKeyJoinColumn(name="groupId", referencedColumnName = "id")
  var group:Group = _

}

