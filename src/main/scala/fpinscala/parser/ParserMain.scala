package fpinscala.parser

import scala.util.matching.Regex


class Parser[+A](run: String => Option[(A, String)]) {

  import fpinscala.parser.Parser._

  def apply(s: String) = run(s)

  def flatMap[B](f: A => Parser[B]): Parser[B] = new Parser(
    s => run(s) flatMap { case (a, rest) => f(a)(rest)}
  )

  def map[B](f: A => B): Parser[B] = flatMap (a => success(f(a)))

  def filter(pred: A => Boolean): Parser[A] = flatMap { a =>
    if (pred(a)) success(a) else failure
  }

  def |[B>:A](other: =>Parser[B]): Parser[B] = new Parser(
     s => run(s).orElse(other(s))
  )

  def many1: Parser[Seq[A]] =
    for(a <- this; as <- many) yield a +: as

  def many: Parser[Seq[A]] =
    many1 | none

  def ~>[B](other: Parser[B]): Parser[B] = flatMap(_ => other)

  // c, c{sep this}*
  def sepBy1[B](sep: Parser[B]): Parser[Seq[A]] =
    for {
      a <- this  // c
      as <- (sep ~> this).many
    } yield a +: as

  def sepBy[B](sep: Parser[B]): Parser[Seq[A]] =
    sepBy1(sep) | none
}

object Parser {
  def star[A](p: Parser[A]): Parser[Seq[A]] =
    ???

  def success[A](a: A): Parser[A] = new Parser(
    s => Option((a, s))
  )

  def failure: Parser[Nothing] = new Parser(
    _ => Option.empty
  )

  def none: Parser[Seq[Nothing]] = success(Seq())

  def item: Parser[Char] = new Parser(
    s => s.headOption.map(c => (c, s.tail))
  )

  def sat(pred: Char => Boolean): Parser[Char] =
    item filter pred

  def char(x: Char): Parser[Char] = sat(_ == x)

  def string(s: String): Parser[String] = new Parser(
    input =>
      if (input.startsWith(s)) Option((s, input.drop(s.length)))
      else Option.empty
  )

  val lower: Parser[Char] = sat(_.isLower)
  val digit: Parser[Char] = sat(_.isDigit)

  val lowers: Parser[String] =
    lower.many map (_.mkString)

  val space: Parser[Unit] = sat(_.isWhitespace).many.map(_ => ())
  def symbol(s: String): Parser[Unit] = space ~> string(s) map (_ => ())
  def token[A](p: Parser[A]): Parser[A] = space ~> p

  def regex(r: Regex): Parser[String] = new Parser(
    input => r.findPrefixOf(input).map(m => (m, input.drop(m.length)))
  )

  def bracket[A](p: Parser[A]) = for {
    _ <- symbol("[")
    ds <- p
    _ <- symbol("]")
  } yield ds

  def brace[A](p: Parser[A]) = for {
    _ <- symbol("{")
    ds <- p
    _ <- symbol("}")
  } yield ds


  val double: Parser[Double]
    = regex("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?".r) map (_.toDouble)
}

object ParserMain extends App {

  import fpinscala.parser.Parser._

  val item12 = item.flatMap(a => item.map(b => (a, b)))
  val item13 = for {
    a <- item
    _ <- item
    b <- item
  } yield (a, b)

  val digits = bracket(token(digit).sepBy(symbol(",")))

  println(item12("abc")) // (a, b) "c"
  println(item13("abc")) // (a, c) ""

  // string("") :: Parser[String]   ^""kdfjkdjf
  println(string("")("abc"))  // "", "abc"

  println(string("ab")("abc"))
  println(lower("abc"))
  println(digit("abc"))
  println(lowers("toUpper"))
  println(token(lowers)("  abc"))
  println(regex("[0-9a-hA-H]{2}".r)("abcd"))
  println(regex("[0-9a-hA-H]{2}".r)("zzcd"))

  println(double("12.3"))
  println(digits(" [ 1 , 2,3, 4 ] "))

  val jsonValue = JsonParser(
    """
      |{
      |  "bool" : true,
      |  "array" : [1,2,3,true,false,null],
      |  "object" : {
      |     "key": "value"
      |     }
      |}
    """.stripMargin)

  println(jsonValue)
  println(JsonParser(
    """
      |{
      | "key": "true"
      |}
    """.stripMargin))
}