package fpinscala.option

/**
 * Created by jooyung.han on 2015-02-10.
 */
object OptionTest {


  def map2[A, B, C](oa: Option[A], ob: Option[B])(f: (A, B) => C): Option[C] =
    oa.flatMap(a =>   // for ( a <- oa
      ob.map(b =>     //       b <- ob )
        f(a, b)))     // yield f(a, b)

  def sequence[A](as: List[Option[A]]): Option[List[A]] = as match {
    case Nil => Some(Nil)
    case h :: t =>   map2(h, sequence(t))(_ :: _)
  }

  println(List(Some(1), Some(2)))


  def main(args: Array[String]): Unit = {

  }
}
