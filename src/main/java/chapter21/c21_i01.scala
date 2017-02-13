package chapter21

import javax.swing.JButton
import java.awt.event.ActionListener
import java.awt.event.ActionEvent

/**
 * 21장 암시적 변환과 암시적 파라미터
 * 
 * 21.1 암시적 변환
 * 
 * 서로를 고려하지 않고 독립적으로 개발한 두 덩어리의 소프트웨어를 한데 묵을 때 종종 유용하다.
 * 
 * 자바의 장황한 코드 Swing을 생각해보자. 버튼 클릭이벤트 하나 만드는데만 해도, 의미없는
 * 얼개코드들이 여러줄  들어간다. Actionlistener, actionPerformed메소드 등
 * 이것들은 당연히 존재해야할 것들이고, 정말 새로운 정보는 실행할 코드 부분, 즉 클릭 이벤트에
 * 해당하는 println() 등을 호출하는 부분과 같다. 이 스윙 코드를 읽기 위해선 정말
 * 매의 눈을 가져야만 잡음을 뚫고 정보가 있는 부분을 찾아낼 수 있다.
 * 
 * 더 스칼라에 친화적인 버전이라면 함수를 인자로 받을 것이다. 이를 통해 얼개 코드의 양을
 * 극적으로 줄일 수 있다.
 * 
 * button.addActionListener(
 *   (_: ActionEvent) => println("pressed!")
 * )
 * 
 * addActionListener가 원하는 것은 액션 리스너이지 함수가 아니다.
 * 그러나 암시적 변환을 적용하면 이 코드를 작동하게 만들 수 있다.
 */

object c21_i01 {
  
  implicit def function2ActionListener(f: ActionEvent => Unit) = {
    new ActionListener {
      def actionPerformed(event: ActionEvent) = f(event)
    }
  }
  
  val button = new JButton
  button.addActionListener(
    function2ActionListener { _: ActionEvent => println("pressed!") }
  )
  // implicit로 표시해뒀으므로, 더 간단한 코드도 가능하다.
  button.addActionListener(
    (_: ActionEvent) => println("pressed!") 
  )
  /*
   * 위 코드가 작동하는 방식은 다음과 같다.
   * 
   * 컴파일러는 코드를 그대로 컴파일 해보고, 타입 오류가 발생한다. 포기하기 전에 암시적 변환을 통해 문제를
   * 해결할 수 있는지 살펴본다. function2ActionListener 메소드가 존재하므로, 시도해보고
   * 작동하면 다음 단계를 진행한다.
   */
}