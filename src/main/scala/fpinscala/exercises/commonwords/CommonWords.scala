package fpinscala.exercises.commonwords

import scala.io.Source
import scala.collection.mutable.Map
/**
 * Created by jooyung.han on 2015-02-10.
 */
object CommonWords {
  def main(args: Array[String]): Unit = {
    val words: Seq[String] = Source.fromFile("warandpeace.txt").getLines.flatMap(_.split("\\s+")).toSeq

  //
  val result: Seq[(String, Int)] =
    words                            // Seq[String]
      .filter(_.length >= 2)         // Seq[String]
      .groupBy(s => s)               // Map[String, Seq[String]]
      .mapValues(_.length)           // Map[String, Int]
      .toSeq                         // Seq[(String, Int)]
      .sortBy(_._2).reverse          // Seq[(String, Int)]
      .take(100)                     // ..

  result map { case (word, count) => "%s %d".format(word, count)} foreach println
}
}