package com.filibuster.data.dao.test

import junit.framework.TestCase
import org.junit.Test
import com.filibuster.data.model.User
import org.junit.runner.RunWith
import org.junit.Assert.{assertTrue,fail}
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

  //val user1 = User("testUser1", 1, "testuser1@testdomain.com", "no_salt", "some_hash")


  @Test
  def testCreateUser() =
  {
//    try{
//      filibusterDataManager.insertUser(user1)
//    }
//    catch
//    {
//      case e:Exception =>
//        e.printStackTrace()
//        fail("expection throw")
//    }

  }


  @Test
  def testUserExists() =
  {

//    try{
//      filibusterDataManager.getUser(user1.username) match
//      {
//        case Some(user) => assertTrue(user.username == user1.username)
//        case None => fail(s"didn't fine ${user1.username} in database")
//      }
//
//    }
//    catch
//      {
//        case e:Exception =>
//          e.printStackTrace()
//          fail("exception thrown")
//      }
  }




}

