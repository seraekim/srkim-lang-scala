package chapter24

/**
 * 24.17 컬렉션 밑바닥부터 만들기
 * 
 * 어떤 컬렉션이든 이름 다음에 괄호 안에 원소를 나열할 수 있다.
 */
object c24_i17 extends App {
  Traversable()                                   //> res0: Traversable[Nothing] = List()
  List()                                          //> res1: List[Nothing] = List()
  List(1.0, 2.0)                                  //> res2: List[Double] = List(1.0, 2.0)
  Vector(1.0, 2.0)                                //> res3: scala.collection.immutable.Vector[Double] = Vector(1.0, 2.0)
  Iterator(1,2,3)                                 //> res4: Iterator[Int] = non-empty iterator
  Set(1,1,2)                                      //> res5: scala.collection.immutable.Set[Int] = Set(1, 2)
  scala.collection.immutable.HashSet(1,1,2)       //> res6: scala.collection.immutable.HashSet[Int] = Set(1, 2)
  Map('a' -> 7, 'b' -> 0)                         //> res7: scala.collection.immutable.Map[Char,Int] = Map(a -> 7, b -> 0)
  /*
   * 내부적으로 위의 각 줄은 어떤 객체의 apply 메소드를 호출한다.
   * List.apply(1.0, 2.0)
   * 이것은 List 클래스의 동반 객체의 apply 메소드를 호출하느 ㄴ것이다.
   * 
   * 컬렉션
   * 구체적 클래스 : List, Stream, Vector
   * 트레이트 : Seq, Set, Traversable
   * 트레이트 경우 apply 호출하면 디폴트 구현을 만듬.
   * 
   * apply 외에 모든 컬렉션 동반 객체에는 empty도 들어 있다. 이 메소드는 빈 컬렉션을 반환한다.
   * 
   * Seq 트레이트의 자손도 역시 동반 객체를 통해 팩토리 연산을 제공한다.
   * 
   * concat : 임의 개수의 순회 가능 객체를 서로 붙여줌
   * fill, tabulate : 주어진 표현식이나 채워넣기 함수에 따라 1차원, 다차원 시퀀스 만듬
   * range : 정해진 증가 값으로 정수 시퀀스 만듬
   * iterate : 함수를 시작 원소에 반복 적용한 결과의 시퀀스를 만듬
   */
  
}