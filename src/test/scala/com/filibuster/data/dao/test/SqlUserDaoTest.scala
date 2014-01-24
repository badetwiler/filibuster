package com.filibuster.dao.test

import junit.framework.TestCase
import org.junit.Test
import com.filibuster.model.User


class SqlUserDaoTest extends TestCase
{

  @Test
  def testInsertUser =
  {
    val username = "testUser"
    val active = 1
    val email = "testuser@testdomain.com"
    val salt ="no salt"
    val hash = "some_hash"

    val user = User(username, active, email, salt, hash)



  }

}

