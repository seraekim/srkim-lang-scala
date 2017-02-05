package chapter18

/**
 * 18장 상태가 있는 객체
 * 
 * 함수적인(변경 불가능한) 객체 말고, 변경 가능한 상태가 있는 객체가 무엇인지 설명하고,
 * 그런 객체를 표현하도록 스칼라가 제공하는 문법 요소를 설명한다.
 * 
 * 이번 장의 뒷 부분에서 더 큰 이산 이벤트 시뮬레이션(discrete event simulation)
 * 사례를 소개한다. 그 시뮬레이션에는 상태 변경 가능한 객체뿐 아니라, 디지털 회로를
 * 정의하기 위한 도메인 특화 언어DSL, domain specific language도 들어있다.
 * 
 * 18.1 무엇이 객체에 변경 가능한 상태를 부여하는가?
 * 
 * val,var로 100% 순수 함수형인지 아닌지 판단할 수는 없다.
 * 예를들어 연산이 오래 걸리는 함수형 클래스가 있다면, 속도 향상을 위해 캐시 클래스도 만들 수 있다.
 * 그런데 캐시의 기능을 위해 캐시 변수는 var로 설정할 수도 있다. 그래도 캐시클래스도 함수형이다.
 */
object c18_i01 extends App {
  /*
   * 객체의 구현을 직접 보지 않더라도 순수 함수형 객체와, 상태가 있는 객체 사이에 중요한 차이를
   * 관찰할 수 있다.
   * 
   * cs.head는 항상 'a'를 반환한다.
   */
  val ce = List('a', 'b', 'c')
  
  val account = new BankAccount
  account deposit 100
  account withdraw 80
  account withdraw 40
}
/*
 * 은행 계좌는 상태가 있는 객체의 좋은 예다.
 * 
 * 비공개 변수 bal, 공개 메소드 3개 정의
 */
class BankAccount {
  private var bal: Int = 0 // 현재 계좌 잔고
  def balance: Int = bal
  def deposit(amount: Int) { // 계좌에 입금
    require(amount > 0)
    bal += amount
  }
  // 출금
  def withdraw(amount: Int): Boolean = {
    if (amount > bal) false
    else {
      bal -= amount
      true
    }
  }
}