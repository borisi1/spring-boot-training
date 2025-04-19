'use client';

import { useEffect, useState } from 'react';
import Link from 'next/link';

interface LinkDto { name: string; url: string; }
interface HomeDto  { links: LinkDto[]; }

export default function HomePage() {
    const [links, setLinks] = useState<LinkDto[]>([]);

    useEffect(() => {
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/home`)
            .then(res => res.json())
            .then((dto: HomeDto) => setLinks(dto.links))
            .catch(console.error);
    }, []);

    return (
        <div style={{ padding: 24 }}>
            <h1>Dashboard</h1>
            <ul>
                {links.map(link => (
                    <li key={link.name}>
                        <Link href={link.url}>{link.name}</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}
