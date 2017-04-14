package akka.kevin.app.scala.actors

import akka.actor.{ActorRef, Props, Actor}
import akka.kevin.app.scala.message.Result

/**
  * Created by Kevin on 14/4/2017.
  */
class MasterActor extends Actor{
  val aggregateActor:ActorRef = context.actorOf(Props[AggregateActor], name = "aggregate")
  val reduceActor:ActorRef = context.actorOf(Props(new ReduceActor(aggregateActor)), name="reduce")
  val mapActor:ActorRef = context.actorOf(Props(new MapActor(reduceActor)), name = "map")


  override def receive: Receive = {
    case message: String =>
      mapActor!message
    case message:Result =>
      aggregateActor ! message
    case _ =>
  }
}
