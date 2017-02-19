package chapter22

/**
 * 22.3 실제 List 클래스
 * 
 * 앞 절의 incAll 과 같은 비 꼬리 재귀 구현은 스택 오버플로우 문제가 존재한다.
 * 
 * 실제 map 메소드(ListBuffer 사용) 구현 소스 및 :: 클래스는 
 * var 변수를 쓰고 있으며, 매우 효율적이다.
 * 
 * 22.4 외부에서 볼 때는 함수형
 * 
 * 리스트가 외부에서는 완저닣 함수적이지만, 내부에서는 리스트 버퍼를 사용해 명령형으로 되어 있음을
 * 볼 수 있었다. 이는 스칼라 핵심전략으로, 순수하지 않은 연산의 효과가 미치는 범위를 주의 깊게
 * 제한함으로써 함수적 순수성을 효율적으로 달성하는 것이다. ::를 사용해 리스트를 구성하면
 * 생성한 리스트의 꼬리를 재사용한다.
 * 
 * 22.5 결론
 * 
 * 22장에서는 스칼라가 리스트를 구현한 방법을 살펴봤다. 리스트의 두 서브 클래스인
 * Nil과 ::는 모두 케이스 클래스다. 하지만 많은 핵심 리스트 메소드는 이런 구조를 재귀적으로
 * 처리하는 대신 ListBuffer를 사용한다. ListBuffer는 추가 메모리를 사용하지 않고도
 * 리스트를 효율적으로 만들어낼 수 있도록 주의 깊게 고안한 것이다.
 * 
 * 바깥에서 볼 때는 함수적이지만, 내부에서는 가장 일반적인 사용 형태인 toList를 호출한 다음에
 * 버퍼를 없애는 경우를 빠르게 처리할 수있도록 변경 간으한 값을 사용한다.
 */
object c22_i03 extends App {
  /*
   * :: 꼬리를 재사용하므로 ys, zs는 xs를 공유하게 된다.
   */
  val xs = List()
  val ys = 1 :: xs
  val zs = 2 :: xs
  /*
   * 만약
   * ys.drop(2).tail = Nil 이 가능하다고 했을 때
   * 부수효과로  zs와 xs의 리스트도 줄어들 수 있다. 변경을 추적하기가 어려워지는 것이다. 그래서
   * 스칼라는 공유를 곳곳에 사용하는 대신 리스트를 변경하지 못하게 막는 쪽을 선택했다.
   * 
   * 스칼라의 List와 ListBuffer 설계는 자바가 String과 StringBuffer에서 하고 있는
   * 일과 아주 비슷하다. 두 경우 모두 설계자는 순수한 변경 불가능한 데이터 구조를 유지하길 원했고,
   * 그러는 동시에 그런 데이터 구조를 점진적으로 구축할 수 있는 효율적인 방법을 제공하고 싶어했다.
   * 
   * 보통 ::는 분할 정복 스타일의 재귀적 알고리즘에 잘 맞아 떨어진다. 리스트 버퍼는 좀 더
   * 전통적인 루프 스타일에서 많이 사용한다.
   */
}