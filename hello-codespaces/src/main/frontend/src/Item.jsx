/* eslint-disable react/prop-types */
import { useState } from 'react';

export default function Item(
    { id,
        title,
        finished,
        // eslint-disable-next-line no-unused-vars
        due,
        updateFinished,
        deleteTodo,
        updateTitle }
) {
    const [edit, setEdit] = useState(false);
    const [editTitle, setEditTitle] = useState(title);

    return (
        <div className="item">
            <div className="circle" onClick={() => updateFinished(id)}>
                {finished ? <span>&#10003;</span> : ""}
            </div>
            <div
                className={finished ? "strike" : ""}
                onDoubleClick={() => {
                    if (!finished) {
                        setEdit(true);
                    }
                }}
            >
                {edit ? (
                    <input
                        type="text"
                        value={editTitle}
                        onChange={(e) => {
                            setEditTitle(e.target.value);
                        }}
                        onBlur={() => {
                            setEdit(false);
                            updateTitle(id, editTitle);
                        }}
                    />
                ) : (title)}
            </div>
            <div className="close" onClick={() => deleteTodo(id)}>
                X
            </div>
        </div>
    );
};