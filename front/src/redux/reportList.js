import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  currentReportInfo: {},
  reportInfoList: [],
};

export const reportInfoSlice = createSlice({
  name: "reportList",
  initialState,
  reducers: {
    setReportInfoList: (state, action) => {
      state.reportInfoList = action.payload;
    },
    setCurrentReportInfo: (state, action) => {
      state.currentReportInfo = action.payload;
    },
  },
});

// Action creators are generated for each case reducer function
export const { setReportInfoList, setCurrentReportInfo } = reportInfoSlice.actions;

export default reportInfoSlice.reducer;

// export const selectCurrentToken = (state) => state.auth.token;
