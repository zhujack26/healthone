import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  currentReportInfo: {},
  reportInfoList: [],
  searchReportInfoList: [],
  sortReportInfoList: [],
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
    setSearchReportInfoList: (state, action) => {
      state.searchReportInfoList = action.payload;
    },
    setSortReportInfoList: (state, action) => {
      state.sortReportInfoList = action.payload;
    },
  },
});

// Action creators are generated for each case reducer function
export const {
  setReportInfoList,
  setCurrentReportInfo,
  setReportFilterStatus,
  setReportFilterOption,
  setSearchReportInfoList,
  setSortReportInfoList,
} = reportInfoSlice.actions;

export default reportInfoSlice.reducer;

// export const selectCurrentToken = (state) => state.auth.token;
