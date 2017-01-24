package chapter09

/**
 * 9.5 이름에 의한 호출 파라미터
 * 
 * 9.4 에서의 withPrintWriter2 를
 * if, while 처럼 언어가 기본 제공하는 형태(함수가 아닌)로 구현하고 싶다면 어떻게 해야할까?
 * 이때 스칼라에서 사용하는 것이 이름에 의한 호출 파라미터(by-name parameter)다.
 */
object c09_i05 extends App {
  /*
   * 이름에 의한 호출을 사용하지 않는다면 myAssert 를 다음과 같이 만들 수 밖에 없다.
   */
  var assertionEnabled = true
  def myAssert(predicate: () => Boolean) =
    if (assertionEnabled && !predicate())
      throw new AssertionError
  myAssert(() => 5 > 3)
  /*
   * 함수리터럴에서  위와 같은 경우 () => 를 지우고 쓸 수 있다면 참으로 좋을 것이다.
   * 사실, 이름에 의한 호출은 이를 위해 존재한다.
   */
  def byNameAssert(predicate: => Boolean) =
    if (assertionEnabled && !predicate)
      throw new AssertionError
  byNameAssert(5 > 3)
  /*
   * 이제 불필요한 괄호를 쓰지 않아도 되니 언어가 기본 제공하는 제어 구조와 똑같이 사용할 수 있게 되었다.
   * 그런데 다음처럼 애초에 Boolean 인자 하나 받는걸로 만드는게 좋다 생각할지도 모른다.
   */
  def boolAssert(predicate: Boolean) =
    if (assertionEnabled && !predicate)
      throw new AssertionError
  boolAssert(5 < 3)
  /*
   * boolAssert 의 5 < 3은 Boolean 인자로 넘겨지기 직전, 표현식이 계산 된다.
   * 반면, byNameAssert는 => Boolean 타입이기 때문에 표현식을 계산하지 않고
   * 5 < 3을 계산하는 내용의 apply 메소드가 들어간 함수 값을 만들어서 넘긴다.
   * 
   * boolAssert는 무조건 5 < 3가 실행되지만
   * byNameAssert는 assertionEnabled를 false로 한다면, 5 < 3은 실행 안된다.
   * 결국, boolAssert는 부수효과가 발생하는 것이다.
   * 
   * 5 < 3 대신에 x / 0 == 0 을 인자로 넘겨보아라.
   */
}