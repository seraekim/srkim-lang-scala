package chapter01.item01

import java.util.HashMap

/**
 * 1.3 왜 스칼라인가?
 * - 호환성
 *   제대로 옷 입히기 한다. 예를들어 자바의 Integer.parseInt(str) 대신에  str.toInt 라고 쓸 수 있다.
 *   항상 암시적 변환을 먼저 시도한다.
 *   
 * - 간결성
 *   class 생성이 한 줄로...
 *   
 * - 고수준 추상화
 *   _.isUpper
 *   
 * - 고급 정적 타이핑
 *   정적타입시스템은 변수나 표현식이 저장하거나 계산하는 값의 종류에 따라 이들을 분류한다.
 *   중첩클래스, 제네릭, 교집합, 추상타입
 *   보통 정적타입시스템은 장황해지고, 유연하지 못하기에 동적타입을 좋아하는 프로그래머가 많다.
 *   하지만 스칼라는 정적타입이지만서도 타입추론과, 패턴매치를 통해서 정적/동적타입시스템의 장점을 살린다.
 *   따라서 스칼라의 정적타입시스템은 다음 3가지 이점이 있다.
 *   
 *     - 프로퍼티 검증 : 정적타입시스템은 어떤 종류의 실행 시점 오류가 없음을 증명할 수 있다.
 *       다익스트라 曰 : "테스트는 오류의 존재를 드러낼 뿐이지, 오류의 부재를 증명할 수는 없다."
 *       바로 정적타입시스템은 오류의 부재까지도 보장해준다.
 *     - 안전한 리팩토링 : 정적타입시스템은 코드 베이스를 상당한 자신감을 가지고 변경할 수 있는 안정망 역할을 한다.
 *     - 문서화 : 정적 타입은 컴파일러가 정확성을 검증하기 위해 사용하는 프로그램 문서화다.
 *       심지어 이것은 IDE의 기능에도 영향을 미친다. 예를들어 인자의 타입이 정해져 있기에 개발자에게 알맞은 변수를 추천할 수 있다.
 */
object c01_i03 {
  def main(args: Array[String]): Unit = {
    val name = "";
    // 자바코드
/*boolean nameHasUpperCase = false;
for (int i = 0; i < name.length(); ++i) {
  if (Character.isUpperCase(name.charAt(i))) {
    nameHasUpperCase = true;
    break;
  }
}*/
// 반면 스칼라에서는 다음과 같다.
    val nameHasUpperCase = name.exists(_.isUpper)
    /*
     * 원론적으로, 이런 제어 추상화를 자바에서도 할 수 있다. 이런 추상화 기능을 제공하는 메소드가 들어 있는 인터페이스를 정의해야 한다.
     * 하지만 너무 무겁기 때문에, 보통은 루프를 작성하고, 코드 안에서 늘어난 복잡성과 함께 살아간다.
     */
    
    // 정적타이핑(타입추론)
    // 다음과 같은 경우 두번 반복이라 누구나 짜증난다.
    val x: HashMap[Int, String] = new HashMap[Int, String]()
    
    val x2 = new HashMap[Int, String]();
    // 다음 방법은 JDK7 부터도 존재.. JDK6이전에도 제네릭활용하여 극복 가능하였지...
    val x3: HashMap[Int, String] = new HashMap();
  }
}
/*
// 자바 코드 
class MyClass {
  private int index;
  private Stinrg name;
  
  public MyClass(int index, String name) {
    this.index = index;
    this.name = name;
  }
}
*/
// 스칼라에서는 위 코드를 다음과 같이 쓸 수 있다
// 유일한 차이가 있는데, 변수가 final로 선언된다는 것이다. final이 아닌 것으로 지정도 가능하다.
class MyClass(index: Int, name: String)
