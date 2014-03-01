package com.filibuster.data.dao

import org.springframework.beans.factory.annotation._
import org.springframework.stereotype._
import org.springframework.transaction.annotation.{Transactional, Propagation}
import javax.persistence._
import scala.collection.JavaConversions._
import com.filibuster.data.model.Group
import scala.beans.BeanProperty

@Repository("groupDao")
class GroupDaoImpl extends GroupDao
{

    @BeanProperty
    @PersistenceContext
    @Autowired
    var entityManager:EntityManager = _

    @Transactional
    def save(group:Group): Unit =
    {
      group.id match
      {
        case 0 => entityManager.persist(group)
        case _ => entityManager.merge(group)
      }
    }

    def find(id: Int): Option[Group] =
    {
      throw new UnsupportedOperationException
    }

    def getAll: List[Group] =
    {
      throw new UnsupportedOperationException
    }

    def getByName(groupname:String) : Option[Group] =
    {
      entityManager.createQuery("FROM Group WHERE groupname = :groupname",
                                classOf[Group]).setParameter("groupname", groupname).getResultList.toList.headOption
    }

}
