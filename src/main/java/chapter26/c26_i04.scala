package chapter26

/**
 * 26.4 가변 인자 익스트랙터
 * 
 * 앞에서 본 전자우편 주소 익스트랙터 메소드는 모두 정해진 개수의 값을 반환했다. 때때로 이는 충분히
 * 유연하지 못하다. 예를 들어 도메인 이름을 표현하는 문자열을 매치하되 도메인의 각 부분을 다른 하위 패턴에
 * 저장하고 싶다고 하자.
 * 
 * 이 예제의 구성은 도메인을 역순으로 확장하게 되어있기에 시퀀스 패턴의 이점을 더 살릴 수 있다.
 * 15.2절에서 인자 목록의 나머지 원소를 시퀀스에 매치해주는 시퀀스 와일드 카드 패턴인 _*를 봤다.
 * 이 기능은 상위 도메인이 먼저 오는 경우 더 유용하다. 이를 사용해 깊이가 일정치 않은 하위 도메인을
 * 매치할 수 있기 때문이다.
 * 
 * 스칼라에서는 가변 길이를 처리할 때 다른 익스트랙션 메소드를 정의해 사용한다.
 * 이 메소드는 unapplySeq라고 한다. 어떻게 unapplySeq를 작성하는지 보기 위해
 * Domain 익스트랙터를 살펴보자.
 */
object Domain {
  /*
   * 필수는 아니지만, 대칭성을 위해 최상위 도메인부터 시작해 도메인 이름의 각 부분을 다양한 길이의 파라미터 인자로
   * 받을 수 있는 apply도 만들었다.
   */
  def apply(parts: String*): String = parts.reverse.mkString(".")
  /*
   * 문자열을 점으로 구분. split의 결과는 부분 문자열의 배열로, 모든 원소를 뒤집어서 Some으로 감싼다.
   * 결과타입은 꼭 Option[Seq[T]]와 부합해야 한다. T는 어떤 것이든 된다.
   * 17.1절에서 봤듯, Seq는 List, Array, WrappedString 등 여러 클래스의 공통 슈퍼
   * 클래스다.
   */
  def unapplySeq(whole: String): Option[Seq[String]] =
    Some(whole.split("\\.").reverse)
}
/*
 * unapplySeq에서 가변 길이 부분과 고정적인 요소를 함께 반환할 수도 있다. 이를 표현 하기 위해서는
 * 튜플에 모든 원소를 넣되, 언제나 처럼 마지막에 가변 부분을 넣으면 된다.
 */
object ExpandedEMail {
  def unapplySeq(email: String): Option[(String, Seq[String])] = {
    val parts = email split "@"
    if (parts.length == 2)
      Some(parts(0), parts(1).split("\\.").reverse)
    else
      None
  }
}
object c26_i04 extends App {
  /*
   * Domain 익스트랙터를 사용해 더 자세한 정보를 얻을 수 있다.
   * 이름이 "tom"이고 최상위 도메인이 "sun.com"인 전자우편 주소를 검색하려면?
   */
  def isTomInDotCom(s: String): Boolean = s match {
    case EMail("tom", Domain("com","sun", _*)) => true
    case _ => false
  }                                               //> isTomInDotCom: (s: String)Boolean
  isTomInDotCom("tom@sun.com")                    //> res0: Boolean = true
  isTomInDotCom("tom@ss.SSsssun.com")             //> res1: Boolean = false
  isTomInDotCom("tom@srkim.sun.com")              //> res2: Boolean = true
  
  val s = "tom@support.epfl.ch"                   //> s  : String = tom@support.epfl.ch
  val ExpandedEMail(name, topdom, subdoms @ _*) = s
                                                  //> name  : String = tom
                                                  //| topdom  : String = ch
                                                  //| subdoms  : Seq[String] = WrappedArray(epfl, support)
  
}