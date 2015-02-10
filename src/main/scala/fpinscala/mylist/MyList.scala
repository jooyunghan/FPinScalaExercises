package fpinscala.mylist

import scala.collection.mutable.ListBuffer

object ListMain {

  sealed trait List[+A] {
    def isEmpty: Boolean

    def head: A

    def tail: List[A]

    def ++[B >: A](bs: List[B]): List[B] =
      append(this, bs)

    override def toString: String = {
      val result = new ListBuffer[A]
      var cur = this
      while (!cur.isEmpty) {
        result += cur.head
        cur = cur.tail
      }
      result.mkString("List(", ", ", ")")
    }
  }

  case object Nil extends List[Nothing] {
    val isEmpty = true

    def head: Nothing = throw new NoSuchElementException

    def tail: List[Nothing] = throw new NoSuchElementException
  }

  case class Cons[+A](head: A, tail: List[A]) extends List[A] {
    val isEmpty = false
  }

  object List {
    def apply[A](as: A*): List[A] =
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))
  }

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(h, t) => h + sum(t)
  }

  def product(ints: List[Int]): Int = ints match {
    case Nil => 1
    case Cons(h, t) => h * product(t)
  }

  def map[A, B](as: List[A])(f: A => B): List[B] = as match {
    case Nil => Nil
    case Cons(h, t) => Cons(f(h), map(t)(f))
  }

  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = ???

  def filter[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Nil => Nil
    case Cons(h,t) if f(h) => Cons(h, filter(t)(f))
    case Cons(_, t) => filter(t)(f)
  }

  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(h,t) => Cons(h, append(t, a2))
  }

  def flatten[A](ass: List[List[A]]): List[A] = ass match {
    case Nil => Nil
    case Cons(as, t) => append(as, flatten(t))
  }

  def length[A](as: List[A]): Int = as match {
    case Nil => 0
    case Cons(_, t) => 1 + length(t)
  }

  def head[A](as: List[A]): A = as.head

  def tail[A](as: List[A]): List[A] = as.tail

  def take[A](as: List[A], n: Int): List[A] =
    if (n <= 0) Nil
    else Cons(head(as), take(tail(as), n-1))

  def drop[A](as: List[A], n: Int): List[A] =
    if (n <= 0) as
    else drop(as.tail, n-1)

  def takeWhile[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Cons(h, t) if f(h) => Cons(h, takeWhile(t)(f))
    case _ => Nil
  }

  def dropWhile[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Cons(h, t) if f(h) => dropWhile(t)(f)
    case Nil => Nil
    case _ => as
  }

  def zip[A, B](as: List[A], bs: List[B]): List[(A, B)] = (as, bs) match {
    case (Cons(x,xs), Cons(y,ys)) => Cons((x, y), zip(xs, ys))
    case _ => Nil
  }

  def zipWith[A, B, C](as: List[A], bs: List[B])(f: (A, B) => C): List[C] = (as, bs) match {
    case (Cons(x,xs), Cons(y,ys)) => Cons(f(x, y), zipWith(xs, ys)(f))
    case _ => Nil
  }

  def group[A](as: List[A]): List[List[A]] = as match {
    case Nil => Nil
    case Cons(h, t) => Cons(takeWhile(as)(h == _), group(dropWhile(as)(h == _)))
  }





  def main(args: Array[String]): Unit = {
    val l = List(1, 2, 3)
    require(map(l)(_ + 1) == List(2, 3, 4))
    require(map(l)(_.toString) == List("1", "2", "3"))
    require(filter(l)(_ % 2 == 0) == List(2))

    require(sum(List(1,2,3,4)) == 10)

    require(append(List(1,2,3), List(4,5)) == List(1,2,3,4,5))
    require(flatten(List(List(1,2,3), List(4,5))) == List(1,2,3,4,5))


    require(dropWhile(List(1,2,3,4,5))(n => n < 3) == List(3,4,5))
  }
}
