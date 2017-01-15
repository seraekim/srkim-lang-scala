package chapter04
/*
 * java.lang, scala패키지멤버를 암시적으로 임포트
 * scala 패키지의 predef라는 싱글톤 객체의 멤버도 항상 임포트
 * predef.println -> Console.println
 * predef.assert
 */
// 스칼라에서는 static import를 하는 것처럼 보이겠지만 다른점 하나는
// 어느 객체에서라도 멤버자체를 임포트 할 수 있다
import c04_i03.calculate

/**
 * 4.4 스칼라 애플리케이션
 * 어떤 독립객체든 간에 main 메소드만 있으면 앱의 시작점 역할을 한다.
 */
object c04_i04 {
  /*
   * ChecksumAccumulator.scala <- ChecksumAccumulator 클래스와 ChecksumAccumulator 동반객체
   * 진입 main메소드를 가진 이것은 Summer.scala
   * 결과 표현식으로 끝나지 않으므로 스칼라 인터프리터는 두 파일을 스크립트로 인식 안함
   * 따라서 스칼라 컴파일러로 자바 클래스파일을 만들어야 한다.
   * 
   * $ scalac ChecksumAccumulator.scala Summer.scala //속도느림
   * $ fsc ChecksumAccumulator.scala Summer.scala
   * fsc 실행하면 특정 포트가 열리며 데몬이 실행된다. 실행중이니까 즉각적으로 컴파일이 가능해진다.
   * fsc -shutdown 명령을 보내어 중지가 가능
   * $ scala Summer of love
   */
  def main(args: Array[String]): Unit = {
    for (arg <- args)
      println(arg +": "+ calculate(arg))
  }
}