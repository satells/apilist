import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setServerHost } from '../category/category';

const ServerHostView = () => {
  const { serverHost } = useSelector((state) => state.category);

  const dispatch = useDispatch();

  const handleChange = ({ target }) => {
    dispatch(setServerHost(target.value));
  };

  return (
    <>
      <div>
        <label>Host</label>
        <input type="text" value={serverHost} onChange={handleChange} />
      </div>
    </>
  );
};

export default ServerHostView;
