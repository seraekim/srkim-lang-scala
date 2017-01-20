package chapter07

/**
 * 7.5 match 표현식
 */
object c07_i05 extends App {
  val myArgs = Array("salt","pp")
  val firstArg = if (myArgs.length > 0) myArgs(0) else ""
    // 자바의 switch문과 달리 match 표현식의 결과는 값이다.
  val friend =
    firstArg match {
      // 자바와 달리 break 필요 없다.
      case "salt" => "pepper"
      case "chips" => "salsa"
      case "eggs" => "bacon"
      // 완전히 알려지지 않은 값을 표시하기 위한 위치 지정자 placeholder _
      case _ => "huh?"
    }
  println(friend)
}