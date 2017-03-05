package chapter28

/**
 * 28.4 직렬화
 */
// 이 클래스는 온도계의 생산연도, 수집년도, 가격 등의 다양한 정보를 저장하는 간단한 클래스다.
abstract class CCTherm {
  val description: String
  val yearMade: Int
  val dateObtained: String
  val bookPrice: Int
  val purchasePrice: Int
  val condition: Int
  
  override def toString = description
  
  // 이 클래스의 인스턴스를 XML로 바꾸려면, XML 리터럴과 중괄호 이스케이프를 사용하는 toXML 메소드를 추가
  def toXML =
    <cctherm>
      <description>{description}</description>
      <yearMade>{yearMade}</yearMade>
      <dateObtained>{dateObtained}</dateObtained>
      <bookPrice>{bookPrice}</bookPrice>
      <purchasePrice>{purchasePrice}</purchasePrice>
      <condition>{condition}</condition>
    </cctherm>
}
object c28_i04 {
  val therm = new CCTherm {
    val description: String = "hot dog #5"
    val yearMade: Int = 1952
    val dateObtained: String = "March 14, 2006"
    val bookPrice: Int = 2199
    val purchasePrice: Int = 500
    val condition: Int = 9
  }
  therm.toXML
  /*
   * new CCTherm은 추상클래스여도 여전히 잘 작동하는데, 이 문법은 실제로
   * CCTherm의 익명 서브클래스를 인스턴스화하기 때문이다. 익명클래스는 20.5절에서 다뤘다.
   * 
   * 중괄호 자체를 스칼라 이스케이프가 아니고 XML 텍스트로 추가하고 싶다면, 두 중괄호를 연속으로 쓰기만 하면 된다.
   */
  <a>{{}}</a>
}