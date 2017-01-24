package chapter10

/**
 * 10.2 추상  클래스
 * 
 * 각 요소는 여러 글자로 이뤄진 2차원 사각형이다.
 * 
 * 10.3 파라미터 없는 메소드 정의
 * 
 * 10.4 클래스 확장
 *  - ArrayElement.scala
 */
abstract class Element {
  /*
   * contents : 문자열하나는 한줄을 차지
   * - 구현이 없는 메소드 선언
   * - 클래스의 추상 멤버
   * - 추상 멤버를 가지려면 반드시 추상 클래스로 선언
   * - 추상 클래스는 인스턴스로 만들 수 없다. new Element
   * - 추상메소드는 구현이 없으면 추상으로 인식되어 abstract 필요없음.
   * - 선언(declaration) 만 되어 있고, 정의(definition)는 없음.
   */
  def contents: Array[String]
  /*
   * 파라미터를 받지 않는 메소드다. def width() 처럼 괄호를 써도 된다.
   * 상당한 혼란을 야기할 수 있기에 호출시에는 width, width() 둘다 가능하다(빈괄호 오버라이드).
   * println, println() 도 둘다 가능하다. 그러나 부수효과가 있으니 () 쓰는게 백번 좋다.
   * 
   * 부수효과가 있다면 ()를 명시하는 것이 좋다. 자칫 필드로 접근하는 것으로 오해할 수 있기 때문이다.
   * 
   * 그렇다면 def 와 val이 하는 일이 같다면 메소드, 필드 둘중 무엇으로 선언하는게 좋을까?
   * - 필드로 구현 시 클래스 초기화 시 값을 미리 계산하기에, 매번 수행하는 메소드 보다는 약간 빠르다.
   * - 필드로 구현 시 Element 클래스 객체마다 값을 저장할 별도의 메모리 공간이 필요하다.
   * - 결론 : 무엇을 선택할 지는 시간에 지남에 따라 다르다. 답은 없다.
   * 
   * val 이든 def 든 중요한 것은, 단일 접근 원칙을 지켜서 클라이언트 코드에 영향을 줘선 안된다.
   */
  //def height: Int = contents.length
  //def width: Int = if (height == 0) 0 else contents(0).length
  val height = contents.length
  val width = if (height == 0) 0 else contents(0).length
}