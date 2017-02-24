package chapter24

/**
 * 24.15 뷰
 * 
 * 컬렉션에는 새로운 컬렉션을 만들 수 있는 메소드가 꽤 많이 들어 있다. 예를 들어보면
 * map, filter, ++ 등이 있다. 이런 메소드를 변환기(transformer)라 부른다.
 * 왜냐하면 이들은 적어도 하나 이상의 컬렉션을 호출 대상 객체와 인자로 받아서 다른 컬렉션을
 * 만들어서 결과로 반환하기 때문이다.
 * 
 * 변환기 구현은 엄격하거나 엄격하지 않은 두 가지 방식이 있다. 엄격한 변환기는 새 컬렉션이
 * 모든 원소를 다 집어넣는다. 엄격하지 않은 게으른 변환기는 결과 컬렉션에 대한 프록시(proxy)
 * 만을 만들고, 원소는 요청이 있을 때 만들어낸다.
 */
object c24_i15 extends App {
  def lazyMap[T, U](col1: Iterable[T], f: T => U) = {
    new Iterable[U] {
      def iterator = col1.iterator map f
    }
  }
  /*
   * lazyMap이 주어진 컬렉션 col1의 모든 원소를 방문하지 않으면서 새 Iterable을 내놓는다는  점에
   * 유의하라. 대신에 새 컬렉션 이터레이터의 원소에 필요할 때마다 함수 f를 적용한다.
   * 
   * 스칼라 컬렉션의 변환기는 Stream의 메소드들을 제외하고는 기본적으로 모두 엄격하다.
   * 
   * 모든 컬렉션을 엄격하게 만들거나 반대로 되돌리는 체계적인 방법이 있다. 그것은 컬렉션 뷰를 바탕으로 한다.
   * 뷰는 어떤 기반 컬렉션을 표현하면서, 모든 변환기를 게으르게 구현하는 특별한 종류의 컬렉션이다.
   * 
   * 컬렉션에서 뷰를 만들려면 그 컬렉션에 대해 view 메소드를 호출하면 된다. 되돌아오고 싶다면 force
   * 메소드를 호출하면 된다.
   */
  val v = Vector(1 to 10: _*)                     //> v  : scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4, 5, 6, 7, 8, 
                                                  //| 9, 10)
  v map (_ + 1) map (_ * 2)                       //> res0: scala.collection.immutable.Vector[Int] = Vector(4, 6, 8, 10, 12, 14, 16
                                                  //| , 18, 20, 22)
  /*
   * map을 두번이나 호출하는데, 중간 과정을 없애려면 어떻게 해야할까?
   */
  (v.view map (_ + 1) map (_ * 2)).force          //> res0: Seq[Int] = Vector(4, 6, 8, 10, 12, 14, 16, 18, 20, 22)

  val vv = v.view                                 //> vv  : scala.collection.SeqView[Int,scala.collection.immutable.Vector[Int]] =
                                                  //|  SeqView(...)
  /*
   * v.view 호출은 SeqView, 즉 필요에 따라 계싼하는 Seq를 반환한다. SeqView 타입에는
   * 타입 파라미터가 둘 있다. 첫 번째 파라미터 Int는 뷰의 원소타입을 의미하고,
   * 두 번째인 Vect[Int]는 뷰를 강제로 엄격한 컬렉션으로 만들 때 사용할 타입 생성자를 보여준다.
   */
  val vv1 = vv map (_ + 1)                        //> vv1  : scala.collection.SeqView[Int,Seq[_]] = SeqViewM(...)
  /*
   * map의 결과는 SeqViewM을 출력하는 값이다. 근본적으로 벡터 v에 함수 (_ + 1)을 가지고
   * map을 적용해야 한다는 사실을 감싸는 객체다. 다만, 그 map을 적용하라고 요청받기 전까지는
   * 실제로 적용하지는 않는다. SeqView 뒤의 M은 뷰에 map 연산을 넣어뒀음을 표시한다.
   * 예를 들어 S는 지연 slice, R은 지연 reverse이다.
   */
  val vv2 = vv1 map (_ * 2)                       //> vv2  : scala.collection.SeqView[Int,Seq[_]] = SeqViewMM(...)
  /*
   * 이제 두 맵 연산을 감싼 SeqView를 얻었다. SeqViewMM 으로 M 2개가 표시 된다.
   */
  vv2.force                                       //> res1: Seq[Int] = Vector(4, 6, 8, 10, 12, 14, 16, 18, 20, 22)
  /*
   * force 연산을 실행하면서 저장했던 두 함수를 모두 적용하고, 새 벡터를 만든다. 이런 방식을 사용하기 때문에
   * 중간 데이터 구조가 필요 없다.
   * 
   * 한가지 지적할 사항은 최종 결과의 정적 타입이 Vector가 아니라 Seq라는 것이다. SeqViewM[Int, Seq[_]]는
   * 뷰가 Vector라는 구체적인 타입에 적용됐던 것이라는 지식을 잃어버렸다는 뜻이다. 모든 구체적인 클래스마다 뷰를 구현하면
   * 코드의 양이 꽤 많기 때문에, 스칼라 컬렉션 라이브러리는 보통 개별 구현이 아니라 일반적인 컬렉션 타입에 대한 뷰만을
   * 제공한다. 이에 대한 예외는 배열이다. 배열에 지연 연산을 적용할 때 나오는 결과의 정적인 타입은 Array다.
   * 
   * 뷰 사용을 고려해야 할 이유가 두 가지 있다.
   * 
   *  - 1. 회문을 찾는 문제
   */
  def isPalindrome(x: String) = x == x.reverse
  def findPalindrome(s: Seq[String]) = s find isPalindrome
  
