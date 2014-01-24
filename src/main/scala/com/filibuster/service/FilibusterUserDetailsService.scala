package com.filibuster.service

import org.springframework.security.core.userdetails.{UsernameNotFoundException, User, UserDetails, UserDetailsService}
import java.util.{HashSet,Arrays}
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority


class FilibusterUserDetailsService extends UserDetailsService
{


  def loadUserByUsername(username: String): UserDetails =
  {

    //throw new UsernameNotFoundException()

    val username = "ben"
    val password = "pw"
    new User(username, password, true, true, true, true,
                        new HashSet[GrantedAuthority](Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))))

  }

}
