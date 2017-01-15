package chapter04

import scala.collection.mutable.Map
/**
 * 4.3 싱글톤 객체
 *
 * 1장에서 언급했듯이, 스칼라가 자바보다 더 객체지향적인 이유 중 하나는 static 멤버가 없다는 것
 * 대신에 스칼라는 싱글톤 객체를 제공
 * 싱글톤 객체 정의는 클래스 정의와 같아 보이지만 class 대신 object라는 키워드로 시작한다.
 * 
 * 싱글톤 객체의 이름이 어떤 클래스와 같을 때, 그 객체를 클래스의 동반객체라 함. (단 같은 소스 파일에 정의)
 * 이 때 역으로 해당 클래스를 싱글톤 객체의 동반클래스라 하며, 상대방의 비공개 멤버에 접근할 수 있다.
 */
object c04_i03 {
  def main(args: Array[String]): Unit = {
    //println(calculate("ABC"))
    println(calculate("1가C"))
    println(calculate("ㄱㄴㄷ"))
    
    // c04_i03 을 ChecksumAccumulator 싱글톤 객체라 생각하자.
    // 자바의 정적메소드 쓰듯이 호출이 가능하다.
    // 싱글톤 객체는 정적 메소드를 보관하는 곳 이상이다. 1급 계층이므로, 객체의 이름을 이름표처럼 생각할 수 있다.
    // 싱글톤 객체만으로는 타입을 만들 수 없으며, 동반 클래스가 필요하다.
    // 동반 클래스가 없는 싱글톤 객체 - 독립객체
    // 싱글톤은 슈퍼클래스를 확장, 트레이트 믹스인할 수 있다.
    // 싱글톤 객체는 new로 인스턴스화할 수 없기에 파라미터를 못받는다.
    // 컴파일러는 싱글톤 객체의 이름뒤에 기호$를 붙여서 클래스를 만들어낸다.
    println(c04_i03.calculate("ABC"))
    println(c04_i03.calculate("1가C"))
    println(c04_i03.calculate("ㄱㄴㄷ"))
  }
  // 이런 식의 캐시 사용은 메모리를 약간 희생해서 계산시간을 버는 것
  // WeakHashMap 을 사용하면 가비지가 메모리 부족시 수거해가기 좋다.
  private val cache = Map[String, Int]()
  
  def calculate(s: String): Int = {
    if (cache.contains(s))
      cache(s)
    else {
      // new 는 클래스를 인스턴스화 할 때만 사용하기에, 여기서 만든 객체는 싱글톤 객체의 인스턴스가 아니다
      val acc = new ChecksumAccumulator
      for (c <- s) {
        println(c.toByte)
        acc.add(c.toByte)
      }
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }

  }
}