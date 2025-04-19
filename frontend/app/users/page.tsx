'use client';

import { useEffect, useState } from 'react';

interface User {
    userId: string;
    userName: string;
    // add whatever other fields your User entity has
}

export default function UsersPage() {
    const [users, setUsers] = useState<User[]>([]);

    useEffect(() => {
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/user`)
            .then((res) => res.json())
            .then((data: User[]) => setUsers(data))
            .catch(console.error);
    }, []);

    return (
        <div style={{ padding: 24 }}>
            <h1>Users</h1>
            <ul>
                {users.map((u) => (
                    <li key={u.userId}>{u.userName}</li>
                ))}
            </ul>
        </div>
    );
}
