package chapter21

/**
 * 21.5 암시적 파라미터
 * 
 * 컴파일러가 암시적 요소를 추가하는 다른 위치로는 인자 목록을 들 수 있다.
 * 컴파일러는 때때로 someCall(a) 호출을 someCall(a)(b)로 바꾸거나, new SomeClass(a)를
 * new SomeClass(a)(b)로 바꿔서 함수 호출을 완선하는 데 필요한, 빠진 파라미터 목록을 채워 넣어준다.
 * 만일 세 가지 파라미터를 받아야 한다면 someCall(a)(b,c,d)로 변경한다.
 */
/*
 * 사용자가 선호하는 셸 프롬프트 문자열 $, > 등을 나타내는 클래스
 */
class PreferredPrompt(val preference: String)

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt) {
    println("Welcome, "+ name +". The system is ready.")
    println(prompt.preference)
  }
}

object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master > ")
}
object c21_i05 extends App {
  // 명시적으로 지정
  val bobsPrompt = new PreferredPrompt("relax> ")
  Greeter.greet("Bob")(bobsPrompt)
  
  // 빠진 파라미터 목록 채워넣기
  // Greeter.greet("Joe") // could not find implicit value for parameter prompt: chapter21.PreferredPrompt
  import JoesPrefs._
  Greeter.greet("Joe")
}