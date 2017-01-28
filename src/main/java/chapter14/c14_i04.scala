package chapter14

import junit.framework.TestCase
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import chapter10.Element.elem
import org.scalatest.junit.JUnit3Suite

/**
 * 14.4 JUnit과 TestNG 사용
 */
class ElementTestCase extends TestCase {
  def testUniformElement() {
    val ele = elem('x', 2, 3)
    assertEquals(2, ele.width)
    assertEquals(3, ele.height)
    try {
      elem('x', -2, -3)
      fail()
    } catch {
      // exception이 발생하더라도 테스트는 success, fail을 경유해야 함.
      case e: IllegalArgumentException => println(e.getMessage)// 발생하리라 예상
    }
  }
}

/*
 * JUnit 테스트에서 ScalaTest 단언 문법을 사용하고 싶다면?? JUnit3Suite 써라
 * JUnit3Suite 트레이트는 junit.framework.TestCase를 상속한다.
 * 또한 JUnit3Suite는 스칼라테스트의 Suite도 믹스인 했다.
 * 
 * 또한 JUnitWrapperSuite 도 스칼라에서 제공하는데, JUnit으로 작성한 자바 테스트를
 * ScalaTest의 실행장치를 통해 실행이 가능한 것이다.
 */
class ElementSuite2 extends JUnit3Suite {
  def testUniformElement() {
    val ele = elem('x', 2, 3)
    assert(ele.width == 2)
    assertResult(3) { ele.height }
    intercept[IllegalArgumentException] {
      elem('x', 2, 3)
    }
  }
}
object c14_i04 {
  
}