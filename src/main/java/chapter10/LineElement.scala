package chapter10

/**
 * 10.7 슈퍼클래스의 생성자 호출
 * 
 * 한 줄짜리 문자열로 이루어진 레이아웃 원소를 표현
 * 
 * 10.8 override 수식자 사용
 * 
 * 우연한 오버라이드는 깨지기 쉬운 기반 클래스(fragile base class)라고 불린다.
 * 이는 클래스 계층에서 기반 클래스(슈퍼클래스)에 추가한 멤버로 인해 클라이언트의 코드가 깨지는 위험을 말한다.
 * 
 * 클라이언트 측에 이미 hidden() 메소드가 있는데, 상속받고있는 클래스엔 기존에 없었으나 hidden()메소드 추가시
 * 클라이언트 측에 override 수식자를 요구함으로써 위와 같은 문제를 피할 수 있다.
 * 
 * 자바의 경우 override 어노테이션은 필수가 아니므로 위와 같은 방법으로 클라이언트 측에 문제를 알리지 못 한다.
 * 
 * 10.11 상속과 구성 사용
 * 
 * 구성과 상속은 이미 존재하는 클래스를 이용해 새로운 클래스를 정의하는 두 가지 방법이다. 주로 코드 재사용을 추구한다면,
 * 상속보다는 구성을 선호할 것이며, 상속의 치명적 단점은 깨지기 쉬운 기반 클래스 문제를 피할 수 없다는 것이다.
 * 
 *  - Is-a 관계를 충족하는가? ArrayElement는 Element이다. 합당하다.
 *  - 클라이언트쪽에서 서브클래스를 원하는가? ArrayElement는 이에 해당한다.
 * 
 * 하지만 LineElement의 경우, ArrayElement의 contents 정의를 재사용하기 위해서 이다. 따라서
 * Element를 직접 상속하는게 맞다.
 * 
 * 새버전에서는 LineElement는 Array와 구성(composition)관계를 갖는다.
 *
 */
class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def width = s.length
  override def height = 1
}
/* 이전 버전 class LineElement(s: String) extends ArrayElement(Array(s)) {
  // 추상멤버를 오버라이드 하는 경우에는 override 생략 가능
  override def width = s.length
  override def height = 1
}*/