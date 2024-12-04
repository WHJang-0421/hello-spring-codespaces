import './App.css';

import React, { useState, useRef, useEffect } from 'react';
import axios from 'axios';

export default function App() {
  const Item = ({
    text,
    completed,
    id,
    updateCompleted,
    deleteTodo,
    updateText
  }) => {
    const [edit, setEdit] = useState(false);
    const [editText, setEditText] = useState(text);

    return (
      <div className="item">
        <div class="circle" onClick={() => updateCompleted(id)}>
          {completed ? <span>&#10003;</span> : ""}
        </div>
        <div
          className={completed ? "strike" : ""}
          onDoubleClick={() => {
            if (!completed) {
              setEdit(true);
            }
          }}
        >
          {edit ? (
            <input
              type="text"
              value={editText}
              onChange={(e) => {
                setEditText(e.target.value);
              }}
              onBlur={() => {
                setEdit(false);
                updateText(id, editText);
              }}
            />
          ) : (text)}
        </div>
        <div class="close" onClick={() => deleteTodo(id)}>
          X
        </div>
      </div>
    );
  };

  const [todos, setTodos] = useState([]);
  const inputRef = useRef();

  // useEffect(() => {
  //   fetchTodos()
  // }, []);
  // function fetchTodos() {
  //   axios.get('/api/v1/tasks')
  //     .then((response) => {
  //       setTodos(response.data);
  //     })
  //     .catch((error) => {
  //       console.log("Error while fetching tasks:", error);
  //     })
  // }

  const handleKeyPress = (e) => {
    if (e.key === "Enter") {
      setTodos([
        ...todos,
        { text: e.target.value, completed: false, id: Date.now() }
      ]);
      inputRef.current.value = "";
    }
  };

  // toggle completed
  const handleCompleted = (id) => {
    const updatedList = todos.map((e) => {
      if (e.id === id) {
        e.completed = !e.completed;
      }

      return e;
    });

    setTodos(updatedList);
  };

  // delete item
  const handleDelete = (id) => {
    const filter = todos.filter((e) => e.id !== id);
    setTodos(filter);
  };

  // handle text update
  const handleUpdateText = (id, text) => {
    const updatedList = todos.map((e) => {
      if (e.id === id) {
        e.text = text;
      }

      return e;
    });

    setTodos(updatedList);
  };

  return (
    <div className="App">
      <input type="text" onKeyUp={handleKeyPress} ref={inputRef} />
      {todos.map((e) =>
      (<Item
        {...e}
        key={e.id}
        updateCompleted={handleCompleted}
        deleteTodo={handleDelete}
        updateText={handleUpdateText}
      />)
      )}
    </div>
  );
}