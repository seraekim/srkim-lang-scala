package chapter14

/**
 * 14장 단언문과 단위 테스트
 * 
 * 검사하는 중요한 두 가지 방법은 단언(assertion)과 단위 테스트(unit test)
 * 
 * 14.1 단언문
 * 
 * assert(c, e: Any), e.toString 호출, 해설 문자열
 * 
 * 10장의 Element 클래스에있는 above 메소드에, 길이가 같음을 확실히 하기위해
 * assert를 넣을 수도 있다.
 * 
 * assert(this1.width == that1.width) 
 * 
 * 더 간단히 하는 방법으로는 widen 메소드에 ensuring (w <= _.width)를 끝에 추가하면 된다.
 * 
 * ensuring은 인자하나를 받는데 술어함수다. 술어는 결과 타입을 받아서 Boolean을 반환한다.
 * 밑줄은 술어가 받는 유일한 인자이다.
 * 
 * 자바 1.8, 스칼라 2.11 버전에서  JVM -da, -ea가 스칼라 단언문에는 영향을 끼치진 못하고
 * scalac 에 -Xdisable-assertions를 명령행 인자로 넘기면 단언문을 아얘 제거하고 컴파일 한다.
 */
object c14_i01 {
  
}