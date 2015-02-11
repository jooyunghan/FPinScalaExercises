object LookAndSay {
  val a = 3

  List.iterate(1, 10)((n:Int) => n * 2)

  def group[A](as: List[A]): List[List[A]] = as match {
    case Nil => Nil
    case h :: t => as.takeWhile(_ == h) :: group(as.dropWhile(_ == h))
  }

  def lookAndSay(n:Int):List[List[Int]] = {
    List.iterate(List(1), n)(ns =>
      group(ns).flatMap(g => List(g.length, g.head)))
  }

  //lookAndSay(10).map(_.mkString).foreach(println)

  def isPrime(n: Int): Boolean =
    1.to(n).filter(n % _ == 0) == Seq(1, n)

  1 to 10 filter isPrime



}