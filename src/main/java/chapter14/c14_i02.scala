package chapter14

import org.scalatest._
import chapter10.Element.elem

/**
 * 14.2 스칼라에서의 단위 테스트
 *
 * 자바의 JUnit, TestNG, 스칼라의 ScalaTest, specs, ScalaCheck
 * 등이 있다.
 *
 * ScalaTest의 Suite는 묶음을 나타냄.
 *
 * FunSuite 트레이트는 execute 메소드를 재정의해서 메소드가 아니라 함수 값으로 테스트를
 * 정의할 수 있게 해준다.
 *
 * 참고 : http://doc.scalatest.org/1.7/org/scalatest/FunSuite.html
 *
 * 14.3 실패 보고 시 더 많은 정보 제공하기
 *
 * 등호 == 대신에 === 연산자도 넣을 수 있는데, 왼쪽 피연산자와 오른쪽 피연산자의 결과 값을 비교해서 보여주는 역할을
 * 한다고 하나, 실제로 현재 버전에서는 ==도 같은 결과를 보여주므로 차이가 없다.
 *
 * 왼쪽 오른쪽의 차이가 아닌, 예상치와 실제값의 차이를 강조하려면 expect 메소드를 사용한다.
 * 2.8애선 expect 인듯 하나 2.11 에서는 assertResult 라고 써야 먹힌다.
 * 
 * 참고 : http://www.scalatest.org/user_guide/using_assertions
 */
class ElementSuite extends Suite {
  def testUniformElement() {
    val ele = elem('x', 2, 3)
    assert(ele.width == 3)
  }
}
class ElementFunSuite extends FunSuite {
  test("elem result should have passed width") {
    val ele = elem('x', 2, 3)
    /*
     * 때로는 메소드에서 어떤 예외를 발생시키리라 예상할 수도 있다.
     * 코드가 예상하는 예외를 던지지 않는 경우 interecpt는
     * TestFailedException을 던진다.
     * 
     * 무어솝다도 사실 음수를 넘긴다고 해서 IllegalArgumentException 발생하는 로직이 아니다.
     * 왜냐하면 스칼라 2.8 2.11 둘다, String * 음수의 경우 그냥 빈 문자열을 반환하기 때문이다.
     */
    intercept[IllegalArgumentException] {
      // IllegalArgumentException 을 던지므로 성공
      //elem('x', -2, 3)
      // 아무 익셉션 없으니 fail
      elem('x', 2, 3)
    }
    assertResult(3) {
      ele.width
    }
    //assert(ele.width == 3)
    assert(3 == ele.width)
  }

}
object c14_i02 extends App {
  // 확실히는 모르겠지만 Suite가 안 먹힌다. deprecated 어쩌고.. 
  (new ElementSuite).execute()
  // trait FunSuite extends Suite 라 그런지 성공한다.
  // 제대로된 결과를 보고 싶다면 ansi escape plugin 설치 필요
  (new ElementFunSuite).execute()
  //new ElementSuite testUniformElement
}