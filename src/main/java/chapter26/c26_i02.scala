package chapter26

/**
 * 26.2 익스트랙터
 * 
 * 스칼라 익스트랙터는 unapply 메소드 멤버가 있는 객체다. unapply 메소드의 목적은
 * 값을 매치시켜 각 부분을 나누는 것이다. 반대로 값을 만들어내는 apply라는 반대 방향 메소드가
 * 익스트랙터 객체에 들어 있는 경우도 자주 있다. 다만 apply 메소드가 꼭 있어야 하는건 아니다.
 * 
 * (String, String) => String) 는 EMail("a", "b.co") 쓰면, "a@b.co" 만드는 것을 명시적으로 표시
 * 하는데 이를 통해 EMail을 Funtion2[String, String, String]을 인자로 받는 메소드에 전달 가능하다.
 * 
 * unapply 는 apply의 반대역할을 하는데, 주어진 문자열을 잠재적으로 사용자와 도메인 문자열을 반환한다.
 * 그런데 전자우편 주소가 아닌 경우도 처리해야하므로, 문자열 쌍이 들어간 Option 타입의 값을 반환한다.
 * 전자우편 주소가 맞다면 Some(user, domain)이고, 아니라면 None을 반환한다.
 * 
 * 따라서, selectorString match { case EMail(user, domain) => ... } 식은
 * EMail.unapply(selectorString) 과 같은 호출이다.
 * 
 * val 
 */
object EMail extends ((String, String) => String) {
  // injection method (선택)
  def apply(user: String, domain: String) = user + "@" + domain
  
  // extractor method (필수)
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}
object c26_i02 {
  
}