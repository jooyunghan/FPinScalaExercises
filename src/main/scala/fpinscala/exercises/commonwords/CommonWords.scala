package fpinscala.exercises.commonwords

import scala.io.Source

/**
 * Created by jooyung.han on 2015-02-10.
 */
object CommonWords {
  def main(args: Array[String]): Unit = {
    val words: Stream[String] = Source.fromFile("warandpeace.txt").getLines.flatMap(_.split("\\s+")).toStream

    val result: Seq[(String, Int)] = ???

    result map { case (word, count) => "%s %d".format(word, count)} foreach println
  }
}