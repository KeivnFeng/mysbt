package akka.kevin.app.scala.message

import java.util.{HashMap, ArrayList}
class Word(val word:String, val count:Integer)
case class Result()
class MapData(val dataList: ArrayList[Word])
class ReduceData(val reduceDataMap: HashMap[String, Integer])


