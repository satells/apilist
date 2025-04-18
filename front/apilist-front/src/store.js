import { configureStore } from '@reduxjs/toolkit';
import categoryReducer from './features/category/category';


const store = configureStore({
  reducer: {
    category: categoryReducer,
  
  },
});

export default store;
