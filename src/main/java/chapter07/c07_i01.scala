package chapter07

/**
 * 7장 내장 제어 구문
 * 
 * if, while, for, try, match, function call
 * 
 * 스칼라에 제어구문의 수가 적은 이유는, 설계 초기부터 함수 리터럴을 포함했기 때문.
 * 자바에는 삼항 연산자가 있는데 스칼라에서는 삼항연산자와 if를 합쳐서 if로만 사용하며
 * if는 값을 결과로 내놓아야 한다.
 * 
 * 스칼라는 이러한 식으로 for, try, match에도 적용했다. 모두 값을 내놓는다.
 * 
 * 프로그래머는 이덕에 함수의 반환값 이용하듯 코드를 더 간단히 할 수 있다.
 * 
 * 7.1 표현식
 */
object c07_i01 extends App{
  /*
   * 명령형 스타일
   */
  var filename = "default.txt"
  if(!(args isEmpty))
    filename = args(0)
  /*
   * 좀더 근사한 함수형 스타일
   * val 이니까 이게 어디에 어떻게 영향을 주고 받는지 모든 스코프를 살펴볼 필요도 없고 깔끔.
   */
  val filename2 = if(!args.isEmpty) args(0) else "default.txt"
  /*
   * val의 강점 두번째는 동일성 추론(equational reasoning) 이다.
   * 즉, 항상 변수명을 표현식으로 대체할 수 있다.
   * val 을 사용하면 코드가 진화하는 동안에도 안전하게 리팩토링 할 수 있다.
   */
  println(filename2)
  println(if(!args.isEmpty) args(0) else "default.txt")
}