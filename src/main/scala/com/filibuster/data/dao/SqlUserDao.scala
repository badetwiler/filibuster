package com.filibuster.data.dao

import org.springframework.beans.factory.annotation._
import org.springframework.stereotype._
import org.springframework.transaction.annotation.{Propagation, Transactional}
import javax.persistence._
import scala.collection.JavaConversions._
import com.filibuster.data.model.User

@Repository("userDao")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
class SqlUserDao extends UserDao
{

  @Autowired
  var entityManager: EntityManager = _

  def save(user: User): Unit = user.id match
  {
    case 0 => entityManager.persist(user)
    case _ => entityManager.merge(user)
  }

  def find(id: Int): Option[User] =
  {
    Option(entityManager.find(classOf[User], id))
  }

  def getAll: List[User] = {
    entityManager.createQuery("From User", classOf[User]).getResultList.toList
  }

  def getByUsername(username:String) : Option[User] =
  {
    None
    //entityManager.createQuery("From user Where lastName = :lastName", classOf[User]).setParameter("lastName", lastName).getResultList.toList
  }
}