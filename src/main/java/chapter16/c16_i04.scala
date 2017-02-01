package chapter16

/**
 * 16.4 리스트 기본 연산
 * 
 * 리스트의 모든 연산은 다음 세가지를 가지고 표현할 수 있다.
 *  - head는 리스트의 첫번째 원소 반환
 *  - tail은 어떤 리스트의 첫번째 원소를 제외한 나머지 원소로 이뤄짐
 *  - isEmpty는 리스트가 비어 있다면 true를 반환
 * 이러한 연산은 모두 List 클래스의 메소드다.
 */
object c16_i04 extends App {
  // 빈 리스트에 head, tail 적용하면 익셉션
  // Nil.head // head of empty list
  /*
   * 수의 리스트를 오름차순으로 정렬하는 방법 중 간단한건 삽입 정렬(insertion sort)이다.
   */
  var cnt = 0
  def isort(xs: List[Int]): List[Int] = {
    cnt+=1
    println("isort",cnt,xs)
    if (xs.isEmpty) Nil
    else insert(cnt,xs.head, isort(xs.tail))
  }
  def insert(cnt: Int, x: Int, xs: List[Int]): List[Int] = {
    println(cnt,x,xs)
    if (xs.isEmpty || x <= xs.head) {print(" < " + x + " >< "+xs+" >");x :: xs}
    else {print(" << " + xs.head+" >><< "+x+" >>");xs.head :: insert(cnt,x, xs.tail)}
  }
  val list = List(5,3,4,2,7,8,2,9,1)
  println("=>"+isort(list))
}