package fpinscala.exercises.words

/**
 * Created by jooyung.han on 2015-02-10.
 */
object Words {

  def main(args: Array[String]): Unit = {
    println(words("Hello World, Functional")) // =="Hello", "World,", "Functional"
  }

  def words(s: String): Seq[String] =
    s.split("\\s+")
}
