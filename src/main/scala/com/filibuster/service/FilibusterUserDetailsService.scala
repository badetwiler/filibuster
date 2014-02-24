package com.filibuster.service

import org.springframework.security.core.userdetails.{UsernameNotFoundException, User, UserDetails, UserDetailsService}
import java.util.{HashSet,Arrays}
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import com.filibuster.data.FilibusterDataManager
import org.springframework.beans.factory.annotation.Autowired

class FilibusterUserDetailsService extends UserDetailsService
{


  override def loadUserByUsername(username: String): UserDetails =
  {
      null
//    dataManager.getUser(username) match
//    {
//      case Some(user) => new User(user.username, user.hash, true, true, true, true,
//                                  new HashSet[GrantedAuthority](Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))))
//
//      case None => throw new UsernameNotFoundException(s"$username does not exist")
//
//    }

  }

}
