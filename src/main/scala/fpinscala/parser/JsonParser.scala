package fpinscala.parser

/**
 * Created by jooyung.han on 2015-02-11.
 */
object JsonParser {

  import Parser._

  def apply(s: String): Option[JsonObject] =
    jsonValue(s).map(_._1)

  def jsonValue: Parser[JsonObject] =
    jsonObject | jsonNumber | jsonLiteral | jsonArray | jsonString

  def jsonNumber: Parser[JSNumber] = double map (JSNumber(_))

  def jsonLiteral: Parser[JsonObject] =
    symbol("true").map(_ => JSBool(true)) |
      symbol("false").map(_ => JSBool(false)) |
      symbol("null").map(_ => JSNull)

  def jsonArray: Parser[JSArray] =
    bracket(jsonValue.sepBy(symbol(","))) map (JSArray(_))

  def jsonString = quotedString map (JSString(_))

  def jsonObject =
    brace(keyValue.sepBy(symbol(","))) map (kvs => JSObject(kvs.toMap))

  def keyValue = for {
    k <- quotedString
    _ <- symbol(":")
    v <- jsonValue
  } yield (k, v)

  def unquote(s: String) = s.tail.init

  def quotedString = token(regex("\"[^\"]*\"".r)) map (unquote)
}
