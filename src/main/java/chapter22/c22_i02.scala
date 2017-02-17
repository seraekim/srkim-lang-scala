package chapter22

import scala.collection.mutable.ListBuffer

/**
 * 22.2 ListBuffer 클래스
 * 
 * 리스트에 대한 전형적인 접근 패턴은 재귀적이다.
 */
object c22_i02 extends App {
  /*
   * 리스트의 모든 원소를 map을 사용해 증가시키려면 다음과 같이 코딩할 수 있다.
   * 
   * 이 패턴의 한 가지 단점은 꼬리 재귀가 아니라는 점이다. 이 incAll의 재귀 호출은 ::내에 있다.
   * 따라서 각 재귀 호출마다 새 스택 프레임이 필요하다.
   * 
   * 이는 최근의 가상 머신에서 30,000에서 50,000개보다 더 많은 원소를 가진 리스트에 incAll을
   * 호출할 수 없다는 뜻이다. 이건 슬픈 일이다.
   * 
   * 어떤 크기의 리스트라도(힙 여유 공간이 허락하는 한) 상관없이 잘 처리할 수 있는 incAll을 만들려면
   * 어떻게 해야 할까?
   */
  def incAll(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: xsl => x + 1 :: incAll(xsl)
  }
  
  /*
   * 한 가지 방법은 루프를 쓰는 것이다.
   * 그러나 매우 비효율 적인데 ::: 는 앞절에서 보았듯이 첫 인자의 길이에 비례하는  시간이 걸리기 때문이다.
   * 따라서 전체 구성 연산은 리스트 길이의 제곱에 비례한 시간이 든다. 
   */
  // 10만에서 27588 ms
  var past = java.lang.System.currentTimeMillis();
  var result = List[Int]()
  for (x <- 0 until 100000) result = result ::: List(x + 1)
  println(result.sum)
  println(java.lang.System.currentTimeMillis() - past)
  
  /*
   * 더 나은 방법으로는 리스트버퍼를 쓰는 것이다.
   * 
   * 새로운 객체 생성 비용이 없어서 그런지 1000만대에서 가장 빠른 걸로 테스트 됨.
   * 100만 이하에서는 reverse와 비슷.
   * List.map()은 head/tail 쪼개면서 함수값을 적용시키는데, 앞의 것과 비교할때,
   * 약 2배 느림..
   * 
   * 연속으로 한번에 실행할 경우, 뒤의 연산들은 보다 빨리 되기에, 테스트 할 것이라면 하나씩 따로 주석처리하여
   * 실행해 볼 것
   */
  // 500만에서 3856 ms
  past = java.lang.System.currentTimeMillis();
  val buf = new ListBuffer[Int]
  for (x <- 0 until 5000000) buf += x + 1
  println(buf.toList.sum)
  println(java.lang.System.currentTimeMillis() - past)
  
  // 500만에서 7086 ms , 리버스 포함 안하는 경우 4148 ms
  past = java.lang.System.currentTimeMillis();
  result = List[Int]()
  for (x <- 0 until 5000000) result = (x + 1) :: result
  println(result.reverse.sum)
  println(result.sum)
  println(java.lang.System.currentTimeMillis() - past)
  
  // 500만에서 7514 ms
  past = java.lang.System.currentTimeMillis();
  result = List.range(0, 5000000)
  println(result.map(_ + 1).sum)
  println(java.lang.System.currentTimeMillis() - past)
  
}