const initialState = {
  isAdmin: false,
};

const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case "LOGIN":
      return { ...state, ...action.payload };
    case "LOGOUT":
    case "RESET_USER":
      return initialState;
    case "SET_IS_ADMIN":
      return { ...state, isAdmin: action.payload };
    default:
      return state;
  }
};

export default userReducer;
