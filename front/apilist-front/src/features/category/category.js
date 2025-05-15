import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import base64 from 'base-64';
import axios from 'axios';

const initialState = {
  loading: false,
  categories: [],
  error: '',
  serverHost: '192.168.56.101:8080',
};
const serverHost = 'seu-servidor';
const username = 'admin'; // Ou 'usuario'
const password = '1234';

export const fetchCategories = createAsyncThunk(
  'category/fetchCategories',
  async (_, { getState }) => {
    const { serverHost } = getState().category;
    return axios
  .get(`http://${serverHost}/categories`, {
    headers: {
      Authorization: `Basic ${base64.encode(`${username}:${password}`)}`,
    },
  })
  .then((response) => response.data)
  .catch((error) => {
    console.error("Erro ao buscar categorias:", error);
  });
/*    axios
      .get(`http://${serverHost}/categories`)
      .then((response) => response.data);
      */
  },
);

const category = createSlice({
  name: 'category',
  initialState,
  reducers: {
    setServerHost: (state, action) => {
      state.serverHost = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(fetchCategories.pending, (state) => {
      state.loading = true;
      state.error = '';
    });
    builder.addCase(fetchCategories.fulfilled, (state, action) => {
      state.loading = false;
      state.categories = action.payload;
    });
    builder.addCase(fetchCategories.rejected, (state, action) => {
      state.loading = false;
      state.categories = [];
      state.error = action.error.message;
    });
  },
});

export default category.reducer;
export const { setServerHost } = category.actions;
