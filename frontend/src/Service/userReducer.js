const userReducer = (state = {}, action) => {
  switch (action.type) {
    case "LOGIN":
      return { ...state, ...action.payload };
    case "LOGOUT":
      return {};
    case "RESET_USER":
      return {};
    default:
      return state;
  }
};

export default userReducer;
