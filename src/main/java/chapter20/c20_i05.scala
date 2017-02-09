package chapter20

/**
 * 20.5 추상 val 초기화
 * 
 * 추상 val은 때때로 슈퍼클래스 파라미터와 같은 역할을 한다. 빠진 자세한 부분을
 * 서브클래스에 전달할 수 있는 수단인 것이다. 트레이트의 경우 특히나 중요한데
 * 트레이트에는 파라미터를 넘길 생성자가 없기 때문이다. 따라서 보통 트레이트를
 * 파라미터화하려면 서브클래스에서 구현하는 추상 val을 통하기 마련이다.
 */
trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)
  val numer = numerArg / g
  val denom = denomArg / g
  private def gcd(a: Int, b: Int): Int = if(b == 0) a else gcd(b, a % b)
  override def toString = numer + "/" + denom
}

object c20_i05 extends App {
  val x = 2
  /*
   * 트레이트를 혼합한 익명 클래스의 인스턴스를 만든다.
   * 이 익명 클래스 인스턴스화 표현식은 new Rational(1, 2)에 상응하는
   * 효과를 발휘한다. 하지만 완전한 대응이라 할 수 없고 차이가 존재한다.
   * 
   * new Rational(expr1, expr2) 클래스 초기화전에 두 식을 계산한다.
   * 
   * 그러나 트레이트의 경우 정반대다. 초기화 하는 도중에 표현식 expr1, expr2를 계산한다.
   * 읽는 경우 Int 타입의 디폴트 값인 0을 돌려받는다.
   * 
   * requirement failed 예외가 발생하는데 이는, 초기화 순서가 클래스 파라미터나
   * 추상 필드와 같지 않다는 사실을 보여준다.
   * 
   * 서브클래스에서  val 정의의 구현은 슈퍼클래스를 초기화한 다음에만 이루어진다.
   * 
   * 이를 해결하기 위한 방법으로는 2가지가 있다.
   *  - 필드를 미리 초기화
   *  - 지연 val 
   */
  /*
  new RationalTrait {
    val numerArg = 1 * x
    val denomArg = 2 * x
  }
  */
  /*
   * 1. 필드를 미리 초기화하기
   * 
   * 필드 정의를 중괄호에 넣고, 슈퍼클래스 생성자 호출 앞에 위치시킨다.
   * 초기화와 슈퍼트레이트 사이는 with로 구분한다.
   * 
   */
  new {
    val numerArg = 1 * x
    val denomArg = 2 * x
  } with RationalTrait
  /*
   * 초기화 시 생성중인 객체를 언급할 수는 없다.
   * this는 생성 중인 클래스나 객체를 포함하는 바깥쪽 객체를 의미하기 때문.
   * 
   * 이런 관점에서 볼 때, 미리 초기화한 필드는 클래스 생성자의 인자와 비슷하게 동작한다.
   */
  new {
    val numerArg = 1
    //val denomArg = this.numerArg * 2
    val denomArg = numerArg * 2
  } with RationalTrait
  /*
   * 2. 지연 계산 val 변수
   * 
   * 때로 시스템이 스스로 모든 것을 어떻게 초기화할지 결정하게 두는 편이 더 나는 경우가 있다.
   * 프로그램에서 처음 val의 값을 사용할 때 초기화 표현식을 계산하는 것이다.
   * 
   * Demo와 달리 Demoe2를 참조해도 초기화가 일어나지 않는다. 직접 val x를 참조할 때에만 초기화가 된다.
   */
  Demo
  println("Demo.x")
  Demo.x
  Demo2
  println("Demo2.x")
  Demo2.x
  
  /*
   * 1. LazyRationalTrait 새 인스턴스 생성. 아무 필드도 초기화 되지 않음.
   * 2. 읙명 서브 클래스의  주 생성자를 실행하여, numerArg=2, denomArg=4로 초기화
   */
  val a = new LazyRationalTrait {
    val numerArg = 1 * x
    val denomArg = 2 * x
  }
  /*
   * 1. toString을 호출하면서, 최초로 numer에 접근 -> numer의 초기화 표현식을 계산
   * 2. numer의 초기화 계산식은 비공개 필드인 g에 접근하면서 numerArg(=2), denomArg(=4)에 접근
   * 3. toString -> denom -> g는 이미 접근되었으므로 생략
   * 4. 1/2 출력
   * lazy 를 쓰게되면 코드상의 순서는 중요하지 않다. 결국 호출되는 순서에 의해서 초기화되는 순서가 결정된다.
   * 
   * val의 초기화에 부수효과가 없거나, 다른 부수효과에 의존하지 않는 경우, 순서가 문제가 되지 않는 한
   * 함수형 객체를 아주 이상적으로 보완해주는 요소다. 하지만 명령형 코드의 경우 지연 값은 잘 어울리지 않는다.
   * 
   * 기존 지연 함수형 프로그래밍 언어(lazy functional programming language)로
   * 하스켈(Haskell)이 가장 잘 알려져 있다.
   */
  println(a)
}

object Demo {
  val x = { println("initializing x"); "done" }
}
object Demo2 {
  lazy val x = { println("initializing x"); "done" }
}
/*
 * 필드를 초기화 한ㄴ 일은 익명 클래스에서만 가능한건 아니다. 객체나 이름이 있는 서브클래스에서도
 * 필드를 미리 초기화할 수 있다.
 * 
 * 미리 정의하는 부분은 extends / with 사이에 오는 것을 볼 수 있다.
 * 
 */
object twoThirds extends {
  val numerArg = 2
  val denomArg = 3
} with RationalTrait
class RationalClass(n: Int, d: Int) extends {
  val numerArg = n
  val denomArg = d
} with RationalTrait {
  def + (that: RationalClass) = new RationalClass(
    numer * that.denom + that.numer * denom,
    denom * that.denom
  )
}

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  lazy val numer = numerArg / g
  lazy val denom = denomArg / g
  override def toString = numer +"/"+ denom
  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}