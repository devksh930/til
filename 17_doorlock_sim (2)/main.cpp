#include "mbed.h"
#include "doorlock_sim.h"
#include "utils.h"    //여기서 선언된 함수들을 
Serial pc(USBTX,USBRX); // 수신,송신   -->default 채널
Timer  btnTm; //버튼입력의 타임아웃을 측정하기위해 사용하는 (타이머) --> 타임출력 측정용

state_t state;  //매크로타입 state 선언
pi_state_t pi_state;  //state 변수
int passwd[4] = {1, 1, 1, 1 };  //배열초기값은 -- >등록된 패스워드를 지정하엿다

int processPasswdInput(){
				static int pw_in[4]; //입력받는 패스워드를 저장할 배열을 선언
				int status = CONTINUE;  // doorlock_sim.h 에 반환값을 선언 -- > 초기화는 계속돌아가는상태로
				uint8_t edge;
	
		if(btnTm.read() >BUTTON_INPUT_TIMEOUT){ //먼저 버튼입력의 타임아웃을 측정
			pi_state = PW_FAILURE;
		}
		
		switch (pi_state) {
		case FIRST_INPUT:
			edge =getButtonInput();
					if(edge & 0x10){  //falling edge 인지 검사하는것이다.
						edge &= 0x07;  //제일마지막의 3비를 가져오는것이다   // 000000111 을의미한다.
						pw_in[0] = (edge == 4) ? 3 : edge; // 4가 의미하는것은 사실상 버튼 3버튼을 눌러진경우를 의미한다.
						pi_state = SECOND_INPUT;
						btnTm.reset();
					}
			break;
		
		case SECOND_INPUT:
			edge =getButtonInput();
				if(edge & 0x10)
					{  //falling edge 인지 검사하는것이다.
					edge &= 0x07;  //제일마지막의 3비를 가져오는것이다   // 000000111 을의미한다.
					pw_in[1] = (edge == 4) ? 3 : edge; // 4가 의미하는것은 사실상 버튼 3버튼을 눌러진경우를 의미한다.
					pi_state = THIRD_INPUT;
					btnTm.reset();
		       }
			break;		
		case THIRD_INPUT:
			edge =getButtonInput();
		     	if(edge & 0x10)
						{  //falling edge 인지 검사하는것이다.
						edge &= 0x07;  //제일마지막의 3비를 가져오는것이다   // 000000111 을의미한다.
						pw_in[2] = (edge == 4) ? 3 : edge; // 4가 의미하는것은 사실상 버튼 3버튼을 눌러진경우를 의미한다.
						pi_state = FOURTH_INPUT;
						btnTm.reset();
		        }
			break;		
						
		case FOURTH_INPUT:
			edge =getButtonInput();
			     	if(edge & 0x10)
							{  //falling edge 인지 검사하는것이다.
							edge &= 0x07;  //제일마지막의 3비를 가져오는것이다   // 000000111 을의미한다.
							pw_in[3] = (edge == 4) ? 3 : edge; // 4가 의미하는것은 사실상 버튼 3버튼을 눌러진경우를 의미한다.
								
							int i;
							for(i=0;i<4;i++){
								if(passwd[1] != pw_in[i]) break;  //첫번째 비밀번호부터 틀리면 빠져나온다.
							}
							if(i>=4) pi_state = PW_SUCCESS;
							else pi_state = PW_FAILURE;
							btnTm.reset();
				
					   	}
		 	break;
		case PW_SUCCESS:
			status = SUCCESS;
		pi_state = FIRST_INPUT;
			
			break;
  	case PW_FAILURE:
			status = FAILED;
		pi_state = FIRST_INPUT;
			break;
}
		return status;
}
int porcessCloseButtonInput()
	{
		int status = CONTINUE;
		
		if(btnTm.read() > BUTTON_INPUT_TIMEOUT){  //타임아웃인경우
			 status = FAILED;
		}
		/// 타임아웃이 아닌경우
		else {
			int edge = getButtonInput(); //타임아웃이 아니면 [버튼 입력을] 읽어오는것이다.
			
			if(edge == BTN1_RISING){  //라이싱엣지면 -- > 3초가 경과됐는지 검사후
				if(btnTm.read()> MODE_CHANGE_TIMEOUT){  //3초보다 크면
					status = SUCCESS;  //성공
				}
				else{
					 status = FAILED;
				}
			}
		}
		return status;
	}
void setup(){ //초기화를 목적으로 만든함수

	pc.baud(115200);
	btnTm.start(); //타이머 초기화
	initDevs();
	
	state =DOOR_CLOSED;
	pi_state = FIRST_INPUT;
}

int main() {
	setup();
	
	while(1){
		switch(state){
			case DOOR_CLOSED:  //도어가 잠긴상태
				DISPLAY_DOOR_CLOSED(); // 문이잠겨있다는것을 디스플레이하는것이다.
			if(getButtonInput() == BTN3_FALLING){  //3번버튼이 눌러졌을때 다음상태로 넘어가고 1번 LED 켜지고
				state = PASSWD_INPUT; 
					startBlinkLed3(); // 3번 LED 는 BLINK 깜빡이게 한다는것이다.
				
				//다음상태로만들어주고  타이머리셋을 해줘야한다 --> 그래야  버튼입력의 타임아웃을 검사할거기 때문에
				btnTm.reset(); // 버튼 입력타이머를 리셋을 시켜준다. 
			}
			break;
		
			case PASSWD_INPUT:
				
				switch(processPasswdInput()){
					case SUCCESS:  //성공상태가 되면
						state =  DOOR_OPEN;  //현재 상태  오픈상태로가고
					  SUCCESS_BEEP(); //성공소리를내준다
						stopBlinkLed3();  //led 3 번 깜빡이는거 멈춤
						break;
					
					case FAILED:
						state = DOOR_CLOSED; //실패했으니 처음상태 닫혀있는상태로가고
						FAILURE_BEEP();
						stopBlinkLed3();
						break;
					case CONTINUE:  //현재상태가 계속 반복하는상태여서   사실상 필요가없긴하다
						break;
				}
			  break;
					
			case DOOR_OPEN:
				DISPLAY_DOOR_OPEN(); // DOOR OPEN 상태를 표시하고.
			
				if(getButtonInput() == BTN1_FALLING){  // 1번버튼이 눌러지면
					state = CLOSE_BUTTON_INPUT; ///현재상태는 버튼입력 닫는상태로 가고
			   	startBlinkLed1(); //1번 led 를 깜빡이게 하자!
					
					btnTm.reset(); // 버튼타이머를 리셋 해줘야한다.
				}
			break;
			
			case CLOSE_BUTTON_INPUT:
					switch(porcessCloseButtonInput()){
						
						case SUCCESS:
							state = DOOR_CLOSED;
							SUCCESS_BEEP();
							stopBlinkLed1(); 
							break;
						
						case FAILED:
					    state = DOOR_OPEN; //이전상태 오픈으로 돌아가고
						  FAILURE_BEEP();
							stopBlinkLed1(); 
	             break;   				
					}
			default:
			break;
			
		}
	}
}

