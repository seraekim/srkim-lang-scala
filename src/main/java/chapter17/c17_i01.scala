package chapter17

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer

/**
 * 17장 컬렉션
 * 
 * 17.1 시퀀스
 * 
 * 시퀀스타입은 순서가 정해진 데이터 그룹에 대해 작업할 수 있게 해준다.
 */
object c17_i01 extends App {
  /*
   * 1. 리스트
   * 
   * List는 앞부분에 빠르게 원소를 삭제하거나 추가할 수 있다. 그러나 리스트를 순차적으로 따라가야만 하므로
   * 임의의 위치에 접근할 때는 느리다. 별로일 수도 있지만..이런 특징은 여러 알고리즘에서 잘 동작하는
   * 최적의 조합이기도 하다.빠르게 추가하고 삭제할 수 있다는 것은 15장의 패턴 매치를 잘 할 수 있다는 뜻이다.
   * 
   * 리스트의 불변성은 리스트를 복사하지 않아도 되기 때문에 효율적이면서 올바른 알고리즘 개발에 도움이 된다.
   * 
   * 3장 - 소개
   * 16장 - 상세한 사용법
   * 22장 - 스칼라에서 리스트 구현하기
   */
  val colors = List("r", "g", "b")
  println(colors.head)
  println(colors.tail)
  
  /*
   * 2. 배열
   * 
   * 배열(array)은 원소의 시퀀스를 저장하며 임의의 위치에 있는 원소를 효율적으로 접근하게 해준다.
   * 
   * 스칼라 배열은 자바 배열과 같은 방법으로 표현한다.
   * 
   * 3.7, 7.3, 10장 참고
   */
  // 크기는 알지만 구체적인 원소를 모를 때 생성
  val fiveInts = new Array[Int](5)
  // 모든 원소 값을 알 때 초기화
  val fiveToOne = Array(5,4,3,2,1)
  fiveInts(0) = fiveToOne(4)
  
  /*
   * 3. 리스트 버퍼
   * 
   * 앞쪽에 대해서는 빠른 접근을 제공. 끝에 굳이 추가해야 한다면, 뒤집힌 리스트를 만들고 작업을 마치고
   * 다시 reverse 호출해야 함.
   * 
   * reverse 호출을 피하려면 ListBuffer를 사용. 추가하는데 상수시간이 걸리며
   * += 연산자로 뒤에 추가
   * +=: 연산자로 앞에 추가.
   * 연산을 모두 끝낸 뒤 toList 호출해서 리스트를 얻을 수 있다.
   */
  val buf = new ListBuffer[Int]
  buf += 1
  buf += 2
  3 +=: buf
  println(buf.toList)
  
  /*
   * 4. 배열 버퍼
   * 
   * 평균적으로 상수시간이 걸리나, 새로운 배열을 할당해야하는 경우 선형시간
   */
  val buf2 = new ArrayBuffer[Int]()
  buf2 += 12
  buf2 += 15
  1 +=: buf2
  println(buf2)
  
  /*
   * 5. 문자열(StringOps를 통합)
   * 
   * Predef에 String을 StringOps로 바꾸는 암시적 변환이 있기 때문에 시퀀스처럼 문자열을 다룬다.
   * 
   * String 클래스 자체에는 exists라는 메소드가 없기에 컴파일러는 StringOps로 변환한다.
   * 문자열을 문자 시퀀스로 처리한다.
   */
  def hasUpperCase(s: String) = s.exists(_.isUpper)
  println(hasUpperCase("e e cF"))
}