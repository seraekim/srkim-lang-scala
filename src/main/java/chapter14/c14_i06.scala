package chapter14

import chapter10.Element.elem
import org.scalatest.prop.Checkers
import org.scalatest.WordSpec
import org.scalacheck.Prop._

/**
 * 14.6 프로퍼티 기반 테스트
 * 
 * ScalaCheck
 * 테스트 코드가 준수해야 하는 프로퍼티를 명시. 각 프로퍼티에 대해 테스트 데이터를 생성하고
 * 프로퍼티를 잘 지키는지 테스트를 실행.
 * 
 * 스칼라체크는 테스트데이터로 수백개를 생성하고, 만족하는지 하나하나 검사한다.
 * 따라서, 메모리 최댓값을 충분히 할당해줄 필요가 있다.
 */
class ElementSpec2 extends WordSpec with Checkers {
  "elem result" must {
    
    "have passed width" in {
      // ==> 함의 연산자(implication operator) 왼쪽이 true라면 오른쪽도 true
      // 여야 한다는 뜻
      check((w: Int) => w > 0 ==> (elem('x', w, 3).width == w))
    }
    "have passed height" in {
      check((h: Int) => h > 0 ==> (elem('x', 2, h).height == h))
    }
  }
}
