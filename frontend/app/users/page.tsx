'use client';

import {useEffect, useState} from 'react';

interface User {
    userName: string;
    firstName: string;
    lastName: string;
    email: string;
}

export default function UsersPage() {
    const [users, setUsers] = useState<User[]>([]);
    const [nameInput, setName] = useState('');
    const [editingUserName, setEdit] = useState<string | null>(null);

    const apiBase = process.env.NEXT_PUBLIC_API_URL + '/api/user';

    const fetchUsers = () =>
        fetch(apiBase)
            .then(res => res.json())
            .then(setUsers);

    useEffect(() => {
        fetchUsers();
    }, []);

    const submit = async (e: React.FormEvent) => {
        e.preventDefault();
        const payload = {userName: nameInput};
        const url = editingUserName
            ? `${apiBase}/${encodeURIComponent(editingUserName)}`
            : apiBase;
        const method = editingUserName ? 'PUT' : 'POST';

        await fetch(url, {
            method,
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(payload),
        });
        setName('');
        setEdit(null);
        fetchUsers();
    };

    const remove = async (userName: string) => {
        await fetch(`${apiBase}/${encodeURIComponent(userName)}`, {method: 'DELETE'});
        fetchUsers();
    };

    const startEdit = (u: User) => {
        setName(u.userName);
        setEdit(u.userName);
    };

    return (
        <div className="p-6">
            <h1 className="text-2xl mb-4">Users</h1>

            <form onSubmit={submit} className="mb-6 space-x-2">
                <input
                    className="border p-2 rounded"
                    placeholder="Username"
                    value={nameInput}
                    onChange={e => setName(e.target.value)}
                />
                <button
                    type="submit"
                    className="bg-blue-500 text-white px-4 py-2 rounded"
                >
                    {editingUserName ? 'Update' : 'Create'}
                </button>
                {editingUserName && (
                    <button
                        type="button"
                        onClick={() => {
                            setEdit(null);
                            setName('');
                        }}
                        className="ml-2 text-gray-500"
                    >
                        Cancel
                    </button>
                )}
            </form>

            <ul className="space-y-2">
                {users.map(u => (
                    <li
                        key={u.userName}   // use userName as the unique key
                        className="flex items-center justify-between border p-4 rounded"
                    >
                        <span>{u.userName}</span>
                        <span>{u.firstName}</span>
                        <span>{u.lastName}</span>
                        <span>{u.email}</span>
                        <div className="space-x-2">
                            <button
                                onClick={() => startEdit(u)}
                                className="text-yellow-600 hover:underline"
                            >
                                Edit
                            </button>
                            <button
                                onClick={() => remove(u.userName)}
                                className="text-red-600 hover:underline"
                            >
                                Delete
                            </button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}
