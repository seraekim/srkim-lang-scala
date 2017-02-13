package chapter20

/*
 * 20.10 사례 연구: 통화 변환
 * 
 * 20장의 나머지 부분에서는 추상 타입을 스칼라에서 어떻게 사용하는지 보여주는 사례 연구를 진행한다.
 */
/*abstract class Currency {
  val amount: Long
  def designation: String
  override def toString = amount +" "+ designation
  def + (that: Currency): Currency
  def * (x: Double): Currency
}*/
abstract class AbstractCurrency {
  type Currency <: AbstractCurrency
  
  val amount: Long
  def designation: String
  override def toString = amount +" "+ designation
  /*
   * class type required 에러 발생.
   * 
   * 스칼라가 추상 타입을 다루는 경우에 대한 제약사항 중 하나는 추상 타입의 인스턴스를 만들 수도 없고,
   * 추상 타입을 다른 클래스의 슈퍼타입으로 만들 수도 없다는 것이다.
   * 따라서 Currency를 인스턴스화하는 위 예제 코드를 거부한다.
   * 
   * 이를 해결하는 방법은 추상 타입의 인스턴스를 직접 만드는 대신, 추상 타입을 만들어내는
   * 추상 메소드를 만들 수 있다.
   */
  //def + (that: Currency): Currency = new Currency {}
  def * (x: Double): Currency
}
abstract class CurrencyZone {
  /*
   * 추상타입 및 팩토리 메소드의 중복으로 인한 코드냄새가 존재하므로 밖으로 꺼낸다.
   */
  type Currency <: AbstractCurrency
  val CurrencyUnit:Currency
  def make(x: Long): Currency
  
  abstract class AbstractCurrency {
    val amount: Long
    def designation: String
    
    override def toString = ((amount.toDouble / CurrencyUnit.amount.toDouble)
        formatted ("%."+ decimals(CurrencyUnit.amount) + "f") +" "+ designation)
    private def decimals(n: Long): Int = if (n < 10) 0 else 1 + decimals(n / 10)
    
    def + (that: Currency): Currency = make(this.amount + that.amount)
    def * (x: Double): Currency = make((this.amount * x).toLong)
    def / (that: Double) = make((this.amount / that).toLong)
    def / (that: Currency) = this.amount.toDouble / that.amount
    
    def from(other: CurrencyZone#AbstractCurrency): Currency =
      make(math.round(
        other.amount.toDouble * Converter.exchangeRate
          (other.designation)(this.designation)))
  }
}
/*
 * CurrencyZone을 구체화 함. US는 CurrencyZone을 확장.
 * 이 지역에 있는 통화의 타입은 US.Dollar이다.
 */
object US extends CurrencyZone {
  /*
   * 타입 Dollar는 US라는 통화 지역의 일반적인 Currency 이름을 표현.
   */
  abstract class Dollar extends AbstractCurrency {
    def designation = "USD"
  }
  type Currency = Dollar
  def make(cents: Long) = new Dollar { val amount = cents }
  
  val Cent = make(1)
  // 값 Dollar는 1달러 표현
  val Dollar = make(100)
  // US 지역의 표준 통화 단위를 Dollar 값으로 지정
  val CurrencyUnit = Dollar
}

object Europe extends CurrencyZone {
  abstract class Euro extends AbstractCurrency {
    def designation = "EUR"
  }
  type Currency = Euro
  def make(cents: Long) = new Euro { val amount = cents }
  
  val Cent = make(1)
  val Euro = make(100)
  val CurrencyUnit = Euro
}

object Japan extends CurrencyZone {
  abstract class Yen extends AbstractCurrency {
    def designation = "JPY"
  }
  type Currency = Yen
  def make(yen: Long) = new Yen { val amount = yen }
  
  val Yen = make(1)
  val CurrencyUnit = Yen
}

object Converter {
  var exchangeRate = Map(
    "USD" -> Map("USD" -> 1.0, "EUR" -> 0.7596, "JPY" -> 1.211),
    "EUR" -> Map("USD" -> 1.316, "EUR" -> 1.0, "JPY" -> 1.594),
    "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272, "JPY" -> 1.0)
  )
}
/*
 * 타입 추상화를 사용해 서로 단위(통화 클래스의 종류)가 다른 두 값을 더하지 못하게 방지할 수 있었다.
 * 1999년 9월 23일 Mars Climate Orbiter 화성 기후 궤도 탐사선이 소실되었었는데, 엔지니어링 팀 중
 * 하나는 미터법, 다른팀은 영국 단위를 쓴게 원인이었다.
 * 
 * 20.11 결론
 * 메소드, val, var, 타입 추상화를 통해 시스템을 효과적으로 구조화하고, 원칙을 제공한다.
 * 클래스 설계 시 아직 알 수 없는 모든 대상을 추상 멤버로 정의하라는 원칙이다.
 */
object c20_i10 extends App {
  val a:Japan.Currency = Japan.Yen from US.Dollar * 100 
  val b = Europe.Euro from a
  val c = US.Dollar from b
  
  println(a)
  println(b)
  println(c)
}