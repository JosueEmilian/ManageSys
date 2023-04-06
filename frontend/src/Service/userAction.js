export const login = (user) => {
  localStorage.setItem("user", JSON.stringify(user)); // Guardar usuario en el almacenamiento local
  return {
    type: "LOGIN",
    payload: user, // Pasar el usuario directamente como carga útil
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
