package chapter10

/**
 * 10.4 클래스 확장
 * 
 * 추상 클래스를 상속한다.
 * 
 *  - ArrayElement는 Element에서 비공개(private)가 아닌 멤버를 모두 물려받는다.
 *  - ArrayElement는 Element의 서브타입(subtype)이다.
 *  - extends 절을 생략하면 스칼라 컴파일러는 암묵적으로 scala.AnyRef를 상속한다 가정.
 *    자바로 따지자면 extends Object와 같다.
 */
class ArrayElement(conts: Array[String]) extends Element {
  def contents: Array[String] = conts
}