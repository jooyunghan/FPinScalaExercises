package fpinscala.exercises.stream

sealed trait Stream[+A] {
  import Stream._

  def head: A
  def tail: Stream[A]
  def isEmpty: Boolean

  def toList: List[A] = this match {
    case Cons(hd, tl) => hd() :: tl().toList
    case _ => List()
  }
  def map[B](f: A => B): Stream[B] =
    if (isEmpty) empty
    else cons(f(head), tail.map(f))

  def filter(pred: A => Boolean): Stream[A] = this match {
    case Cons(hd, tl) =>
      println("filter")
      if (pred(hd()))
        cons(hd(),  tl().filter(pred))
      else
        tl().filter(pred)
    case _ => empty
  }

  def append[B >: A](bs: Stream[B]): Stream[B] = this match {
    case Cons(hd, tl) => cons(hd(), tl().append(bs))
    case _ => bs
  }

  def flatMap[B](f: A => Stream[B]): Stream[B] = this match {
    case Cons(hd, tl) => f(hd()).append(tl().flatMap(f))
    case _ => Empty
  }
}

case object Empty extends Stream[Nothing] {
  val isEmpty = true
  def head: Nothing = throw new NoSuchElementException
  def tail: Stream[Nothing] = throw new NoSuchElementException
  override def toString: String = "Stream()"
}

case class Cons[+A](hd: ()=>A, tl: ()=>Stream[A]) extends Stream[A] {
  val isEmpty = false
  def head: A = hd()
  def tail: Stream[A] = tl()
  override def toString: String = "Stream("+ hd() +", ?)"
}

object Stream {
  def apply[A](as: A*): Stream[A] = {
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))
  }

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty
}

object StreamExercise extends App {
  import Stream._

  val n = 3

  val s = Stream(1,2).append(Stream(3, 4))
  val s2 = s.map(n => n + 1)
  println(s2.toList)

  val s3: Stream[Int] =
    for (a <- Stream(1,2); b <- Stream(3,4)) yield a + b
  println(s3)
  println(s3.toList)
}
