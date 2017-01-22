package chapter08

/**
 * 8.7 클로저
 *
 * 주어진 함수 ㅣㄹ터럴로부터 실행 시점에 만들어낸 객체인 함수 값(객체)을
 * 클로저(closure)라고 함.
 *
 * 리터럴의 본문에 있는 모든 자유 변수에 대한 바인딩을 포획하여  자유변수가 없게 닫는
 * 행위에서 따온 말이다.
 *
 * (x: Int) => x + 1 은
 * 자유변수가 없는 함수 리터럴을 닫힌 코드 조각(closed term)이라 하며, 위의 경우는 엄밀히 말해 클로저가 아니다.
 * (이미 자유변수가 없기 때문)
 *
 * (x: Int) => x + more 은 열린 코드 조각(open term) 이다.
 * 자유변수인 more 변수의 바인딩을 포획해야 한다.
 *
 * 즉 함수 값은 열린 코드 조각을 닫는 행위의 최종 결과물이다.
 */
object c08_i07 extends App {
  /*
   * more는 함수 리터럴에서 의미를 부여한 것이 아니기 때문에
   * 자유 변수(free variable)이라 한다.
   * 
   * 반대로 x는 주어진 함수의 문맥에서만 의미가 있으므로
   * 바운드 변수(bound variable)이라 한다.
   */
  var more = 1
  val addMore = (x: Int) => x + more
  println(addMore(10))

  /*
   * more 를 클로저가 생긴후 바꾸면 어떻게 될까?
   * 클로저는 그 변화를 감지한다.
   * 직관적으로 스칼라의 클로저는 변수가 참조하는 값이 아닌 변수 자체를 포획한다.
   */
  more = 9999
  println(addMore(10))

  /*
   * 위의 경우는 클로저 밖에서의 변화를 감지하는 것이고,
   * 클로저 안에서 포획한 변수를 변경하면 클로저 밖에서도 볼 수 있다.
   */
  val someNumbers = List(-11, -10, 0, 5, 10)
  var sum = 0
  someNumbers.foreach(sum += _) // 클로저 내부
  println(sum) // 클로저 외부
  someNumbers.foreach((x: Int) => sum = sum + x)
  println(sum)
  
  /*
   * 증가시키는 클로저를 만들어서 반환하는 함수
   * 이 함수를 호출할 때마다 새로운 클로저가 생긴다. 각 클로저는 생성 시점(인스턴스화)에
   * 활성화되어 있던 more 변수에 접근한다.
   * 
   * 포획한 인자가 스택이 아닌 힙에 있다.
   * 함수 호출 시 인자와 지역변수는 스택에 쌓이고, 함수 실행이 끝나면 다 제거 된다.
   * 클로저(inc1)에서 포획한 more가 스택에 있는 more를 가리키게 한다면
   * makeIncreaser 실행이 끝난다음 클로저를 호출하면 스택안에 올바른 정보가 남아있을지 보장 못한다.
   * 따라서, 컴파일러가 이를 힙으로 재배치하는 것이다. val, var, 파라미터 무엇이든 포획해도 좋다.
   * 
   */
  def makeIncreaser(more: Int) = (x: Int) => x + more
  val inc1 = makeIncreaser(1)
  println(inc1(10))
  val inc9999 = makeIncreaser(9999)
  println(inc9999(10))
}