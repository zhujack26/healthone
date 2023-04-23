import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  currentUserInfo: {},
  userInfoList: [],
};

export const userInfoSlice = createSlice({
  name: "userList",
  initialState,
  reducers: {
    setUserInfoList: (state, action) => {
      state.userInfoList = action.payload;
    },
    setCurrentUserInfo: (state, action) => {
      state.currentUserInfo = action.payload;
    },
  },
});

// Action creators are generated for each case reducer function
export const { setUserInfoList, setCurrentUserInfo } = userInfoSlice.actions;

export default userInfoSlice.reducer;

// export const selectCurrentToken = (state) => state.auth.token;
