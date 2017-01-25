package chapter10

import Element.elem
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
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
  //val height = contents.length
  //val width = if (height == 0) 0 else contents(0).length
  
  def above(that: Element): Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    // ++ 연산은 두 배열을 이어 붙인다.
    elem(this1.contents ++ that1.contents)
  }
  
  // 배열에서 서로 대응하는 원소를 이어붙여 새 배열의 내용을 채운다. 잘 보면 명령형 스타일이니, 함수형으로 바꾸자
  def beside2(that: Element): Element = {
    val contents = new Array[String](this.contents.length)
    for(i <- 0 until this.contents.length)
      contents(i) = this.contents(i) + that.contents(i)
    new ArrayElement(contents)
  }
  // 함수형으로 바꾼 결과
  def beside(that: Element): Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for(
        (line1, line2) <- this1.contents zip that1.contents
      ) yield line1 + line2
    )
  }
  // 빈 파라미터 목록(빈 괄호)를 사용하지 않았음을 주목. 이는 단일 접근 원칙의 권고를 따른 것.
  override def toString = contents mkString "\n"
  
  //두 결합 대상 중 부전째요소의 길이나 높이가 더 크다면 문제가 된다. 공백을 채워준다.
  def widen(w: Int): Element =
    if(w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }
  def heighten(h: Int): Element =
    if(h <= height) this
    else {
      val top = elem(' ', width, (h -height) / 2)
      var bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }
}
/**
 * 10.13 팩토리 객체 정의
 * 
 * 있는 그대로 클라이언트에게 공개해서 사용해도 되겠지만..
 * 팩토리 객체 뒤로 감추고 제공하는 방법도 있다.
 * 
 * 팩토리 객체는 다른 객체를 생성하는 메소드를 제공하는 객체다.
 * 팩토리 객체가 있으면 클라이언트는 new를 이용해 직접 객체를 만들기 보다는, 패곹리 메소드로 객체를 생성한다.
 * 
 * 이점으로는 객체 생성 기능을 한 곳에 모아서 제공하고, 구체적인 내부 표현을 감출 수 있다는 것이다. 클라이언트는
 * 세부사항을 숨기어 라이브러리를 보다 쉽게 이해할 수 있다.
 * 
 * Element 클래스와 싱글톤 객체만 노출하고, ArrayElement, LineElement, UniformElement
 * 구현은 감출 수 있다.
 * 
 * 팩토리 메소드가 있으므로, 명시적으로 새로운 ArrayElement를 생성하는 것보다
 * Element 클래스의 구현을 변경해 팩토리 메소드를 이용하게 만드는 것이 더 합리적이다.
 * Element 클래스에서 new ArrayElement대신에 elem을 쓰자
 * 
 * 팩토리 메소드가 있다면 ArrayElement, LineElement, UniformElement는 클라이언트가 직접
 * 접근할 대상이 아니므로, 비공개로 만든다. 싱글톤 객체인 Element에 클래스를 정의하고 private으로 선언하자.
 * 
 */
object Element {
  def elem(contents: Array[String]): Element = new ArrayElement(contents)
  def elem(chr: Char, width: Int, height: Int): Element = new UniformElement(chr, width, height)
  def elem(line: String): Element = new LineElement(line)
  
  private class ArrayElement(
    val contents: Array[String]
  ) extends Element
  
  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height = 1
  }
  
  private class UniformElement (
    ch: Char,
    override val width: Int,
    override val height: Int
  ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }
}

