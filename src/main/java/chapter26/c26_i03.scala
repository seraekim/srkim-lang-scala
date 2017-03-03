package chapter26

/**
 * 26.3 변수가 없거나 1개만 있는 패턴
 *
 * unapply 메소드는 성공적인 경우 두 쌍으로 된 원소 값을 반환했다. 이를 N개의 원소로 된 튜플을 Some에 감싸서
 * 반환하는 것으로 일반화가 가능하다. 하지만 패턴이 변수를 하나만 바인딩해야 하는 경우는 다르게 취급한다.
 * 스칼라에는 1튜플이 없기 때문이다. 원소자체를 Some으로 감싸는 것으로 해결할 수 있다.
 */
object Twice {
  def apply(s: String): String = s + s
  def unapply(s: String): Option[String] = {
    val length = s.length / 2
    val half = s.substring(0, length)
    if (half == s.substring(length)) Some(half) else None
  }
}
/*
 * 또한 아무 변수도 바인딩 하지 않는 익스트랙터 패턴도 가능하다. 이런 경우와 대응하는 unapply 메소드는
 * Boolean 값을 반환한다.
 */
object UpperCase {
  // 구성할 만한 대상이 없으므로 apply는 없다.
  def unapply(s: String): Boolean = s.toUpperCase == s
}
object c26_i03 extends App {

  /*
   * 어떤 전자우편 주소의 사용자 부분이 두 번 반복되는 같은 대문자 문자열인 경우와 매치된다.
   */
  def userTwiceUpper(s: String) = s match {
    case EMail(Twice(x @ UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ =>
      "no match"
  }                                               //> userTwiceUpper: (s: String)String

	userTwiceUpper("DIDI@hotmail.com")        //> res0: String = match: DI in domain hotmail.com
	userTwiceUpper("DIDO@hotmail.com")        //> res1: String = no match
	userTwiceUpper("didi@hotmail.com")        //> res2: String = no match
	/*
	 * 함수 userTwiceUpper의 UpperCase가 빈 파라미터 목록을 취한다는 사실을 기억하라.
	 * 15.2절에서 설명한 "표준 변수 바인딩 방식"을 사용한다. x @ UpperCase() 는 UpperCase()에 의해
	 * 매치되는 패턴을 x 변수에 바인딩한다. 예를 들어, 위의 첫 userTwiceUpper 호출에서 x는 "DI"가 된다.
	 * 이 값이 UpperCase() ㅐ턴에 매치가 이뤄진 값이기 때문이다.
	 */
}