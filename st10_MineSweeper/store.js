import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
export const START_GAME = 'START_GMAE';
export const OPEN_CELL = 'OPEN_CELL';
export const FLAG_CELL = 'FLAG_CELL';
export const QUESTION_CELL = 'QUESTION_CELL';
export const NORMALIZE_CELL = 'NORMALIZE_CELL';
export const INCREMENT_TIMER = 'INCREMENT_TIMER';

export default new Vuex.Store({
    state: {
        tableData: [],
        data: {
            row: 0,
            cell: 0,
            mine: 0,
        },
        timer: 0,
        result: '',

    },  //vue의 data 속성
    getters: {}, //vue의 computed와 비슷
    mutations: {
        [START_GAME](state , {row,cell,mine}) {

        },
        [OPEN_CELL](state) {
        },
        [FLAG_CELL](state) {
        },
        [QUESTION_CELL](state) {
        },
        [NORMALIZE_CELL](state) {
        },
        [INCREMENT_TIMER](state) {
        },

    }, //state를 수정할때 사용한다. 동기적 뮤테이션의 함수는 대문자로 짓는게 커뮤니티의 규칙이다 .
    actions: {}, //비동기를 사용할때 여러 뮤테이션을 연달아 실행할때
});