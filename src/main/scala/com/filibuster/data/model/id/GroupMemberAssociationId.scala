package com.filibuster.data.model.id

import scala.beans.BeanProperty


class GroupMemberAssociationId(_memberId:Long, _groupId:Long) extends Serializable
{

  @BeanProperty
  val memberId:Long = _memberId


  @BeanProperty
  val groupId:Long = _groupId

  def this() = this(-1,-1)

  override def toString = memberId.toString + " - " + groupId

}
