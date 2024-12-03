// import logo from './logo.svg';
import './App.css';

import React, { useState, useRef } from 'react';

function App() {
  const Item = ({ text, completed, id }) => {
    return (
      <div className="item">
        <div class="circle">{completed ? <span>&#10003;</span> : ""}</div>
        <div className={completed ? "strike" : ""}>{text}</div>
        <div class="close">X</div>
      </div>
    );
  };

  const [todos, setTodos] = useState([]);
  const inputRef = useRef();
  function handleKeyPress(e) {
    if (e.key === "Enter") {
      setTodos([
        ...todos,
        { text: e.target.value, completed: false, id: Date.now() }
      ]);
      inputRef.current.value = "";
    }
  }


  return (
    <div className="App">
      <input type="text" onKeyUp={handleKeyPress} ref={inputRef} />
      {todos.map(e =>
        <Item {...e} key={e.id} />
      )}
    </div>
  );
}
export default App;