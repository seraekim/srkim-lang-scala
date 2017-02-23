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
                                                  //> buf  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
  buf += 1                                        //> res0: chapter24.c24_i1s.buf.type = ArrayBuffer(1)
  // buf.type = ArrayBuffer(1, 10), buf.type은 buf가 참조하는 객체의 타입을 정확히 가리키는 변수를 의미한다.
  buf += 10                                       //> res1: chapter24.c24_i1s.buf.type = ArrayBuffer(1, 10)
  buf.toArray // Array[Int] = Array(1, 10)        //> res2: Array[Int] = Array(1, 10)
  
  /*
   * 2. 리스트 버퍼
   * 
   * 리스트 버퍼는 배열 버퍼와 비슷하지만, 내부에서 배열 대신 연결 리스트를 사용한다는 점이 다르다.
   * 버퍼를 다 구성한 다음에 리스트로 변환할 예정이라면 배열 버퍼 대신에 리스트 버퍼를 사용하라.
   */
  val buf2 = collection.mutable.ListBuffer.empty[Int]
                                                  //> buf2  : scala.collection.mutable.ListBuffer[Int] = ListBuffer()
  buf2 += 1                                       //> res3: chapter24.c24_i1s.buf2.type = ListBuffer(1)
  buf2 += 10 // buf.type = ListBuffer(1, 10)      //> res4: chapter24.c24_i1s.buf2.type = ListBuffer(1, 10)
  buf2.toList // List[Int] = List(1, 10)          //> res5: List[Int] = List(1, 10)
  
  /*
   * 3. 문자열 빌더
   * 
   * 배열 버퍼가 배열을 만들 때 유용하고, 리스트 버퍼가 리스트를 만들 때 유용한 것처럼, 문자열 빌더는
   * 문자열을 만들 때 유용하다.
   */
  val buf3 = new StringBuilder                    //> buf3  : StringBuilder = 
  buf3 += 'a'                                     //> res6: chapter24.c24_i1s.buf3.type = a
  buf3 ++= "bcdef"                                //> res7: chapter24.c24_i1s.buf3.type = abcdef
  buf3.toString()                                 //> res8: String = abcdef
  
  /*
   * 4. 연결 리스트
   * 
   * 연결 리스트는 다음 노드를 가리키는 next 포이터로 서로 연결해둔 노드들로 이뤄진 변경 가능 시퀀스다.
   * 대부분의 언어에서 빈 연결 리스트를 표현하기 위해 null을 사용한다. 하지만 스칼라 컬렉션에서는
   * 그렇게 할 수 없다. 빈 시퀀스일지라도 시퀀스의 모든 메소드를 지원해야 하기 때문이다.
   * next 포인터가 자기 자신을 가리키는 특별한 방법으로 표시한다.
   * 
   * 5. 이중 연결 리스트
   * DoubleLinkedList는 바로 앞에서 설명한 단일 ㅇ녀결 리스트와 비슷하지만, next외에 현재
   * 노드의 바로 앞 노드를 가리키는 변경 가능한 필드 prev를 제공한다. 링크를 추가해서 생기는 주된
   * 이점은 원소 삭제가 아주 빠르다는 것이다.
   * 
   * 6. 변경 가능한 리스트
   * 
   * MutableList는 단일 연결 리스트에 리스트의 맨 마지막 빈 노드를 가리키는 포인터가 더 들어있다.
   * 그로 인해 리스트 뒤에 덧붙이는 연산을 상수 시간에 할 수 있다. 마지막 노드를 찾기 위해 전체 노드를
   * 다 방문할 필요가 없기 때문이다. MutableList는 mutable.LinearSeq의
   * 표준 구현이다.
   * 
   * 7. 큐
   * 
   * 스칼라는 변경 불가능한 큐 외에 변경 가능한 큐도 제공한다. 변경 가능한 큐를 변경 불가능한 큐와 비슷하게
   * 사용할 수 있다.하지만 enqueue 대신 +=sk ++= 연산을 사용해 큐에 원소를 덧붙인다.
   * 또한 변경 가능 큐에서 dequeue 메소드는 큐의 원소를 제거하고, 그 원소를 반환한다.
   */
  val queue = new scala.collection.mutable.Queue[String]
                                                  //> queue  : scala.collection.mutable.Queue[String] = Queue()
  queue += "a"                                    //> res9: chapter24.c24_i1s.queue.type = Queue(a)
  queue ++= List("b", "c")                        //> res10: chapter24.c24_i1s.queue.type = Queue(a, b, c)
  queue.dequeue                                   //> res11: String = a
  queue                                           //> res12: scala.collection.mutable.Queue[String] = Queue(b, c)
  
  /*
   * 8. 배열 시퀀스
   * 
   * 배열 시퀀스는 원소를 내부에서 Array[AnyRef]로 저장하는 고정된 크기의 변경 가능한 시퀀스다.
   * 스칼라에서는 ArraySeq가 이를 구현한다. 배열과 같은 특성이 필요한데, 원소의 타입도 모르고
   * 실행 시점에 ClassManifest도 없는 상황에서 시퀀스의 제네릭한 인스턴스를 만들 필요가 있다면
   * 보통 ArraySeq를 사용한다.
   * 
   * 9. 스택
   * 
   * 변경 가능 스택은 그 자리에서 변경한다는 점을 제외하면 변경 불가능한 버전과 똑같이 동작한다.
   */
  val stack = new scala.collection.mutable.Stack[Int]
                                                  //> stack  : scala.collection.mutable.Stack[Int] = Stack()
  stack.push(1)                                   //> res13: chapter24.c24_i1s.stack.type = Stack(1)
  stack.push(2)                                   //> res14: chapter24.c24_i1s.stack.type = Stack(2, 1)
  stack.top                                       //> res15: Int = 2
  stack.pop                                       //> res16: Int = 2
  stack                                           //> res17: scala.collection.mutable.Stack[Int] = Stack(1)
  
  /*
   * 10. 배열 스택
   * 
   * ArrayStack은 변경 가능 스택에 대한 대체 구현이다. 필요에 따라 크기를 변화시키는 배열을 기반으로 한다.
   * 배열 스택은 인덱스를 사용한 빠른 접근을 제공하며, 일반적인 스택보다 대부분의 연산에서 보통 아주 약간 더
   * 효율 적이다.
   * 
   * 11. 해시 테이블
   * 
   * 해시 테이블은 원소를 배열에 저장한다. 이 때 각 원소의 해시 코드에 따라 그 위치를 결정한다. 해시 테이블에 있는
   * 배열에 이미 같은 해시 코드를 갖는 원소가 없는 한, 해시 테이블에 원소를 추가하는 데는 상수시간만 걸린다. 따라서
   * 원소들의 해시 코드 분포가 고르기만 하면, 해시 테이블은 아주 빠르다. 따라서 스칼라의 변경 가능 맵과 변경 가능
   * 집합 타입의 기본 구현은 해시 테이블을 기반으로 한다.
   */
  val map = collection.mutable.HashMap.empty[Int, String]
                                                  //> map  : scala.collection.mutable.HashMap[Int,String] = Map()
  map += (1 -> "make a web site")                 //> res18: chapter24.c24_i1s.map.type = Map(1 -> make a web site)
  map += (3 -> "profit!")                         //> res19: chapter24.c24_i1s.map.type = Map(1 -> make a web site, 3 -> profit!)
                                                  //| 
  map(1)                                          //> res20: String = make a web site
  map contains 2                                  //> res21: Boolean = false
  
  /*
   * 해시 테이블에 대한 이터레이션은 어떤 정해진 순서를 따르지 않는다. 이터레이션은 단순히 내부 배열을 있는 그대로
   * 방문할 뿐이다. 이터레이션 순서를 보장해야 한다면 일반 해시 맵이나 집합 대신 연결 해시 맵이나 집합을 사용해야 한다.
   * 
   * 12. 약한 해시맵
   * 
   * 약한 해시 맵은 쓰레기 수집기가 맵 내부에 저장한 키를 따라 방문하지 않는 특별한 종류의 해시 맵이다. 약한 해시맵은
   * 비용이 많이 드는 함수의 결과를 저장해뒀다가 같은 인자로 호출 시 저장했던 결과를 바로 사용하는 캐시 같은 작업에서
   * 유용하다. 키나 함수 결과를 일반 해시 맵에 저장한다면 맵이 계속 자라고, 키는 결코 재활용 대상이 될 수 없다.
   * 약한 해시 맵을 사용하면 이런 문제를 피할 수 있다. java.util.WeakHashMap을 감싸는 클래스다.
   * 
   * 13. 동시적 맵
   * 
   * 동시적 맵은 여러 스레드에서 동시에 접근 가능하다. 일반적인 맵 연산과 더불어 다음과 같은 원자적 연산
   * atomic operation을 제공한다.
   * 
   * m putIfAbsent(k, v), k가 없다면 추가
   * m remove (k, v), k, v 매핑된 경우에만 제거
   * m replace (k, old, new), k가 old와 매핑된 경우에만 new로 변경
   * m replace (k, v), old가 무엇인지는 관계없이 변경
   * 
   * ConcurrentMap 트레이트의 구현체는 java.util.concurrent.ConcurrentMap이다.
   * 
   * 14. 변경 가능한 비트 집합
   * 
   * 변경 가능한 비트 집합은 제자리에서 변경이 가능하다는 사실을 제외하면 변경 불가능한 집합과 같다. 변경하는 일은
   * 좀더 효율적이다. 바뀌지 않은 Long을 복사할 필요가 없기 때문이다.
   */
  val bits = scala.collection.mutable.BitSet.empty//> bits  : scala.collection.mutable.BitSet = BitSet()
  bits += 1                                       //> res22: chapter24.c24_i1s.bits.type = BitSet(1)
  bits += 3                                       //> res23: chapter24.c24_i1s.bits.type = BitSet(1, 3)
  bits                                            //> res24: scala.collection.mutable.BitSet = BitSet(1, 3)
}