  val words = Vector("1")
  findPalindrome(words take 1000000)
  /*
   * 이 코드는 시퀀스에서 첫 100만 단어를 가져오는 것과 회문을 그 안에서 찾는 두 측면을 잘 분리했지만
   * 단점은 회문이 시퀀스에서 첫 단어일지라도 그와 관계없이 항상 100만 단어짜리 시퀀스를 만든다는 것이다.
   * 따라서 나중에 전혀 살펴볼 필요가 없는데도 999,999 단어를 중간 결과에 복사하는 경우가 있을 수 있다.
   * 많은 프로그래머는 여기서 포기하고, 인자로 받은 시퀀스의 맨 앞부터 회문을 찾는 함수를 직접 작성할 것이다.
   * 
   * 하지만 뷰를 사용한다면 그럴 필요가 없다.
   * 뷰를 사용하면 성능과 모듈화 중 하나만을 택할 필요가 없다.
   */
  findPalindrome(words.view take 1000000)
  
  /*
   *  - 2. 변경 가능한 시퀀스에 대해 일부만을 선택적으로 변경할 수 있는 창을 제공한다.
   */
  val arr = (0 to 9).toArray                      //> arr  : Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

  val subarr = arr.view.slice(3, 6)               //> subarr  : scala.collection.mutable.IndexedSeqView[Int,Array[Int]] = SeqViewS
                                                  //| (...)

  /*
   * 이렇게 하면 배열 arr에서 인덱스가 3부터 5까지인 원소만을 참조하는 뷰 subarr을 얻을 수 있다.
   * 이 뷰는 원소를 복사하지 않고, 각 원소에 대한 참조만을 갖는다. 이제 시퀀스의 일부 원소를
   * 변경하는 메소드가 있다고 하자.
   */
  def negate(xs: collection.mutable.Seq[Int]) =
    for (i <- 0 until xs.length) xs(i) = -xs(i)   //> negate: (xs: scala.collection.mutable.Seq[Int])Unit

  negate(subarr)
  arr                                             //> res0: Array[Int] = Array(0, 1, 2, -3, -4, -5, 6, 7, 8, 9)
  /*
   * 여기서도 뷰가 모든 것을 모듈화하는 데 도움이 됨을 볼 수 있다. 어던 메소드를 적용할지와 어던 인덱스 범위의
   * 원소를 바꿀지에 대한 질문을 멋지게 분리했다. 이제 엄격한 컬렉션이 왜 필요한지 반대로 궁금할 것이다.
   * 성능 비교 시 지연 컬렉션이 항상 엄격한 컬렉션보다 더 나은 건 아니라는 사실이 한가지 이유다. 크기가 작은
   * 컬렉션의 경우 뷰에 있는 클로저를 만들고 호출하는 부가 비용이 때로 중간 구조를 생략해 얻는 이익보다 더 클 수 있다.
   * 
   * 두 번째 이유로는 지연 연산에 부수 효과가 있는 경우 뷰를 사용한 계산은 혼동을 일으킬 수 있다는 점이다.
   * 
   * 스칼라 2.8 이전에 많은 문제를 일으킨 Range 타입은 지연 연산이었다.
   * val actors = for (i <- 1 to 10) yield actor { }
   * val actors = (1 to 10) map (i => actor { })
   * 
   * 앞에서 (1 to 10)으로 만든 범위는 뷰와 마찬가지로 동자갛기 때문에 맵의 결과도 여전히 다시 뷰다.
   * 즉 아무 원소도 계산하지 않았고, 그에 따라 아무 액터도 생기지 않았다.
   * 
   * 뷰는 강력한 도구지만 지연 계산의 여러 양상에 복잡하게 얽히지 않기 위하여 두 가지 상황에서만 쓰자.
   * 
   *  - 1. 부수 효과가 없는 컬렉션 변환을 사용하는 완전히 함수적인 코드에서 사용
   *  - 2. 모든 변경을 명시적으로 수행하는 변경 가능 컬렉션에서 사용
   */
}