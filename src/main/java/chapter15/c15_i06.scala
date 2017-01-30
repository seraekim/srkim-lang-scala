package chapter15

/**
 * 15.6 Option 타입
 *
 * 실제 값이라면 Some(x), 값이 없으면 None을 돌려줌
 */
object c15_i06 extends App {
  var capitals = Map("US" -> "Washington", "France" -> "Praris")
  println(capitals get "France")
  println(capitals get "Korea")
  // 옵션을 분리해내는 가장 일반적인 방법은 패턴 매치다.
  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None    => "?"
  }
  println(show(capitals get "France"))
  println(show(capitals get "Korea"))
}