# tobi-spring

# 초난감 DAO 리뷰와 정리

```609792211d94a1cc528690321fc133c7fdec67d7``` 의 커밋까지에 대한 리뷰이다.

무엇보다 실무에서는 이러한 코드를 작성할 기회가 전혀 없어 생소했다.

그럼에도 제일 간단한 구조부터 시작해서, 어떤 문제가 생기는지 직접적으로 겪으며 개선해 나가는 경험은 색달랐다.

디자인 패턴이 몇 개 등장했지만, 여전히 잘 모르겠다. 이러한 디자인 패턴을 이용해 비즈니스 로직을 구현할 기회가 주어졌으면 좋겠다..!


## 원칙과 패턴

### 객체지향 설계 원칙(SOLID) ~~*정처기 때 지긋지긋했다..*~~

- SRP(The Single Responsibility Principle) : 단일 책임 원칙
- OCP(The Open Closed Principle) : 개방 폐쇄 원칙
- LSP(The Liskov Substitution Principle) : 리스코프 치환 원칙
- ISP(The Interface Segregation Principle) : 인터페이스 분리 원칙
- DIP(The Dependency Inversion Principle) : 의존관계 역전 원칙

### 개방 폐쇄 원칙 (OCP, Open-Closed Principle)

- 리팩토링 작업의 특징과 최종적으로 개선된 설계와 코드의 장점이 무엇인지 효과적으로 설명할 수 있다.
- 깔끔한 설계를 위해 적용 가능한 **객체지향 설계 원칙 중 하나**이다.
- 정의하면 클래스나 모듈은 확장에는 열려 있어야 하며, 변경에는 닫혀 있어야 한다.
(ex. UserDao는 DB 연결 방법이라는 기능 확장에는 열려 있다. 하지만 UserDao의 자신의 핵심 기능을 구현한 코드에는 확장에 영향을 받지 않고 유지할 수 있어 변경에는 닫혀 있다.)
- 초난감 DAO는 리팩토링의 과정을 거쳐 개방 폐쇄 원칙 설계 원칙을 지키도록 수정되었다.

### 높은 응집도와 낮은 결합도 (High coherence and Low coupling)

- 개방 폐쇄 원칙은 높은 응집도와 낮은 결합도라는 소프트웨어 개발의 고전적인 원리로도 설명이 가능하다.
- 응집도가 높은 건 하나의 모듈, 클래스가 하나의 책임 또는 관심사에만 집중 되어 있는 것
- 높은 응집도
    - 응집도가 높다는 것은 변화가 발생할 때 해당 모듈에서 변하는 부분이 크다는 것
    - 초난감 DAO는 `DriverManager` 를 이용한 방법에서 다른 라이브러리를 사용하는 방법으로 변경된다고 한다면, 해당 DAO는 여러 관심사와 책임이 얽혀 있기 때문에 변경이 필요한 부분을 찾아내기 어렵고, 오류가 발생하지 않는지 일일히 확인해야 한다.
    - `ConnectionMaker` 인터페이스를 이용한 경우에는 DB 커넥션 풀을 활용하는 ConnectionMaker 구현 클래스를 새로 만들기만 하면 된다. 또한 테스트는 ConnectionMaker 구현 클래스를 직접 테스트하는 것으로 충분하다.
- 낮은 결합도
    - 책임과 관심사가 다른 오브젝트 또는 모듈과는 낮은 결합도
    즉, 느슨하게 연결된 형태를 유지하는 것이 바람직하다.
    - 낮은 결합도는 하나의 변경이 발생할 ㅐ때 다른 모듈과 객체로 변경에 대한 요구가 전파되지 않는 상태이다.
    `ConnectionMaker` 인터페이스 도입으로 DB 연결 기능을 구현할 클래스가 바뀌더라도 DAO의 코드는 변경할 필요가 없다. 이것은 `ConnectionMaker`와 `UserDao` 의 결합도가 낮아진 것이다.

## 전략 패턴

- `UserDaoTest-UserDao-ConnectionMaker` 구조는 디자인 패턴의 시각으로 전략 패턴(`Strategy Pattern`에 해당된다.
- 자신의 기능 맥락(`context`)에서, 필요에 따라 변경이 필요한 알고리즘을 인터페이스를 통해 통째로 외부로 분리시키고, 구현한 구체적인 알고리즘 클래스를 필요에 따라 바꿔서 사용할 수 있게 하는 디자인 패턴이다.
여기서 맥락=context 는 `UserDao` 이다.

**최종 `UserDao`는 개방 폐쇄 원칙을 잘 따르고 있으며, 응집력이 높고, 결합도는 낮고, 전략 패턴을 적용했음을 알 수 있다.**
