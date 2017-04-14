package akka.kevin.app.scala.actors

import java.util
import java.util.StringTokenizer

import akka.actor.{Actor, ActorRef}
import akka.kevin.app.scala.message.{Word, MapData}

/**
  * Created by Kevin on 14/4/2017.
  */
class MapActor(reduceActor: ActorRef) extends Actor{

  val STOP_WORDS_LIST = List("a", "is")

  def evaluateExpression(line: String): MapData = {
    var dataList = new util.ArrayList[Word]
    var parser: StringTokenizer = new StringTokenizer(line)
    var defaultCount: Integer = 1
    while(parser.hasMoreTokens()){
      var word: String = parser.nextToken().toLowerCase()
      if(!STOP_WORDS_LIST.contains(word)){
        var word: String = parser.nextToken().toLowerCase()
        if(!STOP_WORDS_LIST.contains(word)){
          dataList.add(new Word(word, defaultCount))
        }
      }
    }
    return new MapData(dataList)
  }

  override def receive: Receive = {
    case message: String =>
      reduceActor ! evaluateExpression(message)
    case _ =>
  }


}
