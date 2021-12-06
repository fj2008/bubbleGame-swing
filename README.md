# AWT로 버블버블 게임 만들기!!


## 🎬 Jframe라이브러리 사용해서 버블버블을 만들어봤습니다.

## ex08 패키지의 스레드 동작 원리 짚어보기
```
지금 현제 생황은 jpanel에 이미지를 넣고 
player를 만들어서 캐릭터를 추가시켰다
그리고 두가지리스너를 등록해서 두가지를 지켜보도록 했다
1.키보드 프레스
2.키보드 릴리즈

그럼 이벤트루프 큐에 있는 일들을 순차적으로 처리할때
이벤트핸들러스택의 도움을 받는다.
그럼 이벤트핸들러 메서드 스택이 호출돼서
메서드 코드를 한줄씩 실행을 한다.

이때 자바는 기본적으로 main스래드 하나만 돌고 있기때문에
한방향으로 가면서 점프를한다던지하는 동시에 여러가지 일을 Player가 할 수 없어서
스레드를 하나 더 만들어서 처리를 했다.

지금 ex08에서 main스레드는 키보드에 걸려있는 두가지 리스너를 관찰하면서
유저가 키보드를 동작할때 이벤트루프에 등록시키고 실행시키는 일을하고

그외에 여러가지 동시에 동작하는일들은 스레드를 추가해서 실행 하고 있다.
그리고 백그라운드 스레드로 캐릭터에 컬러를 감지하는 좌표를 달아서 해당 컬러에 좌표가 닿으면 멈추도록 하였다.
```

## ex10 패키지의 어댑터 패턴과 인터페이스 defalut

```
ex09패키지에서 물방을울 생성해 내는대까지는 완료 했다.
그럼 이제 물방을이 발사가 돼야 하는데  물방을발사는 플래이어가 향하고 있는 방향 상태가 필요하기때문에
exum타입으로 방향을 정의시켰다. 그리고 버블의 방향이 결정이 나야하는데 버블의 방향을
playerWay의 상태에 따라 다르게 된다.
Moveable인터페이스에는 상하좌우를 강제 구현하도록 명시 돼 있는데 
버블의 특성상 down아래로가는 모션은 게임규칙에 없기때문에 버블의 상태는 상좌우 이렇게 새게의 상태만 있으면된다.
그래서 어댑터 패턴을 사용해봤습니다.
어댑터는 일반적으로 걸러내는 역할을 한다.
인터페이스가 너무 많은 행위의 제약을 가지고있으면 경우에따라서 걸러내기위해서 추상클래스로 걸러낼 메서드를 오버라이드해서 
해당인터페이스를 상속받으면 해당 메서드만 쏙 빼고 인터페이스를 사용할수 있게 해준다.

하지만! 자바는 다중상속을 재한하기 때문에 어댑터패턴의 단점은 분명하다.
그래서 나온것이 interface내부에서 default라는 문법이 생겨 난 것이다.
default는 인터페이스도 몸체가 있는 메서드를 만들 수 있다.
왜 이 문법이 생겼냐?! (다중상속이 안되는 것이 많기 때문이다.)
그래서 최근의 자바버전에선 어댑터 패턴 대신에 default를 사용하는 것이 좋다 
```

## ex11 패키지에서의 버블 스레드 성능 개선

```
플레이어가 생성하는 버블도 백그라운드에서 충돌이나여러가지 들을 감지해야하는데
기존에 BackgroundPlayerServie에서 는 플레이어만 감지해 내는 기능을 하기때문에
여기서 버블까지 감지하는 스레드가 생기도록 만들어버리면 버블이 만아질수록 급격하게 스레드 효율이 떨어진다.
그래서 버블을 감지하는 파일을 하나 더 만들었다.BackgroundBubbleService파일을 만들어서
스레드를 사용하는 것이 아닌 체크만 하도록! boolean타입으로 체크만

왜 이렇게 했는지?!
버블이 하나 생길때 마다 스레드가 하나씩 생긴다. 그렇게됐을때 BackgroundBubbleService에서 
계속 while타고 버블의 좌표를확인하면서 충돌감지를 해야한다. 즉 버블이 하나 생길때마다 스래드가 2개씩생긴다.
이렇게 설계가 돼있으면 엄청나게 스레드 낭비가 일어난다.


```

## ex 12패키지 물방을에서 메모리 소멸시키고 화면 다시 그리기

```
버블이 천장에 닿아서 사라지도록한다고 이미지를 바꾸고 스레드를 메모리에서 소멸시켜도
bomp이미지는 그대로 남아있다. 그래서 플레이할때 진짜시각적으로 버블이 터져서 사라진것 처럼
보이게 하기위해서는 bubblefarame을 지웠다가 다시 그려야한다. 이걸 repaint된다고 한다
그림을 다시 그리는것 리페인팅.. 렌더링..등등으로 불린다.

그림을 다시그릴때 bubbleFrame의 정보가 필요하다. 의존성 콤포지션으로...
그리고 메모리에서 bubble의 스레드를 소멸시키고 repaint시키면 
repaint될때 메모리에없는것은 그려지지 않기 때문에 
화면에서도 버블이 사라지게 된다.
```

### 자바에서 프로그램을 설계할때 가장 중요한것
```
자바에서 프로그램을 설계할때 가장 중요한것

main을 가진 클래스가 모든 객체(heap)의 정보를 가지고 있다.



main을 가진 클래스를 context(문맥)를 가지고 있다고한다

즉 그 프로그램의 모든정보를 main이 들고있다.


```