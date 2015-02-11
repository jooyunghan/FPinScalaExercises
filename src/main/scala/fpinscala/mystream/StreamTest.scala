package fpinscala.mystream

/**
 * Created by jooyung.han on 2015-02-11.
 */
object StreamTest extends App {

  // Stream.iterate(seed)(f)

  val ones = constant(1)

  def constant[A](a: A): Stream[A] =
    a #:: constant(a)

  def from(start: Int): Stream[Int] =
    start #:: from(start + 1)

  // f :: (A, B) => C
  // (A, B)
  // zipWith :: Stream[A], Stream[B], (A, B) => C ==> Stream[C]
  def zipWith[A, B, C](as: Stream[A], bs: Stream[B])(f: (A, B) => C): Stream[C] =
    as.zip(bs).map(f.tupled)

  println(zipWith(from(0), from(1))(_ + _).take(5).toList)
  // 0, 1, 2, 3, 4, ...
  // +  +  +  +  +
  // 1, 2, 3, 4, 5, ...
  // =  =  =  =  =
  // 1, 3, 5, 7, 9, ...


  def fibs: Stream[Int] = 0 #:: 1 #:: zipWith(fibs, fibs.tail)(_+_)
  // fibs      = 0 1 1 2 3 5 8 13 ...
  // fibs.tail   1 1 2 3 5   ...
  //             1 2 3 5     ...

  println(fibs.drop(10).take(10).toList)

  def foldRight[A, B](as: Stream[A], z: =>B)(f: (A, =>B) => B): B = as match {
    case h #:: t => f(h, foldRight(t, z)(f))
    case _ => z
  }

  def takeWhile[A](as: Stream[A])(f: A => Boolean): Stream[A] =
    foldRight(as, Stream.empty[A])((a, b) => if (f(a)) a #:: b else Stream.empty)

  println(takeWhile(fibs)(_ < 10).toList)
}
