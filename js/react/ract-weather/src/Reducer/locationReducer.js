const locationInit = {
  loading: true,
  latitude: 1,
  longitude: 1,
  nx: 1,
  ny: 1,
  addr: '',
};

const locationReducer = (state, action) => {
  switch (action.type) {
    case 'SUCCESS': {
      return {
        ...state,
        loading: false,
        latitude: action.latitude,
        longitude: action.longitude,
        addr: action.addr,
        nx: action.nxny.x,
        ny: action.nxny.y,
      };
    }
    case 'ERROR': {
      return {
        ...state,
        loading: false,
      };
    }
    default:
      throw new Error('이상한 loaction dispatch 사용');
  }
};

export { locationInit, locationReducer };
