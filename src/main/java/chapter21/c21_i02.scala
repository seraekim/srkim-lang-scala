package chapter21

/**
 * 21.2 암시 규칙
 * 
 * 1. 표시규칙: implicit로 표시한 정의만 검토 대상이다
 * 
 * implicit 키워드는 암시 처리시 사용할 선언을 표시한다.
 * 변수, 함수, 객체 정의에 implicit  표시를 달 수 있다.
 * 
 * 2. 스코프 규칙: 삽입할 implicit 변환은 스코프 내에 단일 식별자로만 존재하거나, 변환의 결과나
 * 원래 타입과 연관이 있어야 한다.
 * 
 * some.convert(x) + y 형태로 확장하지 않는다. convert로 해야 한다.
 * 
 * 단일 식별자 규칙에는 한 가지 예외가 있다. 컴파일러는 원 타입이나 변환 결과 타입의 동반 객체에 있는
 * 암시적 정의도 살펴본다. Dollar가 원타입이고, Euro가 결과타입이라면 클래스(Dollar나 Euro)의
 * 동반 객체 안에 Dollar에서 Euro로 변환하는 암시적 변환을 넣어둘 수 있다.
 * 
 * object Dollar {
 *   implicit def dollarToEuro(x: Dollar): Euro = ...
 * }
 * class Dollar { ... }
 * 
 * 이 경우, dollarToEuro가 Dollar 타입과 연관이 있다고 말한다. 컴파일러는 Dollar 타입의 인스턴스를
 * 다른 타입으로 변환할 필요가 있을 때마다 연관이 있는 변환을 찾는다.
 * 
 * 3. 한 번에 하나만 규칙: 오직 하나의 암시적 선언만 사용한다
 * 
 * 컴파일러는 암시적 선언을 시도하는 중에 추가로 암시적 선언을 적용하려 하지 않는다. 그러나 암시적 선언 안에서
 * 암시적 파라미터를 사용해 이런 제약을 우회할 수 있다.
 * 
 * 4. 명시성 우선 규칙: 코드가 그 상태 그대로 타입 검사를 통과한다면 암시를 통한 변환을 시도하지 않는다
 * 
 * 코드가 너무 간결해서 모호성이 큰 경우에는 명시적으로, 코드가 반복이 잦고 장황하다면 암시적 변환을..
 * 
 * 5. 암시적 변환 이름 붙이기
 * 
 * 암시적 변환에는 아무 이름이나 붙일 수 있다. 이름이 문제가 되는 경우는 두 가지뿐이다.
 *  - 메소드 호출 시 명시적으로 변환을 사용하고 싶은 경우
 *  - 프로그램의 특정 지점에서 사용 가능한 암시적 변환이 어떤 것이 있는지 파악해야 하는 경우
 * 
 * 암시적 변환을 여러개 가진 객체가 있다면, import 를 통해 원하는 암시적 변환만 가져올 수 있다.
 * 
 * 6. 암시가 쓰이는 부분
 * 
 * String 을 IndexedSeq[Char] 타입이 필요한 다른 메소드에 넘길 때
 * 
 */
object c21_i02 {
  
}