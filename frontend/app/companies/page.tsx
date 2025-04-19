'use client';

import { useEffect, useState } from 'react';

interface Company {
    companyId: string;
    companyName: string;
    companyAddress: string;
    // add your other Company fields here
}

export default function CompaniesPage() {
    const [companies, setCompanies] = useState<Company[]>([]);

    useEffect(() => {
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/company`)
            .then((res) => res.json())
            .then((data: Company[]) => setCompanies(data))
            .catch(console.error);
    }, []);

    return (
        <div style={{ padding: 24 }}>
            <h1>Companies</h1>
            <ul>
                {companies.map((c) => (
                    <li key={c.companyId}>Name: {c.companyName}, Address: {c.companyAddress}</li>
                ))}
            </ul>
        </div>
    );
}
