package chapter23

/**
 * 23.4 for 표현식 변환
 * 
 * 모든 for 표현식은 map, flatMap, withFilter의 세 가지 고차 고차 함수로 표현 가능하다.
 * 이번 절에서는 스칼라 컴파일러가 사용하는 변환 방법을 설명한다.
 * 
 * 1. 제너레이터가 하나밖에 없는 for 표현식의 변환
 * for (x <- expr1) yield expr2 는 다음과 같이 변환된다.
 * expr1.map(x => expr2)
 * 
 * 2. 제너레이터로 시작하고 필터가 하나 있는 for 표현식의 변환
 * for (x <- expr1 if expr2) yield expr3
 * for (x <- expr1 withFilter (x => expr2)) yield expr3
 * expr1 withFilter (x => expr2) map (x => expr3)
 * 
 * seq가 임의의 제너레이터, 정릐, 필터로 된 시퀀스라고 할 때...
 * for (x <- expr1 if expr2; seq) yield expr3
 * for (x <- expr1 withFilter expr2; seq) yield expr3
 * 
 * 3. 제너레이터 2개로 시작하는 for 표현식의 변환
 * for (x <- expr1; y <- expr2; seq) yield expr3
 * expr1.flatMap(x => for (y <- expr2; seq) yield expr3)
 */
object c23_i04 extends App {
  /*
   * 23.3절에서 본 최소한 두 권 이상의 책을 쓴 작가를 모두 찾는 질의를 살펴보자.
   * map/flatMap/filter 조합으로 바꿀 수 있다.
   */
  val books = new BookList().books;
  books flatMap (b1 =>
    books withFilter (b2 => b1 != b2) flatMap (b2 =>
      b1.authors flatMap (a1 =>
        b2.authors withFilter (a2 => a1 == a2) map ( a2 =>
          a1))))
  
  /*
   * 4. 제너레이터에 있는 패턴의 변환
   * 
   * 튜플에 바인딩하는 제너레이터의 경우라면..
   * for ((x1, ..., xn) <- expr1) yield expr2
   * expr1.map { case (x1, ..., xn) => yield expr2 }
   * 
   * 튜플이 ㅏㅇ닌 임의의 패턴이라면..
   * 
   * for(pat <- expr1) yield expr2
   * expr1 withFilter {
   *   case pat => true
   *   case _ => false
   * } map {
   *   case pat => expr2
   * }
   * 이는 제너레이터가 만들어내는 원소들 중에서 먼저 pat와 일치하는 것만 걸러내서 pat와 매치하는 것들만
   * map에 넘긴다는 뜻이다. 따라서 패턴 매치 제너레이터가 결코 MatchError를 발생시키지 않도록 보장할 수 있다.
   * 
   * 5. 정의 변환
   * 
   * for (x <- expr1; y = expr2; seq) yield expr3
   * for((x, y) <- for (x <- expr1) yield (x, expr2); seq) yield expr3
   * 여기서 expr2를 계산하는 시점을 보면, 새로운 x값을 제너레이터에서 가져올 때마다 재계산함을 알 수 있다.
   * expr2에서 x를 사용한다면 그 x는 각 이터레이션마다 가져온 값을 반영해야 하기 때문이다.
   * 
   * 변수 정의를 했는데 다음 이어지는 표현식에서 안가져다 쓴다면 시간낭비인 것이다..
   * 
   * 따라서 다음과 같은 경우에는 정의 변환의 경우를 제거하는게 효율적이다.
   * for (x <- 1 to 1000; y = expensiveComputationNotInvolvingX)
   * yield x * y
   * 
   * val y = expensiveComputationNotInvolvingX
   * for (x <- 1 to 1000) yield x * y
   * 
   * 6. for 루프 변환
   * 
   * 지금까지 yield가 있는 for 표현식을 변환하는 것을 봤는데, 아무 값도 돌려주지 않고 부수 효과만 사용한다면??
   * for 표현식의  변환에는 map과 flatMap을 사용했지만, for 루프에는 foreach만을 사용한다.
   * 
   * for (x <- expr1) body
   * expr1 foreach (x => body)
   * 
   * for (x <- expr1; if expr2; y <- expr3) body
   * expr1 withFilter (x => expr2) foreach (x =>
   * expr3 foreach (y => body))
   * 
   * 예를 들어 다음 표현식은 리스트의 리스트로 표현한 행렬의 모든 원소를 더한다.
   */
  val xss = List(List(1,2),List(3,4))
  var sum = 0
  xss foreach (xs =>
    xs foreach (x =>
      sum += x))
  println(sum)
}