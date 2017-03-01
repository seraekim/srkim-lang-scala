package chapter24

/**
 * 24.16 이터레이터
 * 
 * 이터레이터는 컬렉션은 아니다. 하지만 컬렉션의 원소에 하나씩 접근할 수 있는 수단이다. 어떤 이터레이터
 * it에 대한 기본 연산은 next와 hasNext이다. it.next() 호출은 이터레이터의 다음 원소를 반환하고,
 * 이터레이터의 상태를 하나 앞으로 전진시킨다. 더 이상 반환할 원소가 없으면 NoSuchElementException
 * 예외를 던진다. 원소가 남아 있는지는 이터레이터의 hasNext 메소드를 호출해 알아볼 수 있다.
 * 
 * 이터레이트가 반환하는 모든 원소를 하나씩 방문하는 가장 직접적이고 쉬운 방법은 while 루프다.
 * while(it.hasNext)
 *   println(it.next())
 * 
 * foreach를 사용하면 다음과 같이 줄어든다.
 * it foreach println
 * 
 * 항상 그렇듯, foreach, map, filter, flatMap을 사용하는 표현식 대신
 * for 표현식을 쓸 수 있다. 원소를 모두 출력하는 또 다른 방법은 다음과 같다.
 * 
 * for (elem <- it) println(elem)
 * 
 */
object c24_i16 extends App {
  /*
   * 이터레이터에도 새 이터레이터를 반환하는 map이 있다.
   */
  val it = Iterator("a", "number", "of", "words") //> it  : Iterator[String] = non-empty iterator
  it.next()                                       //> res0: String = a
  val res0 = it.map(_.length) // map을 호출하고 나면 이터레이터는 맨 마지막으로 전진
                                                  //> res0  : Iterator[Int] = non-empty iterator
  res0 foreach println                            //> 6
                                                  //| 2
                                                  //| 5
  //it.next() error
  
  val it2 = Iterator("a", "number", "of", "words")//> it2  : Iterator[String] = non-empty iterator
  it2 dropWhile (_.length < 2)                    //> res1: Iterator[String] = non-empty iterator
  it2.next()                                      //> res2: String = of
  
  /*
   * 같은 이터레이터를 재사용하도록 허용하는 연산은 오직 duplicate 하나뿐이다.
   */
  val (it3, it4) = it2.duplicate                  //> it3  : Iterator[String] = non-empty iterator
                                                  //| it4  : Iterator[String] = non-empty iterator
  
  /*
   * 정리하면, 이터레이터는 그 안의 메소드를 호출한 다음 다시 재호출하지 않는 한 컬렉션과 마찬가지로 동작.
   * 이런 구분을 TraversableOnce라는 추상화를 사용해 명확히 한다.
   * Iterator와 Traversable의 슈퍼트레이트다.
   * 
   * 1. 버퍼 이터레이터
   * 
   * 때로 이터레이터의 일부를 미리 보기 할 수 있으면 좋은 경우가 있다. 예로 이터레이터에서 맨 앞의 빈 문자열이
   * 연속으로 있으면 생략하고 나머지 문자열을 반환해야 한다고 가정하자. 이 문제를 해결하는 방법은
   * BufferedIterator 트레이트의 인스턴스인 버퍼가 있는 이터레이터를 사용하는 것이다.
   * 
   * head를 사용하면 첫 원소를 반환하지만 위치는 변경하지 않는다.
   */
  def skipEmptyWords(it: BufferedIterator[String]) =
    while (it.head.isEmpty) { it.next() }
  
  // buffered 메소드를 호출해서 버퍼 이터레이터로 바꿀 수 있다.
  val bit = it2.buffered                          //> bit  : scala.collection.BufferedIterator[String] = non-empty iterator
  bit.head                                        //> res3: String = words
  bit.next()                                      //> res4: String = words
}