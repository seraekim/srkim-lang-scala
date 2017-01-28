package chapter12

/**
 * 12.7 트레이트냐 아니냐, 이것이 문제로다
 * 
 * 재사용 가능한 행위를 구현할 때마다, 트레이트를 사용할지 추상 클래스를 사용할지 결정해야 한다.
 * 확고한 규칙은 없으나 가이드라인이 있다.
 * 
 * 클래스
 *  - 어떤 행위를 재사용하지 않을거라면
 *  - 효율이 중요하다면 클래스. 자바 런타임은 클래스 멤버에 대한 가상 메소드 호출을 인터페이스의
 *    메소드 호출보다 빠르게 수행한다. 트레이트는 인터페이스가 되기 때문에 성능상 부가비용 존재.
 * 추상클래스
 *  - 자바 코드에서 스칼라의 내용을 상속할 때 (자바에는 트레이트가 없으므로)
 *  - 예외적으로, 구현 없이 추상 메소드만 있는 스칼라 트레이트는 내부적으로 자바 인터페이스를
 *    만들어 내기 때문에, 이 경우 트레이트 써도 좋다.
 *  - 컴파일한 바이트코드 형태로 배포할 예정이고, 누군가가 이것을 상속해서 쓸 때
 *    클라이언트 측에서  트레이트를 상속하지 않고 호출만 한다면 트레이트 써도 됨.
 * 
 * 트레이트
 *  - 서로 관련이 없는 클래스에서 어떤 행위를 여러 번 재사용해야 할 때
 * 
 * 위의 경우로 판단이 서지 않는 경우??
 *  - 보통 트레이트가 더 많은 가능성이 있으니 걍 트레이트 써라. 언제든 바꾸는 건 가능하다.
 *  
 * 12.8 결론
 * 
 * 트레이트는 다중 상속과 비슷하나, 선형화를 통해 super를 해석하여, 다중 상속의 어려움을 피하면서,
 * 원하는 기능을 스택처럼 쌓아올릴 수 있다.
 */
object c12_i07 {
  
}