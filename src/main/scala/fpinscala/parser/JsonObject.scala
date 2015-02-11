package fpinscala.parser

/**
 * Created by jooyung.han on 2015-02-11.
 */
sealed trait JsonObject

case object JSNull extends JsonObject
case class  JSBool(value: Boolean) extends JsonObject
case class  JSObject(properties: Map[String, JsonObject]) extends JsonObject
case class  JSArray(values: Seq[JsonObject]) extends JsonObject
case class  JSNumber(value: Double) extends JsonObject
case class  JSString(value: String) extends JsonObject
