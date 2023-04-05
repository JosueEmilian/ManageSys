export const login = (email, isAdmin) => {
  localStorage.setItem("user", JSON.stringify({ email, isAdmin }));
  return {
    type: "LOGIN",
    payload: { email, isAdmin },
  };
};

export const logout = () => {
  return {
    type: "LOGOUT",
    payload: null,
  };
};

export const resetUser = () => {
  return {
    type: "RESET_USER",
  };
};

export const setIsAdmin = (isAdmin) => {
  return {
    type: "SET_IS_ADMIN",
    payload: isAdmin,
  };
};
