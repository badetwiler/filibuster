package com.filibuster.dao.test

import junit.framework.TestCase
import org.junit.Test
import com.filibuster.model.User
import org.junit.runner.RunWith
import org.junit.Assert
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import com.filibuster.data.FilibusterDataManager
import org.springframework.beans.factory.annotation.Autowired

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration( Array("classpath:test-context.xml") )
class SqlUserDaoTest extends TestCase
{


  @Autowired
  var filibusterDataManager:FilibusterDataManager = _

  def setFilibusterDataManager(filibusterDataManager:FilibusterDataManager)
  {
    this.filibusterDataManager = filibusterDataManager
  }

  @Test
  def testInsertUser =
  {
    val username = "testUser"
    val active = 1
    val email = "testuser@testdomain.com"
    val salt ="no salt"
    val hash = "some_hash"

    val user = User(username, active, email, salt, hash)

    try{
      filibusterDataManager.insertUser(user)
      Assert.assertTrue(true)
    }
    catch
    {
      case e:Exception =>
        e.printStackTrace()
        Assert.assertTrue(false)
    }


  }

}

