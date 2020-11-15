import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
export const START_GAME = 'START_GMAE';
export const OPEN_CELL = 'OPEN_CELL';
export const FLAG_CELL = 'FLAG_CELL';
export const QUESTION_CELL = 'QUESTION_CELL';
export const NORMALIZE_CELL = 'NORMALIZE_CELL';
export const INCREMENT_TIMER = 'INCREMENT_TIMER';

export const CODE = {
    MINE: -7,   //지뢰가 심어진곳
    NORMAL: -1, //빈칸
    QUESTION: -2,
    FLAG: -3,
    QUESTION_MINE: -4,
    FLAG_MINE: -5,
    CLICKED_MINE: -6,
    OPENED: 0, // 0 이상이면 다 opened
};

const plantMine = (row, cell, mine) => {
    console.log(row, cell, mine);
    const candidate = Array(row * cell).fill().map((arr, i) => {
        return i;
    });
    const shuffle = [];
    while (candidate.length > row * cell - mine) {
        const chosen = candidate.splice(Math.floor(Math.random() * candidate.length), 1)[0];
        shuffle.push(chosen);
    }
    const data = [];
    for (let i = 0; i < row; i++) {
        const rowData = [];
        data.push(rowData);
        for (let j = 0; j < cell; j++) {
            rowData.push(CODE.NORMAL);
        }
    }

    for (let k = 0; k < shuffle.length; k++) {
        const ver = Math.floor(shuffle[k] / cell);
        const hor = shuffle[k] % cell;
        data[ver][hor] = CODE.MINE; //셔플정렬로 지뢰심기ㅜ
    }

    console.log(data);
    return data;
};


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
        [START_GAME](state, {row, cell, mine}) {
            state.data = {
                row, cell, mine,
            };
            state.tableData = plantMine(row, cell, mine);
            state.timer = 0;
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