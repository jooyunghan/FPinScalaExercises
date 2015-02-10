package fpinscala.mylist

import scala.collection.mutable.ListBuffer

object ListMain {

  sealed trait List[+A] {
    def isEmpty: Boolean

    def head: A

    def tail: List[A]

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

  case class Cons[A](head: A, tail: List[A]) extends List[A] {
    val isEmpty = false
  }

  object List {
    def apply[A](as: A*): List[A] =
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))
  }

  def sum(ints: List[Int]): Int = ???

  def product(ints: List[Int]): Int = ???

  def map[A, B](as: List[A])(f: A => B): List[B] = as match {
    case Nil => Nil
    case Cons(h, t) => Cons(f(h), map(t)(f))
  }

  def filter[A](as: List[A])(f: A => Boolean): List[A] = ???

  def append[A](a1: List[A], a2: List[A]): List[A] = ???

  def flatten[A](ass: List[List[A]]): List[A] = ???

  def length[A](as: List[A]): Int = ???

  def head[A](as: List[A]): A = ???

  def tail[A](as: List[A]): List[A] = ???

  def take[A](as: List[A], n: Int): List[A] = ???

  def drop[A](as: List[A], n: Int): List[A] = ???

  def takeWhile[A](as: List[A])(f: A => Boolean): List[A] = ???

  def dropWhile[A](as: List[A])(f: A => Boolean): List[A] = ???

  def main(args: Array[String]): Unit = {
    val l = List(1, 2, 3)
    require(map(l)(_ + 1) == List(2, 3, 4))
    require(map(l)(_.toString) == List("1", "2", "3"))
    require(filter(l)(_ % 2 == 0) == List(2))
  }
}
