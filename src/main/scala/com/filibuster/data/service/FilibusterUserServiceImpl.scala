package com.filibuster.data.service

import org.springframework.security.core.userdetails.{UsernameNotFoundException, UserDetails, UserDetailsService}
import org.springframework.security.core.userdetails.{User => SecurityUser}

import java.util.{HashSet,Arrays}
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.beans.factory.annotation.Autowired
import com.filibuster.data.dao.UserDao
import com.filibuster.data.model.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.{Propagation, Transactional}

//@Transactional( propagation = Propagation.REQUIRES_NEW )
@Service
class FilibusterUserServiceImpl @Autowired() (userDao:UserDao) extends UserDetailsService with FilibusterUserDetailsService
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
  def createNewUser(user: User) =
  {
      userDao.save(user)
  }

  def getAllUsers: List[User] =
  {
      userDao.getAll
  }

  override def loadUserByUsername(username: String): UserDetails =
  {

    userDao.getByUsername(username) match
    {

      case Some(user) => new SecurityUser(user.username, user.hashedPassword, true, true, true, true,
                                          new HashSet[GrantedAuthority](Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))))

      case None => throw new UsernameNotFoundException(s"$username does not exist")

    }

  }

}
