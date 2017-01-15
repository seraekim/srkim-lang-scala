package chapter04

/**
 * 4장 클래스와 객체
 * 4.1 클래스, 필드, 메소드
 */
object c04_i01 {
  def main(args: Array[String]): Unit = {
    val acc = new ChecksumAccumulator
    val csa = new ChecksumAccumulator
    //acc.sum=3 private 적용
    acc.add(100)
    println(acc.checksum())
    acc.add(100)
    println(acc.checksum())
    acc.add(100)
    println(acc.checksum())
  }
}

class ChecksumAccumulator {
  // 스칼라의 기본 접근 수준은 public 이다. 즉 아무것도 안붙이면 된다.
  private var sum = 0

  // 파라미터가 val인 이유는 분석하기 쉽기 때문이다.
  // var인 경우에는 재할당 하는 경우로 인해 추적해야만 한다.
  def add(b: Byte): Unit = {
    // b = 1 재할당 못해
    sum += b
  }
  def checksum(): Int = {
    // 명시적으로 return 안써도, 스칼라는 최종 값을 반환한다.
    // return ~(sum & 0xFF) + 1
    ~(sum & 0xFF) + 1
  }

  // 오직 하나의 표현식만 존재하면 {}를 없앨 수 있다.
  def add2(b: Byte) = sum += b
  def checksum2() = ~(sum & 0xFF) + 1

  // 등호를 없애고 {}를 쓰면 마지막에 반환값이 있더라도 Unit 으로 취급해서 반환값이 사라진다.
  def add3(b: Byte) { sum += b }
  def checksum3() { ~(sum & 0xFF) + 1 }
}