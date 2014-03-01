package com.filibuster.data.model.pk

import javax.persistence._
import scala.beans.BeanProperty
import com.filibuster.data.model.{Group, User}

@Embeddable
class GroupMemberPk(_user:User, _group: Group) extends Serializable
{

    def this() = this(null,null)

    @BeanProperty
    @ManyToOne
    var user:User = _user

    @BeanProperty
    @ManyToOne
    var group:Group = _group

    /*
    override def equals(o:Object): Boolean =
    {
        o match
        {
            case _ if o == this => true
            case _ if o == null => false
            case _ if o.getClass != getClass => flase
            case _ =>
                val that:GroupMemberPk = o.asInstanceOf[GroupMemberPk]

            user != null match
            {
                case true=>


            }
        }

        if (this == o)
            return true
        if (o == null || getClass != o.getClass) re
    }
    */

    override def hashCode: Int =
    {
        var result:Int = 0
        result = if(user != null) user.hashCode() else 0
        result = result * 31 + (if(group != null) group.hashCode() else 0)
        result
    }


}
