package chapter10

import Element.elem
/**
 * 10장 상속과 구성
 * 
 * 상속, 구성, 추상 클래스, 파라미터 없는 메소드, 클래스 확장, 메소드 및 필드 오버로드,
 * 파라미터 필드, 슈퍼클래스 생성자 호출, 다형성 및 동적 바인딩, final 멤버와 final 클래스,
 * 팩토리 객체와 팩토리 메소드에 대해서 알아볼 것이다.
 * 
 * 10.1 2차원 레이아웃 라이브러리
 * 
 * 문자열 블럭을 조립하는 라이브러리를 제작해볼 것이다.
 * combinator 들이 만족하는 재미있는 법칙이 있을까?
 * 
 * 10.2 추상 클래스
 *  - Element.scala
 * 
 * 10.5 한데 모아 시험해보기
 * 
 */
object spiral {
  val space = elem(" ")
  val corner = elem("+")
  def spiral(nEdges: Int, direction: Int): Element = {
    if (nEdges == 1)
      space //elem("+")
    else {
      val sp = spiral(nEdges - 1, (direction + 3) % 4)
      def verticalBar = elem('|', 1, sp.height)
      def horizontalBar = elem('-', sp.width, 1)
      if (direction == 0) (corner beside horizontalBar) above (sp beside space)
      else if (direction == 1) (sp above space) beside (corner above verticalBar)
      else if (direction == 2) (space beside sp) above (horizontalBar beside corner)
      else (verticalBar above corner) beside (space above sp)
    }
  }
  // 인터프리터에서 테스트할거라면, Spiral.main(~) 으로 호출하면 된다.
  def main(args: Array[String]): Unit = {
    //val nSides = args(0).toInt
    println(spiral(17, 0))
  }
  
  /*val e1: Element = new ArrayElement(Array("Hello, world"))
  println("e1 : "+e1.contents.toList)
  val e2: Element = new LineElement("Hello")
  println("e2 : "+e2.contents.toList)
  val e3: Element = new UniformElement('x', 2, 3)
  println("e3 : "+e3.contents.toList)*/
}