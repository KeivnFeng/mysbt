package akka.kevin.app.scala.actors

import java.util

import akka.actor.Actor
import akka.kevin.app.scala.message.{Result, ReduceData}

/**
  * Created by Kevin on 14/4/2017.
  */
class AggregateActor extends Actor{
  var finalReducedMap = new util.HashMap[String, Integer]


  def aggregateInMemoryReduce(reducedList: util.HashMap[String, Integer]): Unit = {
    var count:Integer = 0
    for(key <- reducedList.keySet()){
      if(finalReducedMap.containsKey(key)){
        count = reducedList.get(key)
        count += finalReducedMap.get(key)
        finalReducedMap.put(key, count)
      }else{
        finalReducedMap.put(key, reducedList.get(key))
      }
    }
  }

  override def receive: Receive = {
    case message: ReduceData =>
      aggregateInMemoryReduce(message.reduceDataMap)
    case message: Result =>
      System.out.println(finalReducedMap.toString())
  }
}
