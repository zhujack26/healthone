import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  currentReportInfo: {},
  reportInfoList: [],
  reportFilterStatus: 0,
  reportFilterOption: "전체",
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
    setReportFilterStatus: (state, action) => {
      state.reportFilterStatus = action.payload;
    },
    setReportFilterOption: (state, action) => {
      state.reportFilterOption = action.payload;
    },
  },
});

// Action creators are generated for each case reducer function
export const { setReportInfoList, setCurrentReportInfo, setReportFilterStatus, setReportFilterOption } =
  reportInfoSlice.actions;

export default reportInfoSlice.reducer;

// export const selectCurrentToken = (state) => state.auth.token;
