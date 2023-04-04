export const login = (email) => {
  localStorage.setItem("user", JSON.stringify({ email }));
  return {
    type: "LOGIN",
    payload: { email },
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
