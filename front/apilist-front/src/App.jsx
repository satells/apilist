import { useState } from 'react';
import CategoryView from './features/category/CategoryView';

function App() {
  const [count, setCount] = useState(0);

  const handleClick = () => {
    setCount((count) => count + 1);
  };
  return (
    <>
      <h1></h1>
      <div>
        <button onClick={handleClick}>count is {count}</button>
        <CategoryView />
      </div>
    </>
  );
}

export default App;
