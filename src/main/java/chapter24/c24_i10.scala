package chapter24

/**
 * 24.10 변경 가능한 구체적인 컬렉션 클래스
 * 
 * 지금부터는 변경 가능한 클래스를 살펴볼 것이다.
 * 
 * 1. 배열 버퍼
 * 
 * 17.1에서 이미 살펴봤다. 배열 버퍼는 배열과 크기를 저장한다. 대부분의 연산은 배열과 속도가 같다.
 * 각 연산은 단지 내부의 배열을 호출하거나 변경하기 때문이다. 추가로 배열 끝에 데이터를 효율적으로
 * 추가할 수 있다. 배열의 크기를 늘리는 경우는 자주 일어나지 않으므로, 결국 추가하는 데 상수 분할
 * 상환 시간이 걸린다. (amortized time)
 */
object c24_i10 extends App {
  val buf = collection.mutable.ArrayBuffer.empty[Int]
  buf += 1
  buf += 10 // buf.type = ArrayBuffer(1, 10), buf.type은 buf가 참조하는 객체의 타입을 정확히 가리키는 변수를 의미한다.
  buf.toArray // Array[Int] = Array(1, 10)
  
  /*
   * 2. 리스트 버퍼
   * 
   * 리스트 버퍼는 배열 버퍼와 비슷하지만, 내부에서 배열 대신 연결 리스트를 사용한다는 점이 다르다.
   * 버퍼를 다 구성한 다음에 리스트로 변환할 예정이라면 배열 버퍼 대신에 리스트 버퍼를 사용하라.
   */
  val buf2 = collection.mutable.ListBuffer.empty[Int]
  buf2 += 1
  buf2 += 10 // buf.type = ListBuffer(1, 10)
  buf2.toList // List[Int] = List(1, 10)
  
  /*
   * 3. 문자열 빌더
   * 
   * 배열 버퍼가 배열을 만들 때 유용하고, 리스트 버퍼가 리스트를 만들 때 유용한 것처럼, 문자열 빌더는
   * 문자열을 만들 때 유용하다.
   */
  val buf3 = new StringBuilder
  buf3 += 'a'
  buf3 ++= "bcdef"
  println(buf3.toString())
  
  /*
   * 4. 연결 리스트
   * 
   * 
   */
}