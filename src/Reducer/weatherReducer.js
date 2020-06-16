const weatherInit = {
  loading: false,
  SKY: [],
  POP: [],
  PTY: [],
  TMN: [],
  TMX: [],
  REH: [],
  WSD: [],
  T3H: [],
  error: false,
};

const weatherReducer = (state, action) => {
  switch (action.type) {
    case 'SUCCESS':
      return {
        ...state,
        loading: false,
        SKY: action.SKY,
        POP: action.POP,
        PTY: action.PTY,
        TMN: action.TMN,
        TMX: action.TMX,
        REH: action.REH,
        WSD: action.WSD,
        T3H: action.T3H,
      };
    case 'ERROR':
      return {
        ...state,
        loading: false,
        error: true,
      };
    default:
      throw new Error('이상한 dispatch 명령 사용함');
  }
};

export { weatherInit, weatherReducer };
