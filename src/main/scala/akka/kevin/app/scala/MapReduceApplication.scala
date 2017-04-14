package akka.kevin.app.scala

import akka.actor.{Props, ActorSystem}
import akka.kevin.app.scala.actors.MasterActor
import akka.kevin.app.scala.message.Result

/**
  * Created by Kevin on 14/4/2017.
  */
object MapReduceApplication {

  def main(args: Array[String]): Unit ={
    val _system = ActorSystem("HelloAkka")
    var master = _system.actorOf(Props[MasterActor], name = "master")

    master ! "Hi! My name is Rocky, I'm so so so happy to be here. "
    master ! "Today, I'm going to read a news article for you. "
    master ! "I hope you'll like it."

    Thread.sleep(500)
    master ! new Result

    Thread.sleep(500)
   // _system.shutdown
  }
}
