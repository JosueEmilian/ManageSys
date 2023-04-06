const storedUser = JSON.parse(localStorage.getItem("user")); // Obtener usuario del almacenamiento local si existe

const initialState = {
  user: storedUser ? storedUser : null, // Establecer el estado inicial del usuario desde el almacenamiento local
};

const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case "LOGIN":
      return { ...state, user: action.payload };

    case "LOGOUT":
    case "RESET_USER":
      localStorage.removeItem("user"); // Eliminar usuario del almacenamiento local al cerrar sesión o reiniciar el usuario
      return { ...state, user: null };

    default:
      return state;
  }
};

export default userReducer;
