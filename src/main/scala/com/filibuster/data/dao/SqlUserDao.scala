package com.filibuster.data.dao

import org.springframework.beans.factory.annotation._
import org.springframework.stereotype._
import org.springframework.transaction.annotation.{Transactional, Propagation}
import javax.persistence._
import scala.collection.JavaConversions._
import com.filibuster.data.model.User
import scala.beans.BeanProperty

@Repository("userDao")
@Transactional( propagation = Propagation.MANDATORY )
class SqlUserDao extends UserDao
{

  @BeanProperty
  @PersistenceContext
  @Autowired
  var entityManager:EntityManager = _

  @Transactional
  def save(user: User): Unit =
  {
      user.id match
      {
          case 0 => entityManager.persist(user)
          case _ => entityManager.merge(user)
      }

  }


  def find(id: Int): Option[User] =
  {
    Option(entityManager.find(classOf[User], id))
  }

  def getAll: List[User] =
  {
    entityManager.createQuery("From User", classOf[User]).getResultList.toList
  }

  def getByUsername(username:String) : Option[User] =
  {
    entityManager.createQuery("From user Where username = :username",
                              classOf[User]).setParameter("username", username).getResultList.toList.headOption
  }
}