package chapter14

import chapter10.Element.elem
import org.scalatest.Matchers
import org.scalatest.FlatSpec

/**
 * 14.5 명세를 테스트로 사용하기
 * 
 * 동작 주도 개발(BDD, behavior-driven development) 테스트 스타일은
 * 기대하는 코드 동작을 사람이 읽을 수 있는 명세로 작성하고, 코드가 그 명세에 따라 동작하는지
 * 확인하는 테스트를 함께 추가하는데 중점을 둔다.
 * 
 * ScalaTest의 FlatSpect을 사용한 동작 명시와 테스트
 * 스칼라테스트 2.x 에서는 org.scalatest.matchers.ShouldMatchers를 쓰지만, 
 * 3.x 에서는 org.scalatest.Matchers을 쓴다.
 * 
 * FlatSpect 에서는 명세 절(specifier clause) 사용.
 * ScalaTest는 자체 DSL에 많은 연결자를 제공한다.
 * 
 * should 보다 must 선호한다면 Matchers 대신에 MustMatchers
 * 
 */
class ElementSpec extends FlatSpec with Matchers {
  // 먼저 테스트할 주제에 대해 이름을 문자열로 붙이는 것부터 시작
  // 그 뒤에 should, must, can 넣고, 그 뒤에 그 주제의 작동을 설명하는  문자열과 in
  "A UniformElement" should
  // in의 중괄호에다 명세에 따른 동작을 테스트하는 코드를 작성
    "have a width equal to the passed value" in {
    val ele = elem('x', 2, 3)
    ele.width should be (3)
  }
  // 그 다음절에서 가장 최근에 언급한 주제에 대해 언급할거면 it 사용
  it should "have a height equals to the passed value" in {
    val ele = elem('x', 2, 3)
    ele.height should be (4)
  }
  // 스칼라테스트 3.x의 문법
  // 참고 : http://www.scalatest.org/user_guide/using_matchers#expectedExceptions
  it should "throw an IAE if passed a negative width" in {
    an [IllegalArgumentException] should be thrownBy elem('x', 2, 3)
  }
  // 스칼라테스트 2.x의 문법
  /*it should "throw an IAE if passed a negative width" in {
    evaluating {
      elem('x', -2, 3)
    } should produce [IllegalArgumentException]
  }*/
}
object c14_i05 {
  
}