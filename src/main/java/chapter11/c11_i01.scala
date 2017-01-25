package chapter11

/**
 * 11 스칼라의  계층구조
 * 
 * 모든 클래스의 슈퍼클래스 Any
 * 모든 클래스의 서브클래스 Null, Nothing
 * 
 * 11.1 스칼라의 클래스 계층구조
 * final def ==(that: Any): Boolean
 * final def !=(that: Any): Boolean
 * def equals(that: Any): Boolean
 * def ##: Int
 * def hashCode: Int
 * def toString: String
 * 
 * 모든 클래스가 Any를 상속하기 때문에 모든 객체는 ==, !=, equals를 사용해 비교할 수 있다. 
 * 스칼라의 ==는 근본적으로 equals와 같다.따라서 fianl 인 == !=를 오버라이드하는 대신에
 * equals를 따로 override 하면 된다. 자바에서는 Long(1), Integer(1)은 각기 다른
 * 해시 값을 반환하지만 스칼라 ##는 동일한 해시 코드를 반환한다.
 * 
 * Any의 서브클래스로 AnyVal, AnyRef가 있다.
 * 모든 값 클래스는 추상 클래스인 동시에 final로 만드는 속임수를 사용했기에 new를 사용할 수 없다.
 * 
 * Unit는 인스턴스 값이 ()로 유일하다.
 * 
 * 값 클래스 공간은 평평하다. 모든 값 클래스는 서로 상속 관계가 없다. 다만 서로 암시적 변환을 제공한다.
 * scala.Int 는 필요할 때마다 자동으로 scala.Long 클래스 인스턴스로 넓혀진다.(widening)
 * 
 * AnyRef 는 java.lang.Object에 별명을 붙인것에 지나지 않는다. 자바로 작성했든 스칼라로 했든
 * 모든 참조 클래스는 AnyRef를 상속한다. 스칼라에서는 Object, AnyRef 를 바꿔쓰는게 가능하나
 * AnyRef를 항상 쓰는 것을 권장한다. 또한 AnyRef를 만든 이유로, 닷넷에서도 사용가능하게 하려는
 * 의도 인데, 닷넷에서는 System.Object 이다.
 * 
 * 스칼라 객체는 ScalaObject라는 스칼라 객체 표시를 위한 특별한 트레이트를 상속한다.
 */
object c11_i01 extends App {
  // new Int // class Int is abstract; cannot be instantiated
  println(42.toString) // res: java.lang.String = 42
  println(42.hashCode) // res: Int
  println(42 equals 42) // res: Boolean = true
  
  /*
   * 다음 메소드는 모두 scala.runtime.RichInt에 들어있다.
   * 그리고 Int에서 RichInt로 바꾸는 암시적 변환이 존재한다.
   */
  42 max 43
  42 min 43
  1 until 5
  1 to 5
  3.abs
  (-3).abs
  
}