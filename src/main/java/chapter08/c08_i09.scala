package chapter08

/**
 * 8.9 꼬리 재귀(tail recursive)
 * 
 * 마지막에 자기 자신을 호출
 * 스칼라 컴파일러가 꼬리 재귀라면 최적화를 하여,
 * 새로운 값과 함께 함수의 첫 부분으로 돌아가기 때문에 성능 걱정 안 해도 된다. (while과 비슷한 성능)
 */
object c08_i09 extends App {
  
  /*계산한 어림값이 충분히 좋아질 때까지 추정을 반복하는 재귀 함수
  def approximate(guess: Double): Double =
    if(isGoodEnough(guess)) guess
    else approximate(improve(guess))*/
  
  /*
   * 1. 꼬리 재귀 함수 추적
   * 
   * 꼬리 재귀 함수는 재귀 호출마다 새로운 스택을 만들지 않고, 같은 스택 프레임을 재활용한다.
   * 
   * 프로그램이 실패해 스택 추적 로그를 살펴보면 놀랄지도 모른다.
   */
  // 함수 boom은 마지막에 더하기 연산을 추가로 하므로 꼬리 재귀가 아니다.
  def boom(x: Int): Int =
    if (x==0) throw new Exception("boom!")
    else boom(x - 1) + 1
  //boom(3)
  /*
   * 3번 반복하고 4번째에서 익셉션 발생, 스택에 쌓인 것을 볼 수 있다.
  java.lang.Exception: boom!
	at chapter08.c08_i09$.boom(c08_i09.scala:26)
	at chapter08.c08_i09$.boom(c08_i09.scala:27)
	at chapter08.c08_i09$.boom(c08_i09.scala:27)
	at chapter08.c08_i09$.boom(c08_i09.scala:27)
	*/
  
  def bang(x: Int): Int =
    if (x==0) throw new Exception("bang!")
    else bang(x - 1)
  bang(3)
  /*
   * 익셉션이 발생한 단 하나의 스택만 존재한다.
  java.lang.Exception: bang!
	at chapter08.c08_i09$.bang(c08_i09.scala:39)
	*/
  
  // 꼬리 재귀 최적화를 원하지 않는다면, 스칼라 셸이나 스칼라 컴파일러에
  // -g:notailcalls 옵션을 주면 된다. 그럼 스택이 여러개 찍힌다.
  
  /*
   * 2. 꼬리 재귀의 한계
   * 
   * JVM 명령어 집합만으로 고수준의 꼬리 재귀를 구현하기에는 어려움이 있기에 한계가 있음.
   * 동일한 함수를 직접 재귀 호출하는 경우에만 최적화를 수행한다.
   */
  // 아래와 같이 재귀가 간접적으로 일어나는 경우엔 최적화가 불가능
  def isEven(x: Int): Boolean =
    if (x == 0) true else isOdd(x - 1)
  def isOdd(x: Int): Boolean =
    if (x == 0) false else isEven(x - 1)
  
  // 마지막 호출이 함수 값 (val funValue)인 경우에도 최적화는 일어나지 않는다.
  // 꼬리 재귀 최적화는 자신을 직접 호출하는 경우만 가능하지, 함수 값 등의 중간 경로가 있어서는 안 된다.
  val funValue = nestedFun _
  def nestedFun(x: Int) {
    if (x != 0) { println(x); funValue(x - 1) }
  }
  
}