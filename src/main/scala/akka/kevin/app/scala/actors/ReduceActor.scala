package akka.kevin.app.scala.actors

import java.util

import akka.actor.{Actor, ActorRef}
import akka.kevin.app.scala.message.{ReduceData, Word, MapData}

/**
  * Created by Kevin on 14/4/2017.
  */
class ReduceActor(aggregateActor: ActorRef) extends Actor{

  def reduce(dataList: util.ArrayList[Word]): ReduceData = {
    var reduceMap = new util.HashMap[String, Integer]
    for(wc:Word<-dataList){
      var word:String = wc.word
      if(reduceMap.containsKey(word)){
        reduceMap.put(word, reduceMap.get(word)+1)
      }else{
        reduceMap.put(word, 1)
      }
    }
    return new ReduceData(reduceMap)
  }

  override def receive: Receive = {
    case message: MapData =>
      aggregateActor ! reduce(message.dataList)
    case _ =>
  }
}
