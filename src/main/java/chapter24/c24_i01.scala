package chapter24

/**
 * 24장 스칼라 컬렉션 API
 * 
 * 2.8버전의 가장 중요한 변화중 하나는 스칼라 컬렉션 프레임워크가 생긴 것이다.
 * 
 * 처음에는 컬렉션에 추가 된 클래스나 트레이트 자체가 더 중요해 보일 것이다. 하지만 컬렉션이 여러분의
 * 프로그래밍 스타일에 끼칠 변화가 더 심오하고 클 수 있다. 개별 원소가 아닌 컬렉션 자체를
 * 프로그래밍 기본 블록으로 취급하는 것처럼 더 높은 수준에서 프로그래밍하는 것이다.
 * 
 *  - 사용적이기 쉬움: 20~50개의 메소드만 알면 된다. 머리를 싸매고 복잡한 루프 구조나 재귀
 *  호출을 고민할 필요가 없다.
 *  
 *  - 간결함: 루프가 하나 이상 필요할 때 한 단어로 가능하며, 맞춤으로 만든 듯한 표현식같아 보인다.
 *  
 *  - 안전함: 이 성질은 경험해봐야만 충분히 알 수 있는 것이다.
 *   - (1) 컬렉션 연ㅇ산 자체가 널리 쓰이며, 잘 검증되어 있다.
 *   - (2) 컬렉션 연산을 사용하려면 입력과 출력을 함수 파라미터와 결과 값으로 명시해야만 한다.
 *   - (3) 이런 명시적 입출력이 정적인 타입 검사를 통과해야만 한다.
 *  
 *  - 빠름: 튜닝이 최적하 됨. 일반적인 경우 쓰게되면 매우 효율적. 스칼라 2.9부터 병렬 컬렉션이 들어간다.
 *  이 책은 2.8버전 기준으로 쓰여졌기에, 관심있다면 따로 사용법을 찾아봐야 할 것이다.
 *  
 *  - 보편적임: 어떤 연산을 제공하는 것이 타당하다면, 각 컬렉션은 같은 연산을 제공한다. 따라서
 *  상당히 작은 개수의 연산을 사용해 달성할 수 있는 일이 많다. 예를 들어 문자열은 문자의 시퀀스다. 따라서
 *  스칼라 컬렉션에서는 문자열도 모든 시퀀스 연산을 제공한다. 배열도 마찬가지다.
 *  
 *  25장은 새로운 사용자 정의 컬렉션 타입을 구현할 독자들을 위한 것이다.
 *  
 *  24.1 변경 가능, 변경 불가능 컬렉션
 *  
 *  변경 가능 컬렉션은 메모리상에서(in-place) 변경하거나 확장할 수 있는 컬렉션이다.
 *  즉 부수 효과를 사용해 컬렉션의 원소를 변경, 추가, 삭제할 수 있다는 뜻이다.
 *  
 *  변경 불가능한 컬렉션의 경우 결코 변하지 않는다. 추가, 삭제, 변경을 본뜬 연산이 있긴 하지만,
 *  그런 연산은 새 컬렉션을 반환하며, 원ㄹ 컬렉션은 변하지 않고 그대로 남는다.
 *  
 *  모든 컬렉션 클래스는 scala.collection 패키지나 그 하위 패키지인 mutable, 
 *  immutable, generic 패키지 안에 있다.
 *  
 *  collection.generic 패키지에는 컬렉션을 만드는 구성요소가 들어있다. 보통 컬렉션
 *  클래스들은 자신이 제공하는 연산에 대한 구현을 generic에 있는 클래스에 위임한다. 하지만 컬렉션을
 *  사용하는 최종 사용자가 generic에 있는 클래스를 언급하는 경우는 아주 예외적인 상황이 아니면 드문
 *  일이다.
 */
object c24_i01 {
  
}