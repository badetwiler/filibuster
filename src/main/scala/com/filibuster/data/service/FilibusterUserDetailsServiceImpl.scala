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

@Service
class FilibusterUserDetailsServiceImpl @Autowired() (userDao:UserDao) extends UserDetailsService with FilibusterUserDetailsService
{


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

      case Some(user) => new SecurityUser(user.username, user.hashed_password, true, true, true, true,
                                          new HashSet[GrantedAuthority](Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))))

      case None => throw new UsernameNotFoundException(s"$username does not exist")

    }

  }

}
