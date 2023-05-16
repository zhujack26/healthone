import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  currentUserInfo: {},
  userInfoList: [],
  searchUserInfoList: [],
  sortUserInfoList: [],
  userFilterStatus: 0,
  userFilterOption: "전체",
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
    setUserFilterStatus: (state, action) => {
      state.userFilterStatus = action.payload;
    },
    setUserFilterOption: (state, action) => {
      state.userFilterOption = action.payload;
    },
    setSearchUserInfoList: (state, action) => {
      state.searchUserInfoList = action.payload;
    },
    setSortUserInfoList: (state, action) => {
      state.sortUserInfoList = action.payload;
    },
  },
});

// Action creators are generated for each case reducer function
export const {
  setUserInfoList,
  setCurrentUserInfo,
  setUserFilterStatus,
  setUserFilterOption,
  setSearchUserInfoList,
  setSortUserInfoList,
} = userInfoSlice.actions;

export default userInfoSlice.reducer;

// export const selectCurrentToken = (state) => state.auth.token;
