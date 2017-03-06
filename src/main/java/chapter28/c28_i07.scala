package chapter28

/**
 * 28.7 저장하기와 불러오기
 * 
 * XML 데이터를 바이트 스트림으로 변환하고 역으로 가져오기.
 * 이를 처리해주는 라이브러리가 있기에 가장 쉬운 부분이다.
 * 만약 직접 문자열을 바이트로 변경한다면 문자 인코딩을 추적하는 짐을 직접 짊어져야만 한다..
 * 
 * XML을 바이트가 담긴 파일로 변경하려면 XML.save 명령을 사용할 수 있다.
 */
object c28_i07 extends App {
  val therm = new CCTherm {
    val description: String = "hot dog #5"
    val yearMade: Int = 1952
    val dateObtained: String = "March 14, 2006"
    val bookPrice: Int = 2199
    val purchasePrice: Int = 500
    val condition: Int = 9
  }                                               //> therm  : chapter28.CCTherm = hot dog #5
  
  scala.xml.XML.save("src/main/java/chapter28/therm.xml", therm.toXML)
  val loadnode = xml.XML.loadFile("src/main/java/chapter28/therm.xml")
  
  println(loadnode.toString)
}