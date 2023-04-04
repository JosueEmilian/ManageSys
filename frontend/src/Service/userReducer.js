const userReducer = (state = {}, action) => {
  switch (action.type) {
    case "LOGIN":
      return { ...state, ...action.payload };
    case "LOGOUT":
    case "RESET_USER":
      return {};
    case "CLEAR_EMAIL":
      return { ...state, email: null };
    default:
      return state;
  }
};

export default userReducer;
