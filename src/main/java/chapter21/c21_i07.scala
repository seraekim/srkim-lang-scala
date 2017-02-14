package chapter21

import scala.LowPriorityImplicits
import scala.LowPriorityImplicits


/**
 * 21.7 여러 변환을 사용하는 경우
 * 
 * 스칼라 2.7버전까지는 컴파일러가 처리를 거부했으나, 2.8부터는 가능한 변환 중
 * 하나가 다른 하나보다 절대적으로 더 구체적이라면, 컴파일러는 더 구체적인 것을 선택한다.
 * 
 * 메소드가 하나는 String을, 다른 하나는 Any를 받을 수 있다면, 최종적으로 String쪽을
 * 선택한다. 더 구체적인 경우는 두 규칙을 보면 된다.
 * 
 *  - 전자의 인자 타입이 후자의 서브타입이다.
 *  - 두 변환 모두 메소드인데, 전자를 둘러싼 클래스가 후자를 둘러싼 클래스를 확장한다.
 * 
 * 이 주제를 다시 검토해 규칙을 변경하게 된 동기는, 자바 컬렉션과 스칼라 컬렉션, 그리고 문자열의
 * 상호작용성을 향상하기 위해서 였다.
 */
object c21_i07 extends App {
  val cba = "abc".reverse
  /*
   * cba의 타입을 유추하자면 직관적으로는 String이 되어야 할 것 같지만, 스칼라 2.7에서는
   * abc를 스칼라 컬렉션으로 변환하는 것이었꼬, 이것을 다시 뒤집으면 다시 컬렉션이 나오므로
   * cba의 타입은 컬렉션이었다. 물론 문자열로 돌려주는 암시적 변환도 있었찌만, 그 변환이
   * 모든 문제를 해결해주지는 못했다.
   * 
   * 예를 들어 스칼라 2.8이전에는 "abc" == "abc".reverse.reverse 가 false 였다.
   * 
   * 현재는 String에서 StringOps라는 새로운 타입으로 변환하는 더 구체적인 암시적 변환이 하나 생겼다.
   * StringOps에는 reverse 등의 여러 메소드가 있으며, 컬렉션을 반환하는 대신, String을 반환한다.
   * StringOps로 변환하는 기능은 Predef에 들어 있는 반면, 스칼라 컬렉션으로 변환하는 것은 새로운
   * 클래스인 LowPriorityImplicits에 들어 있다.
   * 
   * 그리고 Predef 는 LowPriorityImplicits를 확장한다.
   * 
   * object Predef extends LowPriorityImplicits with DeprecatedPredef ..
   * 
   * 따라서, StringOps 변환이 더 구체적이 되는 것이다. 
   */
}