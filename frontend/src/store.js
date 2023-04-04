import { createStore } from "redux";
import userReducer from "./Service/userReducer";

const store = createStore(userReducer);

export default store;
