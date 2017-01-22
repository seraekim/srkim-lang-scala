package chapter09

/**
 * 9.2 클라이언트 코드 단순하게 만들기
 * 
 * 고차 함수의 또 다른 중요한 용도는 API에 고차 함수를 포함시켜 클라이언트 코드를 더 간결하게 한다는 것.
 * 스칼라 컬렉션 타입의 특별 루프 메소드는 그 좋은 예로, Traversable 트레이트에 정의된다.
 */
object c09_i02 extends App {
  // 음수가 포함되어 있는지 결정
  def containsNeg(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num < 0)
        exists = true
    exists
  }
  containsNeg(List(1,2,-3,4))
  /*
   * 다음과 같이 고차 함수인 exists를 호출한다면 좀 더 간결하게 정의할 수 있다.
   * 흐름 제어 추상화를 보여준다.
   */
  def containsNeg2(nums: List[Int]) = nums.exists(_ < 0)
  
  
  def containsOdd(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num % 2 == 1)
        exists = true
    exists
  }
  def containsOdd2(nums: List[Int]) = nums.exists(_ % 2 == 1)
  
}