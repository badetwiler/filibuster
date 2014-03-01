package com.filibuster.data.service

import org.springframework.stereotype.Service
import com.filibuster.data.dao.{GroupDao, UserDao}
import org.springframework.beans.factory.annotation.Autowired
import com.filibuster.data.model.{User, GroupMemberAssociation, Group}
import java.util
import org.springframework.transaction.annotation.Transactional

@Service
class FilibusterGroupServiceImpl @Autowired() (userDao:UserDao, groupDao:GroupDao) extends FilibusterGroupService
{

  def usernameExists(username:String):Boolean =
  {
    userDao.getByUsername(username) match
    {
      case Some(user) => true
      case None => false
    }
  }

  @Transactional
  def createGroup(groupname:String):Boolean =
  {
      groupDao.save(new Group(groupname))
      true
  }

  @Transactional
  def addGroupOwner(groupname:String, owner:String): Boolean =
  {

    userDao.getByUsername(owner) match
    {
      case None => false

      case Some(user)  =>

        groupDao.getByName(groupname) match
        {
          case None => false

          case Some(group) =>

            val associations = new util.LinkedList[GroupMemberAssociation]()
            associations.add(new GroupMemberAssociation(user,group,true))
            user.setGroupMemberAssociations(associations)
            true

        }



    }

  }


}
