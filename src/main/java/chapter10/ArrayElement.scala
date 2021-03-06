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
 *    
 * 10.5 메소드와 필드 오버라이드
 *    
 * 스칼라에선 필드와 메소드가 같은 네임스페이스에 속하므로, 필드가 파라미터 없는 메소드를
 * 오버라이드 할 수 있다. 반면, 스칼라에서는 같은 이름의 필드와 메소드를 동시에 정의하지 못한다.
 * 
 *  - 자바의 네임스페이스 : 필드, 메소드, 타입, 패키지
 *  - 스칼라의 네임스페이스 : 값(필드, 메소드, 패키지, 싱글톤 객체), 타입(클래스, 트레이트)
 * 
 */
/*class ArrayElement(conts: Array[String]) extends Element {
  // Element의 contents메소드를 오버라이드(구현)
  //def contents: Array[String] = conts
  val contents: Array[String] = conts
  
}*/
/**
 * 10.6 파라미터 필드 정의
 * 
 * 지금까지 만들어진 ArrayElement를 보면
 * conts라는 이름은 contents 와의 불필요한 중복이나 반복을 염두하여 정한 것으로, 코드냄새다.
 * 
 * 다음과 같이 val을 붙여서 넘기는 것은 파라미터와 필드를 동시에하는 단축표기이다.
 * 물론 재할당을 원한다면 var를 사용할 수도 있다.
 */
class ArrayElement(
  val contents: Array[String]
) extends Element

class Cat {
  val dangerous = false
}

class Tiger (
  override val dangerous: Boolean,
  private var age: Int
) extends Cat