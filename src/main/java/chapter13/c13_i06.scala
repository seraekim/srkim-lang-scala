//package chapter13

/**
 * 13.6 패키지 객체
 * 
 * 모든 패키지는 패키지 객체를 가질 수 있다. 스칼라는 패키지 객체 내부에 있는 모든 정의를
 * 패키지 자체에 속한 멤버로 취급한다.
 * 
 * 패키지 내에서 사용할 타입 별명(type alias)과 암시적 변환(implicit conversion)
 * 을 넣기 위해 패키지 객체를 쓰는 경우가 많다(20, 21장).
 * 
 * 패키지 객체는 package.class라는 이름의 클래스 파일로 컴파일 된다.
 * 따라서 관례상 스칼라 파일도 package.scala로 저장한다.
 * 
 * 13.7 결론
 * 
 * 29장은 패키지로 나누는 것보다 더 유연한 모듈 시스템을 설명한다. 코드를 여러 네임 스페이스로 나누는 것과 달리,
 * 그 접근법은 모듈을 파라미터화할 수 있고, 모듈 간의 상속이 가능하다는 장점이 있다.
 */
package object chapter13 {
  def showFruit(fruit: Fruit) {
    import fruit._
    println(name +"s are "+ color)
  }
}
