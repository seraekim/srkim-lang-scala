package chapter26

/**
 * 26장 익스트랙터
 * 
 * 아마도 지금까지 패턴 매치를 사용해 데이터를 분해하고 분석하는 간결한 방법에 많이 익숙해졌을 것이다.
 * 26장에서는 이 개념을 더 일반화하는 방법을 설명한다.
 * 
 * 지금까지 생성자를 사용한 패턴은 케이스 클래스와 관련이 있었다. 예를 들어 Some(x) 는
 * Some이 케이스 클래스라서 올바른 패턴이었다. 때로 케이스 클래스는 만들고 싶지 않지만, 이런 패턴은
 * 사용하고 싶을 때가 있다. extractor는 그런 방법을 제공한다.
 * 
 * 26.1 예제: 전자우편 주소 추출
 * 
 * 주어진 문자열이 전자우편 주소인지 여부를 결정하고, 맞다면 주소 중 사용자와 도메인 부분에 접근하고 싶다.
 * 이를 해결하기 위한 전통적인 방법은 보통 3 가지 도우미 함수를 사용한다.
 * 
 * 
 */
class c26_i01 extends App {
/*  
  // 도우미 함수
  def isEMail(s: String): Boolean
  def domain(s: String): String
  def user(s: String): String
  
  val s = "str"
  if (isEMail(s)) println(user(s) + "AT" + domain(s))
  else println("not an email address")
*/
  /*
   * 동작하기는 하지만 어설프다. 테스트를 여러 가지 합쳐야 한다면
   * 프로그램이 더욱 복잡해져 버린다. 예를 들어 어떤 리스트에서 연속으로 두 문자열이 같은 사용자의
   * 전자우편 주소인지 알아내고 싶다면? => 15장에서 이미 패턴 매치가 이상적인 방법임을 보았다.
   * 
   * EMail(user, domain) 문자열에 @ 기호가 있따면 매치 가능한 패턴이다.
   * 
   * s match {
   *   case EMail(user, domain) => println(user + " AT " + domain)
   *   case _ => println("not an email address")
   * }
   * 
   * 같은 전자우편 주소 2개가 연속으로 있는 더 복잡한 경우는..
   * ss match {
   *   case EMail(u1, d1) :: EMail(u2, d2) :: _ if (u1 == u2) => ...
   * }
   * 
   * 위 코드는 세 가지 도우미 함수로 작성한 어떤 것보다 더 읽기 쉽다. 하지만 문제는
   * 문자열이 케이스 클래스가 아니란 점에 있다. 문자열은 EMail(user, domain)에 부합하는 표현으로
   * 돼 있지 않다. 바로 여기서 스칼라의 익스트랙터가 역할을 할 수 있다. 패턴이 타입의 내부 표현을 꼭
   * 따를 필요는 없다.
   */
}