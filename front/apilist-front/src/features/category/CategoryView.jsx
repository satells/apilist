import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchCategories } from './category';
import ServerHostView from '../serverhost/ServerHostView';

function CategoryView() {
  const category = useSelector((state) => state.category);

  const dispatch = useDispatch();

  const handleClick = () => {
    dispatch(fetchCategories({ teste: 'testando tudo' }));
  };

  return (
    <>
      <div>
        <h2>Lista de Categorias</h2>
        <ServerHostView />
        <div>
          <button onClick={handleClick}>Categories</button>

          {category.loading && <h2 style={{ color: 'blue' }}>Loading....</h2>}
          {!category.loading && category.error && (
            <h2 style={{ color: 'red' }}>Error {category.error}</h2>
          )}

          <ul>
            {category.categories.map((cate, index) => (
              <li key={index}>
                {cate.name} - {cate.slug}
              </li>
            ))}
          </ul>
        </div>
      </div>
    </>
  );
}

export default CategoryView;
