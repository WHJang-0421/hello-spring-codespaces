import './App.css';

import { useState, useRef, useEffect } from 'react';
import axios from 'axios';

import Item from './Item'

export default function App() {
  const [todos, setTodos] = useState([]);
  const inputRef = useRef();

  useEffect(() => {
    fetchTodos()
  }, []);
  function fetchTodos() {
    axios.get('/api/v1/tasks')
      .then((response) => {
        setTodos(response.data);
      })
      .catch((error) => {
        console.log("Error while fetching tasks:", error);
      });
  }

  const handleKeyPress = (e) => {
    if (e.key === "Enter") {
      const text = e.target.value;
      inputRef.current.value = "";

      axios.post('/api/v1/tasks', {
        title: text,
        finished: false,
        due: Date.now()
      })
        .then((response) => {
          let item = response.data;
          item.key = item.id;
          setTodos([
            ...todos,
            item
          ])
        })
        .catch((error) => {
          console.log("Error while fetching tasks:", error);
        });
    }
  };

  // toggle finished
  const handleFinished = (id) => {
    const updatedList = todos.map((e) => {
      if (e.id === id) {
        e.finished = !e.finished;
      }

      return e;
    });
    setTodos(updatedList);

    const item = todos.filter((e) => e.id == id)[0];

    axios.put(`/api/v1/tasks/${id}`, {
      title: item.title,
      finished: item.finished,
      due: item.due
    });
  };

  // delete item
  const handleDelete = (id) => {
    const filter = todos.filter((e) => e.id !== id);
    setTodos(filter);
    axios.delete(`/api/v1/tasks/${id}`)
      .catch((error) => {
        console.log("Error while fetching tasks:", error);
      });
  };

  // handle text update
  const handleUpdateTitle = (id, title) => {
    const updatedList = todos.map((e) => {
      if (e.id === id) {
        e.title = title;
      }

      return e;
    });
    setTodos(updatedList);

    const item = todos.filter((e) => e.id == id)[0];

    axios.put(`/api/v1/tasks/${id}`, {
      title: item.title,
      finished: item.finished,
      due: item.due
    });
  };

  return (
    <div className="App">
      <input type="text" onKeyUp={handleKeyPress} ref={inputRef} />
      {todos.map((e) =>
      (<Item
        {...e}
        key={e.id}
        updateFinished={handleFinished}
        deleteTodo={handleDelete}
        updateTitle={handleUpdateTitle}
      />)
      )}
    </div>
  );
}