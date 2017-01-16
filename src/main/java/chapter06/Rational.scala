package chapter06

/**
 * 6.2 Rational 생성
 * 
 * 변경 불가 객체의 장단점 비교
 * 
 * 장점
 *   - 1. 시간에 따라 변하는 상태 공간을 가지지 않기에 추론이 쉽다.
 *   - 2. 전달을 비교적 자유롭게 할 수 있다.
 *   - 3. 두 스레드가 동시에 접근하더라도, 상태를 망쳐놓는 일이 없다.
 *   - 4. 안전한 해시 테이블 키다.
 *   
 * 단점
 *   - 1. 그자리에서 바로 상태를 변경하면 간단한 일조차 거대한 객체 그래프를 복사하는 일이 된다.
 *      (객체 그래프란 특정 시점에서 메모리에서 객체들 사이의 참조 관계를 말함)
 * 
 * 따라서 변경가능/불가능 둘다 제공하는데 String, StringBuffer가 그러하다.
 * 
 * 자바에서는 생성자를 내부에 따로 만들어야하지만
 * 스칼라 클래스는 바로 인자를 받는 주 생성자를 표시할 수 있다.
 * 
 * 6.10 스칼라의 식별자
 * 
 * 1. 영숫자 식별자(alphanumeric identifier)
 *   - 문자나 _로 시작, 두번째 부터 문자,숫자,밑줄 가능
 *   - 특수문자 $도 가능하긴 하지만, 컴파일러가 내부적으로 생성하는 식별자에 사용하는 예약문자니 사용자제.
 *   - camel-case를 선호하며 자바와 같다.
 *   - 밑줄 _ 은 식별자외의 용도로도 쓰이니 이것도 되도록이면 피하는게 좋다.
 *   - val name_: int에서는 name_: 이 하나의 식별자로 해석되니 :는 띄워써야 한다.
 *   - 완전상수(val 보다 강력)인 경우조차 자바처럼 A_B_C 표기하지 않고 첫글자만 대문자로 하는게 관례다(Abc)
 *     
 * 2. 연산자 식별자(operator identifier)
 *   - 하나 이상의 연산자 문자로 이뤄짐
 *   - + : ? ~ # 등의 출력가능한 아스키 문자
 *   - 스칼라 컴파일러는 내부적으로 $을 사용해 연산자 식별자를 해체(mangle)하여 적합한 자바 식별자로
 *     다시 만든다. :-> 은 내부적으로 $colon$minus$greater로 바뀐다.
 *   - ex) + ++ ::: <?> :->
 *   - 자바에서는 <- 는 <, -로 해석하지만, 스칼라에선 하나로 본다.
 *   
 * 3. 혼합 식별자(mixed identifier)
 *   - 영숫자로 이뤄진 식별자뒤에 밑줄이 오고 그 뒤에 연산자 식별자가 온다
 *   - ex) unary_+, myvar_=
 *   
 * 4. 리터럴 식별자(literal identifier)
 *   - `..` 같이 역따옴표로 둘러싼 임의의 문자열
 *   - ex) `x` `<clinit>` `yield`
 *   - yield는 스칼라의 예약어이므로, Thread.yield() 사용이 불가하다
 *   - Thread.`yield`() 처럼 지정하면 문제가 없다.
 */
class Rational(n: Int, d: Int) {
  /**
   * 6.4 전재 조건 확인
   * require(boolean) 인자 값이 참이면 그대로 진행
   * 거짓이면 IllegalArgumentException 예외가 발생해 객체의 생성 막음 
   */
  require(d != 0)
  
  /**
   * 6.6 비공개 필드와 메소드
   */
  private val g = gcd(n.abs, d.abs)
  private def gcd(a: Int, b: Int): Int = if(b==0) a else gcd(b, a % b)
  /**
   * 6.5 필드 추가
   * 10.6절에서 더욱 간단하게 코드 작성할 수 있는 파라미터 필드를 다룸.
   */
  val numer: Int = n / g
  val denom: Int = d / g
  /**
   * 6.3 toString 메소드 다시 구현하기
   */
  override def toString = "Created "+numer+"/"+denom 
  def add(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  /**
   * 6.9 연산자 정의
   */
  def +(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  def -(that: Rational): Rational = new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  def *(that: Rational): Rational = new Rational(numer * that.numer, denom * that.denom)
  def /(that: Rational): Rational = new Rational(numer * that.denom, denom * that.numer)
  /**
   * 6.11 메소드 오버로드
   * r * new Rational(2)는 가능한데  r * 2도 가능하게 해보자 
   */
  def +(i: Int): Rational = new Rational(numer + i * denom, denom)
  def -(i: Int): Rational = new Rational(numer - i * denom, denom)
  def *(i: Int): Rational = new Rational(numer * i, denom)
  def /(i: Int): Rational = new Rational(numer, denom * i)
  
  /**
   * 6.6 자기 참조
   * 필드의 this 를 빼도 된다.
   */
  def lessThan(that: Rational) = this.numer * that.denom < that.numer * this.numer
  def max(that: Rational) = if(lessThan(that)) that else this
  /**
   * 6.7 보조 생성자
   * 보조 생성자 호출을 통해서 거슬러 올라가면 결국 주 생성자를 호출하게 만드는 효과가 있다.
   * 따라서 주 생성자는 클래스의 유일한 진입점이다.
   * 
   * 주 생성자만이 슈퍼클래스의 생성자를 호출할 수 있다.
   * 자바보다 더 단순하게 만드는 대신, 유연성을 약간 포기하도록 설계한 것.
   */
  def this(n: Int) = this(n, 1) //보조생성자를 통해 주생성자 호출
}