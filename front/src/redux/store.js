import { combineReducers, configureStore } from "@reduxjs/toolkit";
import userInfoReducer from "./userList";
import reportInfoReducer from "./reportList";

export const store = configureStore({
  reducer: {
    userList: userInfoReducer,
    reportList: reportInfoReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});
